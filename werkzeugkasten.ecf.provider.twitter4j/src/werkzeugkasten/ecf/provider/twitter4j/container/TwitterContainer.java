package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.ecf.core.AbstractContainer;
import org.eclipse.ecf.core.ContainerConnectException;
import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.events.ContainerConnectedEvent;
import org.eclipse.ecf.core.events.ContainerConnectingEvent;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.ecf.core.util.ECFException;
import org.eclipse.ecf.presence.IIMMessageEvent;
import org.eclipse.ecf.presence.IIMMessageListener;
import org.eclipse.ecf.presence.IPresence;
import org.eclipse.ecf.presence.Presence;
import org.eclipse.ecf.presence.chatroom.ChatRoomCreateException;
import org.eclipse.ecf.presence.chatroom.IChatRoomAdminListener;
import org.eclipse.ecf.presence.chatroom.IChatRoomAdminSender;
import org.eclipse.ecf.presence.chatroom.IChatRoomContainer;
import org.eclipse.ecf.presence.chatroom.IChatRoomInfo;
import org.eclipse.ecf.presence.chatroom.IChatRoomInvitationListener;
import org.eclipse.ecf.presence.chatroom.IChatRoomInvitationSender;
import org.eclipse.ecf.presence.chatroom.IChatRoomManager;
import org.eclipse.ecf.presence.chatroom.IChatRoomMessageSender;
import org.eclipse.ecf.presence.chatroom.IChatRoomParticipantListener;
import org.eclipse.ecf.presence.history.IHistoryManager;
import org.eclipse.ecf.presence.im.ChatMessage;
import org.eclipse.ecf.presence.im.ChatMessageEvent;
import org.eclipse.ecf.presence.im.IChatMessage;
import org.eclipse.ecf.presence.im.IChatMessageEvent;
import org.eclipse.ecf.presence.im.IChatMessageSender;
import org.eclipse.ecf.presence.im.IChatMessage.Type;

import twitter4j.AsyncTwitter;
import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.ecf.provider.twitter4j.Constants;
import werkzeugkasten.ecf.provider.twitter4j.identity.TwitterID;

public class TwitterContainer extends AbstractContainer implements
		IChatRoomContainer, IChatRoomManager {

	protected ID localID;
	protected TwitterID targetID;
	protected List<IIMMessageListener> listeners = new Vector<IIMMessageListener>();
	protected List<IChatRoomParticipantListener> participantListeners = new Vector<IChatRoomParticipantListener>();

	protected IChatMessageSender privateMessageSender;
	protected IChatRoomMessageSender messageSender;
	protected AsyncTwitter twitter;
	protected Date since = new Date();

	public static final IChatMessage.Type Direct = new IChatMessage.Type(
			"direct") {
	};

	protected TwitterListener listener = new TwitterAdapter() {
		@Override
		public void sentDirectMessage(DirectMessage dm) {
			Namespace n = getConnectNamespace();
			IChatMessageEvent event = toMessage(n, dm);
			if (event != null) {
				handleMessageEvent(event);
				handlePresenceUpdated(event.getFromID(), new Presence());
			}
		}

		@Override
		public void gotDirectMessages(List<DirectMessage> messages) {
			Namespace n = getConnectNamespace();
			for (DirectMessage dm : messages) {
				IChatMessageEvent event = toMessage(n, dm);
				if (event != null) {
					handleMessageEvent(event);
					handlePresenceUpdated(event.getFromID(), new Presence());
				}
			}
		}

		@Override
		public void updated(Status s) {
			Namespace n = getConnectNamespace();
			IChatMessageEvent event = toMessage(n, s);
			if (event != null) {
				handleMessageEvent(event);
				handlePresenceUpdated(event.getFromID(), new Presence());
			}
		}

		@Override
		public void gotFriendsTimeline(List<Status> statuses) {
			Namespace n = getConnectNamespace();
			for (twitter4j.Status s : statuses) {
				IChatMessageEvent event = toMessage(n, s);
				if (event != null) {
					handleMessageEvent(event);
					handlePresenceUpdated(event.getFromID(), new Presence());
				}
			}
		}

		protected IChatMessageEvent toMessage(Namespace n, twitter4j.Status s) {
			return toMessage(n, s.getUser(), s.getId(), s.getText(),
					IChatMessage.Type.CHAT);
		}

		protected IChatMessageEvent toMessage(Namespace n,
				twitter4j.DirectMessage s) {
			return toMessage(n, s.getSender(), s.getId(), s.getText(), Direct);
		}

		protected Map<Number, Object> cache = new HashMap<Number, Object>(300);

		protected IChatMessageEvent toMessage(Namespace namespace,
				twitter4j.User u, Number id, String txt, IChatMessage.Type type) {
			synchronized (cache) {
				if (cache.get(id) == null) {
					ID from = new TwitterID(namespace, u);
					IChatMessageEvent event = new ChatMessageEvent(from,
							new ChatMessage(from, type, null, txt, null));
					cache.put(id, this);
					return event;
				}
			}
			return null;
		}

		@Override
		public void onException(TwitterException ex, int method) {
			System.err.printf("%s [%s]\n", ex, method);
		}
	};

	public TwitterContainer(ID guid) {
		this.localID = guid;
		this.twitter = new AsyncTwitter(null, null);
		this.privateMessageSender = new IChatMessageSender() {
			@SuppressWarnings("unchecked")
			@Override
			public void sendChatMessage(ID toID, ID threadID, Type type,
					String subject, String body, Map properties)
					throws ECFException {
				sendChatMessage(toID, body);
			}

			@Override
			public void sendChatMessage(ID toID, String body)
					throws ECFException {
				twitter.sendDirectMessageAsync(toID.getName(), body, listener);
			}
		};

		this.messageSender = new IChatRoomMessageSender() {
			@Override
			public void sendMessage(String message) throws ECFException {
				twitter.updateAsync(message, listener);
			}
		};
	}

	@Override
	public void connect(ID targetID, IConnectContext connectContext)
			throws ContainerConnectException {
		if (targetID instanceof TwitterID) {
			this.targetID = (TwitterID) targetID;
		} else {
			this.twitter.getUserTimelineAsync(listener);
			return;
		}

		String password = getPasswordFromConnectContext(connectContext);
		fireContainerEvent(new ContainerConnectingEvent(localID, targetID,
				connectContext));
		this.twitter.setUserId(this.targetID.getUsername());
		this.twitter.setPassword(password);
		fireContainerEvent(new ContainerConnectedEvent(this.localID,
				this.targetID));
	}

	public void readTimeline() {
		synchronized (this.since) {
			this.twitter.getFriendsTimelineAsync(this.since, this.listener);
			this.since.setTime(System.currentTimeMillis());
		}
	}

	@Override
	public void disconnect() {
		// do nothing
	}

	@Override
	public Namespace getConnectNamespace() {
		return IDFactory.getDefault()
				.getNamespaceByName(Constants.ID_NAMESPACE);
	}

	@Override
	public ID getConnectedID() {
		return this.targetID;
	}

	@Override
	public ID getID() {
		return this.localID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class serviceType) {
		if (serviceType.isInstance(this)) {
			return this;
		}
		return super.getAdapter(serviceType);
	}

	public void handleMessageEvent(IIMMessageEvent messageEvent) {
		for (IIMMessageListener l : this.listeners) {
			l.handleMessageEvent(messageEvent);
		}
	}

	@Override
	public void addMessageListener(IIMMessageListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeMessageListener(IIMMessageListener listener) {
		this.removeMessageListener(listener);
	}

	@Override
	public IChatRoomMessageSender getChatRoomMessageSender() {
		return this.messageSender;
	}

	@Override
	public IChatMessageSender getPrivateMessageSender() {
		return this.privateMessageSender;
	}

	@Override
	public IChatRoomAdminSender getChatRoomAdminSender() {
		// do nothing ...
		return null;
	}

	@Override
	public void addChatRoomAdminListener(IChatRoomAdminListener adminListener) {
		// do nothing ...
	}

	@Override
	public void removeChatRoomAdminListener(IChatRoomAdminListener adminListener) {
		// do nothing ...
	}

	@Override
	public void addChatRoomParticipantListener(
			IChatRoomParticipantListener participantListener) {
		this.participantListeners.add(participantListener);
	}

	@Override
	public void removeChatRoomParticipantListener(
			IChatRoomParticipantListener participantListener) {
		this.participantListeners.remove(participantListener);
	}

	protected Set<ID> participants = Collections
			.synchronizedSet(new HashSet<ID>());

	public void handlePresenceUpdated(ID fromID, IPresence presence) {
		if (participants.add(fromID)) {
			for (IChatRoomParticipantListener l : this.participantListeners) {
				l.handlePresenceUpdated(fromID, presence);
			}
		}
	}

	@Override
	public synchronized ID[] getChatRoomParticipants() {
		System.out.printf("getChatRoomParticipants \n  %s\n", participants);
		return this.participants.toArray(new ID[participants.size()]);
		// try {
		// List<User> list = this.twitter.getFriends();
		// List<ID> ids = new ArrayList<ID>(list.size());
		// Namespace n = getConnectNamespace();
		// for (User u : list) {
		// ids.add(new TwitterID(n, u));
		// }
		// return ids.toArray(new ID[ids.size()]);
		// } catch (TwitterException e) {
		// // XXX ???
		// throw new IllegalStateException(e);
		// }
	}

	@SuppressWarnings("unchecked")
	@Override
	public IChatRoomInfo createChatRoom(String roomName, Map properties)
			throws ChatRoomCreateException {
		System.out.printf("createChatRoom %s %s \n", roomName, properties);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatRoomInfo getChatRoomInfo(final String roomName) {
		System.out.printf("getChatRoomInfo %s \n", roomName);
		return new IChatRoomInfo() {
			@Override
			public IChatRoomContainer createChatRoomContainer()
					throws ContainerCreateException {
				System.out.println("createChatRoomContainer");
				return TwitterContainer.this;
			}

			@Override
			public ID getConnectedID() {
				return TwitterContainer.this.targetID;
			}

			@Override
			public String getDescription() {
				return "Description";
			}

			@Override
			public String getName() {
				return StringUtil.isEmpty(roomName) ? "timeline" : roomName;
			}

			@Override
			public int getParticipantsCount() {
				System.out.println("getParticipantsCount");
				return 0;
			}

			@Override
			public ID getRoomID() {
				try {
					if (StringUtil.isEmpty(roomName)) {
						return TwitterContainer.this.targetID;
					}
					return IDFactory.getDefault().createStringID(roomName);
				} catch (IDCreateException e) {
					return null;
				}
			}

			@Override
			public String getSubject() {
				return "Subject";
			}

			@Override
			public boolean isModerated() {
				return false;
			}

			@Override
			public boolean isPersistent() {
				return false;
			}

			@Override
			public boolean requiresPassword() {
				return false;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object getAdapter(Class adapter) {
				return null;
			}
		};
	}

	@Override
	public IChatRoomInfo[] getChatRoomInfos() {
		System.out.println("getChatRoomInfos");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatRoomManager[] getChildren() {
		System.out.println("getChildren");
		return null;
	}

	@Override
	public IHistoryManager getHistoryManager() {
		return null;
	}

	@Override
	public IChatRoomInvitationSender getInvitationSender() {
		return null;
	}

	@Override
	public IChatRoomManager getParent() {
		return null;
	}

	@Override
	public void addInvitationListener(IChatRoomInvitationListener listener) {
		// do nothing ...
	}

	@Override
	public void removeInvitationListener(IChatRoomInvitationListener listener) {
		// do nothing ...
	}
}

package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.core.user.IUser;
import org.eclipse.ecf.core.user.User;
import org.eclipse.ecf.core.util.ECFException;
import org.eclipse.ecf.presence.AbstractPresenceContainer;
import org.eclipse.ecf.presence.IPresence;
import org.eclipse.ecf.presence.IPresenceSender;
import org.eclipse.ecf.presence.Presence;
import org.eclipse.ecf.presence.history.IHistoryManager;
import org.eclipse.ecf.presence.im.ChatMessage;
import org.eclipse.ecf.presence.im.ChatMessageEvent;
import org.eclipse.ecf.presence.im.IChatManager;
import org.eclipse.ecf.presence.im.IChatMessageEvent;
import org.eclipse.ecf.presence.im.IChatMessageSender;
import org.eclipse.ecf.presence.im.IChatMessage.Type;
import org.eclipse.ecf.presence.roster.IRosterEntry;
import org.eclipse.ecf.presence.roster.IRosterManager;
import org.eclipse.ecf.presence.roster.IRosterSubscriptionSender;
import org.eclipse.ecf.presence.roster.Roster;
import org.eclipse.ecf.presence.roster.RosterEntry;
import org.eclipse.ui.progress.WorkbenchJob;

import twitter4j.AsyncTwitter;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.UserWithStatus;
import werkzeugkasten.ecf.provider.twitter4j.Activator;
import werkzeugkasten.ecf.provider.twitter4j.Constants;
import werkzeugkasten.ecf.provider.twitter4j.identity.TwitterID;

public class TwitterPresenceContainer extends AbstractPresenceContainer {

	protected TwitterContainer container;
	protected AsyncTwitter twitter;
	protected TwitterChatManager chatManager;
	protected TwitterRosterManager rosterManager;

	public TwitterPresenceContainer(TwitterContainer container,
			AsyncTwitter twitter) {
		this.container = container;
		this.twitter = twitter;
		IChatMessageSender cms = new IChatMessageSender() {
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
				TwitterPresenceContainer.this.twitter.sendDirectMessageAsync(
						toID.getName(), body);
			}
		};
		IHistoryManager hm = new TwitterHistoryManager(this.twitter);
		this.chatManager = new TwitterChatManager(cms, hm);
	}

	protected IRosterEntry createRosterEntry(Roster parent, twitter4j.User u) {
		TwitterID id = toID(u);
		IUser user = new User(id, u.getName(), u.getScreenName(), null);
		IPresence presence = createPresence(u);
		return new RosterEntry(parent, user, presence);
	}

	protected TwitterID toID(twitter4j.User u) {
		Namespace n = IDFactory.getDefault().getNamespaceByName(
				Constants.ID_NAMESPACE);
		return new TwitterID(n, u.getName());
	}

	protected IPresence createPresence(twitter4j.User user) {
		// final URL url = user.getProfileImageURL();
		return new Presence();
	}

	public void populateRoster(final TwitterID targetID) {
		final Roster roster = new Roster(this);
		this.twitter.getUserDetailAsync(targetID.getUsername(),
				new TwitterAdapter() {
					@Override
					public void gotUserDetail(UserWithStatus userWithStatus) {
						IUser user = new User(targetID, userWithStatus
								.getName(), userWithStatus.getScreenName(),
								null);
						roster.setUser(user);
					}
				});
		this.twitter.getFriendsAsync(new TwitterAdapter() {
			@Override
			public void gotFriends(List<twitter4j.User> users) {
				for (twitter4j.User u : users) {
					roster.addItem(createRosterEntry(roster, u));
				}
			}
		});
		IPresenceSender ps = new IPresenceSender() {
			@Override
			public void sendPresenceUpdate(ID targetID, IPresence presence)
					throws ECFException {
				twitter.updateAsync(presence.getStatus());
			}
		};
		IRosterSubscriptionSender rss = new IRosterSubscriptionSender() {
			@Override
			public void sendRosterAdd(String userAccount, String nickname,
					String[] groups) throws ECFException {
				try {
					twitter.followAsync(userAccount, new TwitterAdapter() {
						@Override
						public void followed(twitter4j.User user) {
							IRosterEntry entry = createRosterEntry(roster, user);
							roster.addItem(entry);
							rosterManager.fireRosterAdd(entry);
						}
					});
				} catch (TwitterException e) {
					Activator.log(e);
				}
			}

			@Override
			public void sendRosterRemove(ID userID) throws ECFException {
				try {
					twitter.leaveAsync(userID.getName(), new TwitterAdapter() {
						@Override
						public void left(twitter4j.User user) {
							IRosterEntry entry = createRosterEntry(roster, user);
							roster.removeItem(entry);
							rosterManager.fireRosterRemove(entry);
						}
					});
				} catch (TwitterException e) {
					Activator.log(e);
				}
			}
		};
		this.rosterManager = new TwitterRosterManager(roster, ps, rss);
		this.rosterManager.fireRosterUpdate(roster);
	}

	private static final long DELAY = 1000 * 60 * 3L; // TODO setting dialog

	protected TwitterListener timelineListener = new TwitterAdapter() {
		@Override
		public void gotUserTimeline(List<twitter4j.Status> statuses) {
			for (twitter4j.Status s : statuses) {
				ID from = toID(s.getUser());
				IChatMessageEvent event = new ChatMessageEvent(from,
						new ChatMessage(from, s.getText()));
				chatManager.handleMessageEvent(event);
			}
		}
	};

	public void readingStart() {
		new WorkbenchJob("Timeline Updater") {
			Date since = new Date();
			{
				twitter.getUserTimelineAsync(timelineListener);
			}

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				twitter.getUserTimelineAsync(twitter.getUserId(), since,
						timelineListener);
				this.since.setTime(System.currentTimeMillis());

				this.schedule(DELAY);
				return Status.OK_STATUS;
			}
		}.shouldSchedule();

	}

	@Override
	public IChatManager getChatManager() {
		return this.chatManager;
	}

	@Override
	public IRosterManager getRosterManager() {
		return this.rosterManager;
	}

}

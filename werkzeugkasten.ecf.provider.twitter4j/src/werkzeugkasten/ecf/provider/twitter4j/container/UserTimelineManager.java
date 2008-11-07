package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.Map;

import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.presence.chatroom.ChatRoomCreateException;
import org.eclipse.ecf.presence.chatroom.IChatRoomContainer;
import org.eclipse.ecf.presence.chatroom.IChatRoomInfo;
import org.eclipse.ecf.presence.chatroom.IChatRoomInvitationListener;
import org.eclipse.ecf.presence.chatroom.IChatRoomInvitationSender;
import org.eclipse.ecf.presence.chatroom.IChatRoomManager;
import org.eclipse.ecf.presence.history.IHistoryManager;

public class UserTimelineManager implements IChatRoomManager {

	@SuppressWarnings("unchecked")
	@Override
	public IChatRoomInfo createChatRoom(String roomName, Map properties)
			throws ChatRoomCreateException {
		System.out.printf("createChatRoom %s %s \n", roomName, properties);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatRoomInfo getChatRoomInfo(String roomName) {
		System.out.printf("getChatRoomInfo %s \n", roomName);
		return new IChatRoomInfo() {

			@Override
			public IChatRoomContainer createChatRoomContainer()
					throws ContainerCreateException {
				return null;
			}

			@Override
			public ID getConnectedID() {
				return null;
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getName() {
				return "timeline";
			}

			@Override
			public int getParticipantsCount() {
				return 0;
			}

			@Override
			public ID getRoomID() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getSubject() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isModerated() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isPersistent() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean requiresPassword() {
				// TODO Auto-generated method stub
				return false;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object getAdapter(Class adapter) {
				// TODO Auto-generated method stub
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

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

}

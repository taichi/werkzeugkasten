package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.Map;

import org.eclipse.ecf.presence.chatroom.ChatRoomCreateException;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatRoomInfo getChatRoomInfo(String roomName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatRoomInfo[] getChatRoomInfos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatRoomManager[] getChildren() {
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

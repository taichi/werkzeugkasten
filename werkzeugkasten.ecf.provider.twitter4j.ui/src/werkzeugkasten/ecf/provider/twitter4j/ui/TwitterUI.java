package werkzeugkasten.ecf.provider.twitter4j.ui;

import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.presence.chatroom.IChatRoomManager;
import org.eclipse.ecf.presence.ui.chatroom.ChatRoomManagerUI;
import org.eclipse.ecf.presence.ui.chatroom.IMessageRenderer;

public class TwitterUI extends ChatRoomManagerUI {

	public TwitterUI(IContainer container, IChatRoomManager manager) {
		super(container, manager);
	}

	@Override
	protected IMessageRenderer getDefaultMessageRenderer() {
		return new TwitterMessageRenderer();
	}
}

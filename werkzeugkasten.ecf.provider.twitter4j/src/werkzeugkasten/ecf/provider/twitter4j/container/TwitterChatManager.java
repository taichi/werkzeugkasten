package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.List;
import java.util.Vector;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.util.ECFException;
import org.eclipse.ecf.presence.IIMMessageEvent;
import org.eclipse.ecf.presence.IIMMessageListener;
import org.eclipse.ecf.presence.history.IHistoryManager;
import org.eclipse.ecf.presence.im.IChat;
import org.eclipse.ecf.presence.im.IChatManager;
import org.eclipse.ecf.presence.im.IChatMessageSender;
import org.eclipse.ecf.presence.im.ITypingMessageSender;

public class TwitterChatManager implements IChatManager {

	protected IChatMessageSender chatMessageSender;
	protected IHistoryManager historyManager;
	protected List<IIMMessageListener> listeners = new Vector<IIMMessageListener>();

	protected TwitterChatManager(IChatMessageSender chatMessageSender,
			IHistoryManager historyManager) {
		this.chatMessageSender = chatMessageSender;
		this.historyManager = historyManager;
	}

	@Override
	public IChat createChat(ID targetUser, IIMMessageListener messageListener)
			throws ECFException {
		// IChat implementation is maybe...
		// * current user timeline
		// * current user reply only timeline
		// * current user direct message timeline
		// * some following user timeline
		// * current user and any reply user only timeline

		return null; // TODO ?? DirectMesseages maybe use IChat.
	}

	@Override
	public IChatMessageSender getChatMessageSender() {
		return this.chatMessageSender;
	}

	@Override
	public IHistoryManager getHistoryManager() {
		return this.historyManager;
	}

	@Override
	public ITypingMessageSender getTypingMessageSender() {
		return null; // nothing to do
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

}

package werkzeugkasten.ecf.provider.twitter4j.adapter;

import java.util.Date;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.ecf.presence.history.IHistoryLine;

import twitter4j.Status;
import werkzeugkasten.ecf.provider.twitter4j.Constants;
import werkzeugkasten.ecf.provider.twitter4j.identity.TwitterID;

public class HistoryLineAdapter implements IHistoryLine {

	protected ID senderID;
	protected Status status;

	public HistoryLineAdapter(Status status) {
		this.status = status;
		Namespace n = IDFactory.getDefault().getNamespaceByName(
				Constants.ID_NAMESPACE);
		this.senderID = new TwitterID(n, status.getUser().getName());
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ID getReceiverID() {
		return null;
	}

	@Override
	public ID getSenderID() {
		return this.senderID;
	}

	@Override
	public String getText() {
		return this.status.getText();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}

package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.List;
import java.util.Vector;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.presence.IPresence;
import org.eclipse.ecf.presence.IPresenceListener;
import org.eclipse.ecf.presence.IPresenceSender;
import org.eclipse.ecf.presence.IPresence.Type;
import org.eclipse.ecf.presence.roster.AbstractRosterManager;
import org.eclipse.ecf.presence.roster.IRoster;
import org.eclipse.ecf.presence.roster.IRosterEntry;
import org.eclipse.ecf.presence.roster.IRosterItem;
import org.eclipse.ecf.presence.roster.IRosterSubscriptionSender;

public class TwitterRosterManager extends AbstractRosterManager {

	protected IPresenceSender presenceSender;
	protected IRosterSubscriptionSender rosterSubscriptionSender;
	protected List<IPresenceListener> listeners = new Vector<IPresenceListener>();

	public TwitterRosterManager(IRoster roster, IPresenceSender presenceSender,
			IRosterSubscriptionSender rosterSubscriptionSender) {
		super(roster);
		this.presenceSender = presenceSender;
		this.rosterSubscriptionSender = rosterSubscriptionSender;
	}

	@Override
	public IPresenceSender getPresenceSender() {
		return this.presenceSender;
	}

	@Override
	public IRosterSubscriptionSender getRosterSubscriptionSender() {
		return this.rosterSubscriptionSender;
	}

	@Override
	public void fireRosterAdd(IRosterEntry entry) {
		super.fireRosterAdd(entry);
	}

	@Override
	public void fireRosterRemove(IRosterEntry entry) {
		super.fireRosterRemove(entry);
	}

	@Override
	public void fireRosterUpdate(IRosterItem changedItem) {
		super.fireRosterUpdate(changedItem);
	}

	@Override
	public void fireSubscriptionListener(ID fromID, Type presencetype) {
		super.fireSubscriptionListener(fromID, presencetype);
	}

	public void handlePresence(ID fromID, IPresence presence) {
		for (IPresenceListener l : this.listeners) {
			l.handlePresence(fromID, presence);
		}
	}

	@Override
	public void addPresenceListener(IPresenceListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removePresenceListener(IPresenceListener listener) {
		this.listeners.remove(listener);
	}
}

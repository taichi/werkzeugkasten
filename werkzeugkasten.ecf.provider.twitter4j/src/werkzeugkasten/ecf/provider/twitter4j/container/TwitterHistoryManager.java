package werkzeugkasten.ecf.provider.twitter4j.container;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.presence.history.IHistory;
import org.eclipse.ecf.presence.history.IHistoryLine;
import org.eclipse.ecf.presence.history.IHistoryManager;

import twitter4j.AsyncTwitter;
import twitter4j.Status;
import twitter4j.TwitterException;
import werkzeugkasten.ecf.provider.twitter4j.Activator;
import werkzeugkasten.ecf.provider.twitter4j.adapter.HistoryLineAdapter;

public class TwitterHistoryManager implements IHistoryManager {

	protected AsyncTwitter twitter;

	public TwitterHistoryManager(AsyncTwitter twitter) {
		this.twitter = twitter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IHistory getHistory(final ID targetID, Map options) {
		return new IHistory() {
			@Override
			public IHistoryLine[] getHistoryLines(Date start, Date end) {
				try {
					List<Status> list = twitter.getFriendsTimeline(targetID
							.getName(), start);
					List<IHistoryLine> result = new ArrayList<IHistoryLine>(
							list.size());
					for (Status s : list) {
						result.add(new HistoryLineAdapter(s));
					}
					return result.toArray(new IHistoryLine[result.size()]);
				} catch (TwitterException e) {
					Activator.log(e);
				}
				return null;
			}

			@Override
			public IHistoryLine[] deleteHistoryLines(Date start, Date end) {
				return null; // nothing to do.
			}

			@Override
			public Object getAdapter(Class adapter) {
				return null;
			}
		};
	}

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void setActive(boolean active) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter.isInstance(this)) {
			return this;
		}
		return null;
	}

}

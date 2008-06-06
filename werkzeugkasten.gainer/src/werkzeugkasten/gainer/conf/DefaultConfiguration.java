package werkzeugkasten.gainer.conf;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.gainer.AinEventListener;
import werkzeugkasten.gainer.DinEventListener;
import werkzeugkasten.gainer.OnBoardButtonEventListener;

public class DefaultConfiguration implements Configuration {

	protected String portId;

	protected ConfigType type;

	protected List<OnBoardButtonEventListener> onBoardButton = new ArrayList<OnBoardButtonEventListener>();
	protected List<AinEventListener> analogInputs = new ArrayList<AinEventListener>();
	protected List<DinEventListener> digitalInputs = new ArrayList<DinEventListener>();

	public DefaultConfiguration(String portId, ConfigType type) {
		this.portId = portId;
		this.type = type;
	}

	@Override
	public String portId() {
		return this.portId;
	}

	@Override
	public ConfigType type() {
		return this.type;
	}

	@Override
	public void addButtonListener(OnBoardButtonEventListener listener) {
		this.onBoardButton.add(listener);
	}

	@Override
	public void addAinListener(AinEventListener listener) {
		this.analogInputs.add(listener);
	}

	@Override
	public void addDinListener(DinEventListener listener) {
		this.digitalInputs.add(listener);
	}

	@Override
	public void notifyButton(boolean pressed) {
		for (OnBoardButtonEventListener l : onBoardButton) {
			l.updated(pressed);
		}
	}

	@Override
	public void notifyAin(List<Number> ain) {
		for (AinEventListener l : analogInputs) {
			l.updated(ain);
		}
	}

	@Override
	public void notifyDin(List<Boolean> din) {
		for (DinEventListener l : digitalInputs) {
			l.updated(din);
		}
	}

}

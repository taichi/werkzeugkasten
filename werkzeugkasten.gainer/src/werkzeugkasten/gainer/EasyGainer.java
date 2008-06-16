package werkzeugkasten.gainer;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.gainer.cmd.CommandFactory;
import werkzeugkasten.gainer.conf.ConfigFactory;
import werkzeugkasten.gainer.conf.ConfigType;
import werkzeugkasten.gainer.conf.Configuration;
import werkzeugkasten.gainer.lifecycle.LifeCycle;
import werkzeugkasten.gainer.util.ClassUtil;
import werkzeugkasten.gainer.util.ImageUtil;
import werkzeugkasten.gainer.util.StringUtil;

public class EasyGainer implements LifeCycle {

	protected static final Logger LOG = LoggerFactory
			.getLogger(EasyGainer.class);

	protected static final ConfigFactory FACTORY_CONFIG = new ConfigFactory();

	protected static final String BUTTON_UPDATED = "gainerButtonPressed";
	protected static final String AIN_UPDATED = "gainerAinUpdated";
	protected static final String DIN_UPDATED = "gainerDinUpdated";

	protected Object listener;

	protected Configuration conf;
	protected GainerDriver driver;
	protected CommandFactory cmdFactory;

	public EasyGainer(Object listener) {
		this.listener = listener;
		this.conf = FACTORY_CONFIG.create();
		this.cmdFactory = new CommandFactory();
	}

	public EasyGainer(String portId, Object listener) {
		this(portId, ConfigType.CONFIG1, listener);
	}

	public EasyGainer(String portId, ConfigType type, Object listener) {
		this(portId, type, listener, new CommandFactory());
	}

	public EasyGainer(String portId, ConfigType type, Object listener,
			CommandFactory factory) {
		this.listener = listener;
		this.conf = FACTORY_CONFIG.create(portId, type);
		this.cmdFactory = factory;
	}

	@Override
	public void initialize() {
		if (this.listener != null) {
			for (Method m : this.listener.getClass().getDeclaredMethods()) {
				if (addButtonEvent(m) && addAinEvent(m) && addDinEvent(m)) {
					LOG.debug("method : [" + m.getName()
							+ "] is not event method");
				}
			}
		}
		this.driver = new GainerDriver();
		this.driver.initialize(this.conf);
	}

	protected boolean addButtonEvent(final Method m) {
		String name = m.getName();
		Class<?>[] types = m.getParameterTypes();
		if (validate(BUTTON_UPDATED, name, types)) {
			return true;
		}
		if (boolean.class.equals(types[0]) || Boolean.class.equals(types[0])) {
			m.setAccessible(true);
			this.conf.addButtonListener(new OnBoardButtonEventListener() {
				@Override
				public void updated(boolean inputs) {
					ClassUtil.invoke(m, listener, inputs);
				}
			});
			return false;
		}
		return true;
	}

	protected boolean validate(String exp, String act, Class<?>[] types) {
		if (isEmpty(types)) {
			return true;
		}
		if (types.length == 1) {
			return true;
		}
		if (StringUtil.isEmpty(act)) {
			return true;
		}
		if (exp.equalsIgnoreCase(act) == false) {
			return true;
		}
		return false;
	}

	protected boolean isEmpty(Class<?>[] types) {
		return types == null || types.length < 1;
	}

	protected boolean isListEventMethod(String exp, Method m) {
		Class<?>[] types = m.getParameterTypes();
		if (validate(exp, m.getName(), types)) {
			return false;
		}
		return List.class.isAssignableFrom(types[0]);
	}

	protected boolean addAinEvent(final Method m) {
		if (isListEventMethod(AIN_UPDATED, m)) {
			this.conf.addAinListener(new AinEventListener() {
				@Override
				public void updated(List<Number> inputs) {
					ClassUtil.invoke(m, listener, inputs);
				}
			});
			return false;
		}
		return true;
	}

	protected boolean addDinEvent(final Method m) {
		if (isListEventMethod(DIN_UPDATED, m)) {
			this.conf.addDinListener(new DinEventListener() {
				@Override
				public void updated(List<Boolean> inputs) {
					ClassUtil.invoke(m, listener, inputs);
				}
			});
			return false;
		}
		return true;
	}

	@Override
	public void dispose() {
		if (this.driver != null) {
			this.driver.dispose();
		}
	}

	public void turnOnLED() {
		this.driver.execute(CommandFactory.ONBOARD_LED_ON);
	}

	public void turnOffLED() {
		this.driver.execute(CommandFactory.ONBOARD_LED_OFF);
	}

	public void beginDigitalInput() {
		this.driver.execute(CommandFactory.BEGIN_CONTINUOUS_DIGITAL_INPUT);
	}

	public void endDigitalInput() {
		this.driver.execute(CommandFactory.EXIT_CONTINUOUS_MODE);
	}

	public void digitalOutput(int channels) {
		this.driver.execute(this.cmdFactory.digitalOutput(channels));
	}

	public void digitalOutput(boolean... values) {
		this.driver.execute(this.cmdFactory.digitalOutput(values));
	}

	public void setHigh(int channelNo) {
		this.driver.execute(this.cmdFactory.setHigh(channelNo));
	}

	public void setLow(int channelNo) {
		this.driver.execute(this.cmdFactory.setLow(channelNo));
	}

	public void analogOutput(int channelNo, int value) {
		this.driver.execute(this.cmdFactory.analogOutput(channelNo, value));
	}

	public void analogOutputAll(final int... values) {
		this.driver.execute(this.cmdFactory.analogOutputAll(values));
	}

	public void scanLine(int lineNo, int... values) {
		this.driver.execute(this.cmdFactory.scanLine(lineNo, values));
	}

	public void scanMatrix(int... values) {
		this.driver.execute(this.cmdFactory.scanMatrix(values));
	}

	public void scanMatrix(BufferedImage image) {
		this.driver.execute(this.cmdFactory
				.scanMatrix(ImageUtil.toArray(image)));
	}

	public void ampGainAGND(int gain) {
		this.driver.execute(this.cmdFactory.ampGainAGND(gain));
	}

	public void ampGainDGND(int gain) {
		this.driver.execute(this.cmdFactory.ampGainDGND(gain));
	}
}

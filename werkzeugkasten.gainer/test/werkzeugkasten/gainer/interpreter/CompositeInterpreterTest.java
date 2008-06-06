package werkzeugkasten.gainer.interpreter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import werkzeugkasten.gainer.AinEventListener;
import werkzeugkasten.gainer.DinEventListener;
import werkzeugkasten.gainer.OnBoardButtonEventListener;
import werkzeugkasten.gainer.conf.ConfigFactory;
import werkzeugkasten.gainer.conf.ConfigType;
import werkzeugkasten.gainer.conf.Configuration;

public class CompositeInterpreterTest {

	static final ConfigFactory factory = new ConfigFactory();
	Configuration config;

	@Before
	public void setUp() throws Exception {
		config = factory.create("COM3", ConfigType.CONFIG1);
	}

	@Test
	public void testProcessAIN() {
		String datas = "i130C0703*";
		final boolean[] called = new boolean[1];
		final List<Number> exp = new ArrayList<Number>();
		exp.add(Integer.parseInt("13", 16));
		exp.add(Integer.parseInt("0C", 16));
		exp.add(Integer.parseInt("07", 16));
		exp.add(Integer.parseInt("03", 16));

		config.addAinListener(new AinEventListener() {
			@Override
			public void updated(List<Number> datas) {
				assertEquals(exp, datas);
				called[0] = true;
			}
		});
		CompositeInterpreter ci = new CompositeInterpreter(config);
		ci.initialize();
		ci.process(datas);

		assertTrue(called[0]);
	}

	@Test
	public void testProcessDIN() {
		String datas = "R0006*";
		final boolean[] called = new boolean[1];
		final List<Boolean> exp = new ArrayList<Boolean>();
		exp.add(Boolean.FALSE);
		exp.add(Boolean.TRUE);
		exp.add(Boolean.TRUE);
		exp.add(Boolean.FALSE);
		config.addDinListener(new DinEventListener() {
			@Override
			public void updated(List<Boolean> datas) {
				assertEquals(exp, datas);
				called[0] = true;
			}
		});

		CompositeInterpreter ci = new CompositeInterpreter(config);
		ci.initialize();
		ci.process(datas);

		assertTrue(called[0]);
	}

	@Test
	public void testProcessOnBoardButtonOn() {
		String datas = "F*";
		final boolean[] called = new boolean[1];
		config.addButtonListener(new OnBoardButtonEventListener() {
			@Override
			public void updated(boolean pressed) {
				assertFalse(pressed);
				called[0] = true;
			}
		});

		CompositeInterpreter ci = new CompositeInterpreter(config);
		ci.initialize();
		ci.process(datas);

		assertTrue(called[0]);
	}
}

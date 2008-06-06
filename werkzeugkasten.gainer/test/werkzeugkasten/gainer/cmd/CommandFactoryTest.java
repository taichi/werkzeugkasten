package werkzeugkasten.gainer.cmd;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

import werkzeugkasten.gainer.conf.ConfigFactory;
import werkzeugkasten.gainer.conf.ConfigType;
import werkzeugkasten.gainer.conf.Configuration;

public class CommandFactoryTest {

	static final ConfigFactory factory = new ConfigFactory();
	CommandFactory target;
	Configuration conf;

	@Before
	public void setUp() throws Exception {
		target = new CommandFactory();
		conf = factory.create("COM3", ConfigType.CONFIG1);
	}

	@Test
	public void testDigitalOutputInt() {
		Command cmd = target.digitalOutput(true);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		cmd.emit(conf, out);
		assertEquals("D0001*", out.toString());

		out = new ByteArrayOutputStream();
		cmd = target.digitalOutput(true, false, true, true);
		cmd.emit(conf, out);
		assertEquals("D000D*", out.toString());

		out = new ByteArrayOutputStream();
		cmd = target.digitalOutput(new boolean[] { true, false, true, false });
		cmd.emit(conf, out);
		assertEquals("D0005*", out.toString());
	}

	@Test
	public void testScanLine() throws Exception {
		conf = factory.create("COM3", ConfigType.CONFIG7);
		Command cmd = target.scanLine(3, 10, 8, 0xb, 0xff, 2, 0x1);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		cmd.emit(conf, out);
		assertEquals("a3A8BF2100*", out.toString());

	}
}

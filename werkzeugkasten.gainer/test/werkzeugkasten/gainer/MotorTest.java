package werkzeugkasten.gainer;

import org.junit.Test;

import werkzeugkasten.gainer.conf.ConfigType;

public class MotorTest {

	@Test
	public void motor() throws Exception {
		EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG1, this);
		try {
			eg.initialize();
			for (int i = 0; i < 100; i++) {
				eg.digitalOutput(true);
			}
		} finally {
			eg.dispose();
		}
	}
}

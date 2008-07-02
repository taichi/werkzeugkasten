package werkzeugkasten.gainer;

import org.junit.Test;

import werkzeugkasten.gainer.conf.ConfigType;

public class MotorTest {

	@Test
	public void motor() throws Exception {
		EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG1, this);
		eg.initialize();
		try {
			for (int i = 0; i < 50; i++) {
				// eg.analogOutput(0, 0x0);
				eg.digitalOutput(true, false, true, false);
				Thread.sleep(1000);
				eg.digitalOutput(true, false, false, true);
				Thread.sleep(1000);
				// eg.digitalOutput(true, false, false, false);
				// eg.analogOutput(0, 0xf);
				// Thread.sleep(8000);
			}
			eg.digitalOutput(false, false, false, false);
			// Thread.sleep(3000);
			// eg.digitalOutput(false, true, false);
			// Thread.sleep(10000);
			// eg.digitalOutput(false, true, true);
			// Thread.sleep(3000);
			// eg.digitalOutput(false, true, false);
		} finally {
			eg.dispose();
		}
	}
}

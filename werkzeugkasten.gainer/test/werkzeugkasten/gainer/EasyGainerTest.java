package werkzeugkasten.gainer;

import org.junit.Before;
import org.junit.Test;

public class EasyGainerTest {

	EasyGainer target;

	@Before
	public void setUp() throws Exception {
		target = new EasyGainer("COM3", this);
	}

	@Test
	public void testAddButtonEvent() {
		target.initialize();
	}

	public void gainerButtonPressed(boolean is) {

	}
}

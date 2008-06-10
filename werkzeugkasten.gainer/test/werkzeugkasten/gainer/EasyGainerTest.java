package werkzeugkasten.gainer;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import werkzeugkasten.gainer.conf.ConfigType;
import werkzeugkasten.gainer.util.ImageUtil;
import werkzeugkasten.gainer.util.ResourceUtil;

public class EasyGainerTest {

	@Before
	public void setUp() throws Exception {
	}

	static final int[] ary0 = { // aaa
	0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, // aaa
			0x0, 0xf, 0x0, 0x0, 0xf, 0x0, 0x0, 0xf, // aaa
			0x0, 0x0, 0xf, 0xf, 0x0, 0xf, 0xf, 0x0, // aaa
			0x0, 0x0, 0xf, 0x0, 0x0, 0x0, 0xf, 0x0, // aaa
			0x0, 0xf, 0x0, 0x0, 0x0, 0x0, 0x0, 0xf, // aaa
			0x0, 0x0, 0xf, 0x0, 0x0, 0x0, 0xf, 0x0, // aaa
			0x0, 0x0, 0xf, 0xf, 0x0, 0xf, 0xf, 0x0, // aaa
			0x0, 0xf, 0x0, 0x0, 0xf, 0x0, 0x0, 0xf, // aaa
	};

	static final int[] ary1 = { // aaa
	0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, // aaa
			0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, // aaa
			0x0, 0x0, 0x0, 0xf, 0xf, 0xf, 0x0, 0x0, // aaa
			0x0, 0x0, 0xf, 0x0, 0x0, 0x0, 0xf, 0x0, // aaa
			0x0, 0x0, 0xf, 0x0, 0x0, 0x0, 0xf, 0x0, // aaa
			0x0, 0x0, 0xf, 0x0, 0x0, 0x0, 0xf, 0x0, // aaa
			0x0, 0x0, 0x0, 0xf, 0xf, 0xf, 0x0, 0x0, // aaa
			0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, // aaa
	};

	@Test
	public void matrixTest() throws Exception {
		EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG7, this);
		try {
			eg.initialize();
			int i = 0, j = 0;
			while (j++ < 20) {
				if (i == 0) {
					eg.scanMatrix(ary0);
					i++;
				} else {
					eg.scanMatrix(ary1);
					i = 0;
				}
				Thread.sleep(300);
			}
		} finally {
			eg.dispose();
		}
	}

	@Test
	public void imageToMatrix() throws Exception {
		BufferedImage cloud = ImageIO.read(ResourceUtil
				.getResource("werkzeugkasten/gainer/cloud.bmp"));
		BufferedImage rain = ImageIO.read(ResourceUtil
				.getResource("werkzeugkasten/gainer/rain.bmp"));
		EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG7, this);
		try {
			eg.initialize();
			int j = 0;
			while (j++ < 10) {
				eg.scanMatrix(cloud);
				Thread.sleep(100);
				eg.scanMatrix(rain);
				Thread.sleep(100);
			}
		} finally {
			eg.dispose();
		}
	}

	@Test
	public void stringToMatrix() throws Exception {
		RenderedImage image = ImageUtil.render("Hello MatrixLED !!");
		EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG7, this);
		try {
			eg.initialize();
			Raster raster = image.getData();
			int[] matrix = new int[64];
			int width = image.getWidth() - 8;
			for (int pos = 0; pos < width; pos++) {
				int[] data = raster.getPixels(pos, 0, 8, 8, matrix);
				eg.scanMatrix(data);
				Thread.sleep(100);
			}
		} finally {
			eg.dispose();
		}
	}
}

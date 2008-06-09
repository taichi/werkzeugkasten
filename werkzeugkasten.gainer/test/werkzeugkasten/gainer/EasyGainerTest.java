package werkzeugkasten.gainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

import werkzeugkasten.gainer.conf.ConfigType;
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

	// @Test
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

	// @Test
	public void imageToMatrix() throws Exception {
		BufferedImage cloud = ImageIO.read(ResourceUtil
				.getResource("werkzeugkasten/gainer/cloud.bmp"));
		BufferedImage rain = ImageIO.read(ResourceUtil
				.getResource("werkzeugkasten/gainer/rain.bmp"));
		EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG7, this);
		try {
			eg.initialize();
			int j = 0;
			while (j++ < 30) {
				eg.scanMatrix(cloud);
				Thread.sleep(300);
				eg.scanMatrix(rain);
				Thread.sleep(300);
			}
		} finally {
			eg.dispose();
		}
	}

	@Test
	public void stringToMatrix() throws Exception {
		BufferedImage bi = new BufferedImage(8, 32,
				BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g2d = bi.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g2d.setFont(new Font(null, Font.PLAIN, 10));
		g2d.setColor(Color.WHITE);
		g2d.drawString("ﾄ", 2, 8);
		g2d.drawString("ｺ", 2, 16);
		g2d.drawString("g", 2, 24);
		g2d.drawString("P", 2, 32);
		FontMetrics fm = g2d.getFontMetrics();
		System.out.println(fm.stringWidth("ﾄ"));
		System.out.println(fm.charWidth('g'));
		System.out.println(fm.getHeight());

		// EasyGainer eg = new EasyGainer("COM3", ConfigType.CONFIG7, this);
		// try {
		// eg.initialize();
		// int j = 0;
		// while (j++ < 10) {
		// eg.scanMatrix(bi);
		// Thread.sleep(300);
		// }
		// } finally {
		// eg.dispose();
		// }
		ImageIO.write(bi, "BMP", new File("C:\\hoge.bmp"));
	}
}

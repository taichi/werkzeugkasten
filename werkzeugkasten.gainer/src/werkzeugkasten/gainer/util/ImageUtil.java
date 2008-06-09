package werkzeugkasten.gainer.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;

public class ImageUtil {

	/**
	 * MatrixLEDで表示する為の、配列に変換する。<br/> 画像の高さは自由だが、幅は、8ドット固定。
	 * 
	 * @param image
	 * @return
	 */
	public static int[] toArray(RenderedImage image) {
		Raster raster = image.getData();
		return raster.getPixels(0, 0, 8, raster.getHeight(), (int[]) null);
	}

	/**
	 * MatrixLEDで表示出来る様に、8xXの画像にした後、配列にする。
	 * 
	 * @param str
	 * @return
	 */
	public static int[] toImageArray(String str) {
		BufferedImage image = new BufferedImage(8, str.length() * 8,
				BufferedImage.TYPE_BYTE_BINARY);
		char[] chars = new char[str.length()];
		str.getChars(0, str.length() - 1, chars, 0);

		Graphics2D g2d = image.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
		g2d.setFont(new Font(null, Font.PLAIN, 10));
		g2d.setColor(Color.WHITE);
		FontMetrics fm = g2d.getFontMetrics();
		for (int i = 0; i < str.length(); i++) {
			if (fm.charWidth(chars[i]) < 7) {
				g2d.drawChars(chars, i, 1, 2, (i + 1) * 8);
			}
		}
		return toArray(image);
	}
}

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
	 * MatrixLEDで表示出来る様に、 高さが8ドットの画像を作り、<br/>
	 * その画像にプラットフォームデフォルトのフォントで文字列をレンダリングする。
	 * 
	 * @param str
	 * @return
	 */
	public static RenderedImage render(String str) {
		// 計測用
		BufferedImage tmp = new BufferedImage(1, 1,
				BufferedImage.TYPE_BYTE_BINARY);
		Font font = new Font(null, Font.PLAIN, 10);

		Graphics2D tmpg = tmp.createGraphics();
		tmpg.setFont(font);
		FontMetrics fm = tmpg.getFontMetrics();
		int width = fm.stringWidth(str);
		tmpg.dispose();

		// 本物
		BufferedImage image = new BufferedImage(width, 8,
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		g2d.drawString(str, 0, 7);
		g2d.dispose();
		return image;
	}

}

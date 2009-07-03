package werkzeugkasten.factory.concept;

import werkzeugkasten.factory.Extension;

public class Client {

	public static void main(String[] args) {
		Extension root = null;
		HogeExtension hoge = root.getAdapter(HogeExtension.class);
		FugaExtension fuga = root.getAdapter(FugaExtension.class);
	}
}

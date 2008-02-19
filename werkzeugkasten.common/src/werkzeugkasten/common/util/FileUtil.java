package werkzeugkasten.common.util;

import java.io.File;

public class FileUtil {

	public static void delete(File file) {
		if (file.isDirectory()) {
			deleteAll(file.listFiles());
		}
		file.delete();
	}

	public static void deleteAll(File[] files) {
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isDirectory()) {
				deleteAll(file.listFiles());
			}
			file.delete();
		}
	}
}

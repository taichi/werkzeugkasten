package werkzeugkasten.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import werkzeugkasten.mvnhack.repository.FileNotFoundRuntimeException;

public class FileUtil {

	public static InputStream open(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundRuntimeException(e);
		}
	}

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
			delete(file);
		}
	}
}

package werkzeugkasten.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import werkzeugkasten.common.exception.IORuntimeException;

public class FileUtil {

	public static InputStream open(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void copy(final InputStream in, final File dest) {
		new Streams.using<FileOutputStream, IOException>(IOException.class) {
			@Override
			public FileOutputStream open() throws IOException {
				return new FileOutputStream(dest);
			}

			@Override
			public void handle(FileOutputStream stream) throws IOException {
				Streams.copy(in, stream);
			}

			@Override
			public void happen(IOException exception) {
				throw new IORuntimeException(exception);
			}
		};
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

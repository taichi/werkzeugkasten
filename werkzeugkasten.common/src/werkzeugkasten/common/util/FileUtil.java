package werkzeugkasten.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
		new Streams.using<FileOutputStream, IOException>() {
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

	public interface PathFilter {
		boolean accept(String path);
	}

	public interface FileHandler {
		void handle(File file);
	}

	public static PathFilter NULL_FILTER = new PathFilter() {
		@Override
		public boolean accept(String path) {
			return true;
		}
	};

	public static class PatternFilter implements PathFilter {
		protected Pattern pattern;

		public PatternFilter(String pattern) {
			this.pattern = Pattern.compile(pattern);
		}

		public PatternFilter(Pattern pattern) {
			this.pattern = pattern;
		}

		@Override
		public boolean accept(String path) {
			return this.pattern.matcher(path).matches();
		}
	}

	public static class ReverseFilter implements PathFilter {
		protected PathFilter delegate;

		public ReverseFilter(PathFilter filter) {
			this.delegate = filter;
		}

		@Override
		public boolean accept(String path) {
			return this.delegate.accept(path) == false;
		}
	}

	public static void list(String file, PathFilter filter, FileHandler handler) {
		File f = new File(file);
		if (f.isDirectory()) {
			for (String s : f.list()) {
				if (filter.accept(s)) {
					list(s, filter, handler);
				}
			}
		}
		if (f.exists() && filter.accept(file)) {
			handler.handle(f);
		}
	}

	public static List<File> list(String file, PathFilter filter) {
		final List<File> list = new ArrayList<File>();
		list(file, filter, new FileHandler() {
			@Override
			public void handle(File file) {
				if (file.isFile()) {
					list.add(file);
				}
			}
		});
		return list;
	}

	public static void delete(String file, PathFilter filter) {
		list(file, filter, new FileHandler() {
			@Override
			public void handle(File file) {
				file.delete();
			}
		});
	}

	public static void delete(String file) {
		delete(file, NULL_FILTER);
	}

	public static void copy(String from, final String to, PathFilter filter) {
		File fromFile = new File(from);
		if (fromFile.exists()) {
			File t = new File(to);
			if (t.exists()) {
				delete(to);
			}
			if (fromFile.isFile()) {
				copy(open(fromFile), t);
			}
			if (fromFile.isDirectory()) {
				String p = fromFile.getAbsolutePath();
				final int base = p.endsWith(File.separator) ? p.length() : p
						.length() + 1;
				list(from, filter, new FileHandler() {
					@Override
					public void handle(File file) {
						String path = file.getAbsolutePath().substring(base);
						File t = new File(to, path);
						if (file.isDirectory()) {
							t.mkdirs();
						}
						if (file.isFile()) {
							copy(open(file), t.getAbsoluteFile());
						}
					}
				});
			}
		}
	}
}

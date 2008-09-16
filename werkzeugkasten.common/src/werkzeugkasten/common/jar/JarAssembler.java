package werkzeugkasten.common.jar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;

import werkzeugkasten.common.util.StreamUtil;

public class JarAssembler {

	public interface Config {
		boolean compress();
	}

	protected static final ExceptionHandler DEFAULT_HANDLER = new ExceptionHandler() {
		public void handle(Exception cause) {
			throw new IllegalStateException(cause);
		}
	};

	protected Map<Class<?>, ExceptionHandler> excetionHandlers = new HashMap<Class<?>, ExceptionHandler>();
	protected Config conf;

	protected Manifest manifest;
	protected JarOutputStream stream;

	protected List<ManifestModifier> modifiers = new ArrayList<ManifestModifier>();

	public JarAssembler(Config conf) {
		this.conf = conf;
	}

	public void add(Class<?> key, ExceptionHandler handler) {
		this.excetionHandlers.put(key, handler);
	}

	public void open(File dest) {
		open(dest, new Manifest());
	}

	public void open(File file, Manifest manifest) {
		try {
			this.manifest = manifest;
			this.stream = new JarOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));
		} catch (Exception e) {
			handle(e);
		}
	}

	protected void handle(Exception ex) {
		ExceptionHandler eh = this.excetionHandlers.get(ex.getClass());
		if (eh == null) {
			DEFAULT_HANDLER.handle(ex);
		} else {
			eh.handle(ex);
		}
	}

	protected interface $entry {
		void handle(InputStream stream) throws Exception;
	}

	protected void entry(final Opener opener, final $entry entry) {
		StreamUtil.is(new StreamUtil._<InputStream, Exception>() {
			public InputStream open() throws Exception {
				return opener.open();
			}

			public void handle(InputStream in) throws Exception {
				entry.handle(in);
			}

			public void happen(Exception ex) {
				JarAssembler.this.handle(ex);
			}
		});

	}

	public void entry(final Opener opener, String path) {
		JarEntry entry = createEntry(opener, path);
		writeEntry(opener, entry);
	}

	protected JarEntry createEntry(final Opener opener, String path) {
		final JarEntry je = new JarEntry(path);
		if (this.conf.compress()) {
			je.setMethod(ZipEntry.DEFLATED);
			// any other JarEntry values will be fill automatically.
		} else {
			je.setMethod(ZipEntry.STORED);
			entry(opener, new $entry() {
				public void handle(InputStream in) throws Exception {
					long size = 0;
					CRC32 crc = new CRC32();
					byte[] buf = new byte[4096];
					while (true) {
						int len = in.read(buf, 0, 4096);
						if (-1 < len) {
							crc.update(buf, 0, len);
							size += len;
						} else {
							break;
						}
					}
					je.setSize(size);
					je.setCrc(crc.getValue());
				}
			});
		}
		return je;
	}

	protected void writeEntry(final Opener opener, JarEntry je) {
		try {
			this.stream.putNextEntry(je);
			entry(opener, new $entry() {
				public void handle(InputStream in) throws Exception {
					StreamUtil.copy(in, stream);
				}
			});
		} catch (Exception e) {
			this.handle(e);
		}
	}

	protected Set<String> existsDirectories = new HashSet<String>();

	public void entry(String path) {
		LinkedList<JarEntry> list = new LinkedList<JarEntry>();
		for (int i = path.lastIndexOf('/'); 0 < i; i = path.lastIndexOf('/',
				i - 1)) {
			path = path.substring(0, i + 1);
			if (this.existsDirectories.add(path)) {
				JarEntry entry = new JarEntry(path);
				entry.setMethod(ZipEntry.STORED);
				entry.setSize(0);
				entry.setCrc(0);
				list.addLast(entry);
			} else {
				break;
			}
		}
		try {
			for (JarEntry je : list) {
				this.stream.putNextEntry(je);
			}
		} catch (Exception e) {
			handle(e);
		}
	}

	public void add(ManifestModifier modifier) {
		this.modifiers.add(modifier);
	}

	public void close() {
		try {
			for (ManifestModifier mm : this.modifiers) {
				mm.modify(this.manifest);
			}
			ZipEntry e = new ZipEntry(JarFile.MANIFEST_NAME);
			this.stream.putNextEntry(e);
			this.manifest.write(this.stream);
			this.stream.closeEntry();
			this.stream.close();
			this.modifiers.clear();
			this.existsDirectories.clear();
		} catch (Exception e) {
			handle(e);
		}
	}
}

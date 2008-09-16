package werkzeugkasten.common.jar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	protected interface State {
		void open(File dest, Manifest manifest);

		void isWritable();

		void close();
	}

	protected class Begin implements State {
		public void open(File f, Manifest m) {
			try {
				manifest = m;
				stream = new JarOutputStream(new BufferedOutputStream(
						new FileOutputStream(f)));
			} catch (Exception e) {
				handle(e);
			}
		}

		public void isWritable() {
			throw new IllegalStateException();
		}

		public void close() {
			throw new IllegalStateException();
		}
	}

	protected class InProcess implements State {
		public void open(File arg0, Manifest arg1) {
			throw new IllegalStateException();
		}

		public void isWritable() {
		}

		public void close() {
			try {
				for (ManifestModifier mm : modifiers) {
					mm.modify(manifest);
				}
				ZipEntry e = new ZipEntry(JarFile.MANIFEST_NAME);
				stream.putNextEntry(e);
				manifest.write(stream);
				stream.closeEntry();
				stream.close();
				modifiers.clear();
			} catch (Exception e) {
				handle(e);
			}
		}
	}

	protected static final ExceptionHandler DEFAULT_HANDLER = new ExceptionHandler() {
		public void handle(Exception cause) {
			throw new IllegalStateException(cause);
		}
	};

	protected Map<Class<?>, ExceptionHandler> excetionHandlers = new HashMap<Class<?>, ExceptionHandler>();
	protected Config conf;

	protected State state;
	protected Manifest manifest;
	protected JarOutputStream stream;

	protected List<ManifestModifier> modifiers = new ArrayList<ManifestModifier>();

	public JarAssembler(Config conf) {
		this.conf = conf;
		this.state = new Begin();
	}

	public void add(Class<?> key, ExceptionHandler handler) {
		this.excetionHandlers.put(key, handler);
	}

	public void open(File dest) {
		open(dest, new Manifest());
	}

	public void open(File f, Manifest m) {
		state.open(f, m);
		state = new InProcess();
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
		this.state.isWritable();
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

	public void entry(String path) {
		this.state.isWritable();

	}

	public void add(ManifestModifier modifier) {
		this.state.isWritable();
		this.modifiers.add(modifier);
	}

	public void close() {
		this.state.close();
		this.state = new Begin();
	}
}

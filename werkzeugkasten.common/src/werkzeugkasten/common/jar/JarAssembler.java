package werkzeugkasten.common.jar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.StreamUtil.using;

public class JarAssembler {

	protected JarConfig conf;

	protected Manifest manifest;
	protected JarOutputStream stream;
	protected List<ManifestModifier> modifiers = new ArrayList<ManifestModifier>();
	protected Set<String> existsDirectories = new HashSet<String>();

	public JarAssembler(JarConfig conf) {
		this.conf = conf;
	}

	public void open(File dest) {
		open(dest, new Manifest());
	}

	public void open(File dest, Manifest manifest) {
		try {
			this.manifest = manifest;
			Attributes main = manifest.getMainAttributes();
			String version = main.getValue(Attributes.Name.MANIFEST_VERSION);
			if (StringUtil.isEmpty(version)) {
				main.putValue(Attributes.Name.MANIFEST_VERSION.toString(),
						"1.0");
			}
			this.stream = new JarOutputStream(new BufferedOutputStream(
					new FileOutputStream(dest)));
		} catch (Exception e) {
			handle(e);
		}
	}

	protected void handle(Exception ex) {
		this.conf.find(ex.getClass()).handle(ex);
	}

	protected interface $entry {
		void invoke(InputStream stream) throws Exception;
	}

	protected void entry(final Opener opener, final $entry func) {
		new using<InputStream, Exception>() {
			public InputStream open() throws Exception {
				return opener.open();
			}

			public void handle(InputStream stream) throws Exception {
				func.invoke(stream);
			}

			public void happen(Exception exception) {
				JarAssembler.this.handle(exception);
			}
		};
	}

	public void entry(final Opener opener, String path) {
		JarEntry entry = createEntry(opener, path);
		writeEntry(opener, entry);
	}

	protected JarEntry createEntry(final Opener opener, String path) {
		final JarEntry entry = new JarEntry(path);
		if (this.conf.compress()) {
			entry.setMethod(ZipEntry.DEFLATED);
			// any other JarEntry values will be fill automatically.
		} else {
			entry.setMethod(ZipEntry.STORED);
			entry(opener, new $entry() {
				public void invoke(InputStream in) throws Exception {
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
					entry.setSize(size);
					entry.setCrc(crc.getValue());
				}
			});
		}
		return entry;
	}

	protected void writeEntry(final Opener opener, JarEntry je) {
		try {
			this.stream.putNextEntry(je);
			entry(opener, new $entry() {
				public void invoke(InputStream in) throws Exception {
					StreamUtil.copy(in, stream);
				}
			});
		} catch (Exception e) {
			this.handle(e);
		}
	}

	public void entry(String path) {
		if ('/' != path.charAt(path.length() - 1)) {
			path += "/";
		}
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

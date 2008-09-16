/**
 * 
 */
package werkzeugkasten.common.jar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileOpener implements Opener {
	protected File file;

	public FileOpener(File file) {
		this.file = file;
	}

	public InputStream open() throws IOException {
		return new FileInputStream(this.file);
	}
}
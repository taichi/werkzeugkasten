/**
 * 
 */
package werkzeugkasten.common.jar;

import java.io.IOException;
import java.io.InputStream;

public interface Opener {
	InputStream open() throws IOException;
}
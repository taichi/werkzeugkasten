package werkzeugkasten.common.jar;

import java.io.InputStream;

public interface Opener {
	InputStream open() throws Exception;
}
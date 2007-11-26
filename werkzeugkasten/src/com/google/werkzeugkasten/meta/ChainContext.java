package com.google.werkzeugkasten.meta;

import com.google.werkzeugkasten.meta.Executable.Execute;

public interface ChainContext<R> {

	@Execute
	R execute();
}

package werkzeugkasten.nlsgen;

import org.eclipse.core.resources.IResource;

public class Constants {

	public static final String ID_PLUGIN = "werkzeugkasten.NLSgen";

	public static final String EXT_RESOURCE_GENERATOR = "resourceGenerator";

	public static final String GENERATOR_TYPE(IResource r) {
		return r.getFullPath().toString() + "#generatorType";
	}

	public static final String GENERATION_DEST(IResource r) {
		return r.getFullPath().toString() + "#generationDest";
	};

}

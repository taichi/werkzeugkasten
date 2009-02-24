package werkzeugkasten.twowaysql;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class Markers {

	public static final String PREFIX_PKG = "werkzeugkasten.twowaysql";

	public static final Marker MARKER_ROOT = MarkerFactory
			.getMarker(PREFIX_PKG);

	// designing symbol message of library and extension points runtime messages
	public static final Marker DESIGN = MarkerFactory.getMarker(PREFIX_PKG
			+ ".design");

	// interface of any other systems or library
	public static final Marker BOUNDARY = MarkerFactory.getMarker(PREFIX_PKG
			+ ".boundary");

	// lifeCycle of objects
	public static final Marker LIFECYCLE = MarkerFactory.getMarker(PREFIX_PKG
			+ ".lifecycle");

	// processing details of method.
	// this marker is defined for library developer
	public static final Marker DETAIL = MarkerFactory.getMarker(PREFIX_PKG
			+ ".detail");

	static {
		Marker[] markers = { DESIGN, BOUNDARY, LIFECYCLE, DETAIL };
		for (Marker m : markers) {
			MARKER_ROOT.add(m);
		}
	}
}

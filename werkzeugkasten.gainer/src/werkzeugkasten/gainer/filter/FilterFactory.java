package werkzeugkasten.gainer.filter;

import werkzeugkasten.gainer.AinEventListener;

public interface FilterFactory {

	Filter create(AinEventListener cause);
}

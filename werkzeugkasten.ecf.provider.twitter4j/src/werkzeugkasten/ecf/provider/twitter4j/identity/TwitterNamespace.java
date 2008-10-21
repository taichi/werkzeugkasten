package werkzeugkasten.ecf.provider.twitter4j.identity;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.Namespace;

public class TwitterNamespace extends Namespace {

	private static final long serialVersionUID = -4543499236593751178L;

	static final Class<?>[][] supports = { { String.class } };

	@Override
	public ID createInstance(Object[] parameters) throws IDCreateException {
		if (parameters == null || parameters.length < 1
				|| (parameters[0] instanceof String == false)) {
			return new TwitterID(this, (String) parameters[0]);
		}
		throw new IDCreateException("illegalArgument");
	}

	@Override
	public String getScheme() {
		return "http://";
	}

	@Override
	public Class<?>[][] getSupportedParameterTypes() {
		return supports;
	}

}

package werkzeugkasten.ecf.provider.twitter4j.container;

import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerTypeDescription;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.provider.IContainerInstantiator;
import org.eclipse.ecf.presence.IPresenceContainerAdapter;

public class TwitterContainerInstantiator implements IContainerInstantiator {

	static final Class<?>[][] EMPTY = new Class[0][0];
	static final String[] supports = { IPresenceContainerAdapter.class
			.getName() };

	@Override
	public IContainer createInstance(ContainerTypeDescription description,
			Object[] parameters) throws ContainerCreateException {
		try {
			ID guid = IDFactory.getDefault().createGUID();
			return new TwitterContainer(guid);
		} catch (IDCreateException e) {
			throw new ContainerCreateException(e);
		}
	}

	@Override
	public String[] getSupportedAdapterTypes(
			ContainerTypeDescription description) {
		return supports;
	}

	@Override
	public Class<?>[][] getSupportedParameterTypes(
			ContainerTypeDescription description) {
		return EMPTY;
	}

}

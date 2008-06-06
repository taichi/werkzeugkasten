package werkzeugkasten.gainer.conf;

import static werkzeugkasten.gainer.cmd.CommandFactory.*;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import werkzeugkasten.gainer.exception.NoSuchPortRuntimeException;
import werkzeugkasten.gainer.util.CommPortUtil;

public class ConfigFactory {

	protected static final byte[] GAINER_VERSION = "?1.1.".getBytes();

	public Configuration create() {
		return create(ConfigType.CONFIG1);
	}

	public Configuration create(ConfigType type) {
		String portId = autoDetect();
		return create(portId, type);
	}

	public Configuration create(String portId, ConfigType type) {
		return new DefaultConfiguration(portId, type);
	}

	/**
	 * Gainerが接続されているポートを自動的に検索する。
	 * 
	 * @return 最初に発見したGainerのポート名
	 * @throws NoSuchPortRuntimeException
	 *             ポートが発見出来ない場合
	 */
	protected String autoDetect() throws NoSuchPortRuntimeException {
		Set<CommPortIdentifier> ports = CommPortUtil
				.getAvailableSerialPorts(new CommPortUtil.CommPortVerifier() {
					@Override
					public boolean verify(CommPort port) throws IOException {
						OutputStream out = port.getOutputStream();
						QUIT.emit(null, out);
						if (QUIT.equals(CommPortUtil.read(port))) {
							VERSION.emit(null, out);
							byte[] bytes = CommPortUtil.read(port);
							final int gvlength = GAINER_VERSION.length;
							if (bytes.length < gvlength) {
								return false;
							}
							for (int i = 0; gvlength < i; i++) {
								if (GAINER_VERSION[i] != bytes[i]) {
									return false;
								}
							}
							return true;
						}
						return false;
					}
				});
		if (0 < ports.size()) {
			return ports.iterator().next().getName();
		}
		throw new NoSuchPortRuntimeException();
	}
}

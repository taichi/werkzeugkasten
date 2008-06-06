package werkzeugkasten.gainer.util;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.gainer.exception.IORuntimeException;

public class CommPortUtil {

	static final Logger LOG = LoggerFactory.getLogger(CommPortUtil.class);

	protected static final int WAIT_FOR_RXTX_PORT = Integer.parseInt(System
			.getProperty("wait.time.rxtx", "50"));

	public static Set<CommPortIdentifier> getAvailableSerialPorts() {
		return getAvailableSerialPorts(NULL_VERIFIER);
	}

	public static Set<CommPortIdentifier> getAvailableSerialPorts(
			CommPortVerifier verifier) {
		Set<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
		Enumeration<?> thePorts = CommPortIdentifier.getPortIdentifiers();
		while (thePorts.hasMoreElements()) {
			CommPortIdentifier com = (CommPortIdentifier) thePorts
					.nextElement();
			switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				CommPort port = null;
				try {
					port = com.open(CommPortUtil.class.getName(), 50);
					if (verifier.verify(port)) {
						h.add(com);
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					throw new IllegalStateException(e);
				} finally {
					close(port);
				}
			}
		}
		return h;
	}

	public interface CommPortVerifier {
		boolean verify(CommPort port) throws IOException;
	}

	public static final CommPortVerifier NULL_VERIFIER = new CommPortVerifier() {
		@Override
		public boolean verify(CommPort port) {
			return true;
		}
	};

	public static CommPort open(String portName) throws NoSuchPortException,
			PortInUseException {
		CommPortIdentifier id = CommPortIdentifier.getPortIdentifier(portName);
		return open(id);
	}

	public static CommPort open(CommPortIdentifier id)
			throws PortInUseException {
		return open(id, 1000);
	}

	public static CommPort open(CommPortIdentifier id, int timeout)
			throws PortInUseException {
		return open(id, CommPortUtil.class.getSimpleName(), timeout);
	}

	public static CommPort open(CommPortIdentifier id, String name, int timeout)
			throws PortInUseException {
		return id.open(name, timeout);
	}

	public static void close(CommPort port) {
		if (port != null) {
			port.close();
		}
	}

	public static void emit(OutputStream out, byte[] bytes) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("EMIT [" + new String(bytes) + "]");
			}
			out.write(bytes);
			out.flush();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * Gainerの為に開いたポートと逐次的にやりとりを行う為の読み取り処理。<br/>
	 * 初期設定時等、通信内容が確実に直列化されている事を想定出来る場合のみ使用する事。<br/>
	 * SerialPortにSerialPortEventListenerが設定されている状態では適切に動作しない。<br/>
	 * 
	 * @param port
	 *            接続先ポート
	 * @return 読み取り結果
	 */
	public static byte[] read(CommPort port) {
		try {
			InputStream input = port.getInputStream();
			byte[] buf = new byte[64];
			int length = 0;
			do {
				try {
					Thread.sleep(WAIT_FOR_RXTX_PORT);
				} catch (InterruptedException ie) {
				}
				int a = input.available();
				if (0 < a) {
					length += input.read(buf, length, a);
				}
			} while (length < 1 || buf[length - 1] != '*');
			return Arrays.copyOf(buf, length);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

}

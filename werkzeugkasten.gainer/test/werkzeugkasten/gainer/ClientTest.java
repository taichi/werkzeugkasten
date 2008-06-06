package werkzeugkasten.gainer;

import static org.junit.Assert.*;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import werkzeugkasten.gainer.util.CommPortUtil;

public class ClientTest {

	// @Test
	public void testAvailableSerialPorts() {
		Set<CommPortIdentifier> ports = CommPortUtil.getAvailableSerialPorts();
		assertEquals(true, 0 < ports.size());
		for (CommPortIdentifier id : ports) {
			System.out.println(id.getName());
		}
	}

	private final int rate = 38400;
	private final int databits = 8;
	private final int stopbits = SerialPort.STOPBITS_1;
	private final int parity = SerialPort.PARITY_NONE;

	// @Test
	public void testOpenSerialPort() throws Exception {
		SerialPort sp = open();
		sp.close();
	}

	private SerialPort open() throws NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		CommPort port = CommPortUtil.open("COM3");
		assertTrue(port instanceof SerialPort);
		SerialPort sp = (SerialPort) port;
		sp.setSerialPortParams(rate, databits, stopbits, parity);
		return sp;
	}

	// @Test
	public void testSend() throws Exception {
		SerialPort port = open();
		try {
			OutputStream out = port.getOutputStream();
			echoAssert("Q*", port, out);
			readAssert("?*", "?1.1.0b00*", port, out);
			echoAssert("KONFIGURATION_1*", port, out);
			echoAssert("h*", port, out);
			echoAssert("l*", port, out);
			echoAssert("H0*", port, out);
			echoAssert("L0*", port, out);
			echoAssert("DFFFF*", port, out);
			echoAssert("D0000*", port, out);
			echoAssert("D0009*", port, out);
			echoAssert("D0000*", port, out);
		} finally {
			port.close();
		}
	}

	// @Test
	public void testAnalogSend() throws Exception {
		SerialPort port = open();
		try {
			OutputStream out = port.getOutputStream();
			echoAssert("Q*", port, out);
			echoAssert("KONFIGURATION_1*", port, out);
			readAssert("A00000000*", "A*", port, out);
			echoAssert("a010*", port, out);
			echoAssert("a000*", port, out);
		} finally {
			port.close();
		}
	}

	private void echoAssert(String cmd, SerialPort port, OutputStream out)
			throws Exception {
		readAssert(cmd, cmd, port, out);
	}

	private void readAssert(String cmd, String expected, SerialPort port,
			OutputStream out) throws Exception {
		readAssert(cmd.getBytes(), expected, port, out);
	}

	private void readAssert(byte[] cmd, String expected, SerialPort port,
			OutputStream out) throws Exception {
		out.write(cmd);
		out.flush();
		Thread.sleep(1000);
		String s = read(port);
		System.out.println(s);
		assertEquals(expected, s);
	}

	private String read(SerialPort port) throws IOException {
		InputStream input = port.getInputStream();
		byte[] buf = new byte[128];
		int length = 0;
		do {
			int a = input.available();
			if (0 < a) {
				length += input.read(buf, length, a);
			}
		} while (length < 1 || buf[length - 1] != '*');

		return new String(Arrays.copyOf(buf, length));
	}

	// @Test
	public void testSendAndRead() throws Exception {
		final SerialPort port = open();
		try {
			final boolean[] flg = { false };
			final InputStream in = port.getInputStream();
			final OutputStream out = port.getOutputStream();
			echoAssert("Q*", port, out);
			echoAssert("KONFIGURATION_1*", port, out);

			final StringBuffer stb = new StringBuffer();
			port.addEventListener(new SerialPortEventListener() {
				@Override
				public void serialEvent(SerialPortEvent event) {
					if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
						try {
							int size = in.available();
							byte[] buf = new byte[size];
							in.read(buf);
							String s = new String(buf);
							stb.append(s);
							if (0 < stb.indexOf("*")) {
								System.out.println(stb);
								out.write("E*".getBytes());
								out.flush();
								flg[0] = true;
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new AssertionError(e);
						}
					}
				}
			});
			port.notifyOnDataAvailable(true);
			out.write("r*".getBytes());
			out.flush();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					while (flg[0] == false) {
					}
				}
			};
			Thread t = new Thread(r);
			t.setName("testSendAndRead");
			t.start();
			t.join();
			System.out.println("#");
			port.notifyOnDataAvailable(false);
			port.removeEventListener();
		} finally {
			CommPortUtil.close(port);
		}
	}

	@Test
	public void testReadQueue() throws Exception {
		final BlockingQueue<byte[]> queue = new LinkedBlockingQueue<byte[]>();

		final SerialPort port = open();
		try {
			final OutputStream out = port.getOutputStream();

			port.addEventListener(new SerialPortEventListener() {
				@Override
				public void serialEvent(SerialPortEvent event) {
					if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
						try {
							InputStream in = port.getInputStream();
							int size = in.available();
							byte[] buf = new byte[size];
							in.read(buf);
							queue.put(buf);
						} catch (Exception e) {
							e.printStackTrace();
							throw new AssertionError(e);
						}
					}
				}
			});

			final long BEGIN[] = new long[1];
			final boolean[] flg = { true };
			Runnable writer = new Runnable() {
				@Override
				public void run() {
					try {
						int wait = 50;
						System.out.println("BEGIN ...");
						out.write("Q*".getBytes());
						out.flush();
						Thread.sleep(wait);
						assertEquals("Q*", read(port));

						out.write("?*".getBytes());
						out.flush();
						Thread.sleep(wait);
						assertEquals("?1.1.0b00*", read(port));

						out.write("V0*".getBytes());
						out.flush();
						Thread.sleep(wait);
						assertEquals("V0*", read(port));

						out.write("KONFIGURATION_1*".getBytes());
						out.flush();
						Thread.sleep(wait);
						assertEquals("KONFIGURATION_1*", read(port));

						System.out.println("END ...");

						System.out.println("BEGIN ....");
						port.notifyOnDataAvailable(true);
						BEGIN[0] = System.currentTimeMillis();

						out.write("H0*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("L0*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("R*".getBytes());
						out.flush();
						out.write("AFFFFFFFF*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("a000*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("I*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("S0*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("M0*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("G31*".getBytes());
						out.flush();
						dummyInput(out);

						out.write("r*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("E*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("i*".getBytes());
						out.flush();
						dummyInput(out);
						out.write("E*".getBytes());
						out.flush();
						dummyInput(out);

						out.write("Q*".getBytes());
						out.flush();
						Thread.sleep(100);
						out.write("KONFIGURATION_2*".getBytes());
						out.flush();
						Thread.sleep(100);
						out.write("i*".getBytes());
						out.flush();
						out.write("E*".getBytes());
						out.flush();
						Thread.sleep(1000);

						out.write("Q*".getBytes());
						out.flush();
						Thread.sleep(100);
						out.write("KONFIGURATION_7*".getBytes());
						out.flush();
						Thread.sleep(100);
						// for config7 matrix control mode only
						out.write("a000000000*".getBytes());
						out.flush();
						out.write("a100000001*".getBytes());
						out.flush();
						out.write("a200000002*".getBytes());
						out.flush();
						Thread.sleep(1000);

						out.write("Q*".getBytes());
						out.flush();
						Thread.sleep(100);
						out.write("KONFIGURATION_8*".getBytes());
						out.flush();
						Thread.sleep(100);
						// for config8 only
						out.write("Tx*".getBytes());
						out.flush();
						dummyInput(out);

						Thread.sleep(1000);
						flg[0] = false;
						System.out.println("END ....");
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}

				private void dummyInput(final OutputStream out)
						throws IOException {
					for (int i = 0; i < 5; i++) {
						out.write("D0000*".getBytes());
						out.flush();
					}
				}
			};

			Runnable reader = new Runnable() {
				StringBuilder stb = new StringBuilder();

				@Override
				public void run() {
					try {
						while (flg[0]) {
							byte[] data = queue.take();
							stb.append(new String(data));
							for (int i = stb.indexOf("*"); -1 < i;) {
								processCmd(stb.substring(0, i + 1));
								stb.delete(0, i + 1);
								i = stb.indexOf("*");
							}
						}
					} catch (InterruptedException e) {
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				protected void processCmd(String cmd) {
					System.out.println(cmd);
					assertEquals(cmd.charAt(cmd.length() - 1), '*');
					assertTrue(cmd.charAt(0) != '!');
				}

			};

			Thread wt = new Thread(writer);
			wt.setName("testReadQueue#WriterThread");
			final Thread rt = new Thread(reader);
			rt.setName("testReadQueue#ReaderThread");
			rt.start();
			wt.start();

			Runnable interruptor = new Runnable() {
				@Override
				public void run() {
					while (BEGIN[0] < 1L
							|| (System.currentTimeMillis() - BEGIN[0]) < 8000) {

					}
					rt.interrupt();
				}
			};
			Thread it = new Thread(interruptor);
			it.setName("testReadQueue#InterruptorThread");
			it.start();

			wt.join();
			rt.join();
			it.join();
			port.notifyOnDataAvailable(false);
			port.removeEventListener();
		} finally {
			CommPortUtil.close(port);
		}
	}
}

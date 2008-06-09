package werkzeugkasten.gainer.cmd;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Formatter;

import werkzeugkasten.gainer.conf.ConfigType;
import werkzeugkasten.gainer.conf.Configuration;
import werkzeugkasten.gainer.util.CommPortUtil;
import werkzeugkasten.gainer.util.ImageUtil;

public class CommandFactory {

	public static final Command QUIT = new StaticCommand("Q*");

	public static final Command VERSION = new StaticCommand("?*");

	public static final Command ONBOARD_LED_ON = new StaticCommand("h*");
	public static final Command ONBOARD_LED_OFF = new StaticCommand("l*");

	public static final Command BEGIN_CONTINUOUS_DIGITAL_INPUT = new StaticCommand(
			"r*");

	public static final Command BEGIN_CONTINUOUS_ANALOG_INPUT = new StaticCommand(
			"i*");

	public static final Command EXIT_CONTINUOUS_MODE = new StaticCommand("E*");

	public static final Command ANALOG_SAMPLING_ALL = new StaticCommand("M0*");

	public static final Command ANALOG_SAMPLING_SINGLE = new StaticCommand(
			"M1*");

	/* ---- DIGITAL OUTPUTS --- */

	/**
	 * 全てのチャンネルの状態を一度に変化させる。<br/> 適用可能な値の範囲は、0x0000 - 0xFFFF<br/> <table
	 * border="1"> <tbody>
	 * <tr>
	 * <td>チャンネル</td>
	 * <td>フラグ</td>
	 * <td>DOUT3</td>
	 * <td>DOUT2</td>
	 * <td>DOUT1</td>
	 * <td>DOUT0</td>
	 * </tr>
	 * <tr>
	 * <td>0x0000</td>
	 * <td>0000 0000 0000 0000</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x0001</td>
	 * <td>0000 0000 0000 0001</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>H0*と同等</td>
	 * </tr>
	 * <tr>
	 * <td>0x0002</td>
	 * <td>0000 0000 0000 0010</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x0003</td>
	 * <td>0000 0000 0000 0011</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * </tr>
	 * <tr>
	 * <td>0x0004</td>
	 * <td>0000 0000 0000 0100</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x0005</td>
	 * <td>0000 0000 0000 0101</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * </tr>
	 * <tr>
	 * <td>0x0006</td>
	 * <td>0000 0000 0000 0110</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x0007</td>
	 * <td>0000 0000 0000 0111</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * </tr>
	 * <tr>
	 * <td>0x0008</td>
	 * <td>0000 0000 0000 1000</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x0009</td>
	 * <td>0000 0000 0000 1001</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * </tr>
	 * <tr>
	 * <td>0x000A</td>
	 * <td>0000 0000 0000 1010</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x000B</td>
	 * <td>0000 0000 0000 1011</td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * </tr>
	 * <tr>
	 * <td>0x000C</td>
	 * <td>0000 0000 0000 1100</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x000D</td>
	 * <td>0000 0000 0000 1101</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * <td>ON </td>
	 * </tr>
	 * <tr>
	 * <td>0x000E</td>
	 * <td>0000 0000 0000 1110</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>OFF</td>
	 * </tr>
	 * <tr>
	 * <td>0x000F</td>
	 * <td>0000 0000 0000 1111</td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * <td>ON </td>
	 * </tr>
	 * </tbody></table>
	 */
	public Command digitalOutput(final int channels) {
		return new ValidatableCommand(new StaticCommand(String.format(
				"D%1$04X*", channels))) {
			@Override
			protected void validate(Configuration conf) {
				if (channels < 0 || 0xFFFF < channels) {
					throw new IllegalArgumentException(
							"cannels must be unsigned value and under 0x10000");
				}
			}
		};
	}

	public Command digitalOutput(boolean... values) {
		if (values == null) {
			throw new IllegalArgumentException("values must not be null.");
		}
		int channels = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i]) {
				channels |= (1 << i);
			}
		}
		return digitalOutput(channels);
	}

	public Command setHigh(final int channelNo) {
		return new ValidatableCommand(new StaticCommand(String.format("H%1$X*",
				channelNo))) {
			@Override
			protected void validate(Configuration conf) {
				validateDoutRange(channelNo, conf);
			}
		};
	}

	protected void validateOutRange(int channelNo, int out) {
		if (channelNo < 0 && out < channelNo) {
			throw new IllegalArgumentException(
					"cannelNo must be unsigned value and under " + out
							+ " current value is " + channelNo);
		}
	}

	protected void validateDoutRange(int channelNo, Configuration conf) {
		validateOutRange(channelNo, conf.type().dout());
	}

	public Command setLow(final int channelNo) {
		return new ValidatableCommand(new StaticCommand(String.format("L%1$X*",
				channelNo))) {
			@Override
			protected void validate(Configuration conf) {
				validateDoutRange(channelNo, conf);
			}
		};
	}

	protected void validateAoutRange(int channelNo, Configuration conf) {
		validateOutRange(channelNo, conf.type().aout());
	}

	protected int round(int value, int max) {
		return value < 0 ? 0 : max < value ? max : value;
	}

	protected int roundFF(int value) {
		return round(value, 0xFF);
	}

	/* ---- ANALOG OUTPUTS --- */
	public Command analogOutput(final int channelNo, final int value) {
		return new ValidatableCommand(new Command() {
			@Override
			public void emit(Configuration conf, OutputStream out) {
				String str = String.format("A%1$X%2$02X*", channelNo,
						roundFF(value));
				CommPortUtil.emit(out, str.getBytes());
			}

		}) {
			@Override
			protected void validate(Configuration conf) {
				validateAoutRange(channelNo, conf);
			}
		};
	}

	protected void validateNull(int[] values) {
		if (values == null) {
			throw new IllegalArgumentException("values must not be null.");
		}
	}

	public Command analogOutput(final int... values) {
		return new ValidatableCommand(new Command() {
			@Override
			public void emit(Configuration conf, OutputStream out) {
				StringBuilder stb = new StringBuilder();
				stb.append("A");
				Formatter fmt = new Formatter(stb);
				int aout = conf.type().aout();
				for (int i = 0; i < aout; i++) {
					int value = 0;
					if (i < values.length) {
						value = roundFF(values[i]);
					}
					fmt.format("%1$02X", value);
				}
				stb.append("*");
				CommPortUtil.emit(out, stb.toString().getBytes());
			}

		}) {
			@Override
			protected void validate(Configuration conf) {
				validateNull(values);
				validateAoutRange(values.length, conf);
			}
		};
	}

	protected void scanLine(Configuration conf, OutputStream out, int lineNo,
			int... values) {
		StringBuilder stb = new StringBuilder();
		stb.append("a");
		Formatter fmt = new Formatter(stb);
		fmt.format("%1$X", lineNo);
		int aout = conf.type().aout();
		for (int i = 0; i < aout; i++) {
			int value = 0;
			if (i < values.length) {
				value = round(values[i], 0xF);
			}
			fmt.format("%1$X", value);
		}
		stb.append("*");
		CommPortUtil.emit(out, stb.toString().getBytes());
	}

	public Command scanLine(final int lineNo, final int... values) {
		return new ValidatableCommand(new Command() {
			@Override
			public void emit(Configuration conf, OutputStream out) {
				scanLine(conf, out, lineNo, values);
			}

		}) {
			@Override
			protected void validate(Configuration conf) {
				validateNull(values);
				validateAoutRange(lineNo, conf);
				validateAoutRange(values.length, conf);
				if (ConfigType.CONFIG7.equals(conf.type()) == false) {
					throw new IllegalStateException(
							"CONFIG7 (Matrix LED mode) only.");
				}
			}
		};
	}

	public Command scanMatrix(final int... values) {
		return new ValidatableCommand(new Command() {
			@Override
			public void emit(Configuration conf, OutputStream out) {
				for (int pos = 0, line = 0; pos < values.length;) {
					int[] ary = new int[8];
					for (int i = 0; i < 8; i++) {
						int v = values[pos++];
						ary[i] = 0xf < v ? (v % 0xf) : v;
					}
					scanLine(conf, out, line++, ary);
					if (8 < line) {
						line = 0;
					}
				}
			}
		}) {
			@Override
			protected void validate(Configuration conf) {
				validateNull(values);
				if (ConfigType.CONFIG7.equals(conf.type()) == false) {
					throw new IllegalStateException(
							"CONFIG7 (Matrix LED mode) only.");
				}
			}
		};
	}

	public Command scanMatrix(BufferedImage image) {
		return scanMatrix(ImageUtil.toArray(image));
	}

	public Command ampGainAGND(final int gain) {
		return new ValidatableCommand(new StaticCommand(String.format("G%1$X1",
				gain))) {
			@Override
			protected void validate(Configuration conf) {
				if (gain < 1 || 0xF < gain) {
					throw new IllegalArgumentException(
							"gain must be 1 or more and 0xf or less");
				}
			}
		};
	}

	public Command ampGainDGND(final int gain) {
		return new ValidatableCommand(new StaticCommand(String.format("G%1$X0",
				gain))) {
			@Override
			protected void validate(Configuration conf) {
				if (gain < 1 || 0xF < gain) {
					throw new IllegalArgumentException(
							"gain must be 1 or more and 0xf or less");
				}
			}
		};
	}

	public Command setSensitivity() {
		return new ValidatableCommand(new StaticCommand("Tx*")) {
			@Override
			protected void validate(Configuration conf) {
				if (ConfigType.CONFIG8.equals(conf.type()) == false) {
					throw new IllegalStateException(
							"CONFIG8 (capacitive sensing switch mode) only.");
				}
			}
		};
	}
}

package werkzeugkasten.gainer.conf;


/**
 * <table border="1"> <thead align="center">
 * <tr>
 * <td>Configuration</td>
 * <td>AIN</td>
 * <td>DIN</td>
 * <td>AOUT</td>
 * <td>DOUT</td>
 * </tr>
 * </thead> <tbody align="center">
 * <tr>
 * <td>1(default configuration)</td>
 * <td>4</td>
 * <td>4</td>
 * <td>4</td>
 * <td>4</td>
 * <tr>
 * <tr>
 * <td>2</td>
 * <td>8</td>
 * <td>0</td>
 * <td>4</td>
 * <td>4</td>
 * <tr>
 * <tr>
 * <td>3</td>
 * <td>4</td>
 * <td>4</td>
 * <td>8</td>
 * <td>0</td>
 * <tr>
 * <tr>
 * <td>4</td>
 * <td>8</td>
 * <td>0</td>
 * <td>8</td>
 * <td>0</td>
 * <tr>
 * <tr>
 * <td>5</td>
 * <td>0</td>
 * <td>16</td>
 * <td>0</td>
 * <td>0</td>
 * <tr>
 * <tr>
 * <td>6</td>
 * <td>0</td>
 * <td>0</td>
 * <td>0</td>
 * <td>16</td>
 * <tr>
 * <tr>
 * <td>7(matrix LED control mode)</td>
 * <td>0</td>
 * <td>8</td>
 * <td>8</td>
 * <td>0</td>
 * <tr>
 * <tr>
 * <td>8(capacitive sensing switch mode (first 4 ports only))</td>
 * <td>0</td>
 * <td>8</td>
 * <td>0</td>
 * <td>8</td>
 * <tr> </tbody>
 * 
 * @author taichi
 */
public enum ConfigType {

	/**
	 * default configuration<br/> <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>4</td>
	 * <td>4</td>
	 * <td>4</td>
	 * <td>4</td>
	 * <tr> </tbody>
	 */
	CONFIG1("1", 4, 4, 4, 4),
	/**
	 * <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>8</td>
	 * <td>0</td>
	 * <td>4</td>
	 * <td>4</td>
	 * <tr> </tbody>
	 */
	CONFIG2("2", 8, 0, 4, 4),
	/**
	 * <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>4</td>
	 * <td>4</td>
	 * <td>8</td>
	 * <td>0</td>
	 * <tr> </tbody>
	 */
	CONFIG3("3", 4, 4, 8, 0),
	/**
	 * <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>8</td>
	 * <td>0</td>
	 * <td>8</td>
	 * <td>0</td>
	 * <tr> </tbody>
	 */
	CONFIG4("4", 8, 0, 8, 0),
	/**
	 * <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>0</td>
	 * <td>16</td>
	 * <td>0</td>
	 * <td>0</td>
	 * <tr> </tbody>
	 */
	CONFIG5("5", 0, 16, 0, 0),
	/**
	 * <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>0</td>
	 * <td>0</td>
	 * <td>0</td>
	 * <td>16</td>
	 * <tr> </tbody>
	 */
	CONFIG6("6", 0, 0, 0, 16),
	/**
	 * matrix LED control mode<br/> <table border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>0</td>
	 * <td>8</td>
	 * <td>8</td>
	 * <td>0</td>
	 * <tr> </tbody>
	 */
	CONFIG7("7", 0, 8, 8, 0),
	/**
	 * capacitive sensing switch mode (first 4 ports only) <br/> <table
	 * border="1"> <thead align="center">
	 * <tr>
	 * <td>AIN</td>
	 * <td>DIN</td>
	 * <td>AOUT</td>
	 * <td>DOUT</td>
	 * </tr>
	 * </thead> <tbody align="center">
	 * <tr>
	 * <td>0</td>
	 * <td>8</td>
	 * <td>0</td>
	 * <td>8</td>
	 * <tr> </tbody>
	 */
	CONFIG8("8", 0, 8, 0, 8), ;

	private byte[] cmmand;
	private int ain;
	private int din;
	private int aout;
	private int dout;

	private ConfigType(String no, int ain, int din, int aout, int dout) {
		this.cmmand = ("KONFIGURATION_" + no + "*").getBytes();
		this.ain = ain;
		this.din = din;
		this.aout = aout;
		this.dout = dout;
	}

	public byte[] command() {
		return this.cmmand;
	}

	public int ain() {
		return ain;
	}

	public int din() {
		return din;
	}

	public int aout() {
		return aout;
	}

	public int dout() {
		return dout;
	}
}

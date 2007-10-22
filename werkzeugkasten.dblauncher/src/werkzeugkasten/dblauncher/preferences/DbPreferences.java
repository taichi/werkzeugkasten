package werkzeugkasten.dblauncher.preferences;

/**
 * @author taichi
 * 
 */
public interface DbPreferences {

	String getBaseDir();

	void setBaseDir(String path);

	String getDbPortNo();

	void setDbPortNo(String no);

	String getWebPortNo();

	void setWebPortNo(String no);

	void setDebug(boolean is);

	boolean isDebug();

	String getUser();

	void setUser(String user);

	String getPassword();

	void setPassword(String pass);

}

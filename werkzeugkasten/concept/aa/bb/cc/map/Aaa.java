package aa.bb.cc.map;

import java.util.Map;

import com.google.werkzeugkasten.core.map.MapAdaptor;

@MapAdaptor
public class Aaa {

	private int bbb;

	private String ccc;

	private Map<String, String> ddd;

	public int getBbb() {
		return bbb;
	}

	public void setBbb(int bbb) {
		this.bbb = bbb;
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public Map<String, String> getDdd() {
		return ddd;
	}

	public void setDdd(Map<String, String> ddd) {
		this.ddd = ddd;
	}

}

package aa.bb.cc.web.hoge.autogen;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.bb.cc.web.hoge.HogeController;
import aa.bb.cc.web.hoge.HogeViewModel;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.core.web.WebContext;
import com.google.werkzeugkasten.core.web.servlet.RequestAttributeScope;
import com.google.werkzeugkasten.core.web.servlet.RequestParameterScope;
import com.google.werkzeugkasten.core.web.servlet.ServletWebContext;
import com.google.werkzeugkasten.core.web.servlet.SessionAttributeScope;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public class HogeControllerContext extends ServletWebContext<_> implements
		HogeViewModel {

	protected RequestAttributeScope requestAttributeScope;
	protected RequestParameterScope requestParameterScope;
	protected SessionAttributeScope sessionAttributeScope;

	@Initialize
	void initialize(HogeController controller) {
		this.requestAttributeScope = new RequestAttributeScope();
		this.requestParameterScope = new RequestParameterScope();
		this.sessionAttributeScope = new SessionAttributeScope();
	}

	public int getId() {
		String key = "id";
		Object o = this.requestParameterScope.get(this, key);
		if (o == null) {
			o = this.requestAttributeScope.get(this, key);
		}
		if (o instanceof Integer) {
			return (Integer) o;
		}
		if (o instanceof Number) {
			return ((Number) o).intValue();
		}
		if (o instanceof String) {
			return Integer.parseInt((String) o);
		}
		return 0;
	}

	public void setId(int id) {
		String key = "id";
		this.sessionAttributeScope.set(this, key, id);
	}

	public String getName() {
		String key = "name";
		Object o = this.requestAttributeScope.get(this, key);
		if (o instanceof String) {
			return (String) o;
		} else if (o != null) {
			return o.toString();
		}
		return null;
	}

	public void setName(String name) {
		String key = "name";
		this.requestAttributeScope.set(this, key, name);
	}

	public <CTX extends WebContext<ServletContext, HttpServletRequest, HttpServletResponse, _>> void validate(
			CTX context) {

	}

}

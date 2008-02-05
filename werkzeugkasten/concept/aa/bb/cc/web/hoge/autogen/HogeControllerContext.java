package aa.bb.cc.web.hoge.autogen;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.bb.cc.web.hoge.HogeController;
import aa.bb.cc.web.hoge.HogeViewModel;

import com.google.werkzeugkasten.core.web.WebContext;
import com.google.werkzeugkasten.core.web.servlet.RequestAttributeScope;
import com.google.werkzeugkasten.core.web.servlet.RequestParameterScope;
import com.google.werkzeugkasten.core.web.servlet.ServletWebContext;
import com.google.werkzeugkasten.core.web.servlet.SessionAttributeScope;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public class HogeControllerContext extends ServletWebContext implements
		HogeViewModel {

	protected HogeController controller;
	protected RequestAttributeScope<HogeControllerContext> requestAttributeScope;
	protected RequestParameterScope<HogeControllerContext> requestParameterScope;
	protected SessionAttributeScope<HogeControllerContext> sessionAttributeScope;

	@Initialize
	void initialize(HogeController controller) {
		this.controller = controller;
		this.requestAttributeScope = new RequestAttributeScope<HogeControllerContext>();
		this.requestParameterScope = new RequestParameterScope<HogeControllerContext>();
		this.sessionAttributeScope = new SessionAttributeScope<HogeControllerContext>();
	}

	public int getId() {
		return 0;
	}

	public void setId(int id) {

	}

	public <CTX extends WebContext<ServletContext, HttpServletRequest, HttpServletResponse>> void validate(
			CTX context) {

	}

}

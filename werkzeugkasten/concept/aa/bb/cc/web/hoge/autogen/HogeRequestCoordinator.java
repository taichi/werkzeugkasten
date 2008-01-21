package aa.bb.cc.web.hoge.autogen;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.bb.cc.web.hoge.HogeController;

import com.google.werkzeugkasten.core.web.Action;
import com.google.werkzeugkasten.core.web.WebContext;
import com.google.werkzeugkasten.core.web.servlet.ServletRequestCoordinator;

public class HogeRequestCoordinator implements
		ServletRequestCoordinator<HogeController> {

	public void initialize(HogeController controller) {
		// TODO Auto-generated method stub

	}

	public Action<ServletContext, HttpServletRequest, HttpServletResponse> execute(
			WebContext<ServletContext, HttpServletRequest, HttpServletResponse> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}

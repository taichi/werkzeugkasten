package aa.bb.cc.web.hoge;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.bb.cc.web.hoge.autogen.HogeControllerContext;

import com.google.werkzeugkasten.core.web.ActivateUrl;
import com.google.werkzeugkasten.core.web.WebController;
import com.google.werkzeugkasten.core.web.Welcome;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedFinder;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedRenderer;

@WebController(context = HogeControllerContext.class)
public class HogeController {

	@ActivateUrl("/hoge/get")
	public String get(HogeViewModel model) {
		return null;
	}

	@ActivateUrl("/hoge/edit")
	public void edit(Map<String, String[]> params) {

	}

	@ActivateUrl("/hoge/download")
	public OutputStream download(HttpServletRequest request) {
		return null;
	}

	@ActivateUrl("/hoge/.*")
	@Welcome
	public ServletBasedRenderer index(HttpServletRequest request,
			HttpServletResponse response, ServletBasedFinder finder) {
		return null;
	}

}

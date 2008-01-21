package aa.bb.cc.web.hoge;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ActivateUrl;
import com.google.werkzeugkasten.core.web.WebController;
import com.google.werkzeugkasten.core.web.Welcome;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedFinder;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedRenderer;
import com.google.werkzeugkasten.core.web.servlet.ServletWebContext;

@WebController
public class HogeController {

	@ActivateUrl("/hoge/get")
	public String get(ServletWebContext<String> ctx, ServletBasedFinder finder) {
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
			HttpServletResponse response) {
		return null;
	}

}

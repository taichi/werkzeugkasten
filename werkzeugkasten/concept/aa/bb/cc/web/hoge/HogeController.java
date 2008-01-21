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

@WebController(context = ServletWebContext.class)
public class HogeController {

	@ActivateUrl("/hoge/get")
	public String get(ServletWebContext ctx,
			ServletBasedFinder<ServletWebContext> finder) {
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
	public ServletBasedRenderer<ServletWebContext> index(
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}

package aa.bb.cc.web.hoge;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ActivateUrl;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedFinder;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedRenderer;
import com.google.werkzeugkasten.core.web.servlet.ServletWebContext;

public interface HogeController {

	@ActivateUrl("/hoge/get")
	String get(ServletWebContext<String> ctx, ServletBasedFinder finder);

	@ActivateUrl("/hoge/edit")
	void edit(Map<String, String[]> params);

	@ActivateUrl("/hoge/download")
	OutputStream download(HttpServletRequest request);

	@ActivateUrl("/hoge/.*")
	ServletBasedRenderer index(HttpServletRequest request,
			HttpServletResponse response);

}

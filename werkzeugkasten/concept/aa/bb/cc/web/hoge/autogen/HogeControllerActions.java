package aa.bb.cc.web.hoge.autogen;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.bb.cc.web.hoge.HogeController;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.core.web.Renderer;
import com.google.werkzeugkasten.core.web.WebContext;
import com.google.werkzeugkasten.core.web.servlet.ServletAction;
import com.google.werkzeugkasten.core.web.servlet.ServletActionRegistry;
import com.google.werkzeugkasten.core.web.servlet.ServletBasedRenderer;
import com.google.werkzeugkasten.core.web.servlet.ServletRequestPathMatcher.RegEx;

;

public class HogeControllerActions {

	protected HogeController controller = new HogeController();

	public void initialize(ServletActionRegistry registry) {
		registry.add(new String$get$HogeViewModel());
	}

	class String$get$HogeViewModel extends ServletAction {

		protected String$get$HogeViewModel() {
			super();
			matchers.add(new RegEx("/hoge/get"));
			matchers.add(TRUE);
		}

		public <CTX extends WebContext<ServletContext, HttpServletRequest, HttpServletResponse, _>> Renderer<ServletContext, HttpServletRequest, HttpServletResponse> execute(
				CTX context) {
			HogeControllerContext ctx = new HogeControllerContext();
			final String result = controller.get(ctx);
			return new ServletBasedRenderer() {
				public <CTX2 extends WebContext<ServletContext, HttpServletRequest, HttpServletResponse, _>> void render(
						CTX2 context) {
					try {
						context.getResponse().getWriter().println(result);
					} catch (Exception e) {

					}
				}
			};
		}

	}
}

package aa.bb.cc.web.hoge.autogen;

import aa.bb.cc.web.hoge.HogeController;

import com.google.werkzeugkasten.core.web.servlet.ServletBasedRenderer;
import com.google.werkzeugkasten.core.web.servlet.ServletWebContext;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public class HogeControllerContext extends ServletWebContext<ServletBasedRenderer> {

	protected HogeController controller;

	@Initialize
	void initialize(HogeController controller) {
		this.controller = controller;
	}
}

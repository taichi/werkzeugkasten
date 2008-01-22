package aa.bb.cc.web.hoge;

import com.google.werkzeugkasten.core.web.Scope;
import com.google.werkzeugkasten.core.web.servlet.RequestAttributeScope;
import com.google.werkzeugkasten.core.web.servlet.RequestParameterScope;
import com.google.werkzeugkasten.core.web.servlet.SessionAttributeScope;

@Scope(RequestAttributeScope.class)
public interface HogeViewModel {

	@Scope( { RequestParameterScope.class, SessionAttributeScope.class })
	int getId();

	@Scope(SessionAttributeScope.class)
	void setId(int id);
}

package aa.bb.cc.web.hoge;

import com.google.werkzeugkasten.core.web.servlet.RequestAttributeScope;
import com.google.werkzeugkasten.core.web.servlet.RequestParameterScope;
import com.google.werkzeugkasten.core.web.servlet.ServletViewModel;
import com.google.werkzeugkasten.core.web.servlet.SessionAttributeScope;
import com.google.werkzeugkasten.meta.Scope;

@Scope(RequestAttributeScope.class)
public interface HogeViewModel extends ServletViewModel {

	@Scope( { RequestParameterScope.class, SessionAttributeScope.class })
	int getId();

	@Scope(SessionAttributeScope.class)
	void setId(int id);

	// String なら変換かからない。
	String getName();

	void setName(String name);
}

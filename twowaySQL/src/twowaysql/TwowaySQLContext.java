package twowaysql;

import java.util.List;


public interface TwowaySQLContext {

	Object getContextObject();

	void setContextObject(Object object);

	void append(CharSequence txt);

	String getQuery();

	void addBinder(BindingHandler handler);

	List<BindingHandler> getBinders();

	void append(TwowaySQLContext context);

	void setConditionState(boolean is);

	boolean getConditionState();
}

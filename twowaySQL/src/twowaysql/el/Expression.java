package twowaysql.el;


public interface Expression {

	String getSource();

	Object invoke(Object context);
}

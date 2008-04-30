package twowaysql.model;

public interface ConditionalNode extends Node {

	void add(ConditionalNode next);
}

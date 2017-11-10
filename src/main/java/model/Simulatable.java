package model;

public interface Simulatable<T extends Simulatable<T>> {
	
	T play();
	T applyConditions(Rule<? extends Simulatable<T>> ...rule);
	T progress();
	T currentConditions();
	T conditionOf(Integer x, Integer y);
	
}

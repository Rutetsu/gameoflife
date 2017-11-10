package model;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Rule<T extends Simulatable<T>> {
	
	private T simulatable;
	@NonNull private Predicate<? super T> condition;
	@NonNull private BiFunction<T, Predicate<? super T>, T> mapper;
	
	void testCondition(){
		if (condition.test(simulatable.currentConditions())) {
			mapper.apply(simulatable, condition);
			
		}
	}

}

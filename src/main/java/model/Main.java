package model;

import java.util.function.Predicate;

public class Main {
	
	
	public static void main(String[] args) {
		Game g = new Game();
		Simulatable ofLife = new GameOfLife(5);
		Predicate<Environment> boardPredicate = b -> b.getBoard().contains(false);
		
		
//		Rule rule = new Rule(ofLife, boardPredicate);
		
		g.simulate(ofLife);
		
	}
}


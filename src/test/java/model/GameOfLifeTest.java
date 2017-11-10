package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class GameOfLifeTest {
	
	private Environment environment;
	private GameOfLife game;
	private static final int BOARD_SIZE = 5;
	
	@BeforeEach
	void init() {
		environment = new Environment(BOARD_SIZE);
		game = new GameOfLife(BOARD_SIZE);
		
	}
	
	@Test
	@DisplayName("Should set conditions for environment after .play()")
	void testingPlayMethod() {
		GameOfLife lifeSpy = Mockito.spy(game);
		lifeSpy.play();
		verify(lifeSpy).play();
		assertTrue(lifeSpy.isStarted());
		
		assertTrue(lifeSpy
				.getEnvironment()
				.getBoard()
				.stream()
				.map(row -> row
						.stream()
						.filter(cell -> cell == Cell.LIVING)
						.count())
				.count() > 3);
		
	}
	
	
	/*
	@Test
	@DisplayName("Apply conditions should properly add to ruleSet")
	void addingToRuleSetTest() {
		GameOfLife lifeSpy = Mockito.spy(game);
		*//*Rule<GameOfLife> rule = new Rule<>(lifeSpy,
				condition -> Objects.equals(condition.currentConditions(), 0),
				simulatable -> Environment.setCell(simulatable.currentConditions().getEnvironment().getBoard(), 1, 1, Cell.DEAD));
		lifeSpy.play();*//*
		lifeSpy.applyConditions(rule);
		
		verify(lifeSpy).play();
		verify(lifeSpy).applyConditions(rule);
		
		assertEquals(1, lifeSpy.getRuleSet().size());
		assertTrue(lifeSpy.getRuleSet().contains(rule));
		
	}
	*/
	
	/*@Test
	@DisplayName("Progress should throw an error if ruleSet is empty")
	void shouldThrowEmptyConditionsSet() {
		
		game.play();
		assertThrows(EmptyStackException.class, () -> game.progress());
		
	}*/
/*
	@Test
	@DisplayName("Conditions are applied to environment")
	void checkConditionsTest() {
		Predicate<GameOfLife> isStarted = GameOfLife::isStarted;
		Rule<GameOfLife> ofLifeRule = new Rule<>(game, isStarted, (gameOfLife, predicate) -> gameOfLife);
		game
				.play()
				.applyConditions(ofLifeRule)
				.progress();
		assertTrue(game.getEnvironment().getBoard().get(0).get(0) == Cell.LIVING);
		
	}*/
	@Test
	@DisplayName("A cell will live if it has three living neighbors")
	void conditionOfThreeLivingNeighborTest() {
		game.play();
		GameOfLife gol = mock(GameOfLife.class);
		Environment env = mock(Environment.class);
		Environment gameEnvironment = gol.getEnvironment();
		when(gol.play()).then(env.setCell(gameEnvironment.getBoard(), 1, 1, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 1, 2, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 1, 3, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 2, 1, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 2, 3, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 3, 1, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 3, 2, Cell.LIVING);
		gameEnvironment.setCell(gameEnvironment.getBoard(), 3, 3, Cell.LIVING);
		game.progress();
		assertEquals(Cell.LIVING, gameEnvironment.getBoard().get(0).get(3));
		assertEquals(Cell.DEAD, gameEnvironment.getBoard().get(0).get(4));
		assertEquals(Cell.DEAD, gameEnvironment.getBoard().get(1).get(2));
		assertEquals(Cell.DEAD, gameEnvironment.getBoard().get(1).get(3));
		game.progress();
		assertEquals(Cell.LIVING, gameEnvironment.getBoard().get(0).get(3));
		assertEquals(Cell.LIVING, gameEnvironment.getBoard().get(1).get(3));
		assertEquals(Cell.DEAD, gameEnvironment.getBoard().get(1).get(0));
		assertEquals(Cell.DEAD, gameEnvironment.getBoard().get(1).get(4));
//		BiFunction<GameOfLife, Integer, Boolean> isValidX = (g, x) -> x > 0 && x < g.getEnvironment().getBoard().size();
//		BiFunction<GameOfLife, Integer, Boolean> isValidY = (g, y) -> y > 0 && y < g.getEnvironment().getBoard().get(0).size();
		
	
		
		
		
		
		
		
	}
		
		
}

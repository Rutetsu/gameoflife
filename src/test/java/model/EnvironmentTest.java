package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EnvironmentTest {
	
	private List<List<Cell>> board;
	private Environment gameEnvironment;
	private static final int BOARD_SIZE = 5;
	private Simulator simulator;
	private GameOfLife ofLife;
	
	@BeforeEach
	void init() {
		Cell[] row = new Cell[BOARD_SIZE];
		Arrays.fill(row, Cell.DEAD);
		board = new ArrayList<>(BOARD_SIZE);
		for (int i = 0; i < BOARD_SIZE; i++) {
			board.add(Arrays.asList(row));
		}
		gameEnvironment = new Environment(BOARD_SIZE);
		ofLife = new GameOfLife(BOARD_SIZE);
		simulator = new Game();
	}
	
	@Test
	@DisplayName("Given a environment size y, should create a y x y sized environment")
	void createBoardTest() {
//		simulator.simulate(ofLife);
		assertEquals(board.size(), gameEnvironment.getBoard().size());
		for (int i = 0; i < BOARD_SIZE - 1; i++) {
			assertEquals(board.get(i).size(), gameEnvironment.getBoard().get(i).size());
		}
	}
	
	@Test
	@DisplayName("Environment should be initialized with all Cell.DEAD")
	void falseBoard() {
		assertIterableEquals(board, gameEnvironment.getBoard());
	}
	
	
	//TODO move to other class (Game)
	@Test
	@DisplayName("Game should begin simulation")
	void wasSimulateAccessed() {
		Simulator gameSpy = Mockito.spy(simulator);
		gameSpy.simulate(ofLife);
		Mockito.verify(gameSpy).simulate(ofLife);
		assertTrue(ofLife.isStarted());
	}
	
	
	@Test
	@RepeatedTest(20)
	@DisplayName("Begin creates random cells on the environment")
	void beginSetsTrueValuesInCell(){
		
		gameEnvironment.begin();
		for (List<Cell> row: gameEnvironment.getBoard()) {
			assertTrue(row.stream().filter(cell -> cell == Cell.LIVING).count() > 1);
		}
	}
	
	@Test
	@DisplayName("Environment sets correct value")
	void settingCellsTest(){
		gameEnvironment.begin();
		gameEnvironment.setCell(gameEnvironment.getBoard(),1, 2, Cell.LIVING);
		assertTrue(gameEnvironment.getBoard().get(1).get(2) == Cell.LIVING);
	}
	
	
}
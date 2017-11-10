package model;


import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Environment {
	@Getter
	private List<List<Cell>> board;
	
	public Environment(int boardSize) {
		Cell[] row = new Cell[boardSize];
		Arrays.fill(row, Cell.DEAD);
		board = new ArrayList<>(boardSize);
		for (int i = 0; i < boardSize; i++) {
			board.add(Arrays.asList(row));
		}
	}
	
	public List<List<Cell>> begin() {
		Random r = new Random();
		
		board = board
				.stream()
				.map(
					rows -> rows
							.stream()
							.map(cell -> coinFlip(r) ? Cell.LIVING : Cell.DEAD)
											.collect(toList()))
				.collect(toList());
		
		return board;
		
	}
	
	private boolean coinFlip(Random r) {
		return r.nextInt(100) > 50;
	}
	
	void setCell(List<List<Cell>> gameBoard, int x, int y, Cell cell){
		gameBoard.get(x).set(y, cell);
		this.board = gameBoard;
	}
	
	boolean check(int x, int y, Predicate<Cell> condition){
		return condition.test(this.getBoard().get(x).get(y));
	}
	
	
		
	
	
	
}

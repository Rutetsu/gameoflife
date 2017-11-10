package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GameOfLife implements Simulatable<GameOfLife> {
	@Getter private Environment environment;
	@Getter private Set<Rule> ruleSet = new HashSet<>(0);
	@NonNull private int boardSize;
	@Getter private boolean isStarted = false;
	
	
	@Override
	public GameOfLife play() {
		environment = new Environment(boardSize);
		isStarted = true;
		environment.begin();
		return this;
	}
	
	
	@Override
	public GameOfLife applyConditions(Rule... rule) {
		ruleSet.addAll(Arrays.asList(rule));
		return this;
	}
	
	@Override
	public GameOfLife progress() {
		/*if (ruleSet.isEmpty()) {
			throw new EmptyStackException();
		}
		ruleSet.forEach(Rule::testCondition);
		return this;*/
		/*System.out.println("Progressing...");
		IntStream.range(0, this.boardSize)
				.forEach(x -> IntStream.range(0, this.boardSize)
						.forEach(y -> conditionOf(x, y)));*/
		this.getEnvironment().getBoard().forEach(System.out::println);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
					conditionOf(i, j);
				}
			}
		return this;
	}
	
	@Override
	public GameOfLife currentConditions() {
		return this;
	}
	
	@Override
	public GameOfLife conditionOf(Integer x, Integer y) {
		checkCell(x, y);
		return this;
	}
	
	void checkCell(int x, int y) {
		List<Integer> xCoords = filterBoundry(x);
		List<Integer> yCoords = filterBoundry(y);
		int count = 0;
		for (int i = 0; i < xCoords.size(); i++) {
			for (int j = 0; j < yCoords.size(); j++) {
				if (this.getEnvironment().getBoard().get(i).get(j) == Cell.LIVING) {
					count++;
				}
			}
		}
		System.out.println(count);
		hasNeighbors(x, y, count);
		
	}
	
	private void hasNeighbors(int x, int y, int count) {
		if (count >= 2) {
			environment.setCell(this.getEnvironment().getBoard(), x, y, Cell.LIVING);
		}
		else {
			environment.setCell(this.getEnvironment().getBoard(), x, y, Cell.DEAD);
		}
	}
	
	
	List<Integer> filterBoundry(int i) {
		List<Integer> validSpots = new ArrayList<>();
		
		if (i == 0) {
			validSpots.add(0);
			validSpots.add(1);
			return validSpots;
		}
		if (i == this.getEnvironment().getBoard().size()) {
			validSpots.add(-1);
			validSpots.add(0);
			return validSpots;
		}
		if (i > this.getEnvironment().getBoard().size() || i < 0) {
			throw new IllegalArgumentException("out of boundry check");
		}
		validSpots.add(-1);
		validSpots.add(0);
		validSpots.add(1);
		return validSpots;
		
		
	}
}

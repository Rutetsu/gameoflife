package model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Game implements Simulator {
	
	@Override
	public void simulate(Simulatable simulatable) {
		simulatable.play();
		
	}
}

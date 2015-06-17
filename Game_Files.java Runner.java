package Game;

import java.awt.Color;

public class Runner extends Zombie{
	
	  
	Runner(){
		super();
		color = Color.ORANGE;
		speed = Math.random() * 0.2 + 0.35;
		health = 2;
		width = 7;
		height = 7;
	}
}

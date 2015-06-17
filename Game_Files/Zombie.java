package Game;

import java.awt.Color;
import java.awt.Rectangle;

public class Zombie extends Rectangle {
		
	//INT x and y are used for the rectangles position on the screen
	//double xx and yy are used for moving, so we can move at less than 1 pixel/cycle
	double yy, xx;
	Color color = Color.GREEN.brighter();
	boolean dead = false;
	int health = 4;
	int damage = 2;
	double speed = Math.random() * 0.2;
	
	Zombie () {
		this.width = 10;
		this.height = 10;
	}
	
}




package Graphics;

import java.awt.Color;
import java.awt.Rectangle;

//this class must be moved to a separate file
class Ball extends Rectangle {		
	
	//final static Color[] colourList  = {Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN,  Color.WHITE, Color.BLUE,  Color.MAGENTA };
	
	double xspeed =Math.random()*15;
	double yspeed = Math.random()*15;
	
	double bx, by;
	Color colour;
	
	Ball() {
		bx = Math.random()*1750;
		by = Math.random()*1750;
	//	bx = Math.random()*5;
	//	by = Math.random()*5;
		this.width = (int)(Math.random()*30) +10;
		this.height =  this.width;
	//	int n = (int)(Math.random() * colourList.length);
		//this.colour = colourList[n];
		this.colour =  ColourRainbow.getNextColour();
	}		
}

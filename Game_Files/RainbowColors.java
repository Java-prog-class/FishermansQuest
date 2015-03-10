package Graphics;

import java.awt.Color;

public class ColourRainbow {
	
	static int red=255, green=0, blue=0;
	static int stepSize = 5;
	static Color getNextColour() {

		Color newColour = new Color(red,green%256,blue); 
		//red to orange
		//red stays at 100%
		//add in green
		if (red == 255 && green < 255 && blue == 0) {
			green += stepSize;
			if (green > 255) green = 255;
		}
		else if (red > 0 && green == 255 && blue == 0) {
			red -= stepSize;
			if (red < 0) {
				red = 0;
			}
		}
		//green to blue
		//green goes from 255 to 0
		//add in blue
		else if ( green == 255 && blue < 255) {
			blue +=stepSize;
			if (blue > 255) blue = 255;
		}
		else if (green > 0 && blue == 255) {
			green -= stepSize;
			if (green < 0) {
				green = 0;
			}
		}
		else if (blue == 255 && red < 255) {
			red += stepSize;
			if (red > 255) red = 255; 
		}
		else if (blue > 0 && red == 255 && green == 0) {
			blue -= stepSize;
			if (blue < 0) {
				blue = 0;
			}	
		}
		
		return newColour;
	}
}

package Graphics;


import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class FlowExample {
	public static void main(String[] args) {

		new FlowExample();		
	}

	FlowExample() {

		JButton btn1, btn2, btn3, btn4, btn5;
		
		JFrame window = new JFrame("BoxLayout");
		window.setSize(500,500);
		window.setLocationRelativeTo(null); // Always sets it to the middle of the screen
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		btn1 = new JButton("Boom!");
		
		pnl1.add(btn1);
		
		for (int i=0; i < 20000; i++) {
			pnl1.add(new ColourLabel());
		}
	

		window.add(pnl1);// Last ever thing to do
		window.setVisible(true);

	}

	class ColourLabel extends JLabel {
		
		ColourLabel() {
			this.setText(" ");
			Color nextColour = ColourRainbow.getNextColour();
			this.setBackground(nextColour);
			this.setOpaque(true);
			this.setAlignmentX(0.5f);
		}
	}
}

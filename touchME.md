package Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class PaintProject implements ActionListener, MouseListener, MouseMotionListener {
	public static void main(String[] args) {	
		new PaintProject();
	}

	//global variables
	JButton cbtn1, cbtn2, cbtn3, cbtn4, cbtn5, cbtn6, cbtn7, pencil, eraser, clear;
	DrawingPanel drawingPanel;
	JColorChooser CC;
	boolean firstClick = true;
	int x1,x2,y1,y2;
	Color paintColour = Color.BLACK;
	String mode = "line";

	PaintProject() {

		//local variables
		x1 = x2 = y1 = y2 = -1;
		
		//frame and frame settings
		JFrame window = new JFrame("~Paint-A-Paint~");
		
		//Panels
		JPanel sidePanel = new JPanel(new MigLayout());
		sidePanel.setBackground(Color.RED);

		drawingPanel = new DrawingPanel();
		drawingPanel.setBackground(Color.white);
		drawingPanel.addMouseListener(this);
		drawingPanel.addMouseMotionListener(this);
		drawingPanel.requestFocus();

		//Color buttons
		cbtn1 = new JButton("Red");
		cbtn2 = new JButton("Blue");
		cbtn3 = new JButton("Green");
		cbtn4 = new JButton("Orange");
		cbtn5 = new JButton("Gray");
		cbtn6 = new JButton("Magenta");
		cbtn7 = new JButton("Cyan");

		//Tool buttons
		eraser = new JButton("Eraser");
		pencil = new JButton("Pencil");
		clear = new JButton("Clear");

		//adding ActionListeners to buttons
		cbtn1.addActionListener(this);
		cbtn2.addActionListener(this);
		cbtn3.addActionListener(this);
		cbtn4.addActionListener(this);
		cbtn5.addActionListener(this);
		cbtn6.addActionListener(this);
		cbtn7.addActionListener(this);
		pencil.addActionListener(this);
		clear.addActionListener(this);
		eraser.addActionListener(this);

		//add all buttons to side panels
		sidePanel.add(cbtn1, "wrap");
		sidePanel.add(cbtn2, "wrap");
		sidePanel.add(cbtn3, "wrap");
		sidePanel.add(cbtn4, "wrap");
		sidePanel.add(cbtn5, "wrap");
		sidePanel.add(cbtn6, "wrap");
		sidePanel.add(cbtn7, "wrap");
		sidePanel.add(eraser, "wrap");
		sidePanel.add(pencil, "wrap");
		sidePanel.add(clear, "wrap");

		window.add(sidePanel, BorderLayout.EAST);
		window.add(drawingPanel, BorderLayout.CENTER);
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	class DrawingPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			g.setColor(paintColour);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (mode.equals("line")) {
				if (x1 == -1) return;
				if (firstClick) {
					g.setColor(this.getBackground());
					g.fillOval(x1,y1,3,3);
					//draw line
					g.setColor(paintColour);
					g.drawLine(x1,y1,x2,y2);
				} else {
					g.fillOval(x1,y1,3,3);
				}
			}
			
			if (mode.equals("pencil")){
				g.drawLine(x1,y1,x2,y2);			
			}
			
			if (mode.equals("clear")) {
				g.setColor(this.getBackground());
				g.fillRect(0,0,this.getWidth(), this.getHeight());

				g.setColor(paintColour);
				mode = "line";		
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cbtn1)) {
			paintColour = Color.RED;
		}
		
		if (e.getSource().equals(cbtn2)) {
			paintColour = Color.blue;
		}
		
		if (e.getSource().equals(cbtn3)) {
			paintColour = Color.GREEN;
		}
		
		if (e.getSource().equals(cbtn4)) {
			paintColour = Color.ORANGE;	
		}
		
		if (e.getSource().equals(cbtn5)) {
			paintColour = Color.GRAY;	
		}
		
		if (e.getSource().equals(cbtn6)) {
			paintColour = Color.MAGENTA;	
		}
		
		if (e.getSource().equals(cbtn7)) {
			paintColour = Color.CYAN;
		}
		
		if (e.getSource().equals(eraser)) {
			paintColour = Color.WHITE;
		}
		
		if (e.getSource().equals(clear)) {
			mode="clear";
			firstClick = true;
			drawingPanel.repaint();
		}
		
		if (e.getActionCommand().equals("Pencil")) {
			mode="pencil";	
			System.out.println("pencil");
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(mode.equals("pencil")) {
			x1 = e.getX();
			y1 = e.getY();
			x2=x1;
			y2=y1;
			return;
		}
		
		if (firstClick) {
			x1 = e.getX();
			y1 = e.getY();
		} else {
			x2 = e.getX();
			y2 = e.getY();
		}
		
		drawingPanel.repaint();
		firstClick = !firstClick;
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {

	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {

	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(mode.equals("line")) {
			return;
		}
		x2=x1;
		y2=y1;
		x1 = e.getX();
		y1 = e.getY();
		drawingPanel.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

}

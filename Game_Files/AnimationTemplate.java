package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer; 
import java.util.ArrayList;


public class AnimationTemplate  implements MouseListener, MouseMotionListener, KeyListener{
	public static void main(String[] args) {
		new AnimationTemplate();  
	}




	/****************/
	/* Variables    */
	/****************/
	Font font1 = new Font ("Arial", Font.BOLD, 14);
	ArrayList <Ball> ballList = new ArrayList<Ball>();	
	//window sizes
	static int winWidth =0;  
	static int winHeight =0;
	
	Rectangle rect = new Rectangle(700,400,100,100);

	JPanel mainPanel;  // the main JPanel

	//timer
	Timer timer;
	int t_speed = 10; //speed of timer repeats (ms)
	int t_pause = 10;  //initial delay (ms)


	//x and y position of the mouse
	int mx =50;
	int my =50;  
	int mxClick =50;
	int myClick =50;  

	//data

	int numBalls = 1500;



	/*****************************************
	 *   Set up the window (JFrame)           *
	 *   and initialize whatever you need to  *
	 *****************************************/
	AnimationTemplate() {

		//Setup timers
		TimerListener tl = new TimerListener();  
		timer = new Timer(t_speed, tl);
		timer.setInitialDelay(t_pause);
		// timer.start(); //moved to after frame is drawn

		JFrame window = new JFrame("Animation Template");
		mainPanel = new DrawingPanel();
		window.add(mainPanel);

		//add all listeners
		mainPanel.addMouseMotionListener(this);
		mainPanel.addMouseListener(this);
		mainPanel.addKeyListener(this);

		//window setup
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//set window size to full screen
		Toolkit tk = Toolkit.getDefaultToolkit();  
		winWidth = ((int) tk.getScreenSize().getWidth());  
		winHeight = ((int) tk.getScreenSize().getHeight());  
		window.setSize(winWidth,winHeight); 		

		window.setLocationRelativeTo(null);
		window.setResizable(true);

		//setup data
		setUpData();

		window.setVisible(true);
		timer.start(); //put this after all of the drawing and setup has been done

	}

	void setUpData() {
		for (int i=0; i<numBalls; i++){
			ballList.add(new Ball());
		}


	}

	//JPanel inner class
	private class DrawingPanel extends JPanel{
		DrawingPanel() {
			this.setBackground(Color.BLACK);
		} 

		//paint components
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


			/*************************/
			/* Draw all objects here */
			/*************************/

			//draw where the mouse is clicked
		g2.setColor(ColourRainbow.getNextColour());
		//	g2.drawRect(mxClick-10,myClick-10,50,50);
	g2.fillRect(rect.x, rect.y, rect.width, rect.height);
			//draw all of the balls
			
			for (Ball b: ballList){
				g2.setColor(b.colour);
				g2.fillOval(b.x, b.y, b.width,b.height);
			}
			//g2.fillOval(ball.x, ball.y, ball.width,ball.height);

		}
	} //end of DrawingPanel inner class 


	/*********************************************************************************/
	/* All event listeners here . They are connected to the thing in the outer class */
	/*********************************************************************************/
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		mainPanel.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		mxClick = e.getX();
		myClick = e.getY();
		mainPanel.repaint();
	}
	public void mousePressed(MouseEvent e) {} 
	public void mouseExited(MouseEvent e) {} 
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {
		mainPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR)); //change the mouse cursor
		mainPanel.requestFocus();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == 32) timer.stop();
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == 32) timer.start();
	}
	public void keyTyped(KeyEvent e) {}


	//###############################################
	//inner class for Timer Listener
	private class TimerListener implements ActionListener {  
		public void actionPerformed(ActionEvent e) {

			/***************************/
			/* All timer tasks here    */
			/***************************/
			//do something
			moveStuff();
			mainPanel.repaint();			
		}
	}  //end of inner class

	/**********************************/
	/* put any special functions here */
	/**********************************/
	void moveStuff (){
		//move all of the balls

		for (Ball b: ballList){
			b.bx += b.xspeed;
			b.by += b.yspeed;	
			
			//bouncing off the edges
			if (b.by>winHeight-50) {
				b.yspeed = -b.yspeed;
			}
			if (b.bx>winWidth-30) {
				b.xspeed = -b.xspeed;
			}
			if (b.by<0) {
				b.yspeed = -b.yspeed;
			}
			if (b.bx<0) {
				b.xspeed = -b.xspeed;
			}
			
			//bounce off rectangle
	if (b.intersects(rect)){
			b.xspeed = -b.xspeed;
			b.yspeed = -b.yspeed;
			}
			b.x = (int)b.bx;
			b.y = (int)b.by;
		}
	} //end of moveStuff



} //end of outer class




package Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//Ryan Fagan,June 2015

/*TODO: Make a game that allows the player to move 
around and shoot zombie's without dying. With surviving comes
power ups and new weapons to help you go farther in the game
to see how long you can last.
 */

public class Shooter implements KeyListener, MouseListener {
	public static void main(String[] args) {
		new Shooter();
	}

	final static int MAXZOMBIE = 200;
	final static int STARTZOMBIE = 20;
	final static int scrW = 1200;
	final static int scrH = 800;

	Player player;
	JFrame window;
	DrawingPanel dp = new DrawingPanel();
	int panelW, panelH;
	Timer timerMain, timerSpawn;
	int mx, my;
	int t_speed = 1;
	int t_pause = 0;
	int killCount = 1;

	enum ZombieType{ZOMBIE, RUNNER, GIANT, BABY, SHADOW}
	enum BulletType{BULLET}

	//hold regular, as well as sub-types
	ArrayList <Zombie> zombieList = new ArrayList<Zombie>(); 
	ArrayList <Bullet> bulletList = new ArrayList<Bullet>();

	Shooter() {
		for(int i=0; i< STARTZOMBIE; i++) {
			createZombie(ZombieType.ZOMBIE);
		}

		//set up objects for game
		player = new Player();
		player.x = 500;
		player.y = 300;

		TimerListener timerL1 = new TimerListener();
		TimerListener2 timerL2 = new TimerListener2();
		timerMain = new Timer(t_speed,timerL1);
		timerMain.setInitialDelay(t_pause);

		timerSpawn = new Timer(2000, timerL2);
		timerSpawn.setInitialDelay(4000);
		timerSpawn.start();

		dp.setBackground(Color.gray.brighter());
		dp.addMouseListener(this);
		dp.requestFocus();

		window = new JFrame("--Shoot To Start game--");
		window.addKeyListener(this);
		window.add(dp);
		window.setSize(1000, 600);
		window.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);

		dp.repaint();
	}

	void makeBullet(int mx, int my) {
		int dx =(mx - player.x);
		int dy =(my - player.y);
		double angle = Math.atan2(dy,dx);

		if(player.weapon == BulletType.BULLET) createBasicBullet(angle);
	}

	void createBasicBullet(double angle) {
		Bullet b = new Bullet();
		b.x = player.x;
		b.y = player.y;
		b.angle = angle;		
		bulletList.add(b);
	}

	void createZombie(ZombieType zt) {
		Zombie z = null; 
		if (zt == ZombieType.ZOMBIE) z = new Zombie();
		if (zt == ZombieType.RUNNER) z = new Runner();
		if (zt == ZombieType.GIANT) z = new Giant();
		if (zt == ZombieType.BABY) z = new Baby();
		if (zt == ZombieType.SHADOW) z = new Shadow();

		int side = (int)(Math.random() * 4);

		//set x, y to come on screen
		if (side == 0) {	//top
			z.yy = 0;
			z.xx = Math.random() * scrW;
		}

		if (side == 1) {	//right
			z.xx = scrW;
			z.yy = Math.random() * scrH;
		}

		if (side == 2) {	//bottom				
			z.yy = scrH;
			z.xx = Math.random() * scrW;				
		}

		if (side == 3) {	//left
			z.yy = Math.random() * scrH;
			z.xx = 0;
		}
		zombieList.add(z);		
	}

	class DrawingPanel extends JPanel {

		DrawingPanel() {
			this.setBackground(Color.WHITE);			
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			panelW = this.getWidth();
			panelH = this.getHeight();

			Graphics2D g2 = (Graphics2D) g;			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(player.color);
			g.fillRect(player.x, player.y, player.width, player.height);
			g.setColor(Color.BLACK);
			g.drawRect(player.x, player.y, player.width, player.height);

			for(Bullet shot: bulletList) {
				g.setColor(shot.color);
				g.fillOval((int)shot.x, (int)shot.y,Bullet.bulletSize,Bullet.bulletSize);
				g.drawOval((int)shot.x, (int)shot.y,Bullet.bulletSize,Bullet.bulletSize);
			}

			for(Zombie z: zombieList) {
				g.setColor(z.color);
				g.fillRect(z.x, z.y, z.width, z.height);
				g.setColor(Color.BLACK);
				g.drawRect(z.x, z.y, z.width, z.height);
			}

			g.setColor(new Color(255,255,255,128));			
			g.fillRect(890, 5, 90, 40);
			g.setColor(Color.BLACK);
			g.drawString("KillCount: " + killCount,900,20);
			g.drawString("Health: " + player.health,900,35);
		}
	}

	private class TimerListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			createZombie(ZombieType.ZOMBIE);
			if(killCount >= 50) {
				if(Math.random() > 0.5) {
					createZombie(ZombieType.RUNNER);
				} else {
					createZombie(ZombieType.ZOMBIE);
				}
			}

			if(killCount >= 150) {
				if(Math.random() < 0.5) {
					createZombie(ZombieType.GIANT);
				} else {
					createZombie(ZombieType.ZOMBIE);
				}
			}

			if(killCount >= 350) {
				if(Math.random() < 0.7) {
					createZombie(ZombieType.BABY);
				} else {
					createZombie(ZombieType.ZOMBIE);
				}
			}

			if(killCount >= 450) {
				if(Math.random() < 0.7) {
					createZombie(ZombieType.SHADOW);
				} else {
					createZombie(ZombieType.ZOMBIE);
				}
			}
		}
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			//move all bullets
			for (Bullet bullet: bulletList) {
				bullet.move();
			}

			for (Bullet b: bulletList) {
				for(Zombie z: zombieList) {

					//collision check
					if(b.x >= z.x && b.x <= z.x + player.width) {
						if(b.y >= z.y && b.y <= z.y + player.height) {
							z.health -= b.damage;
							b.x = -1000; b.y = -1000;
						}
					}

					if(z.health <= 0) {
						z.dead = true;
					}

					if(z.dead == true) {
						for (Zombie s: zombieList) {
							s.speed += 0.0025;				
						}
					}	
				}
			}

			for(int i = 0; i< zombieList.size(); i++) {
				if (zombieList.get(i).dead == true) {
					zombieList.remove(i);
					killCount++;
				}
			}

			//remove off-screen bullets
			for(int i = 0; i< bulletList.size(); i++) {
				if (bulletList.get(i).x < 0 
						|| bulletList.get(i).y < 0 
						|| bulletList.get(i).x > scrW 
						|| bulletList.get(i).y > scrH) {
					bulletList.remove(i);
				}
			}

			checkKillCount();
			moveZombies();
			dp.repaint();
		}

		void moveZombies() {		
			for(Zombie z: zombieList) {

				double saveX = z.xx;
				double saveY = z.yy;
				
				//move based on angle
				double dx = player.x - z.xx;
				double dy = player.y - z.yy;
				double speedx = z.speed * dx / Math.hypot(dx,dy);
				double speedy = z.speed * dy / Math.hypot(dx,dy);

				z.xx += speedx;
				z.yy += speedy;

				z.x = (int) z.xx;
				z.y = (int) z.yy;

				//don't let zombie's pile on top of each other
				for(Zombie otherZ: zombieList) {
					if (z.equals(otherZ)) continue;
					if (z.intersects(otherZ)) {
						
						//move zombie back to where it was
						z.xx = saveX;
						z.yy = saveY;
						z.x = (int) z.xx;
						z.y = (int) z.yy;
					}
				}

				if(z.intersects(player)) {
					player.health -= z.damage;

					//move zombie back
					z.xx = saveX;
					z.yy = saveY;
					z.x = (int) z.xx;
					z.y = (int) z.yy;
				}
			} //end of main for loop for all zombie's
			
			if(player.health <= 0) {
				player.dead = true;
			}

			if(player.dead == true) {
				timerMain.stop();
				timerSpawn.stop();
			}
		}

		void checkKillCount() {
			for (Bullet b: bulletList) {

				if(killCount >= 101) {
					dp.setBackground(Color.DARK_GRAY.brighter());			
					player.speed = 7;
					b.color = Color.BLUE;
					b.damage = 3;
					b.baseSpeed = 4.0;
				}

				if(killCount >= 201) {
					dp.setBackground(Color.DARK_GRAY);			
					player.speed = 9;
					b.color = Color.CYAN;
					b.damage = 5;
					b.baseSpeed = 6.0;
				}

				if(killCount >= 301) {
					dp.setBackground(Color.DARK_GRAY.darker());
					player.speed = 12;
					b.color = Color.ORANGE.brighter();
					b.damage = 7;
					b.baseSpeed = 8.0;
				}

				if(killCount >= 401) {
					dp.setBackground(Color.BLACK);			
					player.speed = 13;	
					b.color = Color.WHITE.brighter();
					b.damage = 9;
					b.baseSpeed = 10.0;
				}
			}
		}
	}

	boolean toggle = true;

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		dp.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		//this is to fix the problem that nothing shows up until you click the mouse
		if (!timerMain.isRunning() && !player.dead) timerMain.start(); 
		mx = e.getX();
		my = e.getY();

		//make a bullet
		makeBullet(mx, my);
		window.setTitle("Zombie Survival");
		dp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (player.dead == true) {
			return;
		}

		if(e.getKeyChar() == 'w') {
			if(player.y > player.height){
				player.y -= player.speed;
			}
		}

		if(e.getKeyChar() == 'a') {
			if (player.x > player.width) {
				player.x -= player.speed;
			}
		}

		if(e.getKeyChar() == 's') {
			if(player.y < (panelH - 20)) {
				player.y += player.speed;
			}
		}

		if(e.getKeyChar() == 'd') {
			if((player.x + player.width + 10) < panelW){
				player.x += player.speed;
			}
		}

		if (e.getKeyChar() == 'p') {

			timerMain.stop();
			timerSpawn.stop();

			if (toggle == false) {
				
				timerMain.start();
				timerSpawn.start();
			//	toggle = true;
			//	return;
			}
			
			//toggle = false;
			toggle = !toggle;
		}
		



		dp.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e){}
	@Override
	public void keyTyped(KeyEvent e){
		keyPressed(e);
	}
}

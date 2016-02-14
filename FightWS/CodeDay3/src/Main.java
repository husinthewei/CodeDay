import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements KeyListener{
	public static BufferedImage test;
	public static BufferedImage backgrnd;
	public static int x = 0;
	public static int xbg = -250;
	public static Fighter player1 = new JON(1);
	public static Fighter player2 = new JON(2);
	
	public static String scene = "Game";
	
	
	public static void createFrame(Main g) throws IOException{
		JFrame frame = new JFrame("Sewer Slugger 3: This time it's personal");
		frame.add(g);
		frame.setSize(1000, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.addKeyListener(g);
		g.setFocusable(true);
		frame.setVisible(true);
	}
	
	public static void InitializeImages() throws IOException{
		test = ImageIO.read(new File("test.png"));
		backgrnd = ImageIO.read(new File("Background.png"));
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Main demo = new Main();
		createFrame(demo);
		InitializeImages();
		
		while(true){
			//System.out.println(player1.getX() + " " + player2.getX());
			//System.out.println(player1.getDirection() + " " + player2.getDirection());
			//System.out.println(player1.getTrueX() + " " + player2.getTrueX());
			//System.out.println(player1.getState() + " " + player2.getState());
			//System.out.println(player1.getState() + " " + player1.getDirection() + "  " +player2.getState() + " " + player2.getDirection() + " " + player2.getFrameNum() + " " + player2.getFrameCount());
			System.out.println(player1.getY() + " " + player2.getY());
			demo.repaint();
			Thread.sleep(10);
		}

	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		paintComponent(g);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(scene == "Start"){
			g2d.drawImage(backgrnd,0,0,null);
			
			
		}
		
		if(scene == "Game"){
			checkBoundaries();
			player1.move();
			player2.move();
			g2d.drawImage(backgrnd,xbg,0,null);
			//g2d.drawImage(test, player1.getX(), player1.getY(),null);
			//g2d.drawImage(test, player2.getX(), player2.getY(), null);
			player1.updateFrame(); player2.updateFrame();
			//g2d.fillRect(player1.getX(), player1.getY(), 200, 300);
			g2d.drawImage(player1.getCurrentFrame(), player1.getX() - 150, player1.getY(), null);
			g2d.drawImage(player2.getCurrentFrame(), player2.getX() - 150, player2.getY(), null);
			drawHealth(g);
		}

	}
	
	public void drawHealth(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fillRect(100, 10, (int)(3 * (player1.getHealth())), 40);
		g2d.fillRect(600, 10, (int)(3 * (player2.getHealth())), 40);
		//player1.decreaseHealth(1);
		g2d.setColor(Color.BLACK);
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 87){
			player1.setJumping(true);
		}
		
		if(arg0.getKeyCode() == 68){
			player1.setMoveRight(4);
			player1.setState("moving");
		}
		if(arg0.getKeyCode() == 65){
			player1.setMoveLeft(4);
			player1.setState("moving");
		}

		
		if(arg0.getKeyCode() == 38){
			player2.setJumping(true);
		}
		if(arg0.getKeyCode() == 39){
			player2.setMoveRight(4);
			player2.setState("moving");
		}
		if(arg0.getKeyCode() == 37){
			player2.setMoveLeft(4);
			player2.setState("moving");
		}
		
		if(arg0.getKeyCode() == 71){
			player1.setState("shield");
		}
		if(arg0.getKeyCode() == 72){
			  player1.useAttack2(player1, player2);
		}
		
		if(arg0.getKeyCode() == 110){
			  player2.useAttack2(player2, player1);
		}
		
		if(arg0.getKeyCode() == 74){
			if(((player1.getState() == "rest"||player1.getState() == "moving")) && !player1.isJumping())
			  player1.useAttack1(player1, player2);
		}
		
		if(arg0.getKeyCode() == 96){
			if((player2.getState() == "rest"||player2.getState() == "moving") && !player2.isJumping())
				  player2.useAttack1(player2, player1);			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == 68){
			player1.setMoveRight(0);
			player1.setState("rest");
		}
		if(arg0.getKeyCode() == 65){
			player1.setMoveLeft(0);
			player1.setState("rest");
		}
		
		if(arg0.getKeyCode() == 39){
			player2.setMoveRight(0);
			player2.setState("rest");
		}
		if(arg0.getKeyCode() == 37){
			player2.setMoveLeft(0);
			player2.setState("rest");
		}
		
		if(arg0.getKeyCode() == 71){
			player1.setState("rest");
		}
	}
	
	public static void checkBoundaries(){
		if(player1.getX() <= 0){
			player1.setX(1);
			if(xbg >= -10){
				xbg = -10;
			}
			xbg += 4;
		}
		if(player1.getX() >= 891){
			player1.setX(890);
			if(xbg <= -500){
				xbg = -500;
			}
			xbg -= 4;
		}
		
		if(player2.getX() <= 0){
			player2.setX(1);
			if(xbg >= -10){
				xbg = -10;
			}
			xbg += 4;
		}
		if(player2.getX() >= 891){
			player2.setX(890);
			if(xbg <= -500){
				xbg = -500;
			}
			xbg -= 4;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

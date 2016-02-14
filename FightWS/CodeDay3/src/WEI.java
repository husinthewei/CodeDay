import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WEI extends Fighter{
	private BufferedImage[] rest = new BufferedImage[4];
	private BufferedImage[] Rrest = new BufferedImage[4];
	private BufferedImage[] Rwalk = new BufferedImage[3];
	
	private BufferedImage[] arial = new BufferedImage[3];
	private BufferedImage[] Rarial = new BufferedImage[3];
	
	private BufferedImage[] punch = new BufferedImage[6];
	private BufferedImage[] Rpunch = new BufferedImage[6];
	public BufferedImage[] walk = new BufferedImage[3];
	
	private BufferedImage[] fall = new BufferedImage[2];
	private BufferedImage[] Rfall = new BufferedImage[2];
	
	private BufferedImage hurt;
	private BufferedImage jump;
	private BufferedImage Rhurt;
	private BufferedImage Rjump;
	
	private BufferedImage rShield;
	private BufferedImage lShield;
	
	private boolean Hit;
	private int frameCount=0;
	private int frameCountj = 0;
	private int frameNum;
	private Attack[] attacks = new Attack[2];
	public WEI(int PlayerNum){
		super(PlayerNum);	
		attacks[0] = new hAttack(230,10, 1); //radius, damage, attack num
		attacks[1] = new aAttack(230, 10, 2);
		try {
			InitializeImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InitializeImages() throws IOException{
		rest[0] = ImageIO.read(new File("WRR.png"));
		rest[1] = ImageIO.read(new File("WRR.png"));
		rest[2] = ImageIO.read(new File("WRR.png"));
		rest[3] = ImageIO.read(new File("WRR.png"));
		
		punch[0] = ImageIO.read(new File("WPR.png"));
		punch[1] = ImageIO.read(new File("WPR.png"));
		punch[2] = ImageIO.read(new File("WPR.png"));
		punch[3] = ImageIO.read(new File("WP1R.png"));
		punch[4] = ImageIO.read(new File("WP1R.png"));
		punch[5] = ImageIO.read(new File("WP1R.png"));
		
		Rpunch[0] = ImageIO.read(new File("WP.png"));
		Rpunch[1] = ImageIO.read(new File("WP.png"));
		Rpunch[2] = ImageIO.read(new File("WP.png"));
		Rpunch[3] = ImageIO.read(new File("WP1.png"));
		Rpunch[4] = ImageIO.read(new File("WP1.png"));
		Rpunch[5] = ImageIO.read(new File("WP1.png"));
		
		walk[0] = ImageIO.read(new File("WRR.png"));
		walk[1] = ImageIO.read(new File("WRR.png"));
		walk[2] = ImageIO.read(new File("WRR.png"));
		
		Rwalk[0] = ImageIO.read(new File("WR.png"));
		Rwalk[1] = ImageIO.read(new File("WR.png"));
		Rwalk[2] = ImageIO.read(new File("WR.png"));
		
		jump = ImageIO.read(new File("WRR.png"));
		hurt = ImageIO.read(new File("WHR.png"));
		
		Rjump = ImageIO.read(new File("WR.png"));
		Rhurt = ImageIO.read(new File("WH.png"));
		
		Rrest[0] = ImageIO.read(new File("WR.png"));
		Rrest[1] = ImageIO.read(new File("WR.png"));
		Rrest[2] = ImageIO.read(new File("WR.png"));
		Rrest[3] = ImageIO.read(new File("WR.png"));
		
		arial[0] = ImageIO.read(new File("WA2.png"));
		arial[1] = ImageIO.read(new File("WA3.png"));
		arial[2] = ImageIO.read(new File("WA5R.png"));
		Rarial[0] = ImageIO.read(new File("WA5R.png"));
		Rarial[1] = ImageIO.read(new File("WA3.png"));
		Rarial[2] = ImageIO.read(new File("WA2.png"));
		
		fall[0] = ImageIO.read(new File("WF2.png"));
		fall[1] = ImageIO.read(new File("WF3.png"));	
		Rfall[0] = ImageIO.read(new File("WF2R.png"));
		Rfall[1] = ImageIO.read(new File("WF3R.png"));
		
		rShield = ImageIO.read(new File("WS1.png"));
		lShield = ImageIO.read(new File("WS1R.png"));
	}
	
	public void useAttack1(Fighter user, Fighter target){
		attacks[0].use(user, target);
	}
	public void useAttack2(Fighter user, Fighter target){
		attacks[1].use(user, target);
	}
	public int getTrueX(){
		return this.getX() + 100;
		
	}
	public void setFrameNum(int n){
		frameNum = n;
	}
	public void setFrameCount(int n){
		frameCount = n;
	}
	
	public void setHit(boolean h){
		Hit = h;
	}
	
	public boolean getHit(){
		return Hit;
	}
	public int getFrameCount(){
		return frameCount;
	}
	public int getFrameNum(){
		return frameNum;
	}
	public void updateFrame(){
		//			
		if(this.getState()!= "dead"){
		if(this.isJumping() == true && this.getDirection() == "r"){
			frameCountj++;
			if(this.getJumpState() == 1){
				this.setCurrentFrame(jump);				
			}

			if(this.getJumpState() == 1 && frameCountj == 10){
				this.setJumpState(2);
				this.setYVel(30); 
				this.setCurrentFrame(rest[0]);
			}
			
			if(this.getJumpState() == 3){
				this.setCurrentFrame(rest[0]);
				this.setJumpingFalse();
				this.setState("rest");
				frameCountj = 0;
			    this.setJumpState(0);
			}

		}
		
		if(this.isJumping() == true && this.getDirection() == "l"){
			frameCountj++;
			if(this.getJumpState() == 1){
				this.setCurrentFrame(Rjump);				
			}

			if(this.getJumpState() == 1 && frameCountj == 10){
				this.setJumpState(2);
				this.setYVel(30); 
				this.setCurrentFrame(Rrest[0]);
			}
			
			if(this.getJumpState() == 3){
				this.setCurrentFrame(Rrest[0]);
				this.setJumpingFalse();
				this.setState("rest");
				frameCountj = 0;
			    this.setJumpState(0);
			}

		}
		if(this.getState() == "rest" && this.getDirection() == "r"){
			frameCount++;
			if(frameCount >= 10){
				frameNum++;
				frameCount = 0;
			}
			if(frameNum >= 5){
				frameNum =0;
				frameCount = 0;
			}
			
			if(frameNum == 0)
				this.setCurrentFrame(rest[0]);
			
			if(frameNum == 1)
				this.setCurrentFrame(rest[1]);
			
			if(frameNum == 2)
				this.setCurrentFrame(rest[2]);
			
			if(frameNum == 4)
				this.setCurrentFrame(rest[3]);	
		}
		if(this.getState() == "rest"&& this.getDirection() == "l"){
			frameCount++;
			if(frameCount >= 10){
				frameNum++;
				frameCount = 0;
			}
			if(frameNum >= 5){
				frameNum =0;
				frameCount = 0;
			}
			
			if(frameNum == 0)
				this.setCurrentFrame(Rrest[0]);
			
			if(frameNum == 1)
				this.setCurrentFrame(Rrest[1]);
			
			if(frameNum == 2)
				this.setCurrentFrame(Rrest[2]);
			
			if(frameNum == 4)
				this.setCurrentFrame(Rrest[3]);	
		}
		
		
	
		
		if(this.getState() == "moving" && this.getDirection() == "r"){
			frameCount++;
			if(frameCount >= 10){
				frameNum++;
				frameCount = 0;
			}
			if(frameNum >= 3)
				frameNum = 0;
			
			if(frameNum == 0)
				this.setCurrentFrame(walk[0]);
			
			if(frameNum == 1)
				this.setCurrentFrame(walk[1]);
			
			if(frameNum == 2)
				this.setCurrentFrame(walk[2]);
		}
		
		if(this.getState() == "moving" && this.getDirection() == "l"){
			frameCount++;
			if(frameCount >= 10){
				frameNum++;
				frameCount = 0;
			}
			if(frameNum >= 3)
				frameNum = 0;
			
			if(frameNum == 0)
				this.setCurrentFrame(Rwalk[0]);
			
			if(frameNum == 1)
				this.setCurrentFrame(Rwalk[1]);
			
			if(frameNum == 2)
				this.setCurrentFrame(Rwalk[2]);
		}
		
	
		if(this.getState() == "Attack1" && this.getDirection() == "r"){
			frameCount++;
			if(frameCount >= 6){
				frameNum++;
				frameCount = 0;
			}
			if(frameNum == 0)
				this.setCurrentFrame(punch[0]);
			if(frameNum == 1){
				this.setCurrentFrame(punch[1]);
				if(Hit)
				attacks[0].makeHurt();
			}
			if(frameNum == 2){
				this.setCurrentFrame(punch[2]);
				if(Hit){
					attacks[0].dealDmg();
					Hit = false;
				}
			}
			if(frameNum == 3)
				this.setCurrentFrame(punch[3]);
			if(frameNum == 4)
				this.setCurrentFrame(punch[4]);
			if(frameNum == 5){
				this.setCurrentFrame(punch[5]);		
				this.setState("rest");
			}
		}
		
		if(this.getState() == "Attack1" && this.getDirection() == "l"){
			frameCount++;
			if(frameCount >= 6){
				frameNum++;
				frameCount = 0;
			}
			if(frameNum == 0)
				this.setCurrentFrame(Rpunch[0]);
			if(frameNum == 1){
				this.setCurrentFrame(Rpunch[1]);
				if(Hit)
				attacks[0].makeHurt();
			}
			if(frameNum == 2){
				this.setCurrentFrame(Rpunch[2]);
				if(Hit){
					attacks[0].dealDmg();
					Hit = false;
				}
			}
			if(frameNum == 3)
				this.setCurrentFrame(Rpunch[3]);
			if(frameNum == 4)
				this.setCurrentFrame(Rpunch[4]);
			if(frameNum == 5){
				this.setCurrentFrame(Rpunch[5]);		
				this.setState("rest");
			}
		}
		
		if(this.getState() == "Attack2" && this.getDirection() == "r"){
			frameCount++;
			if(frameCount <= 10)
				this.setCurrentFrame(arial[0]);
			
			if(frameCount == 8){
				if(Hit)
				attacks[1].makeHurt();
			}
			
			if(frameCount > 10 && frameCount <= 20){
				this.setCurrentFrame(arial[1]);
			}
			if(!this.isJumping()){
				this.setState("rest");
				frameCount = 0;
				frameNum = 0;
			}
			
			if(frameCount > 20){
				this.setCurrentFrame(arial[2]);
				if(Hit){
					attacks[1].dealDmg();
					Hit = false;
				}	
			}
		
		

		
		
		}
		
		if(this.getState() == "Attack2" && this.getDirection() == "l"){
			frameCount++;
			if(frameCount <= 10)
				this.setCurrentFrame(Rarial[0]);
			if(frameCount > 10 && frameCount <= 15){
				this.setCurrentFrame(Rarial[1]);
				if(Hit)
				attacks[1].makeHurt();
			}
			if(!this.isJumping()){
				this.setState("rest");
				frameCount = 0;
				frameNum = 0;
			}
			
			if(frameCount > 15){
				this.setCurrentFrame(Rarial[2]);
				if(Hit){
					attacks[1].dealDmg();
					Hit = false;
				}	
			}
		}
		
		

		
		if(this.getState() == "hurt" && this.getDirection() == "r"){
			frameCount++;
			if(frameCount < 8){
				this.setCurrentFrame(rest[0]);
			}
			if(frameCount >= 15){
				this.setCurrentFrame(hurt);
			}
			if(frameCount == 21){
				this.setCurrentFrame(rest[0]);
				this.setState("rest");			
			}
		}
		
		if(this.getState() == "hurt" && this.getDirection() == "l"){
			frameCount++;
			if(frameCount < 8){
				this.setCurrentFrame(Rrest[0]);
			}
			if(frameCount >= 15){
				this.setCurrentFrame(Rhurt);
			}
			if(frameCount == 21){
				this.setCurrentFrame(Rrest[0]);
				this.setState("rest");			
			}
		}
		
		if(this.getState() == "shield" && this.getDirection() == "r")
			this.setCurrentFrame(rShield);
		if(this.getState() == "shield" && this.getDirection() == "l")
			this.setCurrentFrame(lShield);			
	
			
}
		
		else{
			for(int i = 0; i < 30; i++){
				if(i <= 15)
					this.setCurrentFrame(fall[0]);
				if(i > 15)
					this.setCurrentFrame(fall[1]);
			}
		}
	}//end method
	}//end class


import java.awt.image.BufferedImage;


public class Fighter{
	private int x;
	private int y;
	private double health= 100; 
	private String direction;
	private String state = "rest"; 
	private BufferedImage CurrentFrame;
	private double yVel = 0;
	private boolean jumping = false;
	private int jumpState = 0;
	
	private int[] movements = {0,0};
	public Fighter(int PlayerNum){
		y = 320;
		if(PlayerNum==1){
			x = 154;
			direction = "r";
		}
		if(PlayerNum == 2){
			x = 646;
			direction = "l";
		}
	}
	public void setX(int r){
		x=r;
	}
	public  int getX(){
		return x;
	}
	public void setY(int r){
		y=r;
	}
	public  int getY(){
		return y;
	}
	
	public int getMoveRight(){
		return movements[1];
	}
	
	public int getMoveLeft(){
		return movements[0];
	}
	
	public void setMoveRight(int num){
		movements[1] = num;
		if(num > 0)
			direction = "r";
	}
	public void setMoveLeft(int num){
		movements[0] = num;
		if(num > 0)
			direction = "l";
	}
	public boolean isJumping(){
		return jumping;
	}
	public void setJumping(boolean j){
		if(jumping ==false){
			jumping = j;
			jumpState = 1;
			state = "jumping";
			this.setFrameCount(0);
			this.setFrameNum(0);
		}
	}
	
	public void setJumpingFalse(){
		jumping = false;
	}
	public double getHealth(){
		return health;
	}
	public void setHealth(int h){
		health = h;
	}
	public void decreaseHealth(int h){
		health -=h;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String s){
		state = s;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public void setDirection(String d){
		direction = d;
	}
	
	public int getTrueX(){
		return 0;
	}
	public void move(){
		x-= movements[0];
		x+= movements[1];
		if(jumping && jumpState == 2)
			yVel-=1.5;
		y-=yVel;
		if(jumping && y >= 320 && jumpState ==2){
			y = 320;
			yVel = 0;
			jumpState = 3;
		}
		if(this.getHealth() <= 0){
			state = "dead";
		}
	}

	public BufferedImage getCurrentFrame(){
		return CurrentFrame;
	}
	public void setCurrentFrame(BufferedImage c){
		CurrentFrame = c;
	}
	
	public int getJumpState(){
		return jumpState;
	}
	public void setJumpState(int s){
		jumpState = s;
	}
	public int getYVel(){
		return (int) yVel;
	}
	public void setYVel(int v){
		yVel = v;
	}
	
	
	public void useAttack1(Fighter user, Fighter target){}
	public void useAttack2(Fighter user, Fighter target){}
	public void updateFrame(){}
	public void setFrameCount(int n){}
	public int getFrameCount(){return 0;};
	public int getFrameNum(){return 0;};
	public void setFrameNum(int n){}
	public void setHit(boolean h){}
	public boolean getHit(){return true;}

}

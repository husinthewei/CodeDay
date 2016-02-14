import java.lang.annotation.Target;


public class aAttack extends Attack{
	private int aNum;
	private Fighter opp;
	public aAttack(int rad, int d, int AttackNum){
		super(rad, d);
		aNum = AttackNum;
	}
	
	public void use(Fighter user, Fighter target){
		opp = target;
		boolean dir = facingTarget(user, target);
		if(target.getState()!= "shield" &&(user.isJumping()) && dir && user.getY() > 190 && Math.abs(user.getTrueX() - target.getTrueX()) < this.getRad() && target.getY() >= 300){
			user.setHit(true);
			target.setFrameCount(0);
		}
		else{
			user.setHit(false);
		}
		
		if(user.isJumping()){
		switch(aNum){
			case 1: user.setState("Attack1"); break;
			case 2: user.setState("Attack2"); break;
		}
		}
		
		user.setFrameCount(0);
		user.setFrameNum(0);
	}
	
	public void makeHurt(){
		opp.setState("hurt");
	}
	public void dealDmg(){
		opp.decreaseHealth(this.getDmg());
	}
	
	public boolean facingTarget(Fighter user, Fighter target){
		if(user.getX() > target.getX() && user.getDirection() == "l")
			return true;
		if(user.getX() < target.getX() && user.getDirection() == "r")
			return true;
			
		return false;
	}
}

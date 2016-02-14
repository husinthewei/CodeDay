import java.awt.image.BufferedImage;


public class Attack {
	private int damage;
	private int radius;
	public Attack(int rad, int d){
		radius = rad;
		damage = d;

	}
	
	public void use(Fighter user, Fighter target){
		
	}	
	public int getRad(){
		return radius;
	}
	public void setRad(int r){
		radius = r;
	}
	
	public int getDmg(){
		return damage;
	}
	public void setDmg(int d){
		damage = d;
	}
	public void dealDmg(){}
	public void makeHurt(){}
}

package logic;

import java.util.Random;

public class Boss extends Monster{
	private static final int BONUS_DEF = 3;

	public Boss(String name,int maxHp, int attack, int defense,int position) {
		super(name,maxHp, attack, defense+BONUS_DEF,position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(Entity opponent) {
		Random generator = new Random();
		double number = generator.nextDouble();
		int bonusDamage = (int)Math.floor(number * this.getAtk());
		this.setAttack(this.getAtk()+bonusDamage);
		super.attack(opponent);
		this.setAttack(this.getAtk()-bonusDamage);
	}
	

}

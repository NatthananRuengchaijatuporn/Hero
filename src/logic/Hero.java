package logic;

import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Hero extends Entity {
	private int score;
	private int exp;
	private int maxExp;
	private int lv;
	private static final int MAX_LV = 10;
	private static final int[] MAX_EXP = { 10, 30, 50, 70, 90, 110, 130, 150, 170 };

	public Hero(String name,int maxHp, int attack, int defense) {
		super(name,maxHp, attack, defense);
		this.score = 0;
		this.exp = 0;
		this.lv = 1;
		this.maxExp = MAX_EXP[this.lv - 1];
	}

	@Override
	public void attack(Entity opponent) {
		Random generator = new Random();
		double number = generator.nextDouble();
		int bonusDamage = (int)Math.floor(number * this.getAtk());
		this.setAttack(this.getAtk()+bonusDamage);
		opponent.takeDamage(this.getAtk() - opponent.getDef());
		this.setAttack(this.getAtk()-bonusDamage);
		opponent.takeDamage(this.getAtk() - opponent.getDef());
		if(opponent.isDefeated()) {
			score += 100;
		}
		// TODO Auto-generated method stub

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void takeDamage(int damage) {
		int hp = this.getHp() - damage;
		if (hp <= 0)
			hp = 0;
		this.setHp(hp);

	}

	public boolean isDefeated() {
		if (getHp() <= 0) {
			return true;
		}
		return false;
	}

	public boolean isLevelUp() {
		if (getExp() >= this.maxExp) {
			return true;
		}
		return false;
	}

	public boolean isMaxLevel() {
		if (this.getLv() == MAX_LV) {
			return true;
		}
		return false;
	}

	public void gainExp(int exp) {
		this.exp += exp;
		if (isLevelUp() && !isMaxLevel()) {
			levelUp();
			this.restoreHp();
		}
	}

	public void levelUp() {
		this.exp -= this.maxExp;
		this.setLv(this.getLv() + 1);
		this.setMaxExp(this.getLv());
		this.setAttack(this.getAtk() + 3);
		this.setDefense(this.getDef() + 1);
		this.setMaxHp(this.getMaxHp() + 5);
		this.restoreHp();
		
	}

	public void restoreHp() {
		this.setHp(this.getMaxHp());
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

}

package logic;

public abstract class Entity implements Attackable{
	private String name;
	private int hp;
	private int maxHp;
	private int atk;
	private int def;
	
	public Entity(String name,int maxHp,int attack,int defense) {
		this.name = name;
		this.hp= maxHp;
		this.maxHp = maxHp;
		this.atk = attack;
		this.def = defense;
	}
	
	public abstract void takeDamage(int damage);
	public abstract void attack(Entity opponent);
	public abstract boolean isDefeated() ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAttack(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDefense(int def) {
		this.def = def;
	}
	
	
}

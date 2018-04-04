package logic;

public class Monster extends Entity {
	private double position;
	private boolean isFaceLeft;
	private boolean visible,destroyed;
	public Monster(String name,int maxHp, int attack, int defense,int position) {
		super(name,maxHp, attack, defense);
		this.position = position;
		this.isFaceLeft = true;
		this.visible = true;
		this.destroyed = false;
	}

	@Override
	public void attack(Entity opponent) {
		opponent.takeDamage(this.getAtk() - opponent.getDef());
		
	}

	public double getPosition() {
		return position;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public boolean isFaceLeft() {
		return isFaceLeft;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public void setFaceLeft(boolean isFaceLeft) {
		this.isFaceLeft = isFaceLeft;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		int hp = this.getHp() - damage;
		if (hp <= 0)
			hp = 0;
		this.setHp(hp);
	}

	@Override
	public boolean isDefeated() {
		// TODO Auto-generated method stub
		if (getHp() <= 0) {
			return true;
		}
		return false;
	}

}

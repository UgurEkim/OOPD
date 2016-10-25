package nl.han.ica.ShootEmOut;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Monster extends SpriteObject {
	protected int health;
	public ShootEmOut SEO;
	protected double attackInterval;
	protected int attackAmount;
	protected int scoreValue;
	protected int attackDamage;

	public Monster(String fileName, ShootEmOut SEO) {
		super(new Sprite(fileName));
		this.SEO = SEO;
		this.setY(-50);
	}

	public void setxPosition() {
		Random random = new Random();
		float spawnX = random.nextInt(SEO.getWidth());
		if (spawnX > SEO.getWidth() - this.width * 2) {
			spawnX = SEO.getWidth() - this.width * 2;
		}
		this.setX(spawnX);
	}

	public void removeHealth() {
		this.setHealth(this.getHealth() - 1);
		if (getHealth() == 0) {
			kill();
			SEO.addScore(getScoreValue());
			spawnPowerup();
		}
	}

	public void kill() {
		if (this instanceof Skeleton) {
			((Skeleton) this).stopAlarm();
		}
		if (this instanceof Slime) {
			((Slime) this).stopAlarm();
		}
		if (this instanceof Boss){
			((Boss) this).stopAlarm();
		}
		
		SEO.deleteGameObject(this);
	}
	
	private void spawnPowerup(){
		Random r = new Random();
		int rPowerup = r.nextInt(20);
		
		Powerup pUp;
		
		switch (rPowerup) {
		case 0:
			pUp = new AttackType(SEO, this.getX(), this.getY());
			SEO.addGameObject(pUp);
			break;
		case 1:
			pUp = new AttackSpeed(SEO, this.getX(), this.getY());
			SEO.addGameObject(pUp);
			break;
		case 2:
			pUp = new Shield(SEO, this.getX(), this.getY(), 5.0);
			SEO.addGameObject(pUp);
			break;
		case 3:
			pUp = new AddHealth(SEO, this.getX(), this.getY());
			SEO.addGameObject(pUp);
			break;
			
			default:
				break;
		}
	}

	@Override
	public void update() {
		if (this.getY() >= SEO.screenHeight) {
			kill();
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getAttackInterval() {
		return attackInterval;
	}

	public void setAttackInterval(double attackInterval) {
		this.attackInterval = attackInterval;
	}

	public int getAttackAmount() {
		return attackAmount;
	}

	public void setAttackAmount(int attackAmount) {
		this.attackAmount = attackAmount;
	}

	public int getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
}

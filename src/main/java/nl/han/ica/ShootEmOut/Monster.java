package nl.han.ica.ShootEmOut;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public abstract class Monster extends SpriteObject {
	protected int health;
	protected ShootEmOut SEO;
	protected double attackInterval;
	protected int attackAmount;
	protected int scoreValue;
	protected int attackDamage;

	public Monster(String fileName, ShootEmOut SEO) {
		super(new Sprite(fileName));
		this.SEO = SEO;
		this.setY(-50);   
	}

	protected void setxPosition() {
		Random random = new Random();
		float spawnX = random.nextInt(SEO.getWidth());
		if (spawnX > SEO.getWidth() - this.width * 2) {
			spawnX = SEO.getWidth() - this.width * 2;
		}
		this.setX(spawnX);
	}

	protected void removeHealth() {
		if (this instanceof Boss) {
			if (((Boss) this).getCanHit()) {
				this.setHealth(this.getHealth() - 1);
			}
		} else {
			this.setHealth(this.getHealth() - 1);
		}

		if (getHealth() == 0) {
			kill();
			SEO.playKillEnemySound();
			SEO.addScore(getScoreValue());
			spawnPowerup();
		}
	}

	protected abstract void kill();

	private void spawnPowerup() {
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

	protected int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		this.health = health;
	}

	protected double getAttackInterval() {
		return attackInterval;
	}

	protected void setAttackInterval(double attackInterval) {
		this.attackInterval = attackInterval;
	}

	protected int getAttackAmount() {
		return attackAmount;
	}

	protected void setAttackAmount(int attackAmount) {
		this.attackAmount = attackAmount;
	}

	protected int getScoreValue() {
		return scoreValue;
	}

	protected void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}

	protected int getAttackDamage() {
		return attackDamage;
	}

	protected void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
}

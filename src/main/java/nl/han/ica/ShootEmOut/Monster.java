package nl.han.ica.ShootEmOut;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Monster extends SpriteObject {
	protected int health;
	protected ShootEmOut SEO;
	protected double attackInterval;
	protected int scoreValue;
	
	public Monster(String fileName, ShootEmOut SEO) {
		super(new Sprite(fileName));
		this.SEO = SEO;
		setY(-50);   
	}

	protected void setxPosition() {
		Random random = new Random();
		float spawnX = random.nextInt(SEO.getWidth());
		if (spawnX > SEO.getWidth() - getWidth() * 2) {
			spawnX = SEO.getWidth() - getWidth() * 2;
		}
		setX(spawnX);
	}

	public void removeHealth() {
		if (this instanceof Boss) {
			if (((Boss) this).getCanHit()) {
				setHealth(getHealth() - 1);
			}
		} else {
			setHealth(getHealth() - 1);
		}

		if (getHealth() == 0) {
			kill();
			SEO.playKillEnemySound();
			SEO.addScore(getScoreValue());
			spawnPowerup();
		}
	}

	public abstract void kill();

	private void spawnPowerup() {
		Random r = new Random();
		int rPowerup = r.nextInt(20);

		Powerup pUp;

		switch (rPowerup) {
		case 0:
			pUp = new AttackType(SEO, getX(), getY());
			SEO.addGameObject(pUp);
			break;
		case 1:
			pUp = new AttackSpeed(SEO, getX(), getY());
			SEO.addGameObject(pUp);
			break;
		case 2:
			pUp = new Shield(SEO, getX(), getY(), 5.0);
			SEO.addGameObject(pUp);
			break;
		case 3:
			pUp = new AddHealth(SEO, getX(), getY());
			SEO.addGameObject(pUp);
			break;

		default:
			break;
		}
	}

	@Override
	public void update() {
		if (getY() >= SEO.screenHeight) {
			kill();
		}
	}

	private int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		this.health = health;
	}

	protected void setAttackInterval(double attackInterval) {
		this.attackInterval = attackInterval;
	}

	public int getScoreValue() {
		return scoreValue;
	}

	protected void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}
}

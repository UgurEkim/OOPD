package nl.han.ica.ShootEmOut;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Monster extends SpriteObject {
	protected int health;
	protected int speed;
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
		health -= 1;
		if (health == 0) {
			kill();
			SEO.addScore(100);
		}
	}

	public void kill() {
		SEO.deleteGameObject(this);
		if (this instanceof Skeleton) {
			((Skeleton) this).stopAlarm();
		}
		if (this instanceof Slime) {
			((Slime) this).stopAlarm();
		}
	}

	@Override
	public void update() {
		if (this.getY() >= SEO.screenHeight) {
			kill();
		}
	}
}

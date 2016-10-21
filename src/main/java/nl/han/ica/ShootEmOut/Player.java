package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import processing.core.PConstants;

public class Player extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener {
	private Alarm alarm;
	private int speed;
	private double attackInterval;
	private int attackAmount;
	private int attackDamage;
	private Health health;
	private Powerup[] powerup;
	public ShootEmOut SEO;

	private boolean canShoot;
	private boolean leftKey;
	private boolean rightKey;
	private boolean spaceKey;

	public Player(ShootEmOut SEO, float x) {
		super(new Sprite("src/main/java/nl/han/ica/ShootEmOut/media/player.png"));
		this.setX(x);
		this.setY(650);
		this.setWidth(52);
		this.setHeight(64);
		this.SEO = SEO;
		this.attackInterval = 0.3 ;
		this.speed = 8;
		health = new Health(5, 3, this);
		SEO.addGameObject(health);
		resetAlarm();
	}

	public void attack() {
		canShoot = false;
		Attack attack = new Attack(SEO, true, getX(), getY(), 7, 0, 20);
		SEO.addGameObject(attack);
	}

	public void move(int direction) {

	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == PConstants.LEFT) {
			leftKey = true;
		}
		if (keyCode == PConstants.RIGHT) {
			rightKey = true;
		}
		if (key == ' ') {
			spaceKey = true;
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		if (keyCode == PConstants.LEFT) {
			leftKey = false;
		}
		if (keyCode == PConstants.RIGHT) {
			rightKey = false;
		}
		if (key == ' ') {
			spaceKey = false;
		}
	}

	@Override
	public void update() {
		if (rightKey) {
			setDirectionSpeed(90, speed);
		} else if (leftKey) {
			setDirectionSpeed(270, speed);
		} else {
			setDirectionSpeed(0, 0);
		}

		if (spaceKey && canShoot) {
			attack();
		}

		if (getX() < 0) {
			setxSpeed(0);
			setX(0);
		}
		if (getX() > SEO.getWidth() - getWidth()) {
			setxSpeed(0);
			setX(SEO.getWidth() - getWidth());
		}
	}

	private void resetAlarm() {
		this.alarm = new Alarm("PlayerAttack", attackInterval);
		alarm.addTarget(this);
		alarm.start();
	}

	public void removeHealth() {
		health.removeBar();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Monster) {
				((Monster) g).kill();
				removeHealth();
			}
		}
	}

	@Override
	public void triggerAlarm(String alarmName) {
		canShoot = true;
		resetAlarm();
	}

}

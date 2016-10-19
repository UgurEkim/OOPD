package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Player extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener {
	private Alarm alarm;
	private int speed;
	private double attackInterval;
	private int attackAmount;
	private int attackDamage;
	private Health health;
	private Powerup[] powerup;
	private ShootEmOut SEO;

	private boolean canShoot;
	private boolean leftKey;
	private boolean rightKey;
	private boolean spaceKey;

	public Player(ShootEmOut SEO, float x) {
		super(new Sprite("src/main/java/nl/han/ica/ShootEmOut/media/player.png"));
		this.setX(x);
		this.setY(700);
		this.setWidth(52);
		this.setHeight(64);
		this.SEO = SEO;
		this.attackInterval = 0.33;
		this.speed = 20;
		resetAlarm();
	}

	public void attack() {
		canShoot = false;
		Attack attack = new Attack(SEO, getX(), getY(), 7, 0);
		SEO.addGameObject(attack);
	}

	public void move(int direction) {

	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == SEO.LEFT) {
			leftKey = true;
		}
		if (keyCode == SEO.RIGHT) {
			rightKey = true;
		}
		if (key == ' ') {
			spaceKey = true;
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		if (keyCode == SEO.LEFT) {
			leftKey = false;
		}
		if (keyCode == SEO.RIGHT) {
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

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
            if (g instanceof Monster) {
                SEO.deleteGameObject(g);
            }
        }
	}

	@Override
	public void triggerAlarm(String alarmName) {
		canShoot = true;
		resetAlarm();
	}

}

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
	private Health health;
	private ShootEmOut SEO;

	private int attackType;
	private boolean shield;
	private double attackSpeedInterval;
	private float movementSpeed;

	private boolean canShoot;
	private boolean leftKey;
	private boolean rightKey;
	private boolean spaceKey;

	public Player(ShootEmOut SEO, float x) {
		super(new Sprite("src/main/java/nl/han/ica/ShootEmOut/media/player.png"));
		this.setX(x);
		this.setY(650);
		this.setWidth(44);
		this.setHeight(58);
		this.SEO = SEO;
		this.setAttackSpeedInterval(0.3);
		this.setMovementSpeed(8.0F);
		this.shield = false;
		this.attackType = 1;
		health = new Health(5, 3, this, SEO);
		SEO.addGameObject(health);
		resetAlarm();
	}

	protected void attack() {
		setCanShoot(false);
		Attack attack;
		switch (getAttackType()) {
		case 1:
			attack = new Attack(SEO, true, getX(), getY(), 0.0F, 20);
			SEO.addGameObject(attack);
			break;

		case 2:
			for (int i = 0; i < 2; i++) {
				attack = new Attack(SEO, true, getX(), getY(), 355.0F + 10.0F * i, 20);
				SEO.addGameObject(attack);
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++) {
				attack = new Attack(SEO, true, getX(), getY(), 350.0F + 10.0F * i, 20);
				SEO.addGameObject(attack);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				attack = new Attack(SEO, true, getX(), getY(), 352.5F + 5.0F * i, 20);
				SEO.addGameObject(attack);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if (keyCode == PConstants.LEFT) {
			setLeftKey(true);
		}
		if (keyCode == PConstants.RIGHT) {
			setRightKey(true);
		}
		if (key == ' ') {
			setSpaceKey(true);
		}
	}

	@Override
	public void keyReleased(int keyCode, char key) {
		if (keyCode == PConstants.LEFT) {
			setLeftKey(false);
		}
		if (keyCode == PConstants.RIGHT) {
			setRightKey(false);
		}
		if (key == ' ') {
			setSpaceKey(false);
		}
	}

	@Override
	public void update() {
		if (isRightKey()) {
			setDirectionSpeed(90, getMovementSpeed());
		} else if (isLeftKey()) {
			setDirectionSpeed(270, getMovementSpeed());
		} else {
			setDirectionSpeed(0, 0);
		}

		if (isSpaceKey() && isCanShoot()) {
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
		this.alarm = new Alarm("PlayerAttack", getAttackSpeedInterval());
		alarm.addTarget(this);
		alarm.start();
	}

	protected void removeHealth() {
		if (!isShield()) {
			health.removeBar();
		}
	}

	protected void resetHealth() {
		health.resetBar();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Monster) {
				((Monster) g).kill();
				removeHealth();
			}

			if (g instanceof Powerup) {
				((Powerup) g).setPlayer(this);
				((Powerup) g).effect();

				SEO.playPowerupSound();
				SEO.deleteGameObject(g);
			}
		}
	}
	
	public boolean isDead(){
		return health.isDead();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		setCanShoot(true);
		resetAlarm();
	}

	protected double getAttackSpeedInterval() {
		return attackSpeedInterval;
	}

	protected void setAttackSpeedInterval(double attackSpeedInterval) {
		this.attackSpeedInterval = attackSpeedInterval;
	}

	protected float getMovementSpeed() {
		return movementSpeed;
	}

	protected void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	private boolean isCanShoot() {
		return canShoot;
	}

	private void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	private boolean isLeftKey() {
		return leftKey;
	}

	private void setLeftKey(boolean leftKey) {
		this.leftKey = leftKey;
	}

	private boolean isRightKey() {
		return rightKey;
	}

	private void setRightKey(boolean rightKey) {
		this.rightKey = rightKey;
	}

	private boolean isSpaceKey() {
		return spaceKey;
	}

	private void setSpaceKey(boolean spaceKey) {
		this.spaceKey = spaceKey;
	}

	protected void setAttackType(int attackType) {
		if (attackType <= 4) {
			this.attackType = attackType;
		}
	}

	protected int getAttackType() {
		return attackType;
	}

	protected boolean isShield() {
		return shield;
	}

	protected void setShield(boolean shield) {
		this.shield = shield;
	}
}

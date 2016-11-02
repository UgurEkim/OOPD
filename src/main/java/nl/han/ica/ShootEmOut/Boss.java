package nl.han.ica.ShootEmOut;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Boss extends Monster implements IAlarmListener {

	private Alarm alarm;
	private boolean canHit;

	public Boss(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/dragon.png", SEO);
		setySpeed(1);
		setHeight(150);
		setWidth(166);
		setX(SEO.getWidth() / 2 - getWidth() / 2);
		setY(-300);
		setAttackInterval(2.0 / SEO.getLevel());
		setHealth(200 * SEO.getLevel());
		setScoreValue(450 * SEO.getLevel());
		this.canHit = false;
	}
	
	/*
	 * Stops alarm, deletes the game object and if player is not dead,
	 * go to next level.
	 */
	public void kill() {
		stopAlarm();
		SEO.deleteGameObject(this);
		if (!SEO.getPlayer().isDead()) {
			SEO.nextLevel();
		}
	}

	private void attack() {
		Random r = new Random();
		int number = r.nextInt(4);
		int[] attackColor = { r.nextInt(255), r.nextInt(255), r.nextInt(255) };

		switch (number) {
		case 0:
			for (int i = 0; i < 3; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + getWidth() / 2, getY() + getHeight(), 7,
						165.0F + 15.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		case 1:
			for (int i = 0; i < 5; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + getWidth() / 2, getY() + getHeight(), 5,
						170.0F + 15.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		case 2:
			for (int i = 0; i < 5; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + getWidth() / 2, getY() + getHeight(), 5,
						190.0F - 15.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		case 3:
			for (int i = 0; i < 20; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + getWidth() / 2, getY() + getHeight(), 3,
						18.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		}
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (!SEO.getPlayer().isDead()) {
			attack();
			resetAlarm();
		} else {
			kill();
		}
	}

	private void resetAlarm() {
		alarm = new Alarm("BossAttack", attackInterval);
		alarm.addTarget(this);
		alarm.start();
	}

	private void stopAlarm() {
		alarm.stop();
	}

	@Override
	public void update() {
		if (getY() > 50 && !canHit) {
			canHit = true;
			setySpeed(0);
			resetAlarm();
		}
	}

	public boolean getCanHit() {
		return canHit;
	}
}

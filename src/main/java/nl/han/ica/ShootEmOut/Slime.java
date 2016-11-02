package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Slime extends Monster implements IAlarmListener {

	private Alarm alarm;
	private int[] attackColor = { 0, 0, 255 };

	public Slime(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/slime.png", SEO);
		setySpeed(1 + SEO.getLevel());
		setHeight(64);
		setWidth(58);
		setHealth(5 * SEO.getLevel());
		setAttackInterval(2.00 / SEO.getLevel());
		setScoreValue(250);
		resetAlarm();
		setxPosition();

	}

	private void attack() {
		Attack attack = new Attack(SEO, attackColor, getX() + 29, getY() + 64, 6, 180.0F, 20);
		SEO.addGameObject(attack);
	}

	/*
	 * Stops alarm and delete game object.
	 */
	public void kill() {
		stopAlarm();
		SEO.deleteGameObject(this);
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (!SEO.getPlayer().isDead()) {
			attack();
			resetAlarm();
		}
		else {
			kill();
		}
	}

	private void resetAlarm() {
		alarm = new Alarm("SlimeAttack", attackInterval);
		alarm.addTarget(this);
		alarm.start();
	}

	/*
	 * Stops alarm
	 */
	public void stopAlarm() {
		alarm.stop();
	}
}

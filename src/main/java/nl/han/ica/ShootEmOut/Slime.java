package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Slime extends Monster implements IAlarmListener {

	private Alarm alarm;
	private int[] attackColor = { 0, 0, 255 };

	public Slime(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/slime.png", SEO);
		this.setySpeed(1);
		this.setHeight(64);
		this.setWidth(58);
		this.setHealth(5);
		this.setAttackInterval(2.00);
		this.setScoreValue(250);
		resetAlarm();
		setxPosition();

	}

	public void attack() {
		Attack attack = new Attack(SEO, attackColor, getX() + 29, getY() + 64, 6, 180.0F, 20);
		SEO.addGameObject(attack);
	}

	@Override
	public void triggerAlarm(String alarmName) {
		attack();
		resetAlarm();
	}

	private void resetAlarm() {
		this.alarm = new Alarm("SlimeAttack", attackInterval);
		alarm.addTarget(this);
		alarm.start();
	}

	public void stopAlarm() {
		alarm.stop();
	}
}

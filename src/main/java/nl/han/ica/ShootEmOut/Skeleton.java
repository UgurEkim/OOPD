package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Skeleton extends Monster implements IAlarmListener {

	private Alarm alarm;
	private int[] attackColor = { 255, 255, 255 };

	public Skeleton(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/skeleton.png", SEO);
		this.setySpeed(2);
		this.setHeight(64);
		this.setWidth(64);
		this.attackInterval = 1.50;
		resetAlarm();
		this.health = 3;
		setxPosition();
	}

	public void attack() {
		Attack attack = new Attack(SEO, attackColor, getX() + 26, getY() + 64, 4, 165);
		Attack attack2 = new Attack(SEO, attackColor, getX() + 26, getY() + 64, 4, 195);
		SEO.addGameObject(attack);
		SEO.addGameObject(attack2);
	}

	@Override
	public void triggerAlarm(String alarmName) {
		attack();
		resetAlarm();
	}

	private void resetAlarm() {
		this.alarm = new Alarm("SkeletonAttack", attackInterval);
		alarm.addTarget(this);
		alarm.start();
	}

	public void stopAlarm() {
		alarm.stop();
	}

}

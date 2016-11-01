package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Skeleton extends Monster implements IAlarmListener {

	private Alarm alarm;
	private int[] attackColor = { 255, 255, 255 };

	public Skeleton(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/skeleton.png", SEO);
		this.setySpeed(2 + SEO.getLevel());
		this.setHeight(64);
		this.setWidth(64);
		this.setAttackInterval(1.50 / SEO.getLevel());
		this.setHealth(3*SEO.getLevel());
		this.setScoreValue(150);
		resetAlarm();
		setxPosition();
	}

	public void attack() {
		Attack attack = new Attack(SEO, attackColor, getX() + 30, getY() + 64, 4, 165.0F, 10);
		Attack attack2 = new Attack(SEO, attackColor, getX() + 30, getY() + 64, 4, 195.0F, 10);
		SEO.addGameObject(attack);
		SEO.addGameObject(attack2);
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
	
	public void kill(){
		stopAlarm();
		SEO.deleteGameObject(this);
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

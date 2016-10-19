package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Skeleton extends Monster implements IAlarmListener {
	
	private Alarm alarm;
	private double attackInterval;
	
	public Skeleton(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/skeleton.png", SEO);
		this.setySpeed(2);
		this.setHeight(64);
		this.setWidth(64);
		this.attackInterval = 1.00;
		resetAlarm();
		setxPosition();
	}
	

	public void attack() {
		Attack attack = new Attack(SEO, this, getX(), getY(), 7, 180);
		SEO.addGameObject(attack);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
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


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

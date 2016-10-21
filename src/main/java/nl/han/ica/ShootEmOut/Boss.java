package nl.han.ica.ShootEmOut;
import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Boss extends Monster implements IAlarmListener {

	private Alarm alarm;
	private int[] attackColor = { 255, 0, 0 };
	
	public Boss(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/dragon.png", SEO);
		this.setySpeed(1);
		this.setHeight(150);
		this.setWidth(166);
		this.setX(SEO.getWidth() / 2 - this.width / 2);
		this.setY(-300);
		this.attackInterval = 2.00 / SEO.getLevel();
		this.health = 50 * SEO.getLevel();
	}


	public void attack() {
		Attack attack = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 4, 165, 30);
		Attack attack2 = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 4, 180, 30);
		Attack attack3 = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 4, 195, 30);
		SEO.addGameObject(attack);
		SEO.addGameObject(attack2);
		SEO.addGameObject(attack3);
	}

	@Override
	public void triggerAlarm(String alarmName) {
		attack();
		resetAlarm();
	}

	private void resetAlarm() {
		this.alarm = new Alarm("BossAttack", attackInterval);
		alarm.addTarget(this);
		alarm.start();
	}

	public void stopAlarm() {
		alarm.stop();
	}

	@Override
	public void update(){
		if(this.getY() > 50 && this.getySpeed() > 0){
			this.setySpeed(0);
			resetAlarm();
		}
	}
}

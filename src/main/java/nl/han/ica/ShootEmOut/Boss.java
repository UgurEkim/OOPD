package nl.han.ica.ShootEmOut;

import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Boss extends Monster implements IAlarmListener {

	private Alarm alarm;
	// private int[] attackColor = { 255, 0, 0 };

	public Boss(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/dragon.png", SEO);
		this.setySpeed(1);
		this.setHeight(150);
		this.setWidth(166);
		this.setX(SEO.getWidth() / 2 - this.width / 2);
		this.setY(-300);
		this.setAttackInterval(2.0 / SEO.getLevel());
		this.setHealth(200 * SEO.getLevel());
	}

	public void attack() {
		Random r = new Random();
		int number = r.nextInt(4);
		int[] attackColor = { r.nextInt(255), r.nextInt(255), r.nextInt(255) };

		switch (number) {
		case 0:
			for (int i = 0; i < 3; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 7,
						165.0F + 15.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		case 1:
			for (int i = 0; i < 5; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 5,
						170.0F + 15.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		case 2:
			for (int i = 0; i < 5; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 5,
						190.0F - 15.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		case 3:
			for (int i = 0; i < 20; i++) {
				Attack attack = new Attack(SEO, attackColor, getX() + this.getWidth() / 2, getY() + this.getHeight(), 3,
						18.0F * i, 30);
				SEO.addGameObject(attack);
			}
			break;
		}
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
	public void update() {
		if (this.getY() > 50 && this.getySpeed() > 0) {
			this.setySpeed(0);
			resetAlarm();
		}
	}
}

package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Shield extends Powerup implements IAlarmListener {

	private double duration;
	private Alarm alarm;
	
	public Shield(ShootEmOut SEO, float x, float y, double duration) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/shield.png", SEO, x, y);
		this.setDuration(duration);
		alarm = new Alarm("Shield", duration);
	}

	@Override
	protected void effect() {
		player.setShield(true);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void update() {
	}

	protected double getDuration() {
		return duration;
	}

	protected void setDuration(double duration) {
		this.duration = duration;
	}

	@Override
	public void triggerAlarm(String alarmName) {
		player.setShield(false);
	}

}

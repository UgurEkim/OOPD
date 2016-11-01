package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

public class Shield extends Powerup implements IAlarmListener {

	private Alarm alarm;

	public Shield(ShootEmOut SEO, float x, float y, double duration) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/shield.png", SEO, x, y);
		alarm = new Alarm("Shield", duration);
	}

	@Override
	public void effect() {
		player.setShield(true);
		alarm.addTarget(this);
		alarm.start();
	}

	@Override
	public void triggerAlarm(String alarmName) {
		player.setShield(false);
	}

}

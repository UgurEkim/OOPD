package nl.han.ica.ShootEmOut;
import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Rat extends Monster {

	public Rat(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/rat.png", SEO);
		this.setySpeed(3);
		this.setHeight(32);
		this.setWidth(20);
		
		setxPosition();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		
	}

	@Override
	public void triggerAlarm(String alarmName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

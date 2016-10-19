package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Skeleton extends Monster {

	public Skeleton(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/skeleton.png", SEO);
		this.setySpeed(2);
		this.setHeight(64);
		this.setWidth(64);
		
		setxPosition();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
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

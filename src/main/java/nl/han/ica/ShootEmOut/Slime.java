package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

public class Slime extends Monster {

	public Slime(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/slime.png", SEO);
		this.setySpeed(1);
		this.setHeight(64);
		this.setWidth(58);
		this.health = new Health(5, this);
		
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

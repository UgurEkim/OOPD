package nl.han.ica.ShootEmOut;
import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.waterworld.Swordfish;

public abstract class Monster extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener{

	protected int speed;
	
	protected ShootEmOut SEO;

	protected double attackInterval;

	protected int attackAmount;

	protected int scoreValue;
	
	protected int attackDamage;

	private Health health;

	public Monster(String fileName, ShootEmOut SEO) {
		super(new Sprite(fileName));
		this.SEO = SEO;		
		this.setY(-50);
	}

	public void attack() {

	}
	
	public void setxPosition(){
		Random random = new Random();
		float spawnX = random.nextInt(SEO.getWidth()); 
		if(spawnX > SEO.getWidth() - this.width){
			spawnX = SEO.getWidth() - this.width;
		}
		this.setX(spawnX);
	}
	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		
	}

}

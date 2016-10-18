package nl.han.ica.ShootEmOut;
import java.util.List;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Player extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener{ 

	private float attackInterval;

	private int attackAmount;

	private int attackDamage;

	private Health health;

	private Powerup[] powerup;

	public Player() {

	}

	public void attack() {

	}

	public void move(int direction) {

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerAlarm(String alarmName) {
		// TODO Auto-generated method stub
		
	}

}

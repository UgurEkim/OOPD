import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Monster extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener{

	protected int speed;

	protected double attackInterval;

	protected int attackAmount;

	protected int scoreValue;
	
	protected int attackDamage;

	private Health health;

	public Monster(float x) {
		
	}

	public void attack() {

	}

}

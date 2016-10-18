package nl.han.ica.ShootEmOut;

import java.util.List;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Player extends SpriteObject implements ICollidableWithGameObjects, IAlarmListener {

	private float attackInterval;
	private int attackAmount;
	private int attackDamage;
	private Health health;
	private Powerup[] powerup;
	private ShootEmOut SEO;

	public Player(ShootEmOut SEO, float x) {
		super(new Sprite("src/main/java/nl/han/ica/ShootEmOut/media/player.png"));
		this.setX(x);
		this.setY(700);
		this.SEO = SEO;
		setFriction(0F);
	}

	public void attack() {

	}

	public void move(int direction) {
		
	}
	
    @Override
    public void keyPressed(int keyCode, char key) {
        final int speed = 5;
        if (keyCode == SEO.LEFT) {
            setDirectionSpeed(270, speed);
        }
        if (keyCode == SEO.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == SEO.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == SEO.DOWN) {
            setDirectionSpeed(180, speed);
        }
        if (key == ' ') {
            System.out.println("Spatie!");
        }
    }

    @Override
    public void keyReleased(int keyCode, char key) {
    	setDirectionSpeed(0, 0);
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

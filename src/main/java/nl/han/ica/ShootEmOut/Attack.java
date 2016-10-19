package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Attack extends GameObject implements ICollidableWithGameObjects {

	private int speed;
	private int direction;
	private ShootEmOut SEO;

	public Attack(ShootEmOut SEO, float x, float y, int speed, int direction) {
		super(x, y, 10, 10);
		this.SEO = SEO;
		this.speed = speed;
		this.direction = direction;
		setDirectionSpeed(direction, speed);
	}

	@Override
	public void update() {
		if (this.getY() <= 0) {
			SEO.deleteGameObject(this);
		}
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CENTER);
		g.stroke(0, 0, 200);
		g.fill(0, 0, 255);
		g.ellipse(getX() + 32, getY(), 10, 10);
	}
	
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
            if (g instanceof Monster) {
                SEO.deleteGameObject(g);
                SEO.deleteGameObject(this);
            }
        }
	}


}

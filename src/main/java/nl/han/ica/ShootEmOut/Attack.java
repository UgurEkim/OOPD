package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Attack extends GameObject implements ICollidableWithGameObjects {

	private int speed;
	private int direction;
	private ShootEmOut SEO;
	private boolean player;
	private Monster monster;
	private int[] color = new int[3];

	public Attack(ShootEmOut SEO, boolean player, float x, float y, int speed, int direction) {
		super(x + 21, y, 10, 10);
		this.player = player;
		this.SEO = SEO;
		this.speed = speed;
		this.direction = direction;
		setDirectionSpeed(direction, speed);
		this.color[0] = 255;
		this.color[1] = 255;
		this.color[2] = 0;
	}

	public Attack(ShootEmOut SEO, int[] rgb, float x, float y, int speed, int direction) {
		super(x, y, 10, 10);
		this.SEO = SEO;
		this.color = rgb;
		this.speed = speed;
		this.direction = direction;
		setDirectionSpeed(direction, speed);
	}

	@Override
	public void update() {
		if (this.getY() <= 0 || this.getY() >= SEO.screenHeight || this.getX() <= 0 || this.getX() >= SEO.screenWidth) {
			SEO.deleteGameObject(this);
		}
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(g.CORNER);
		g.stroke(0, 0, 200);
		g.fill(color[0], color[1], color[2]);
		g.ellipse(getX(), getY(), this.width, this.height);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Monster && player) {
				SEO.deleteGameObject(this);
				((Monster) g).removeHealth();
			}
			if (g instanceof Player && !player) {
				SEO.deleteGameObject(this);
				((Player) g).removeHealth();
			}
		}
	}

}

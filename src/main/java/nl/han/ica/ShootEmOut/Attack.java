package nl.han.ica.ShootEmOut;

import java.util.List;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Attack extends GameObject implements ICollidableWithGameObjects {
	private ShootEmOut SEO;
	private boolean player;
	private int[] color = new int[3];

	public Attack(ShootEmOut SEO, boolean player, float x, float y, float direction, int size) {
		super(x + 24, y, size, size);
		this.player = player;
		this.SEO = SEO;
		setDirection(direction);
		setDirectionSpeed(direction, 8);
		this.color[0] = 255;
		this.color[1] = 255;
		this.color[2] = 0;
		
		SEO.playAttackSound();
	}

	public Attack(ShootEmOut SEO, int[] rgb, float x, float y, int attackSpeed, float direction, int size) {
		super(x, y, size, size);
		this.SEO = SEO;
		this.color = rgb;
		setDirection(direction);
		setDirectionSpeed(direction, attackSpeed);
		
		SEO.playEnemyAttackSound();
	}

	@Override
	public void update() {
		if (getY() <= 0 || getY() >= SEO.screenHeight || getX() <= 0 || getX() >= SEO.screenWidth) {
			SEO.deleteGameObject(this);
		}
	}

	@Override
	public void draw(PGraphics g) {
		g.ellipseMode(PConstants.CENTER);
		g.stroke(0, 0, 200);
		g.fill(color[0], color[1], color[2]);
		g.ellipse(getX(), getY(), getWidth(), height);
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

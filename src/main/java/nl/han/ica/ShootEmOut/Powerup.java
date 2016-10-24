package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Powerup extends SpriteObject {

	private ShootEmOut SEO;

	public Powerup(String fileName, ShootEmOut SEO, float x, float y) {
		super(new Sprite(fileName));
		this.setySpeed(1.0F);
		this.setHeight(32);
		this.setWidth(32);
		this.setX(x);
		this.setY(y);
		this.SEO = SEO;
	}

	public abstract void effect();
	
	public void update() {
		if (this.getY() >= SEO.screenHeight) {
			SEO.deleteGameObject(this);
		}
	}
}

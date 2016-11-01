package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Powerup extends SpriteObject {

	protected ShootEmOut SEO;
	protected Player player;

	public Powerup(String fileName, ShootEmOut SEO, float x, float y) {
		super(new Sprite(fileName));
		setySpeed(1.0F);
		setHeight(32);
		setWidth(32);
		setX(x);
		setY(y);
		this.SEO = SEO;
	}

	public abstract void effect();
	
	@Override
	public void update() {
		if (getY() >= SEO.screenHeight) {
			SEO.deleteGameObject(this);
		}
	}
	
	protected void setPlayer(Player player){
		this.player = player;
	}
}

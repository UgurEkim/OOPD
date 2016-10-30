package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public abstract class Powerup extends SpriteObject {

	protected ShootEmOut SEO;
	protected Player player;

	public Powerup(String fileName, ShootEmOut SEO, float x, float y) {
		super(new Sprite(fileName));
		this.setySpeed(1.0F);
		this.setHeight(32);
		this.setWidth(32);
		this.setX(x);
		this.setY(y);
		this.SEO = SEO;
	}

	protected abstract void effect();
	
	public void update() {
		if (this.getY() >= SEO.screenHeight) {
			SEO.deleteGameObject(this);
		}
	}
	
	protected void setPlayer(Player player){
		this.player = player;
	}
}

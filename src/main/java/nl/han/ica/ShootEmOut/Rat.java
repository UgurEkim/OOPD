package nl.han.ica.ShootEmOut;

public class Rat extends Monster {

	public Rat(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/rat.png", SEO);
		setySpeed(5 + SEO.getLevel());
		setHeight(32);
		setWidth(20);
		setScoreValue(100);
		setHealth(1 * SEO.getLevel());
		setxPosition();
	}

	/*
	 * Deletes this game object.
	 */
	public void kill() {
		SEO.deleteGameObject(this);
	}
}

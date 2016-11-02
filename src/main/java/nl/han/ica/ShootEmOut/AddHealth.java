package nl.han.ica.ShootEmOut;

public class AddHealth extends Powerup {

	public AddHealth(ShootEmOut SEO, float x, float y) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/addhealth.png", SEO, x, y);
	}

	/*
	 * Reset player health to hes maximum.
	 */
	@Override
	public void effect() {
		player.resetHealth();
	}
}

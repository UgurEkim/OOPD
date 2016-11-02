package nl.han.ica.ShootEmOut;

public class AttackSpeed extends Powerup {

	public AttackSpeed(ShootEmOut SEO, float x, float y) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/attackspeed.png", SEO, x, y);
	}

	/*
	 * Set player attack speed interval.
	 */
	@Override
	public void effect() {
		player.setAttackSpeedInterval(player.getAttackSpeedInterval() * 0.85);
	}
}

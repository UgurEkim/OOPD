package nl.han.ica.ShootEmOut;

public class AttackType extends Powerup {

	public AttackType(ShootEmOut SEO, float x, float y) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/attacktype.png", SEO, x, y);
	}

	@Override
	public void effect() {
		player.setAttackType(player.getAttackType() + 1);
	}
}

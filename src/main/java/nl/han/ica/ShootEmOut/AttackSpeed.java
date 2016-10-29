package nl.han.ica.ShootEmOut;

public class AttackSpeed extends Powerup {

	public AttackSpeed(ShootEmOut SEO, float x, float y) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/attackspeed.png", SEO, x, y);
	}
	
	@Override
	protected void effect() {
		player.setAttackSpeedInterval(player.getAttackSpeedInterval() * 0.85);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

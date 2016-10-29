package nl.han.ica.ShootEmOut;


public class AddHealth extends Powerup {

	public AddHealth(ShootEmOut SEO, float x, float y) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/movementspeed.png", SEO, x, y);
	}
	
	@Override
	protected void effect() {
		player.resetHealth();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

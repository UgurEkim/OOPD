package nl.han.ica.ShootEmOut;

public class AttackType extends Powerup {
	
	private ShootEmOut SEO;

	public AttackType(ShootEmOut SEO, float x, float y) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/attacktype.png", SEO, x, y);
		this.SEO = SEO;
	}

	@Override
	public void effect() {
		SEO.setAttackType(SEO.getAttackType() + 1);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}

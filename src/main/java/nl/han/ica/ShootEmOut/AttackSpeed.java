package nl.han.ica.ShootEmOut;

public class AttackSpeed extends Powerup {

	private float attackSpeedModifer;
	private ShootEmOut SEO;

	public AttackSpeed(ShootEmOut SEO, float x, float y, float attackSpeedModifer) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/attackspeed.png", SEO, x, y);
		this.setAttackSpeedModifer(attackSpeedModifer);
		this.SEO = SEO;
	}
	
	@Override
	public void effect() {
		SEO.setAttackSpeedModifier(this.attackSpeedModifer());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public float attackSpeedModifer() {
		return attackSpeedModifer;
	}

	public void setAttackSpeedModifer(float attackSpeedModifer) {
		this.attackSpeedModifer = attackSpeedModifer;
	}

}

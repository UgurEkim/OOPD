package nl.han.ica.ShootEmOut;

public class Rat extends Monster {

	public Rat(ShootEmOut SEO) {
		super("src/main/java/nl/han/ica/ShootEmOut/media/rat.png", SEO);
		this.setySpeed(5);
		this.setHeight(32);
		this.setWidth(20);
		this.health = 1;
		setxPosition();
	}
}

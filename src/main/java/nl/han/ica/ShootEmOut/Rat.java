package nl.han.ica.ShootEmOut;

import java.util.List;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;

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

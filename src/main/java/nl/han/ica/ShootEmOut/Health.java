package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Health extends GameObject {

	private Player player;

	private int max;
	private int bar;
	private int lives;

	public Health(int bar, int lives, Player player) {
		this.setBar(bar);
		this.setMax(bar);
		this.setLives(lives);
		this.player = player;
	}

	protected void removeLife() {
		setLives(getLives() - 1);
	}

	protected void removeBar() {
		setBar(getBar() - 1);

		if (getBar() == 0) {
			removeLife();
			if (getLives() >= 0) {
				setBar(max);
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics g) {
		if (player != null) {
			g.fill(255, 0, 0);
			g.rect(100, 750, bar * 50, 30);

			g.fill(255, 0, 0);
			g.textSize(30);
			g.textAlign(PConstants.LEFT, PConstants.TOP);
			g.text(bar + " / " + max, 100, 715);

			g.ellipseMode(PConstants.CORNER);
			g.fill(255, 100, 100);

			for (int i = 0; i < lives; i++) {
				g.ellipse(500 + i * 55, 730, 50, 50);
			}
		}
	}

	protected void resetBar() {
		this.bar = max;
	}

	protected int getMax() {
		return max;
	}

	protected void setMax(int max) {
		this.max = max;
	}

	protected int getBar() {
		return bar;
	}

	protected void setBar(int bar) {
		if (bar >= 0) {
			this.bar = bar;
		} else {
			this.bar = 0;
		}
	}

	protected int getLives() {
		return lives;
	}

	protected void setLives(int lives) {
		if (lives >= 0) {
			this.lives = lives;
		} else {
			this.lives = -1;
		}
	}
}

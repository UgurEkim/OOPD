package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Health extends GameObject {

	private Player player;

	private int max;
	private int bar;
	private int lives;
	private ShootEmOut SEO;
	
	public Health(int bar, int lives, Player player, ShootEmOut SEO) {
		setBar(bar);
		setMax(bar);
		setLives(lives);
		this.player = player;
		this.SEO = SEO;
	}

	private void removeLife() {
		setLives(getLives() - 1);
		SEO.playLoselifeSound();
		
		if(isDead()){
			SEO.gameOver();
		}
	}

	/*
	 * Removes a life bar from your total life bars.
	 * If life bar is 0, removes 1 life.
	 */
	public void removeBar() {
		setBar(getBar() - 1);

		if (getBar() == 0) {
			removeLife();
			if (getLives() >= 0) {
				setBar(getMax());
			}
		}
	}
	
	/*
	 * Checks if bar and lives are lower then 0.
	 * If yes, return true (dead) else false (alive)
	 * @return boolean
	 */
	public boolean isDead(){
		if (getBar() <= 0 && getLives() < 0) {
			return true;
		}
		
		return false;
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

	/*
	 * Reset the bar to max (used when picking up the addhealth powerup
	 */
	public void resetBar() {
		this.bar = max;
	}

	private int getMax() {
		return max;
	}

	private void setMax(int max) {
		this.max = max;
	}

	private int getBar() {
		return bar;
	}

	private void setBar(int bar) {
		if (bar >= 0) {
			this.bar = bar;
		} else {
			this.bar = 0;
		}
	}

	private int getLives() {
		return lives;
	}

	private void setLives(int lives) {
		if (lives >= 0) {
			this.lives = lives;
		} else {
			this.lives = -1;
		}
	}
}

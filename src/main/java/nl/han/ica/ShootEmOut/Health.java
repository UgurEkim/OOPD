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
		this.max =  bar;
		this.bar =  bar;
		this.lives = lives;
		this.player = player;
	}
	
	public void removeLife() {
		lives -= 1;
		bar = max;
	}

	public void removeBar() {
		bar -= 1;
		
		if(bar == 0){
			removeLife();
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g){
		if(player != null){
			g.fill(255,0,0);
			g.rect(100, 750, bar * 50, 30);
			
			g.fill(255,0,0);
			g.textSize(30);
			g.textAlign(PConstants.LEFT, PConstants.TOP);
			g.text(bar + " / " + max, 100, 715);
			
			g.ellipseMode(PConstants.CORNER);
			g.fill(255, 100, 100);
			
			for(int i = 0; i < lives; i++){
				g.ellipse(500 + i * 55, 730, 50, 50);
			}
		}
	}
	
	public void resetBar(){
		this.bar = max;
	}
}

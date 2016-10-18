package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Button extends GameObject{
// KANKER
	private String text;

	public Button(float x, float y, String text) {
		super(x, y, 300, 150);
		this.text = text;
	}

	@Override
	public void update() {

	}
	
	public void draw(PGraphics g){
		g.rectMode(g.CENTER);
		g.fill(80,200,80);
		g.rect(x, y, width, height);
		g.fill(255);
		g.textAlign(g.CENTER, g.CENTER);
		g.textSize(40);
		g.text(text, x, y);
		
	}


	private boolean isMuisBinnen(float mouseX, float mouseY) {
		if(mouseX > x - width/2 && mouseX < x + width/2 && mouseY > y - height/2 && mouseY < y + height /2){
			return true;
		}
		return false;
	}
	
	@Override
	public void mouseClicked(int x, int y, int button) {
		if (isMuisBinnen(x, y) && button == PConstants.LEFT) {
			System.out.println(text);
		}
	}
}

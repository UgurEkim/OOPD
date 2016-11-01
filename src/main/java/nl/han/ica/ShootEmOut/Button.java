package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;
import processing.core.PConstants;
import processing.core.PGraphics;

public class Button extends GameObject implements IMouseInput {

	private String text;
	private ShootEmOut SEO;

	public Button(ShootEmOut SEO, float x, float y, String text) {
		super(x, y, 300, 150);
		this.SEO = SEO;
		this.text = text;
	}
	
	@Override
	public void draw(PGraphics g) {
		g.rectMode(PConstants.CENTER);
		g.fill(80, 200, 80);
		g.rect(getX(), getY(), getWidth(), getHeight());
		g.fill(255);
		g.textAlign(PConstants.CENTER, PConstants.CENTER);
		g.textSize(40);
		g.text(text, getX(), getY());
	}

	private boolean isMuisBinnen(float mouseX, float mouseY) {
		if (mouseX > getX() - getWidth() / 2 && mouseX < getX() + getWidth() / 2 && 
				mouseY > getY() - getHeight() / 2 && mouseY < getY() + getHeight() / 2) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(int x, int y, int button) {
		if (isMuisBinnen(x, y) && button == PConstants.LEFT) {
			SEO.removeMenu(this);
		}
	}

	public String getText() {
		return text;
	}

	@Override
	public void update() {
		
	}
}

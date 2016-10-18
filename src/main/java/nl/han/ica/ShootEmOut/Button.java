package nl.han.ica.ShootEmOut;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.UserInput.IMouseInput;

public class Button extends SpriteObject implements IMouseInput {

	private ShootEmOut SEO;
	private TextObject text;
	private static Sprite sprite = new Sprite("src/main/java/nl/han/ica/ShootEmOut/media/shield.png");

	public Button(ShootEmOut SEO, float x, float y, String text) {
		super(sprite);
		this.SEO = SEO;
		this.x = x;
		this.y = y;
		this.text = new TextObject(text, 30);
		this.width = 300;
		this.height = 150;
	}

	@Override
	public void update() {

	}


	private boolean isMuisBinnen(float mouseX, float mouseY) {
		return (mouseX >= x && mouseX <= + this.width && mouseY >= y && mouseY <= y + this.height);
	}
	
	@Override
	public void mouseClicked(int x, int y, int button) {
		if (isMuisBinnen(x, y) && button == SEO.LEFT) {
			System.out.println(text.getText());
		}
	}
}

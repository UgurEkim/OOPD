package nl.han.ica.ShootEmOut;

import java.util.ArrayList;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class ShootEmOut extends GameEngine {

	private Powerup[] powerup;
	private Player player;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	protected int screenWidth;
	protected int screenHeight;

	public static void main(String[] args) {
		PApplet.main("nl.han.ica.ShootEmOut.ShootEmOut");
	}

	@Override
	public void setupGame() {
		screenWidth = 700;
		screenHeight = 800;
		createView();
		initMenu();
	}

	private void createView() {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/ShootEmOut/media/bg.png"));

		setView(view);
		size(screenWidth, screenHeight);
	}

	private void initMenu() {
		Button startButton = new Button(this, screenWidth / 2, 300, "Start");
		buttons.add(startButton);
		
		Button highscoreButton = new Button(this, screenWidth / 2, 500, "Highscore");
		buttons.add(highscoreButton);
		
		for (Button b : buttons) {
			addGameObject(b, b.getX(), b.getY());
		}
	}

	protected void removeMenu() {
		for (Button b : buttons) {
			switch (b.getText()) {
			case "Start":
				player = new Player(this, screenWidth / 2 - 32);
				addGameObject(player);
				break;

			case "Highscore":
				
				break;

			default:
				break;

			}
			deleteGameObject(b);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}

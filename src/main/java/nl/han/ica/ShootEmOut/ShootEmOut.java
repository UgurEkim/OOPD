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
	
	public static void main(String[] args) {
		PApplet.main("nl.han.ica.ShootEmOut.ShootEmOut");
	}

	@Override
	public void setupGame() {
		createViewWithoutViewport(700, 800);
		initButtons();
	}
	
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/ShootEmOut/media/bg.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }
    
    private void initButtons() {
    	Button startButton = new Button(this, 300, 200, "Start");
    	buttons.add(startButton);
    	Button highscoreButton = new Button(this, 300, 400, "Highscore");
    	buttons.add(highscoreButton);
    	Button exitButton = new Button(this, 300, 600, "Afsluiten");
    	buttons.add(exitButton);
    	
    	for(Button b : buttons) {
    		addGameObject(b, b.getX(), b.getY());
    	}
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

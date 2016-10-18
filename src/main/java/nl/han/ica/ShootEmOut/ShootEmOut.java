package nl.han.ica.ShootEmOut;
import java.util.ArrayList;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class ShootEmOut extends GameEngine {
	// KANKER
	private Powerup[] powerup;
	private Player player;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private int screenWidth;
	private int screenHeight;
	
	public static void main(String[] args) {
		PApplet.main("nl.han.ica.ShootEmOut.ShootEmOut");
	}

	@Override
	public void setupGame() {
		screenWidth = 700;
		screenHeight = 800;
		createViewWithoutViewport();
		initButtons();
	}
	
    private void createViewWithoutViewport() {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/ShootEmOut/media/bg.png"));

        setView(view);
        size(screenWidth, screenHeight);
    }
    
    private void initButtons() {
    	Button startButton = new Button(screenWidth/2, 300, "Start");
    	buttons.add(startButton);
    	Button highscoreButton = new Button(screenWidth/2, 500, "Highscore");
    	buttons.add(highscoreButton);
    	Button exitButton = new Button(screenWidth/2, 700, "Afsluiten");
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

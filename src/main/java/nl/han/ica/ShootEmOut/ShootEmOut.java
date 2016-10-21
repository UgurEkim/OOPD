package nl.han.ica.ShootEmOut;

import java.util.ArrayList;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameThread;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class ShootEmOut extends GameEngine implements IAlarmListener {

	private Alarm monsterAlarm;
	private Alarm levelTimeAlarm;
	private int spawnSpeed;
	private int level;
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
	
	public int getLevel(){
		return level;
	}

	protected void removeMenu(Button buttonClicked) {
		switch (buttonClicked.getText()) {
		case "Start":
			addGameObject(new Player(this, screenWidth / 2 - 26));
			level = 1;
			spawnSpeed = 1; 
			monsterSpawner();
			levelTimeAlarmReset();
			break;

		case "Highscore":

			break;

		default:
			break;

		}

		for (Button b : buttons) {
			deleteGameObject(b);
		}
	}

	@Override
	public void update() {
		
	}
	
	public void monsterSpawner() {
		Random random = new Random();
		this.monsterAlarm = new Alarm("Monster", random.nextDouble() * (3.0 / (spawnSpeed + level)));
		monsterAlarm.addTarget(this);
		monsterAlarm.start();
	}
	
	public void levelTimeAlarmReset(){
		this.levelTimeAlarm = new Alarm("Time", 3); 
		levelTimeAlarm.addTarget(this);
		levelTimeAlarm.start();
	}
	
	
	public void spawnBoss(){
		addGameObject(new Boss(this));
	}

	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Monster") {
			Random random = new Random();
			int monsterNumber = random.nextInt(4);
			Monster m;

			switch (monsterNumber) {
			case 0:
				m = new Slime(this);
				addGameObject(m);
				break;
			case 1:
				m = new Skeleton(this);
				addGameObject(m);
				break;
			default:
				m = new Rat(this);
				
				addGameObject(m);
				break;
			}
			
			if(spawnSpeed < 5){
				monsterSpawner();
			}
		}
		
		if(alarmName == "Time"){
			spawnSpeed += 1;
			System.out.println(spawnSpeed);
			if(spawnSpeed >= 5){
				spawnBoss();
			}
			else{
				levelTimeAlarmReset();
			}
		}

	}

}

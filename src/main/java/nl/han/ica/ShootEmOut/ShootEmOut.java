package nl.han.ica.ShootEmOut;

import java.util.ArrayList;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class ShootEmOut extends GameEngine implements IAlarmListener {

	private Player player;

	private Alarm monsterAlarm;
	private Alarm levelTimeAlarm;

	private ArrayList<Button> buttons;
	private ArrayList<Score> scores;

	private TextObject scoreDashboard;

	private IPersistence persistence;

	private int score;

	protected int screenWidth;
	protected int screenHeight;

	private int spawnSpeed;
	private int level;

	public static void main(String[] args) {
		PApplet.main("nl.han.ica.ShootEmOut.ShootEmOut");
	}

	@Override
	public void setupGame() {
		screenWidth = 700;
		screenHeight = 800;
		buttons = new ArrayList<Button>();
		scores = new ArrayList<Score>();
		persistence = new FilePersistence("main/java/nl/han/ica/waterworld/media/highscore.txt");

		setScore(0);
		createView();
		initMenu();
	}

	private void createDashboard(int width, int height) {
		Dashboard dashboard = new Dashboard(250, 20, width, height);
		scoreDashboard = new TextObject("Score: 0", 24);
		scoreDashboard.setForeColor(255, 255, 255, 255);
		dashboard.addGameObject(scoreDashboard);
		addGameObject(dashboard);
	}

	private void createView() {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage("src/main/java/nl/han/ica/ShootEmOut/media/bg.png"));

		setView(view);
		size(screenWidth, screenHeight);
	}

	protected void initMenu() {
		Button startButton = new Button(this, screenWidth / 2, 300, "Start");
		buttons.add(startButton);

		Button highscoreButton = new Button(this, screenWidth / 2, 500, "Highscore");
		buttons.add(highscoreButton);

		for (Button b : buttons) {
			addGameObject(b, b.getX(), b.getY());
		}
	}

	protected int getLevel() {
		return level;
	}

	protected void removeMenu(Button buttonClicked) {
		switch (buttonClicked.getText()) {
		case "Restart":
		case "Start":
			player = new Player(this, screenWidth / 2 - 26);
			addGameObject(player);
			level = 1;
			spawnSpeed = 1;
			monsterSpawner();
			levelTimeAlarmReset();
			createDashboard(screenWidth, 100);
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

	public void monsterSpawner() {
		Random random = new Random();
		monsterAlarm = new Alarm("Monster", random.nextDouble() * (5.0 / (spawnSpeed + (level * 2))));
		monsterAlarm.addTarget(this);
		monsterAlarm.start();
	}
	
	public void levelTimeAlarmReset(){
		levelTimeAlarm = new Alarm("Time", 15); 
		levelTimeAlarm.addTarget(this);
		levelTimeAlarm.start();
	}

	public void spawnBoss() {
		monsterAlarm.stop();
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
			monsterSpawner();
		}

		if (alarmName == "Time") {
			setSpawnSpeed(getSpawnSpeed() + 1);
			if (getSpawnSpeed() >= 5) {
				spawnBoss();
			} else {
				levelTimeAlarmReset();
			}
		}
	}
	
	protected void gameOver() {
		deleteAllGameOBjects();
		monsterAlarm.stop();
		levelTimeAlarm.stop();
		setScore(0);
		setLevel(1);
		setSpawnSpeed(1);
		
		Button restartButton = new Button(this, screenWidth / 2, 400, "Restart");
		addGameObject(restartButton, restartButton.getX(), restartButton.getY());	
		buttons.add(restartButton);
		
		TextObject gameOverText = new TextObject("Game Over!", 30);
		Dashboard dashboard = new Dashboard(screenWidth / 2, 200, width, height);
		gameOverText.setForeColor(255, 255, 255, 255);
		dashboard.addGameObject(gameOverText);
		addGameObject(dashboard);
		
	}

	protected void addScore(int value) {
		this.score = this.score + value;
		scoreDashboard.setText("Score: " + score);
	}

	protected void nextLevel() {
		setLevel(getLevel() + 1);
		setSpawnSpeed(1);
		monsterSpawner();
		levelTimeAlarmReset();
	}

	@Override
	public void update() {

	}

	protected int getScore() {
		return score;
	}

	protected void setScore(int score) {
		this.score = score;
	}

	protected float getPlayerX() {
		return player.getX();
	}

	protected float getPlayerY() {
		return player.getY();
	}

	protected void addHighscore(Score score) {
		scores.add(score);
	}

	private int getSpawnSpeed() {
		return spawnSpeed;
	}

	private void setSpawnSpeed(int spawnSpeed) {
		this.spawnSpeed = spawnSpeed;
	}

	private void setLevel(int level) {
		this.level = level;
	}

	protected Player getPlayer() {
		return player;
	}
}

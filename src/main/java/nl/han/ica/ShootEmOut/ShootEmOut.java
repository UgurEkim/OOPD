package nl.han.ica.ShootEmOut;

import java.util.ArrayList;
import java.util.Random;

import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class ShootEmOut extends GameEngine implements IAlarmListener {

	private Player player;

	private Alarm monsterAlarm;
	private Alarm levelTimeAlarm;

	private Sound backgroundmusic;

	private ArrayList<Score> highscores;
	private ArrayList<Button> buttons;

	private TextObject scoreDashboard;

	protected Score score;

	protected int screenWidth;
	protected int screenHeight;

	private int spawnInterval;
	private int level;

	public static void main(String[] args) {
		PApplet.main("nl.han.ica.ShootEmOut.ShootEmOut");
	}

	@Override
	public void setupGame() {
		screenWidth = 700;
		screenHeight = 800;
		highscores = new ArrayList<Score>();
		buttons = new ArrayList<Button>();
		

		initializeSound();
		createView();
		initMenu();
	}

	protected void createDashboard(int x, int y, int width, int height, String text) {
		Dashboard dashboard = new Dashboard(x, y, width, height);
		scoreDashboard = new TextObject(text, 24);
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

	public void initMenu() {
		clearButtons();
		Button button;
		
		button = new StartButton(this, 300);
		addButton(button);
		
		button = new HighscoreButton(this, 500);
		addButton(button);
		
		initButtons();
	}

	public void addButton(Button button) {
		buttons.add(button);
	}

	protected void initButtons() {
		for (Button button : buttons) {
			addGameObject(button, button.getX(), button.getY());
		}
	}

	public void clearButtons() {
		if (!buttons.isEmpty()) {
			buttons.clear();
		}
	}

	/*
	 * Spawn a monster object depending on the level and spawnInterval
	 */
	public void monsterSpawner() {
		Random random = new Random();
		monsterAlarm = new Alarm("Monster", random.nextDouble() * (5.0 / (spawnInterval + (level * 2))));
		monsterAlarm.addTarget(this);
		monsterAlarm.start();
	}

	/*
	 * Resets the level alarm
	 */
	public void levelTimeAlarmReset() {
		levelTimeAlarm = new Alarm("Time", 5);
		levelTimeAlarm.addTarget(this);
		levelTimeAlarm.start();
	}

	private void spawnBoss() {
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
			spawnInterval += 1;
			if (spawnInterval >= 5) {
				spawnBoss();
			} else {
				levelTimeAlarmReset();
			}
		}
	}

	/*
	 * Removes all gameobjects, stops all alarms and sets all variables back to
	 * normal, adds current score to highscore list
	 */
	public void gameOver() {
		deleteAllGameOBjects();

		highscores.add(score);

		monsterAlarm.stop();
		levelTimeAlarm.stop();

		initMenu();

		createDashboard(135, 100, screenWidth, screenHeight, "Game Over!");

	}

	/*
	 * Add score to your current score.
	 * 
	 * @param int value
	 */
	public void addScore(int value) {
		score.setScore(value);
		scoreDashboard.setText("Score: " + score.getScore());
	}

	/*
	 * Go to next level and increase game stats.
	 */
	public void nextLevel() {
		this.level += 1;
		this.spawnInterval = 1;
		monsterSpawner();
		levelTimeAlarmReset();
	}

	private void initializeSound() {
		backgroundmusic = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/bgm.mp3");
		backgroundmusic.loop(-1);
	}

	@Override
	public void update() {

	}

	public int getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	protected void setLevel(int level) {
		this.level = level;
	}

	protected void setSpawnInterval(int spawnInterval) {
		this.spawnInterval = spawnInterval;
	}

	public ArrayList<Score> getHighscores() {
		return highscores;
	}
}

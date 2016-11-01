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
	private Sound levelupSound;
	private Sound attackSound;
	private Sound enemyAttackSound;
	private Sound powerupSound;
	private Sound loselifeSound;
	private Sound killEnemySound;

	private ArrayList<TextObject> highscores;

	private TextObject scoreDashboard;

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
		highscores = new ArrayList<TextObject>();

		initializeSound();
		setScore(0);
		createView();
		initMenu();		
	}

	private void createDashboard(int x, int y, int width, int height, String text) {
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

	protected void initMenu() {
		addButton("Start", screenWidth / 2, 300);
		addButton("Highscore", screenWidth / 2, 500);
		
	}

	protected int getLevel() {
		return level;
	}

	protected void removeMenu(Button buttonClicked) {
		switch (buttonClicked.getText()) {
		case "Restart":
		case "Start":
			deleteAllGameOBjects();
			player = new Player(this, screenWidth / 2 - 26);
			addGameObject(player);
			level = 1;
			spawnSpeed = 1;
			monsterSpawner();
			levelTimeAlarmReset();
			createDashboard(250, 20, screenWidth, 100, "Score: " + getScore());
			break;

		case "Highscore":
			deleteAllGameOBjects();
			addButton("Back", screenWidth / 2, 450);
			
			if (!highscores.isEmpty()) {
				for (int i = 0; i < highscores.size(); i++) {
					createDashboard(150, 50 + (i * 20), screenWidth, screenHeight, highscores.get(i).getText());
				}
			}
			else {
				createDashboard(125, 150, screenWidth, screenHeight, "No highscores");
			}
			break;

		case "Back":
			deleteAllGameOBjects();
			initMenu();
			break;

		default:
			break;

		}
	}

	/*
	 * Spawn monster objects.
	 */
	public void monsterSpawner() {
		Random random = new Random();
		monsterAlarm = new Alarm("Monster", random.nextDouble() * (5.0 / (spawnSpeed + (level * 2))));
		monsterAlarm.addTarget(this);
		monsterAlarm.start();
	}

	/*
	 * Resets the level alarm
	 */
	public void levelTimeAlarmReset() {
		levelTimeAlarm = new Alarm("Time", 15);
		levelTimeAlarm.addTarget(this);
		levelTimeAlarm.start();
	}

	/*
	 * Spawns boss
	 */
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

	/*
	 * Resets the game, adds current score to highscore list
	 */
	protected void gameOver() {
		deleteAllGameOBjects();
		
		TextObject t = new TextObject(Integer.toString(getScore()), 24);
		highscores.add(t);
		
		monsterAlarm.stop();
		levelTimeAlarm.stop();
		setScore(0);
		setLevel(1);
		setSpawnSpeed(1);

		addButton("Restart", screenWidth / 2, 300);
		addButton("Highscore", screenWidth / 2, 500);

		createDashboard(135, 100, screenWidth, screenHeight, "Game Over!");

	}

	private void addButton(String text, int x, int y) {
		Button button = new Button(this, x, y, text);
		addGameObject(button, button.getX(), button.getY());
	}

	/*
	 * Add score to your current score.
	 * 
	 * @param int value
	 */
	protected void addScore(int value) {
		this.score = this.score + value;
		scoreDashboard.setText("Score: " + score);
	}

	/*
	 * Go to next level and increase game stats.
	 */
	protected void nextLevel() {
		setLevel(getLevel() + 1);
		setSpawnSpeed(1);
		monsterSpawner();
		levelTimeAlarmReset();
		levelupSound.rewind();
		levelupSound.play();
	}

	/*
	 * Initialize all sound files
	 */
	private void initializeSound() {
		backgroundmusic = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/bgm.mp3");
		backgroundmusic.loop(-1);

		levelupSound = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/level.mp3");
		attackSound = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/shoot.wav");
		enemyAttackSound = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/enemyshoot.mp3");
		powerupSound = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/powerup.mp3");
		loselifeSound = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/loselife.mp3");
		killEnemySound = new Sound(this, "src/main/java/nl/han/ica/ShootEmOut/media/kill.mp3");
	}

	@Override
	public void update() {

	}

	/*
	 * Play sounds
	 */
	public void playAttackSound() {
		attackSound.rewind();
		attackSound.play();
	}

	public void playEnemyAttackSound() {
		enemyAttackSound.rewind();
		enemyAttackSound.play();
	}

	public void playPowerupSound() {
		powerupSound.rewind();
		powerupSound.play();
	}

	public void playLoselifeSound() {
		loselifeSound.rewind();
		loselifeSound.play();
	}

	public void playKillEnemySound() {
		killEnemySound.rewind();
		killEnemySound.play();
	}

	/*
	 * Getters and setters
	 */
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

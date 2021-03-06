package nl.han.ica.ShootEmOut;

public class StartButton extends Button {
	
	public StartButton(ShootEmOut SEO, float y) {
		super(SEO, SEO.screenWidth / 2, y, "Start");
	}
	
	@Override
	public void clicked() {
		SEO.deleteAllGameOBjects();
		SEO.setPlayer(new Player(SEO, SEO.screenWidth / 2 - 26));
		SEO.addGameObject(SEO.getPlayer());
		SEO.setLevel(1);
		SEO.setSpawnInterval(1);

		SEO.score = new Score();
		SEO.monsterSpawner();
		SEO.levelTimeAlarmReset();
		SEO.createDashboard(250, 20, SEO.screenWidth, 100, "Score: " + SEO.score.getScore());
	}

}

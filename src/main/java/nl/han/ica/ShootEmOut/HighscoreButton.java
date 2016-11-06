package nl.han.ica.ShootEmOut;

public class HighscoreButton extends Button {

	private ShootEmOut SEO;
	
	public HighscoreButton(ShootEmOut SEO, float y) {
		super(SEO.screenWidth / 2, y, "Highscore");
		this.SEO = SEO;
	}
	
	@Override
	public void clicked() {
		SEO.deleteAllGameOBjects();
		SEO.clearButtons();
		Button button = new BackButton(SEO, 700);
		SEO.addButton(button);
		SEO.initButtons();
		
		if (!SEO.getHighscores().isEmpty()) {
			for (int i = 0; i < SEO.getHighscores().size(); i++) {
				SEO.createDashboard(150, 50 + (i * 20), SEO.screenWidth, SEO.screenHeight, SEO.getHighscores().get(i).toString());
			}
		} else {
			SEO.createDashboard(125, 150, SEO.screenWidth, SEO.screenHeight, "No highscores");
		}
	}
}

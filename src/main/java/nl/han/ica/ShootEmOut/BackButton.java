package nl.han.ica.ShootEmOut;

public class BackButton extends Button {

	private ShootEmOut SEO;
	
	public BackButton(ShootEmOut SEO, float y) {
		super(SEO.screenWidth / 2, y, "Back");
		this.SEO = SEO;
	}
	
	@Override
	public void clicked() {
		SEO.deleteAllGameOBjects();
		SEO.initMenu();
	}

}

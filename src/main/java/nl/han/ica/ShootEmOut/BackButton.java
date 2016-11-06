package nl.han.ica.ShootEmOut;

public class BackButton extends Button {
	
	public BackButton(ShootEmOut SEO, float y) {
		super(SEO, SEO.screenWidth / 2, y, "Back");
	}
	
	@Override
	public void clicked() {
		SEO.deleteAllGameOBjects();
		SEO.initMenu();
	}

}

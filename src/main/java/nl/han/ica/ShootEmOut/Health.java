package nl.han.ica.ShootEmOut;

public class Health {

	private Player player;
	private Monster monster;
	
	private int bar;

	private int lives;

	private float barX;

	private int barY;

	private int livesY;

	private int livesX;

	public Health(int bar, int lives, Player player) {
		this.bar =  bar;
		this.lives = lives;
		this.player = player;
	}
	
	public Health(int bar, Monster monster){
		this.bar = bar;
		this.lives = 1;
		this.monster = monster;
	}

	public void removeLife() {
		lives -= 1;
		
		if(lives == 0){
			if(player != null){
				player.SEO.deleteGameObject(player);
			}
			else{
				monster.SEO.deleteGameObject(monster);
			}
		}
	}

	public void removeBar() {
		bar -= 1;
		
		if(bar == 0){
			removeLife();
		}
	}

	public void draw() {

	}

}

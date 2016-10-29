package nl.han.ica.ShootEmOut;
//wtf
public class Score {

	private int score;
	private String name;

	public Score(String name) {
		this.name = name;
		this.score = 0;
	}

	public void addScore(int value) {
		this.score += value;
	}

	public String toString() {
		return name + ": " + score;
	}
	
	public int getScore() {
		return score;
	}
}

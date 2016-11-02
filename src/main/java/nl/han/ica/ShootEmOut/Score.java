package nl.han.ica.ShootEmOut;

public class Score {

	private int score;

	public Score() {
		this.score = 0;
	}

	public void setScore(int value) {
		this.score += value;
	}

	public int getScore() {
		return score;
	}
	
	@Override
	public String toString(){
		return Integer.toString(score);
	}
}

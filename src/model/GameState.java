package model;

public class GameState {

	private static final long START_NANO_TIME = 60000000000L;

	int score;
	long remainingNanoTime;

	GameState() {
		score = 0;
		remainingNanoTime = START_NANO_TIME;
	}

}

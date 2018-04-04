package model;

public class GameModel {
	
	private GameState gameState;
	// TODO fill code
	public GameModel(){
		gameState = new GameState();
	}
	public void decreaseRemainingTime(long decreasedNanoTime) {
		gameState.remainingNanoTime=gameState.remainingNanoTime-decreasedNanoTime;
	}
	public int getScore() {
		return gameState.score;
	}
    public long getTimeNanosecond() {
    		return gameState.remainingNanoTime;
    }
    public int getTimeSecond() {
		return (int) (getTimeNanosecond()*0.000000001);
    }
}
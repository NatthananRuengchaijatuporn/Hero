package model;

import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scene.BackgroundScene;
import scene.StageSelection;
import scene.FirstUI;
import scene.FirstUI;

public class GameCanvas extends Canvas{

	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;
	private GameModel model;
	private Thread gameAnimation;
	private boolean isAnimationRunning;
	private Canvas canvas;
	private Font font = new Font("Monospace", 50);
	public GameCanvas(GameModel model) {
		super(1300,710);
		this.model=model;
		isAnimationRunning = false;
		
		// TODO fill code
	}
	public void startAnimation() {
		gameAnimation = new Thread(this::animationLoop, "Game Animation Thread");
		gameAnimation.setDaemon(true);
		isAnimationRunning = true;
		gameAnimation.start();
	}

	public void stopAnimation() {
		isAnimationRunning = false;
	}

	private void animationLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isAnimationRunning) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateAnimation(now);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void updateAnimation(long now) {
		GraphicsContext gc = this.getGraphicsContext2D();
		//gc.setFill(Color.TRANSPARENT);
		//gc.fillRect(0, 0, 1300, 710);
//		gc.setFill(Color.TRANSPARENT);
//		gc.fillRect(0, 0, 1300, 710);
		gc.clearRect(0, 0, 320, 300);
		gc.setFill(Color.GOLD);
		gc.setTextBaseline(VPos.TOP);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setFont(font);
		gc.fillText("Score: "+FirstUI.getHero().getScore(), 20, 10);
		gc.fillText("Time: "+model.getTimeSecond(), 20, 65);
		gc.fillText("Level: "+FirstUI.getHero().getLv(), 25, 120);
		gc.setTextAlign(TextAlignment.RIGHT);
		// gc.setFill(Color.GOLD);
		// gc.fillText("Level: 1", 1300-20, 10);
		gc.fillText("HP:", 1300 - 340, 10);
		gc.setFill(Color.GRAY);
		gc.fillRect(1300 - 320, 12, 300, 50);
		gc.setFill(Color.GREEN);
		float d = (FirstUI.getHero().getHp()*100/FirstUI.getHero().getMaxHp());
		//System.out.println(d*(300/BackgroundScene.getHero().getMaxHp()));
		gc.fillRect(1300 - 320, 12,(d*3), 50);
		gc.setFill(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeRect(1300 - 320, 12, 300, 50);
		gc.setFill(Color.GOLD);
		gc.fillText("EXP:", 1300 - 340, 70);
		gc.setFill(Color.GRAY);
		gc.fillRect(1300 - 320, 72, 300, 50);
		gc.setFill(Color.GREEN);
		gc.fillRect(1300 - 320, 72, ((FirstUI.getHero().getExp())/20)*300, 50);
		gc.setFill(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeRect(1300 - 320, 72, 300, 50);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setFill(Color.BLACK);
		gc.setFont(new Font("Monospace", 80));
		gc.fillText("Stage "+ StageSelection.checkStage, 520, 10);
		gc.setTextAlign(TextAlignment.CENTER);
		
		
		
		// TODO fill code

	}

	

}

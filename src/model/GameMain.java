package model;

import java.util.Optional;


import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import scene.BackgroundScene;
import scene.Stage2Scene;
import scene.FirstUI;
import window.SceneManager;
import javafx.scene.control.Alert.AlertType;

public class GameMain {

	private static GameModel model;
	private static GameCanvas canvas;
	private static GameLogic logic;

	public static Canvas newGame() {
		model = new GameModel();
		canvas = new GameCanvas(model);
		logic = new GameLogic(model, canvas);
		logic.startGame();
		canvas.startAnimation();
		// TODO fill code
		return canvas;
		
	}
	
	public static void stopGameLogicAndAnimation() {
		logic.stopGame();
		canvas.stopAnimation();
		// TODO fill code 
		
	}
	
	private static void displayResult() {
		Alert alert = new Alert(AlertType.NONE,"Game over! Your score is "+FirstUI.hero.getScore(),ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		 if (result.isPresent() && result.get() == ButtonType.OK) {
			 FirstUI.resource.normal.stop();
			 FirstUI.resource.boss.stop();
		     FirstUI.resource.open.setCycleCount(100);
		     FirstUI.resource.open.play();
		     SceneManager.gotoStageSelection();
		 }
		// TODO fill code
		
	}

	public static void stopGame() {
		stopGameLogicAndAnimation();
		Platform.runLater(GameMain::displayResult);
	}

}

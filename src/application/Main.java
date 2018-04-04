package application;

import javafx.application.Application;
import javafx.stage.Stage;
import window.SceneManager;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMain();
			primaryStage.setTitle("THE HERO");
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.sizeToScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

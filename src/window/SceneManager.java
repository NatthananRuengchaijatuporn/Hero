package window;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scene.BackgroundScene;
import scene.Enter;
import scene.Stage2Scene;
import scene.StageSelection;
import scene.FirstUI;
import scene.LogInUI;
import scene.SignUpUI;

public final class SceneManager {

	private static Stage primaryStage;
	private static int check=0;
	private static Stage2Scene stage2;
	private static BackgroundScene stage1;

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoMain() {
		FirstUI first = new FirstUI();
		primaryStage.setScene(first.getScene());
		//TODO Fill Code
	}
	public static void gotoStart(int i) {
		Enter enter = new Enter(i);
		primaryStage.setScene(enter.getScene());
		
		//TODO Fill Code
	}
	public static void gotoSignIn() {
		LogInUI login = new LogInUI();
		primaryStage.setScene(login.getScene());
	}
	public static void gotoSignUp() {
		SignUpUI signup = new SignUpUI();
		primaryStage.setScene(signup.getScene());
	}
	public static void gotoStageSelection() {
		StageSelection selection = new StageSelection();
		primaryStage.setScene(selection.getScene());
		//TODO Fill Code
	}
	public static void gotoStage1() {
		stage1 = new BackgroundScene();
		primaryStage.setScene(stage1.getScene());
		//TODO Fill Code
	}
	public static void gotoStage2() {
		stage2 = new Stage2Scene();
		primaryStage.setScene(stage2.getScene());
		//TODO Fill Code
	}
	
}

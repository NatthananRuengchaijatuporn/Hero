package scene;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import window.SceneManager;

public class SignUpUI implements GetScene{
	private final VBox ROOT = new VBox(15);
	private Scene scene = new Scene(ROOT,600,400);
	public SignUpUI() {
		ROOT.setAlignment(Pos.CENTER);
		ROOT.setPadding(new Insets(20));
		Canvas canvas = new Canvas(250,100);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//gc.fillRect(0, 0, 200, 100);
		//gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.RED);
		gc.setFont(new Font("Zapfino",30));
		gc.fillText("SIGN UP", 20, 75);
		gc.setStroke(Color.GOLD);
		gc.setLineWidth(1.5);
		gc.strokeText("SIGN UP", 20, 75);
		ROOT.getChildren().add(canvas);
		Button stop = new Button();
		stop.setShape(new Circle(100,Color.BLACK));
		stop.setAlignment(Pos.BOTTOM_LEFT);
		stop.setPrefSize(55,55);
		stop.setTranslateX(-240);
		stop.setTranslateY(22);
		stop.setGraphic(new ImageView(FirstUI.resource.stop));
		//stop.setDepthTest(DepthTest.DISABLE);
		stop.setCancelButton(true);
		stop.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				FirstUI.click.play(100);
				Platform.exit();
				// TODO Auto-generated method stub
				
			}
			
		});
//		ROOT.getChildren().add(stop);
		String backgroud_path= ClassLoader.getSystemResource("photo/Sega-Game-Backgrounds.jpg").toString();
		Image bimg = new Image(backgroud_path);
		ROOT.setBackground(new Background(new BackgroundImage(bimg,null,null,null,new BackgroundSize(1000, 800, false, false,false,true))));
		HBox hpass = new HBox(5);
		HBox hpass2 = new HBox(5);
		hpass.setAlignment(Pos.CENTER);
		input.InputField inputfielduser = new input.InputField("User ");
		inputfielduser.setAlignment(Pos.CENTER);
		ROOT.getChildren().add(inputfielduser);
		PasswordField pass = new PasswordField();
		pass.setPromptText("Enter Password");
		Label passl = new Label("Password :");
		passl.setTextFill(Color.WHITE);
		Label space = new Label("      ");
		pass.setPrefWidth(200);
		hpass.getChildren().addAll(passl,pass,space);
		ROOT.getChildren().add(hpass);
		Label passl2 = new Label("Re-Password :");
		PasswordField pass2 = new PasswordField();
		pass2.setPromptText("Re-Enter Password");
		passl2.setTextFill(Color.WHITE);
		pass2.setPrefWidth(200);
		Label space2 = new Label("            ");
		hpass2.getChildren().addAll(passl2,pass2,space2);
		hpass2.setAlignment(Pos.CENTER);
		ROOT.getChildren().add(hpass2);
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		Button backButton = new Button("Back To LogIn");
		Button signUpButton =  new Button("Sign Up");
		backButton.setPrefWidth(150);
		signUpButton.setPrefWidth(150);
		hbox.getChildren().addAll(signUpButton,backButton);
		ROOT.getChildren().add(hbox);
		signUpButton.setDisable(false);
		if(inputfielduser.getInputData()!=null)
		backButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				FirstUI.click.play(100);
				SceneManager.gotoSignIn();
				// TODO Auto-generated method stub
				
			}
		});
		signUpButton.setOnAction(new input.SignUpEventHandle(inputfielduser,pass, pass2));
		ROOT.getChildren().add(stop);
	}
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}
}

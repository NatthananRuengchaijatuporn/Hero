package scene;
	

import java.awt.Graphics2D;

import com.sun.javafx.geom.Shape;

import gameresource.Resource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import logic.Hero;
import window.SceneManager;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;


public class FirstUI implements GetScene{
	private final static GridPane ROOT = new GridPane();
	private final static Scene SCENE = new Scene(ROOT,600,400);
	public static Resource resource;
	public static AudioClip sound;
	public static AudioClip click;
	public static Hero hero = new Hero("..", 100, 25, 10);
	public FirstUI() {
			resource = new Resource();
			resource.loadResourses();
			ROOT.setAlignment(Pos.CENTER);
			sound = resource.open;
			sound.setCycleCount(100000);
			sound.setVolume(10);
			sound.play();
			click = resource.click;
			VBox box = new VBox();
			Canvas canvas = new Canvas(600,400);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.RED);
			gc.setFont(new Font("Zapfino",50));
			gc.fillText("HERO", 75, 270);
			gc.setStroke(Color.GOLD);
			gc.setLineWidth(1.5);
			gc.strokeText("HERO", 75, 270);
			ROOT.getChildren().add(canvas);
			//ROOT.setPadding(new Insets(220, 200, 0, 0));
			String backgroud_path= ClassLoader.getSystemResource("photo/Sega-Game-Backgrounds.jpg").toString();
			Image bimg = new Image(backgroud_path);
			Button start = new Button("Start !!!");
			//box.setAlignment(Pos.TOP_RIGHT);
			start.setAlignment(Pos.CENTER_LEFT);
			start.setPadding(new Insets(10));
			Button stop = new Button();
			stop.setShape(new Circle(100,Color.BLACK));
			stop.setAlignment(Pos.BOTTOM_LEFT);
			stop.setPrefSize(55,55);
			stop.setTranslateX(-178);
			stop.setTranslateY(3);
			stop.setGraphic(new ImageView(resource.stop));
			//stop.setDepthTest(DepthTest.DISABLE);
			stop.setCancelButton(true);
			stop.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					click.play(100);
					Platform.exit();
					// TODO Auto-generated method stub
					
				}
				
			});
			box.getChildren().add(start);
			box.getChildren().add(stop);
			box.setTranslateX(180);
			box.setTranslateY(300);
			ROOT.getChildren().add(box);
			ROOT.setBackground(new Background(new BackgroundImage(bimg,null,null,null,new BackgroundSize(1000, 800, false, false,false,true))));
			start.setOnMouseClicked(new EventHandler<MouseEvent>() {
				 public void handle(MouseEvent t) {
					 click.play(100);
					 SceneManager.gotoSignIn();
				 }
			});
	}

	public Scene getScene() {
		// TODO Auto-generated method stub
		return SCENE;
	}
	public static Hero getHero() {
		return hero;
	}

}

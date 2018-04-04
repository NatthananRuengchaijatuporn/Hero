package scene;

import java.io.Serializable;

import game.storage.ScoreData;
import gameresource.Resource;
import input.SignInEventHandle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import utility.ResourceManager;
import window.SceneManager;

public class StageSelection implements GetScene {
	public static Button stage2;
	private final StackPane root = new StackPane();
	private Scene scene = new Scene(root,600,400);
	private Font font = new Font("Monospace", 50);
	private Font font1 = new Font("Monospace", 30);
	public static int checkStage=0;
	public void setCheckStage(int checkStage) {
		this.checkStage = checkStage;
	}
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}
	public StageSelection() {
//		Resource resource = new Resource();
//		resource.loadResourses();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(20));
		String backgroud_path= ClassLoader.getSystemResource("photo/Sega-Game-Backgrounds.jpg").toString();
		Image bimg = new Image(backgroud_path);
		root.setBackground(new Background(new BackgroundImage(bimg,null,null,null,new BackgroundSize(1000, 800, false, false,false,true))));
		Canvas canvas =new Canvas(600,400);
		GraphicsContext gc= canvas.getGraphicsContext2D();
		gc.setFont(font);
		gc.setFill(Color.GOLD);
		gc.fillText("Stage Selection",120, 60);
		gc.setFill(Color.BLACK);
		gc.setLineWidth(1.5);
		gc.strokeText("Stage Selection",120, 60);
	
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < FirstUI.resource.imgsStopped.length; i++) {
					gc.drawImage(FirstUI.resource.imgsStopped[i], 5, 65,200,300);
					try {
						Thread.sleep(150);
						gc.clearRect(5, 65, 200, 300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// TODO Auto-generated method stub
				
			}
		});
		thread.setDaemon(true);
		thread.start();
		root.getChildren().add(canvas);
		VBox vbox = new VBox(20);
		//vbox.setPadding(new Insets(15));
		vbox.setAlignment(Pos.CENTER_RIGHT);
		vbox.setTranslateX(-190);
		vbox.setTranslateY(70);
		Button stage1 = new Button();
		stage1.setPrefSize(180, 20);
		stage1.setFont(font1);
		stage1.setText("Stage 1");
		stage2 = new Button();
		stage2.setPrefSize(180, 20);
		stage2.setFont(font1);
		stage2.setText("Stage 2");
		stage1.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				FirstUI.click.play(100);
				checkStage = 1;
				System.out.println(checkStage);
				SceneManager.gotoStart(1);
//				Platform.runLater(new Runnable() {
//					
//					@Override
//					public void run() {
//						SceneManager.gotoSignIn();
//						// TODO Auto-generated method stub
//						
//					}
//				});
				// TODO Auto-generated method stub
				
			}
			
		});
		stage2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				FirstUI.click.play(100);
				checkStage = 2;
				System.out.println(checkStage);
				SceneManager.gotoStart(2);
//				Platform.runLater(new Runnable() {
//					
//					@Override
//					public void run() {
//						SceneManager.gotoSignIn();
//						// TODO Auto-generated method stub
//						
//					}
//				});
				// TODO Auto-generated method stub
				
			}
			
		});
		vbox.getChildren().addAll(stage1,stage2);
		root.getChildren().add(vbox);
		Button stop = new Button();
		stop.setShape(new Circle(100,Color.BLACK));
		stop.setAlignment(Pos.BOTTOM_LEFT);
		stop.setPrefSize(55,55);
		stop.setTranslateX(255);
		stop.setTranslateY(160);
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
		root.getChildren().add(stop);
		if(SignInEventHandle.stage == 1 && BackgroundScene.checkLevel() == 0) {
			stage2.setDisable(true);
		}else {
			stage2.setDisable(false);
		}
		if(BackgroundScene.checkLevel() == 1) {
			ScoreData.addData(SignInEventHandle.user.get(SignInEventHandle.temp).getUsername(),
								SignInEventHandle.user.get(SignInEventHandle.temp).getPassword(), 2);
			// TODO Auto-generated catch
		}
	}
	public int getCheckStage() {
		return checkStage;
	}
}

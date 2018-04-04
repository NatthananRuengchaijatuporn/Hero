package scene;


import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import window.SceneManager;

public class Enter implements GetScene{
	List<Image> imgRuns = new ArrayList<>(10);
	private final GridPane ROOT = new GridPane();
	Canvas draw;
	private Scene scene = new Scene(ROOT,600,400);
	public Enter(int a) {
		ROOT.setAlignment(Pos.CENTER_RIGHT);
		ROOT.setPadding(new Insets(100,90,0,0));
		String backgroud_path= ClassLoader.getSystemResource("photo/Sega-Game-Backgrounds.jpg").toString();
		Image bimg = new Image(backgroud_path);
		ROOT.setBackground(new Background(new BackgroundImage(bimg,null,null,null,new BackgroundSize(1000, 800, false, false,false,true))));
		draw = new Canvas(300,50);
		ROOT.getChildren().add(draw);
		for(int i = 1 ; i<11;i++) {
			String run= ClassLoader.getSystemResource("Character/Run/"+i+".png").toString();
			Image rimg = new Image(run,60,50,true,true);
			imgRuns.add(rimg);
		}
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
					GraphicsContext gc = draw.getGraphicsContext2D();
					int count =0;
					for(int i = 1 ; i<11;i++) {
						if(i==10) {
							i=1;
							count++;
						}
						if(count ==3) {
							break;
						}
						if(count==0) {
						gc.drawImage(imgRuns.get(i), i*8, 0);
						}else {
							gc.drawImage(imgRuns.get(i), (i*8)+(80*count), 0);
						}
						try {
							Thread.sleep(60);
							gc.clearRect(0, 0, 300, 50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							FirstUI.sound.stop();
							StageSelection stage = new StageSelection();
							System.out.println(stage.checkStage);
							if(a==1) {
								SceneManager.gotoStage1();
							}else {
								SceneManager.gotoStage2();
							}
							// TODO Auto-generated method stub
							
						}
					});
				// TODO Auto-generated method stub
				
			}
		});
		thread.start();

	}
	
	
	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene ;
	}
	
	
}

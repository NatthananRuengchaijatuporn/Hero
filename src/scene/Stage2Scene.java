package scene;

import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Boss;
import logic.Hero;
import logic.Monster;
import model.GameMain;
import window.SceneManager;

public class Stage2Scene implements EventHandler<ActionEvent>,GetScene {

	Pane root;
	boolean runningState;
	boolean shootingState;
	boolean jumpingState;
	boolean right = true;
	boolean jumpReleased;
	int pace30FPS;
	int pace15FPS;
	int pace5FPS;
	int pace0_5FPS;
	double delay;
	int enemyLife = 10;
	double decrease;
	QuadCurve jumpCurve;
	TranslateTransition translateAnim;
	PathTransition jumpTransition;
	MotionBlur motionBlur;
	ArrayList<ImageView> list;
	ImageView view;
	Rectangle characterRect;
	TranslateTransition translateTransition;
	boolean editor;
	int typechange;
	int collisiondelay;
	private boolean slidingState;
	boolean motionblurEnabled = true;
	boolean pressed;
	boolean isFalling;
	Line line;
	boolean shift = true;
	// Canvas canvas = new Canvas(1300, 710);
	boolean isAnimationRunning;
	public static StackPane root1 = new StackPane();
	private Canvas canvas;
	private Canvas canvasBoss;
	private Rectangle bossRect;
	private Rectangle characterRectMid;
	private boolean attack = false;
	private Canvas canvasMonster;
	private Rectangle recmon = new Rectangle();
	private Rectangle recmon1;
	public static Monster monster = new Monster("..", 100, 25, 8, 10);
	public static Boss bosss = new Boss("", 500, 40, 10, 0);
	public static AudioClip sword;
	public static AudioClip boss;
	private int temp = -1;
	public Scene scene;
	private boolean attackmon = false;
	public Stage2Scene() {
		sword = FirstUI.resource.sword;
		FirstUI.resource.boss.setCycleCount(100);
		FirstUI.resource.boss.play();
		scene = new Scene(root1, 1300, 710);
		FirstUI.hero = new Hero("..", 200, 35, 20);
		FirstUI.hero.setLv(10);
		root = new Pane();
		root.setBackground(new Background(new BackgroundImage(FirstUI.resource.imgGround,null,null,null,new BackgroundSize(1000, 800, false, false,false,true))));
		root1.getChildren().add(root);
		line = new Line();
		list = new ArrayList<>();
		initMotionListeners();
		drawBackground();
		initWorld();
		initGame();
		root.setScaleShape(true);
//		createBot(monster);
//		createBot(monster);
		createBot(monster);
		Pane pane = new Pane(GameMain.newGame());
		root1.getChildren().add(pane);
		drawBoss();

	}
	private void drawBackground() {
		canvas = new Canvas(2000,710);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(FirstUI.resource.imgGround, 0, 0);
		root.getChildren().add(canvas);
	}
	private void checkAttack() {
		if(characterRectMid.intersects(bossRect.getBoundsInLocal())) {
			System.out.println(2);
			bosss.attack(FirstUI.hero);
		}
		if(characterRectMid.intersects(recmon.getBoundsInParent())) {
			System.out.println(1);
			monster.attack(FirstUI.hero);
		}
	}
	private void drawBoss() {
		canvasBoss = new Canvas(1920,710);
		bossRect = new Rectangle(FirstUI.resource.imgsBossIdle[0].getWidth(), FirstUI.resource.imgsBossIdle[0].getHeight(),Color.TRANSPARENT);
		bossRect.setX(1050);
		bossRect.setY(400);
		Pane pane1 = new Pane(canvasBoss);
		root1.getChildren().add(pane1);
		//root.getChildren().add(bossRect);
		GraphicsContext gc = canvasBoss.getGraphicsContext2D();
		Thread thread = new Thread( new Runnable() {
			public void run() {
				for(int i = 0;i<400;i++) {
				//gc.fillRect(1000, 250, 300, 450);
					checkAttack();
					if(FirstUI.hero.isDefeated()) {
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setContentText("You Dead!!! Your Score is "+FirstUI.hero.getScore());
								Optional<ButtonType> result = alert.showAndWait();
								 if (result.isPresent() && result.get() == ButtonType.OK) {
								     SceneManager.gotoStageSelection();
								     FirstUI.resource.boss.stop();
								     FirstUI.resource.open.setCycleCount(100);
								     FirstUI.resource.open.play();
								 }
								
								// TODO Auto-generated method stub
								
							}
						});
						break;
					}
					if(bosss.isDefeated()) {
						for(int a =0;a<8;a++) {
							gc.drawImage(FirstUI.resource.imgsBossDeadlist.get(a),0,0,FirstUI.resource.imgsBossDeadlist.get(a).getWidth(),FirstUI.resource.imgsBossDeadlist.get(a).getHeight(),1300,290,-FirstUI.resource.imgsBossDeadlist.get(a).getWidth(),FirstUI.resource.imgsBossDeadlist.get(a).getHeight());
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gc.clearRect(800, 250, 600,450 );
						}
						break;
					}else if(attack==true){
						for(int a =0;a<5;a++) {
							gc.drawImage(FirstUI.resource.imgsBossHurtlist.get(a),0,0,FirstUI.resource.imgsBossHurtlist.get(a).getWidth(),FirstUI.resource.imgsBossHurtlist.get(a).getHeight(),1300,290,-FirstUI.resource.imgsBossHurtlist.get(a).getWidth(),FirstUI.resource.imgsBossHurtlist.get(a).getHeight());
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gc.clearRect(950, 250, 350,450 );
						}
						try {
							Thread.sleep(150);
							gc.clearRect(1000, 250, 300,450 );
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						gc.drawImage(FirstUI.resource.imgsBossIdle[i], 0, 0, FirstUI.resource.imgsBossIdle[i].getWidth(),
								FirstUI.resource.imgsBossIdle[i].getHeight(), 1300, 290, -FirstUI.resource.imgsBossIdle[i].getWidth(),
								FirstUI.resource.imgsBossIdle[i].getHeight());
						try {
							Thread.sleep(150);
							gc.clearRect(1000, 250, 300, 470);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}	
				if(!FirstUI.hero.isDefeated()) {
				Platform.runLater(new Runnable() {
					
					private Optional<ButtonType> result;

					@Override
					public void run() {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setContentText("Congratulation!! You win the boss");
						result = alert.showAndWait();
						 if (result.isPresent() && result.get() == ButtonType.OK) {
						     SceneManager.gotoStageSelection();
						     FirstUI.resource.boss.stop();
						     FirstUI.resource.open.setCycleCount(100);
						     FirstUI.resource.open.play();
						 }
						// TODO Auto-generated method stub
						
					}
				});
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
		thread.setName("Boss");
	}
	private void createBot(Monster monster) {
		canvasMonster = new Canvas(1920,710);
		Pane pane = new Pane(canvasMonster);
		root.getChildren().add(pane);
		GraphicsContext gc = canvasMonster.getGraphicsContext2D();
		Thread thread = new Thread(new Runnable() {


			@Override
			public void run() {
				int i = 0;
				int count = 1;
				while (i != 24) {
					if (count == -1) {
						if (i == 1) {
							count = 1;
						}
						recmon = new Rectangle(350 + ((23-i) * 40), 500, FirstUI.resource.monsterflist.get(0).getWidth(),FirstUI.resource.monsterflist.get(0).getHeight());
						temp = i;
						checkAttack();
						if ((monster.getHp() / monster.getMaxHp()) * 100 <= 25) {
							gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight());
							gc.drawImage(FirstUI.resource.monhurt, 1300- (temp * 40), 470);
							try {
								Thread.sleep(500);
							} catch (InterruptedException l) {
								// TODO Auto-generated catch block
								l.printStackTrace();
							}
							gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight());
							// TODO Auto-generated method stub
						} if (monster.isDefeated()) {
							System.out.println("Pass");
							gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight());
							gc.drawImage(FirstUI.resource.mondead, 1300 - (temp * 40) + 100, 600);
							// System.out.println(hero.getExp());
							try {
								Thread.sleep(500);
								gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight()+50);
								Platform.runLater(new Runnable() {
									
									@Override
									public void run() {
//										try {
//											Thread.sleep(2000);
//										} catch (InterruptedException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
										monster.setHp(100);
										createBot(monster);
										// TODO Auto-generated method stub
										
									}
								});
								// root.getChildren().remove(bot);
								// initCollisionDetection();
							} catch (InterruptedException b) {
								// TODO Auto-generated catch block
								b.printStackTrace();
							}
							break;
						}else {
							gc.drawImage(FirstUI.resource.monsterflist.get(23-i), 1300 - (i * 40), 500);
						try {
							Thread.sleep(150);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						gc.clearRect(1300 - (i * 40), 500, 250, 300);
						}
						if (attackmon) {
							Thread threadmon = new Thread(new Runnable() {

								@Override
								public void run() {
									for (int a = 0; a < 5; a++) {
										gc.drawImage(FirstUI.resource.blood.get(a), 1300 - ((temp) * 40 + (a * 20)), 500 + (a + 20));
										try {
											Thread.sleep(150);
											gc.clearRect(1300 - ((temp) * 40 + (a * 20)), 500 + (a + 20), 200, 200);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
									// TODO Auto-generated method stubdddd

								}
							});
							threadmon.setDaemon(true);
							threadmon.start();
						}
						i--;
					} else {
						if (i == 22) {
							count = -1;
						}
						//ddddddddcheckAttack();
						// gc.drawImage(firstUI.resource.monsterflist.get(23-i),2000+(i*40), 500);
						recmon = new Rectangle(350 + (23-i) * 40, 500, FirstUI.resource.monsterflist.get(i).getWidth(),
								FirstUI.resource.monsterflist.get(i).getHeight());
							temp = i;
							//checkAttack();
							if(attackmon==true) {
									Thread threadmon = new Thread(new Runnable() {

										@Override
										public void run() {
											for (int a = 0; a < 5; a++) {
												gc.drawImage(FirstUI.resource.blood.get(a), 1300 - ((temp) * 40 + (a * 20)),
														500 + (a + 20));
												try {
													Thread.sleep(150);
													gc.clearRect(1300 - ((temp) * 40 + (a * 20)), 500 + (a + 20), 200,
															200);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}

											}
											// TODO Auto-generated method stubdddd

										}
									});
									threadmon.setDaemon(true);
									threadmon.start();
							}
							if (monster.isDefeated()) {
								gc.drawImage(FirstUI.resource.mondead, 0, 0, FirstUI.resource.mondead.getWidth(), FirstUI.resource.mondead.getHeight(),
										1300 - (temp * 40) + 100, 600, -FirstUI.resource.mondead.getWidth(),
										FirstUI.resource.mondead.getHeight());
								try {
									Thread.sleep(500);
									gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight()+50);
								} catch (InterruptedException b) {
									// TODO Auto-generated catch block
									b.printStackTrace();
								}
								Platform.runLater(new Runnable() {
									
									@Override
									public void run() {
//										try {
//											Thread.sleep(2000);
//										} catch (InterruptedException e) {
//											// TODO Auto-generated catch block
//											e.printStackTrace();
//										}
										monster.setHp(100);
										createBot(monster);
										// TODO Auto-generated method stub
										
									}
								});
								break;
							}
							if ((monster.getHp() / monster.getMaxHp()) * 100 <= 25) {
								gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight());
								gc.drawImage(FirstUI.resource.monhurt, 0, 0, FirstUI.resource.monhurt.getWidth(), FirstUI.resource.monhurt.getHeight(),
										1300 - (temp * 40), 470, -FirstUI.resource.monhurt.getWidth(), FirstUI.resource.monhurt.getHeight());
								try {
									Thread.sleep(500);
									gc.clearRect(0, 470,1300,FirstUI.resource.monsterflist.get(0).getHeight());
								} catch (InterruptedException l) {
									// TODO Auto-generated catch block
									l.printStackTrace();
								}
								// TODO Auto-generated method stub
							}else {
								gc.drawImage(FirstUI.resource.monsterflist.get(i), 0, 0, FirstUI.resource.monsterflist.get(i).getWidth(),
										FirstUI.resource.monsterflist.get(i).getHeight(), 1300 - (i * 40), 500,
										-FirstUI.resource.monsterflist.get(i).getWidth(), FirstUI.resource.monsterflist.get(i).getHeight());
							try {
								Thread.sleep(150);
								gc.clearRect(1300 - ((i + 2) * 40) - 10, 500, 200, 300);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
							i++;
					}
				}

			}
		});
		thread.setDaemon(true);
		thread.setName("Bot");
		thread.start();
	}
	private void initWorld() {
		jumpCurve = new QuadCurve();
		motionBlur = new MotionBlur();
		characterRect = new Rectangle();
		translateTransition = new TranslateTransition();
		translateTransition.setInterpolator(Interpolator.EASE_IN);
		characterRect.setFill(Color.TRANSPARENT);
		characterRect.setStroke(Color.GREY);
		characterRect.setHeight(FirstUI.resource.character.getBoundsInParent().getHeight() - 50);
		characterRect.setWidth(150);
		characterRect.xProperty()
				.bind(FirstUI.resource.character.translateXProperty().add(FirstUI.resource.character.getBoundsInParent().getWidth() / -20));
		characterRect.yProperty().bind(FirstUI.resource.character.translateYProperty().add(30));
		characterRectMid = new Rectangle();
		characterRectMid.setFill(Color.TRANSPARENT);
		characterRectMid.setStroke(Color.GREY);
		characterRectMid.setHeight(FirstUI.resource.character.getBoundsInParent().getHeight() - 50);
		characterRectMid.setWidth(30);
		characterRectMid.xProperty()
				.bind(FirstUI.resource.character.translateXProperty().add(FirstUI.resource.character.getBoundsInParent().getWidth() / 2.0 - 5.0));
		characterRectMid.yProperty().bind(FirstUI.resource.character.translateYProperty().add(30));

		jumpTransition = new PathTransition(Duration.millis(1000), jumpCurve, FirstUI.resource.character);
		jumpCurve.startXProperty().bind(FirstUI.resource.character.translateXProperty().add(27).add(FirstUI.resource.character.xProperty()));
		jumpCurve.startYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2)
				.add(FirstUI.resource.character.xProperty()));

		jumpCurve.controlXProperty()
				.bind(FirstUI.resource.character.translateXProperty().add(25 * 10 / 2).add(27).add(FirstUI.resource.character.xProperty()));
		jumpCurve.controlYProperty().bind(FirstUI.resource.character.translateYProperty().add(-80).add(FirstUI.resource.character.xProperty()));

		jumpCurve.endXProperty()
				.bind(FirstUI.resource.character.translateXProperty().add(25 * 10).add(37).add(FirstUI.resource.character.xProperty()));
		jumpCurve.endYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2)
				.add(FirstUI.resource.character.xProperty()));

		// firstUI.resource.sky1.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-3.5));
		// firstUI.resource.sky2.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-3.5).add(999));
		//firstUI.resource.ground1.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-0.2));
		// firstUI.resource.ground2.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-3).add(999));
		// firstUI.resource.mountain1.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-3));
		// firstUI.resource.mountain2.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-3).add(1000));
		// firstUI.resource.gun.setVisible(false);
		// firstUI.resource.gun.translateXProperty().bind(firstUI.resource.character.translateXProperty().add(30));
		// firstUI.resource.gun.translateYProperty().bind(firstUI.resource.character.translateYProperty().add(42));
		Rectangle r = new Rectangle(0, 280, 2000, 100);
		r.setFill(new Color(70.0 / 255.0, 38.0 / 255.0, 25 / 255.0, 1.0));
		// if (characterRect.intersects(0, 280, 2000, 100)) {
		// firstUI.resource.ground1.translateXProperty().bind(firstUI.resource.character.translateXProperty().divide(-10000));
		// }
		jumpCurve.setFill(Color.TRANSPARENT);
		jumpCurve.setStroke(Color.BLACK);
		// firstUI.resource.dust.setVisible(false);

		addToWorld(
				//ares.ground1,
				FirstUI.resource.character

		);

	}

	public void initGame() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(33.33), this));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		translateAnim = new TranslateTransition(Duration.millis(20000), FirstUI.resource.character);

	}

	private void addToWorld(Node... n) {
		root.getChildren().addAll(n);
	}

	@Override
	public void handle(ActionEvent event) {
		pace30FPS++;
		pace15FPS = (pace30FPS % 2 == 0) ? pace15FPS + 1 : pace15FPS;
		pace5FPS = (pace30FPS % 6 == 0) ? pace5FPS + 1 : pace5FPS;
		pace0_5FPS = (pace30FPS % 60 == 0) ? pace0_5FPS + 1 : pace0_5FPS;
		if (collisiondelay == 0) {
			if (slidingState) {
				FirstUI.resource.character.setImage(FirstUI.resource.imgSlide);
				// firstUI.resource.dust.setImage(firstUI.resource.imgsDust[pace15FPS % 6]);
				FirstUI.resource.character.setViewport(new Rectangle2D(0, 0, 340, 180));
			} else if (shootingState && jumpTransition != null
					&& jumpTransition.getStatus() == PathTransition.Status.STOPPED) {
				if (runningState) {
					FirstUI.resource.character.setImage(FirstUI.resource.imgsRunning[pace15FPS % 10]);
					// firstUI.resource.dust.setImage(firstUI.resource.imgsDust[pace15FPS % 6]);

				} else {
					FirstUI.resource.character.setImage(FirstUI.resource.imgsStoppedShoot[pace0_5FPS % 10]);
				}

			} else if (!shootingState && jumpTransition != null
					&& jumpTransition.getStatus() == PathTransition.Status.STOPPED) {
				if (runningState) {
					FirstUI.resource.character.setImage(FirstUI.resource.imgsRunning[pace30FPS % FirstUI.resource.imgsRunning.length]);
					// firstUI.resource.dust.setImage(firstUI.resource.imgsDust[pace15FPS % 6]);

				} else {
					FirstUI.resource.character.setImage(FirstUI.resource.imgsStopped[pace15FPS % FirstUI.resource.imgsStopped.length]);
				}
			} else if (jumpTransition != null && jumpTransition.getStatus() == PathTransition.Status.RUNNING) {
				if (shootingState) {
					FirstUI.resource.character.setImage(FirstUI.resource.imgsJumpingShoot);
					// firstUI.resource.dust.setImage(firstUI.resource.imgsDust[pace15FPS % 6]);
				} else {
					FirstUI.resource.character.setImage(FirstUI.resource.imgsJumping);

				}
			}
		} else {
			collisiondelay--;
		}
		if (!runningState && !jumpReleased) {
			if (delay > 0 && !right && motionblurEnabled) {
				motionBlur.setAngle(-180);
				motionBlur.setRadius(0);
				// firstUI.resource.mountain1.setEffect(motionBlur);
				// firstUI.resource.mountain2.setEffect(motionBlur);
				FirstUI.resource.ground1.setEffect(motionBlur);
				FirstUI.resource.ground2.setEffect(motionBlur);

			} else if (delay > 0 && right && motionblurEnabled) {
				motionBlur.setAngle(0);
				motionBlur.setRadius(0);
				// firstUI.resource.mountain1.setEffect(motionBlur);
				// firstUI.resource.mountain2.setEffect(motionBlur);
				FirstUI.resource.ground1.setEffect(motionBlur);
				FirstUI.resource.ground2.setEffect(motionBlur);

			}
		} else if (delay > 0 && jumpReleased && motionblurEnabled) {
			motionBlur.setAngle(90);
			motionBlur.setRadius((delay -= 4) * 0.7);
			// firstUI.resource.mountain1.setEffect(motionBlur);
			// firstUI.resource.mountain2.setEffect(motionBlur);
			FirstUI.resource.ground1.setEffect(motionBlur);
			FirstUI.resource.ground2.setEffect(motionBlur);

		} else {
			jumpReleased = false;
		}
		if (jumpTransition.getStatus() != Animation.Status.RUNNING) {
			for (int ir = 0; ir < list.size(); ir++) {
				if (list.get(ir).getBoundsInParent().contains(characterRect.getX() + 5,
						characterRect.getY() + characterRect.getHeight() + 5)) {
					isFalling = false;
					break;
				} else if (ir == list.size() - 1 && characterRect.getY() < 177) {
					FirstUI.resource.character.setTranslateY(FirstUI.resource.character.getTranslateY() + 5);
					isFalling = true;
					break;
				} else {
					isFalling = false;
				}
			}
		}
	}

	private void initMotionListeners() {
		root.requestFocus();
		root.setOnKeyPressed(e -> {
			translateAnim.setDuration(Duration.millis(20000));
			if (e.getCode() == KeyCode.A && pressed == false) {
				pressed = true;
				if ((jumpTransition.getStatus() != Animation.Status.RUNNING)) {
					// firstUI.resource.dust.setScaleX(1);
					right = false;
					FirstUI.resource.character.setScaleX(-1.0);
					// firstUI.resource.character.setX(firstUI.resource.character.getX()-1);
					// firstUI.resource.dust.translateXProperty().bind(firstUI.resource.character.translateXProperty());

					if (motionblurEnabled) {
						// motionBlur.setAngle(-180);
						// motionBlur.setRadius(delay * 0.5);
						// firstUI.resource.mountain1.setEffect(motionBlur);
						// firstUI.resource.mountain2.setEffect(motionBlur);
						FirstUI.resource.ground1.setEffect(motionBlur);
						FirstUI.resource.ground2.setEffect(motionBlur);
						// firstUI.resource.dust.setEffect(motionBlur);
					}
					if (jumpTransition.getStatus() == PathTransition.Status.STOPPED
							&& translateAnim.getStatus() != PathTransition.Status.RUNNING
							&& translateTransition.getStatus() != PathTransition.Status.RUNNING) {
						runningState = true;
						translateAnim.setByX(-10000);
						translateAnim.play();
					}
					// if(characterRect.getX()<210) {
					// firstUI.resource.character.setX(0);
					// }
					// if (firstUI.resource.character.getX() == 0) {
					// translateAnim.stop();
					// }
				}
			} else if (e.getCode() == KeyCode.D && pressed == false) {
				pressed = true;

				if ((jumpTransition.getStatus() != Animation.Status.RUNNING)) {
					// firstUI.resource.dust.setScaleX(-1);
					FirstUI.resource.character.setX(FirstUI.resource.character.getX() + 1);
					FirstUI.resource.character.setScaleX(1);
					right = true;

					// firstUI.resource.dust.translateXProperty().bind(firstUI.resource.character.translateXProperty().add(-40));
					// firstUI.resource.dust.translateYProperty().bind(firstUI.resource.character.translateYProperty().add(42));
					if (motionblurEnabled) {
						// motionBlur.setAngle(0);
						// motionBlur.setRadius(delay * 0.5);
						// firstUI.resource.mountain1.setEffect(motionBlur);
						// firstUI.resource.mountain2.setEffect(motionBlur);
						FirstUI.resource.ground1.setEffect(motionBlur);
						// firstUI.resource.ground2.setEffect(motionBlur);
						// firstUI.resource.dust.setEffect(motionBlur);
					}
					if (jumpTransition.getStatus() == PathTransition.Status.STOPPED
							&& translateAnim.getStatus() != PathTransition.Status.RUNNING
							&& translateTransition.getStatus() != PathTransition.Status.RUNNING) {
						translateAnim.setByX(+10000);
						translateAnim.play();
						runningState = true;
					}
				}
			} else if (e.getCode() == KeyCode.SHIFT) {
				if (jumpTransition.getStatus() == PathTransition.Status.STOPPED) {
				}

			} else if (e.getCode() == KeyCode.A) {

				right = false;
				// if (shootingState) {
				// TranslateTransition tt = new TranslateTransition(Duration.millis(700));
				// Bullet b;
				// if (firstUI.resource.gun.getScaleX() == 1.0) {
				// b = new Bullet(firstUI.resource.gun.getTranslateX() + 30, firstUI.resource.gun.getTranslateY() + 9, 3);
				// tt.setByX(1000);
				// } else {
				//
				// b = new Bullet(firstUI.resource.gun.getTranslateX(), firstUI.resource.gun.getTranslateY() + 9, 3);
				// tt.setByX(-1000);
				// }
				// b.setScaleX(firstUI.resource.gun.getScaleX());
				// addToWorld(b);
				// tt.setNode(b);
				//
				// }
			} else if (e.getCode() == KeyCode.W && !isFalling) {
				if (right) {
					jumpRight();

				} else {
					jumpLeft();
				}

				translateAnim.stop();
				runningState = false;
				// firstUI.resource.dust.setVisible(false);
				// firstUI.resource.dust.setVisible(false);
				jumpTransition.setCycleCount(1);
				jumpTransition.play();
				jumpTransition.setOnFinished(ev -> {
					motionBlur.setAngle(90);
					delay = 30;
					jumpReleased = true;
				});
			} else if (e.getCode() == KeyCode.SPACE) {
				sword.play();
				shootingState = true;
				pressed = true;
				if (characterRect.getBoundsInParent().intersects(bossRect.getBoundsInParent())) {
					//System.out.println(1);
					FirstUI.hero.attack(bosss);
					attack = true;
				}
				if(characterRect.getBoundsInParent().intersects(recmon.getBoundsInParent())) {
					FirstUI.hero.attack(monster);
					attackmon  = true;
				}
					
			}
			// else if (e.getCode() == KeyCode.DOWN) {
			// if (!(translateAnim.getStatus() == Animation.Status.RUNNING)) {
			// translateAnim.setDuration(Duration.millis(400));
			// if (right) {
			// translateAnim.setByX(150);
			// } else {
			// translateAnim.setByX(-150);
			// }
			// firstUI.resource.character.setTranslateY(firstUI.resource.character.getTranslateY() + 56);
			// slidingState = true;
			// characterRect.setTranslateY(characterRect.getTranslateY() + 34);
			// characterRect.setWidth(firstUI.resource.character.getBoundsInParent().getHeight());
			// characterRect.setHeight(10);
			// translateAnim.play();
			// translateAnim.setOnFinished((ActionEvent event) -> {
			// if (slidingState) {
			// slidingState = false;
			//
			// firstUI.resource.character.setTranslateY(firstUI.resource.character.getTranslateY() - 56);
			// firstUI.resource.character.setViewport(new Rectangle2D(0, 0, 180, 340));
			// characterRect.setTranslateY(characterRect.getTranslateY() - 34);
			// characterRect.setHeight(firstUI.resource.character.getBoundsInParent().getHeight());
			// characterRect.setWidth(10);
			//
			// }
			// });
			// }
			// }

		});
		root.setOnKeyReleased(e -> {
			pace15FPS = 0;
			translateAnim.setDuration(Duration.millis(500));
			switch (e.getCode()) {
			case A:
				pressed = false;
				translateAnim.stop();
				// firstUI.resource.dust.setVisible(false);
				decrease = (double) delay / 10.0;
				runningState = false;
				delay = 0;
				if (motionblurEnabled) {
					motionBlur.setRadius(0);
					// firstUI.resource.mountain1.setEffect(motionBlur);
					// firstUI.resource.mountain2.setEffect(motionBlur);
					FirstUI.resource.ground1.setEffect(motionBlur);
					FirstUI.resource.ground2.setEffect(motionBlur);
					// firstUI.resource.dust.setEffect(motionBlur);
				}
				break;
			case D:
				pressed = false;
				// firstUI.resource.dust.setVisible(false);
				translateAnim.stop();
				decrease = (double) delay / 10.0;
				runningState = false;
				delay = 0;
				if (motionblurEnabled) {
					motionBlur.setRadius(0);
					// firstUI.resource.mountain1.setEffect(motionBlur);
					// firstUI.resource.mountain2.setEffect(motionBlur);
					FirstUI.resource.ground1.setEffect(motionBlur);
					FirstUI.resource.ground2.setEffect(motionBlur);
					// firstUI.resource.dust.setEffect(motionBlur);
				}
				break;
			case W:
				// firstUI.resource.dust.setVisible(false);
				break;
			case SPACE:
				pressed = false;
				shootingState = false;
				attack = false;
				attackmon=false;
			default:
				break;
			} 

		});
	}

	private void jumpLeft() {
		if (delay > 25) {
			jumpCurve.startXProperty().bind(FirstUI.resource.character.translateXProperty().add(47).add(FirstUI.resource.character.xProperty()));
			jumpCurve.startYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2)
					.add(FirstUI.resource.character.xProperty()));
			
			jumpCurve.controlXProperty().bind(
					FirstUI.resource.character.translateXProperty().add(-delay * 10 / 2).add(20).add(FirstUI.resource.character.xProperty()));
			jumpCurve.controlYProperty()
					.bind(FirstUI.resource.character.translateYProperty().add(-80).add(FirstUI.resource.character.xProperty()));

			jumpCurve.endXProperty()
					.bind(FirstUI.resource.character.translateXProperty().add(-delay * 10).add(47).add(FirstUI.resource.character.xProperty()));
			jumpCurve.endYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2)
					.add(FirstUI.resource.character.xProperty()));
		} else {
			jumpCurve.startXProperty().bind(FirstUI.resource.character.translateXProperty().add(47).add(FirstUI.resource.character.xProperty()));
			jumpCurve.startYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2)
					.add(FirstUI.resource.character.xProperty()));

			//ตำแหน่งกึ่งกลางของ curve การกระโดด
			jumpCurve.controlXProperty()
					.bind(FirstUI.resource.character.translateXProperty().add(-7.5 * 10 / 2).add(47).add(FirstUI.resource.character.xProperty()));
			
			// set ความสูงในการกระโดด ลบมากสูงมาก
			jumpCurve.controlYProperty()
					.bind(FirstUI.resource.character.translateYProperty().add(-150).add(FirstUI.resource.character.xProperty()));

			//set ตำแหน่งปลายของ curve
			jumpCurve.endXProperty()
					.bind(FirstUI.resource.character.translateXProperty().add(-15 * 10).add(47).add(FirstUI.resource.character.xProperty()));
			jumpCurve.endYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2)
					.add(FirstUI.resource.character.xProperty()));
		}
	}

	private void jumpRight() {
		if (delay > 25) {
			jumpCurve.startXProperty().bind(FirstUI.resource.character.translateXProperty().add(47));
			jumpCurve.startYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2));

			jumpCurve.controlXProperty().bind(FirstUI.resource.character.translateXProperty().add(delay * 10 / 2).add(27));
			jumpCurve.controlYProperty().bind(FirstUI.resource.character.translateYProperty().add(-80));

			jumpCurve.endXProperty().bind(FirstUI.resource.character.translateXProperty().add(delay * 10).add(47));
			jumpCurve.endYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2));
		} else {
			jumpCurve.startXProperty().bind(FirstUI.resource.character.translateXProperty().add(47));
			jumpCurve.startYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2));

			//ตำแหน่งกึ่งกลางของ curve การกระโดด
			jumpCurve.controlXProperty().bind(FirstUI.resource.character.translateXProperty().add(7.5 * 10 / 2).add(47));
			
			// set ความสูงในการกระโดด ลบมากสูงมาก
			jumpCurve.controlYProperty().bind(FirstUI.resource.character.translateYProperty().add(-150));

			//set ตำแหน่งปลายของ curve
			jumpCurve.endXProperty().bind(FirstUI.resource.character.translateXProperty().add(15 * 10).add(47));
			jumpCurve.endYProperty().bind(FirstUI.resource.character.translateYProperty().add(FirstUI.resource.character.getFitHeight() / 2));
		}
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return scene;
	}

}
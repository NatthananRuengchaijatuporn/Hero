package gameresource;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.media.AudioClip;

public class Resource {

	public Image[] imgsRunning;
	public Image[] imgsStopped;
	public Image imgsJumpingShoot;
	public Image imgsJumping;
	public Image[] imgsRunningShoot;
	public Image[] imgsStoppedShoot;
	public Image[] imgsDust;
	public Image[] imgsBossIdle;
	public static Image imgsBlocks;
	public Image imgSky;
	public Image imgGround;
	public Image imgGun;
	public Image imgMountain;
	public Image imgSlide;
	public Image imgCollide;

	public ImageView character;
	public ImageView sky1;
	public ImageView sky2;
	public ImageView ground1;
	public ImageView ground2;
	public ImageView gun;
	public ImageView mountain1;
	public ImageView mountain2;
	public ImageView dust;
	public ImageView slide;
	public List<Image> imgsRunninglist = new ArrayList<>(10);
	public List<Image> imgsStoppedlist = new ArrayList<>(10);
	public List<Image> imgsStoppedShootlist = new ArrayList<>(10);
	public List<Image> imgsRunningShootlist = new ArrayList<>(10);
	public List<Image> imgsBossIdlelist = new ArrayList<>(4);
	public List<Image> imgsBossAttacklist = new ArrayList<>(6);
	public List<Image> imgsBossDeadlist = new ArrayList<>(8);
	public List<Image> imgsBossHurtlist = new ArrayList<>(5);
	public List<Image> monsterflist = new ArrayList<>(23);
	public List<Image> blood = new ArrayList<>(6);
	public Image mondead;
	public Image monhurt;
	public AudioClip open;
	public AudioClip normal;
	public AudioClip boss;
	public AudioClip click;
	public AudioClip sword;
	public Image stop;

	public void loadResourses() {
		try {
			
			imgsRunning = new Image[400];
			imgsStopped = new Image[400];
			imgsStoppedShoot = new Image[10];
			// imgsDust = new Image[6];
			imgsRunningShoot = new Image[10];
			imgsBossIdle = new Image[400];
			for(int i = 1; i<7;i++) {
				String bossattack = ClassLoader.getSystemResource("boss/attack/"+i+".png").toString();
				Image imgba = new Image(bossattack);
				imgsBossAttacklist.add(imgba);
			}
			for(int i = 1; i<9;i++) {
				String bossdead = ClassLoader.getSystemResource("boss/dead/"+i+".png").toString();
				Image imgbd = new Image(bossdead);
				imgsBossDeadlist.add(imgbd);
			}
			for(int i = 1; i<6;i++) {
				String bosshurt = ClassLoader.getSystemResource("boss/hurt/"+i+".png").toString();
				Image imgbh = new Image(bosshurt);
				imgsBossHurtlist.add(imgbh);
			}
			for (int i = 1; i < 11; i++) {
				String run = ClassLoader.getSystemResource("Character/Run/" + i + ".png").toString();
				Image imgr = new Image(run);
				imgsRunninglist.add(imgr);
				String stop = ClassLoader.getSystemResource("Character/Idle/Idle (" + i + ").png").toString();
				Image imgs = new Image(stop);
				imgsStoppedlist.add(imgs);
			}
			for (int i = 1; i < 11; i++) {
				String shootrun = ClassLoader.getSystemResource("Character/attack/Attack (" + i + ").png").toString();
				Image imgsr = new Image(shootrun);
				imgsRunningShootlist.add(imgsr);
				
				String shootstop = ClassLoader.getSystemResource("Character/attack/Attack (" + i + ").png").toString();
				Image imgss = new Image(shootstop);
				imgsStoppedShootlist.add(imgss);
			}
			for(int i =1;i<5;i++) {
				String bossidle = ClassLoader.getSystemResource("boss/idle/"+i+".png").toString();
				Image imgbi = new Image(bossidle);
				imgsBossIdlelist.add(imgbi);
			}
			
			// imgsBlocks = new Image();
			for (int i = 1; i < imgsRunning.length + 1; i++) {
				if (i < imgsRunning.length) {
					imgsRunning[i - 1] = imgsRunninglist.get(i%10);
				}
				if (i < imgsStopped.length) {
					imgsStopped[i - 1] = imgsStoppedlist.get(i%10);
				}
				if (i < imgsBossIdle.length) {
					imgsBossIdle[i - 1] = imgsBossIdlelist.get(i%4);
				}
			}
			for (int i = 1; i < imgsRunningShoot.length + 1; i++) {
				if (i <= imgsRunningShoot.length) {
					imgsRunningShoot[i - 1] = imgsRunningShootlist.get(i - 1);
				}
				
				if (i <= imgsStoppedShoot.length) {
					imgsStoppedShoot[i - 1] = imgsStoppedShootlist.get(i - 1);
				}
				// if (i < 6) {
				// imgsDust[i] = new
				// Image(Resourses.class.getResourceAsStream("effects/blood_a_000" + (i + 1) +
				// ".png"));
				// }
				if (i < 1) {
					imgsBlocks = new Image(ClassLoader.getSystemResource("block/block1.png").toString());

				}
			}
			for(int i = 1 ; i<24;i++) {
				String run= ClassLoader.getSystemResource("enemy/forward/"+i+".gif").toString();
				Image rimg = new Image(run,300,200,true,true);
				monsterflist.add(rimg);
			}
			for(int i = 1 ; i<6;i++) {
				String run= ClassLoader.getSystemResource("enemy/blood/"+i+".png").toString();
				Image rimg = new Image(run,100,100,true,true);
				blood.add(rimg);
			}
			// TODO Auto-generated method stub

			imgSky = new Image(ClassLoader.getSystemResource("photo/backgroud.jpg").toString());
			imgGround = new Image(ClassLoader.getSystemResource("photo/backgroudshot.jpg").toString(),1920,800,false,true);
			// imgGun = new Image(Resourses.class.getResourceAsStream("world/gun.png"));
			// imgMountain = new
			// Image(Resourses.class.getResourceAsStream("world/mountains.png"));
			imgsJumpingShoot = new Image(
					ClassLoader.getSystemResource("Character/jumpattack/JumpAttack (1).png").toString());
			imgsJumping = new Image(ClassLoader.getSystemResource("Character/jump/Jump (1).png").toString());
			mondead = new Image(ClassLoader.getSystemResource("enemy/dead.png").toString(),200,300,true,true);
			monhurt = new Image(ClassLoader.getSystemResource("enemy/hurt.gif").toString(),100,222,true,true);
			stop = new Image(ClassLoader.getSystemResource("photo/stop.png").toString(),55,55,true,true);
			open = new AudioClip(ClassLoader.getSystemResource("sound/openning.wav").toString());
			boss = new AudioClip(ClassLoader.getSystemResource("sound/boss.wav").toString());
			normal = new AudioClip(ClassLoader.getSystemResource("sound/normal.wav").toString());
			click = new AudioClip(ClassLoader.getSystemResource("sound/click.wav").toString());
			sword = new AudioClip(ClassLoader.getSystemResource("sound/sword.wav").toString());
			// imgSlide = new
			// Image(Resourses.class.getResourceAsStream("character/slide.png"));
			// imgCollide = new
			// Image(Resourses.class.getResourceAsStream("test/collide.png"));
			attachDefaultImages();

		} catch (Exception e) {
			System.out.println("error loading one of the resourses");
		}
	}

	private void attachDefaultImages() {
		try {
			character = new ImageView(imgsStopped[0]);
			// sky1 = new ImageView(imgSky);
			// sky1.setFitHeight(800);
			// sky1.setFitWidth(1450);
			// sky2 = new ImageView(imgSky);

			// set background
			// imgSky.set;
			ground1 = new ImageView(imgSky);
			ground1.setFitHeight(800);
			// ground1.setFitWidth(1920);
			//
			ground2 = new ImageView(imgSky);
			ground2.setFitHeight(800);

			// ground2.setFitWidth(1450);

			//

			// ground1.setTranslateY(270);
			// ground2.setTranslateY(270);

			// character.setViewport(new Rectangle2D(0, 0, 180, 315));

			// set character size
			character.isResizable();
			character.setFitHeight(225);
			character.setFitWidth(150);
			// sky1.setFitHeight(1300);
			// sky1.setFitWidth(1080);
			// sky1.setFitHeight(1300);
			// sky1.setFitWidth(1080);

			character.setSmooth(true);
			character.setCache(true);

			// set character position
			character.setTranslateY(480);
			character.setTranslateX(150);

			// mountain1 = new ImageView(imgMountain);
			// mountain2 = new ImageView(imgMountain);

			// mountain1.setTranslateY(82);
			// mountain2.setTranslateY(82);
		} catch (Exception e) {
			System.out.println("error attaching imgs");
		}
	}
}
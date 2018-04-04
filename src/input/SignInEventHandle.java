package input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

import game.storage.ScoreData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import window.SceneManager;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import model.User;
import scene.FirstUI;

public final class SignInEventHandle implements EventHandler<ActionEvent> {
	private InputField userInput;
	private PasswordField passInput;
	private String username;
	private String password;
	private String currentline;
	public static int temp;
	public static List<User> user;
	public static int stage;
	public SignInEventHandle(InputField userInput, PasswordField passInput) {
		this.userInput = userInput;
		this.passInput = passInput;
	}


	@Override
	public void handle(ActionEvent argo) {
		FirstUI.click.play(100);
		try {
		username = userInput.getInputData();
		password = passInput.getText();
		if(!username.trim().equals("") && !password.equals("")) {
			System.out.println(username + " test");
			System.out.println(password+ " test");
			System.out.println(username.trim() != null);
			boolean grantAccess = false;
			try {
			user = (ScoreData.getData());
			for(int i = 0 ;i<user.size();i++) {
				if(user.get(i).getUsername().trim().equals(username.trim())) {
					System.out.println("Pass Here1");
					if(user.get(i).getPassword().equals(password)) {
						System.out.println("Pass Here2");
						stage = user.get(i).getStage();
						grantAccess = true;
						temp = i;
					}else {
						
					}
				}
			}
			}catch(Exception e) {
				grantAccess=false;
			}
			
		
/*		String backgroud_path= ClassLoader.getSystemResource("text/users.txt").toString();
		File f = new File(backgroud_path);
		try {
		     read = new Scanner(f); 
		     while(read.hasNextLine()){
		    	 if(read.nextLine().equals(username+" "+password)){ // if the same user name // check password
			             grantAccess=true; // if also same, change boolean to true
			             break; // and break the for-loop
			          }
			       }
		     if(grantAccess){
		        // let the user continue 
	    	 		Alert alert1 = new Alert(AlertType.CONFIRMATION, "Granted Access", ButtonType.OK);
	    	 		alert1.show();

		        // and do other stuff, for example: move to next window ..etc
		     }
		     else{
		    	 		Alert alert = new Alert(AlertType.ERROR, "Please verify that you've entered everything correctly.", ButtonType.OK);
					alert.show(); 
		         // return Alert message to notify the deny
		     }

		} catch (FileNotFoundException e) {

		        e.printStackTrace();
		}*/

		if(grantAccess){
			// let the user continue 
			SceneManager.gotoStageSelection();

			// and do other stuff, for example: move to next window ..etc
		}
		else{
			Alert alert = new Alert(AlertType.ERROR, "Please verify that you've entered everything correctly.", ButtonType.OK);
			alert.show(); 
			// return Alert message to notify the deny
		}
		}else if(username.trim().equals("")){
			throw new NoInputException("Please fill Username.");
		}else {
			throw new NoInputException("Please fill Password.");
		}
		}catch(NoInputException noInputException) {
			Alert alert = new Alert(AlertType.ERROR,noInputException.getMessage() , ButtonType.OK);
			alert.show(); 
		}
	}
}

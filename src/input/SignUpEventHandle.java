package input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import scene.FirstUI;

public final class SignUpEventHandle implements EventHandler<ActionEvent> {
	private InputField userInput;
	private PasswordField passInput;
	private PasswordField repassInput;
	private String username;
	private String password;
	private String repassword;
	public SignUpEventHandle(InputField userInput, PasswordField passInput,PasswordField repassInput) {
		this.userInput = userInput;
		this.passInput = passInput;
		this.repassInput = repassInput;
	}


	@Override
	public void handle(ActionEvent argo) {
		FirstUI.click.play(100);
		try {
		username = userInput.getInputData();
		password = passInput.getText();
		repassword = repassInput.getText();
		if(!username.trim().equals("") && !password.equals("")&&!repassword.trim().equals("")) {
		if(!password.equals(repassword)) {
			Alert alert = new Alert(AlertType.ERROR, "Please recheck your password", ButtonType.OK);
			alert.show();
			passInput.clear();
			repassInput.clear();
		}else {
			ScoreData.addData(username, password, 1);
			Alert alert = new Alert(AlertType.CONFIRMATION, "Sign Up Completed", ButtonType.OK);
			Optional<ButtonType> result = alert.showAndWait();
			 if (result.isPresent() && result.get() == ButtonType.OK) {
			     SceneManager.gotoSignIn();
			 }
			
		}
		}else if(username.trim().equals("")){
			throw new NoInputException("Please fill Username.");
		}else{
			System.out.println("test");
			throw new NoInputException("Please fill Password.");
		}
		}catch(NoInputException noInputException) {
			Alert alert = new Alert(AlertType.ERROR,noInputException.getMessage() , ButtonType.OK);
			alert.show(); 
		}
	}
}

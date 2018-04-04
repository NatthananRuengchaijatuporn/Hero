package model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	String username;
	String password;
	int stage;
	public User(String username , String password, int stage) {
		this.username = username;
		this.password = password;
		this.stage = stage;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	
}

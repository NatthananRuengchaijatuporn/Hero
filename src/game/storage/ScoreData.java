package game.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utility.ResourceManager;

public class ScoreData implements Serializable {

	

	/**
	 * 
	 */
	public List<User> data;

	public ScoreData() {
		data = new ArrayList<>();
	}

	public static void addData(String username, String password, int stage) {
		ScoreData scoreData = null;
		try {
			scoreData = (ScoreData) ResourceManager.load("ScoreData.save");
		} catch (Exception e) {
			scoreData = new ScoreData();
		}
		scoreData.data.add(new User(username, password, stage));

		try {
			ResourceManager.save(scoreData, "ScoreData.save");
		} catch (Exception e) {
		}
	}
	public static void removeData(String username, String password, int stage) {
		ScoreData scoreData = null;
		try {
			scoreData = (ScoreData) ResourceManager.load("ScoreData.save");
		} catch (Exception e) {
		}
		scoreData.data.remove(new User(username, password, stage));

		try {
			ResourceManager.save(scoreData, "ScoreData.save");
		} catch (Exception e) {
		}
	}
	public static List<User> getData() {
		try {
			return ((ScoreData) ResourceManager.load("ScoreData.save")).data;
		} catch (Exception e) {
		}
		return null;
	}
}

package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.Act;
import edu.utdallas.gamePlayEngine.model.Scene;
import edu.utdallas.gamePlayEngine.model.Screen;

public class GameState {

	private Message msg;
	private Act currentAct;
	private Screen currentScreen;
	private Scene currentScene;
	private boolean isCorrect;
	private String title;
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	GameState()
	{
		
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Act getCurrentAct() {
		return currentAct;
	}

	public void setCurrentAct(Act currentAct) {
		this.currentAct = currentAct;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}
	
	
}

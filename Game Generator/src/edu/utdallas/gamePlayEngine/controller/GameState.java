package edu.utdallas.gamePlayEngine.controller;


import javax.swing.JPanel;

import edu.utdallas.gamePlayEngine.model.Act;
import edu.utdallas.gamePlayEngine.model.Quiz;
import edu.utdallas.gamePlayEngine.model.ChallengeStructure;
import edu.utdallas.gamePlayEngine.model.GameElement;
import edu.utdallas.gamePlayEngine.model.Scene;
import edu.utdallas.gamePlayEngine.model.Screen;

public class GameState {

	Act act;
	Screen screen;
	Scene scene;
	GameElement gameElement;
	Quiz quiz;
	ChallengeStructure challenge;
	private String title;
	JPanel myMainPanel;// Zac ZHANG Added
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private boolean isCorrect;
	
	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public GameElement getGameElement() {
		return gameElement;
	}

	public void setGameElement(GameElement gameElement) {
		this.gameElement = gameElement;
	}

	Message message;
	
	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz q) {
		quiz = q;
	}
	
	public ChallengeStructure getChallengeStructure() {
		return challenge;
	}
	public void setChallengeStructure(ChallengeStructure c) {
		this.challenge = c;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public JPanel getMenuFrame()
	{
		return this.myMainPanel;
	}
	
	public GameState(JPanel myMenuPanel) {
		this.act = null;
		this.scene = null;
		this.screen = null;
		this.message = null;
		this.myMainPanel = myMenuPanel;
	}
}

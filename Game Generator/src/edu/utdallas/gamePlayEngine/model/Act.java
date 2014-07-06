/**
 * Act model class - Contains one or more scenes and is contained in Game
 * This file defines the Act model class
 */
package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.Timer;
import edu.utdallas.gamePlayEngine.model.ActGameElement;
import edu.utdallas.gamePlayEngine.model.GameElement;
import edu.utdallas.gamePlayEngine.model.Graphic;
import edu.utdallas.gamePlayEngine.model.Scene;
import edu.utdallas.gamePlayEngine.model.Screen;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Act Model class. This class is observed by the view for any updates.
 * 
 */
public class Act extends Observable {
	private List<Scene> scenes;
	private String timer;
	private ActGameElement actGameElement;
	
	
	@XmlElement(name = "gameElement")
	public ActGameElement getActGameElement() {
		return actGameElement;
	}

	public void setActGameElement(ActGameElement actGameElement) {
		this.actGameElement = actGameElement;
	}

	@XmlElements
	(
			{
				@XmlElement(name = "sceneStructure"),
				@XmlElement(name = "Includes-Scenes")
			})
	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	
	@XmlElement(name = "timer")
	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		for (Scene scene : scenes) {
			scene.addObserver(o);
		}
	}

	public void actStart(GameState gameState) {
		// Handle Act specific activities in complex games
		setChanged();
		gameState.setAct(this);
		gameState.setMessage(Message.Start);
		notifyObservers(gameState);
	}

	/**
	 * Go through each Scene and start, play and end it If the process was to
	 * become any complex it can be moved to ActControl similar to GameControl
	 * 
	 * @throws InterruptedException
	 */
	public void actPlay(GameState gameState) throws InterruptedException {
		setChanged();
		gameState.setAct(this);
		gameState.setMessage(Message.Play);
		notifyObservers(gameState);
		/*
		 * while (currentScene < scenes.size()) { sceneToPlay.sceneStart();
		 * sceneToPlay.scenePlay(); sceneToPlay.sceneEnd(); currentScene += 1; }
		 */
	}

	public void actEnd(GameState gameState) {
		// Handle Act specific cleanups in complex games
		setChanged();
		gameState.setScene(null);
		gameState.setScreen(null);
		gameState.setGameElement(null);
		gameState.setMessage(Message.End);
		notifyObservers(gameState);
	}

	public void actpause() {
		throw new UnsupportedOperationException();
	}

	public void actResume() {
		throw new UnsupportedOperationException();
	}

	public void actSave() {
		throw new UnsupportedOperationException();
	}

}

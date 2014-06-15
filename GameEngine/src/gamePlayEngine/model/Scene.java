/**
 * Scene model class. Scene contains one or more screens and Backdrop and is contained in Act
 * This file defines Scene Model class
 */
package gamePlayEngine.model;

import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;
import gamePlayEngine.model.Timer;
import gamePlayEngine.model.GameElement;
import gamePlayEngine.model.Backdrop;
import gamePlayEngine.model.Graphic;
import gamePlayEngine.model.Identifier;
import gamePlayEngine.model.Screen;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Scene Model class. This class is observed by the view for any updates.
 * 
 */
public class Scene extends Observable {
	private List<Screen> screens;
	private String identifier;
	private String backdrop;	
	private String timer;

	@XmlElements
	(
			{
				@XmlElement(name = "screenStructure"),
				@XmlElement(name = "Includes-Screens")
			})
	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}

	@XmlElement(name = "backdrop")
	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}
	@XmlElement(name = "identifier")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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
		for (Screen screen : screens) {
			screen.addObserver(o);
		}
		//backdrop.addObserver(o);
	}

	public void sceneStart(GameState gameState) {
		setChanged();
		gameState.setScene(this);
		gameState.setMessage(Message.Start);
		notifyObservers(gameState);
		// backdrop.gameElementStart();
		// Thread.sleep(5000);
	}

	/**
	 * Go through each Screen and start, play and end it If the process was to
	 * become any complex it can be moved to SceneControl similar to GameControl
	 */
	public void scenePlay(GameState gameState) {
		setChanged();
		gameState.setMessage(Message.Play);
		notifyObservers(gameState);
		// backdrop.gameElementPlay();
		/*
		 * currentScreenToPlay = screens.get(currentScreen); while
		 * (currentScreen < screens.size()) { currentScreenToPlay.screenStart();
		 * currentScreenToPlay.screenPlay(); currentScreenToPlay.screenEnd();
		 * currentScreen += 1; }
		 */
	}

	public void sceneEnd(GameState gameState) {
		//backdrop.gameElementEnd();
		setChanged();
		gameState.setMessage(Message.End);		
		gameState.setScreen(null);
		gameState.setGameElement(null);
		notifyObservers(gameState);
	}

	public void scenePause() {
		throw new UnsupportedOperationException();
	}

	public void sceneResume() {
		throw new UnsupportedOperationException();
	}

	public void sceneSave() {
		throw new UnsupportedOperationException();
	}

}

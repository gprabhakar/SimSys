/**
 * Screen model class. Screen contains one or more Game Elements and is contained in Scene
 */
package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.Timer;
import edu.utdallas.gamePlayEngine.model.GameElement;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;

public class Screen extends Observable {

	private  List<GameElement> gameElements;
	private String timer;

	@XmlElement(name = "gameElement")
	public List<GameElement> getGameElements() {
		return gameElements;
	}

	public void setGameElements(List<GameElement> gameElements) {
		this.gameElements = gameElements;
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
		for (GameElement gameElement : gameElements) {
			gameElement.addObserver(o);
		}
	}

	public void screenStart(GameState gameState) {
		// Handle Screen specific activities in complex games
		setChanged();
		gameState.setMessage(Message.Start);
		gameState.setScreen(this);
		notifyObservers(gameState);
	}

	/**
	 * Go through each GameElement and start, play and end it
	 * If the process was to become any complex it can be moved to ScreenControl similar to GameControl
	 */
	public void screenPlay(GameState gameState) {
		setChanged();
		gameState.setMessage(Message.Play);
		notifyObservers(gameState);
		/*while (currentGameElement < gameElements.size()) {
			GameElement gameElementToPlay = gameElements.get(currentGameElement);
			gameElementToPlay.gameElementStart();
			gameElementToPlay.gameElementPlay();
			gameElementToPlay.gameElementEnd();			
			currentGameElement += 1;
		}*/
	}
	
	public void screenEnd(GameState gameState) {
		// Handle Screen specific cleanups in complex games
		setChanged();
		gameState.setMessage(Message.End);		
		gameState.setGameElement(null);
		notifyObservers(gameState);
	}
	
	public void screenPause() {
		throw new UnsupportedOperationException();
	}

	public void screenResume() {
		throw new UnsupportedOperationException();
	}

	public void screenSave() {
		throw new UnsupportedOperationException();
	}


}

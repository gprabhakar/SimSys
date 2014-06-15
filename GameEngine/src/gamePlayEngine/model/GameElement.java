/**
 * Generic GameElement class servers as a base class for other derived classes - GameCharacer, Graphic and Audio 
 * This file contains the definition of this class.
 */
package gamePlayEngine.model;

import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;
import gamePlayEngine.model.Audio;
import gamePlayEngine.model.GameCharacter;
import gamePlayEngine.model.Graphic;

import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Base class for GameCharacter, Graphic and Audio provides default
 * implementation of start, play and end Derived classes can override these
 * methods to provide suitable implementation.
 */
@XmlSeeAlso({ GameCharacter.class, Graphic.class, Audio.class })
public class GameElement extends Observable {

	private GameElementIdentifier gameElementIdentifier;
	private ChallengeStructure challengeStructure;

	@XmlElement(name = "challengeStructure")
	public ChallengeStructure getChallengeStructure() {
		return challengeStructure;
	}

	public void setChallengeStructure(ChallengeStructure challengeStructure) {
		this.challengeStructure = challengeStructure;
	}

	@XmlElement(name = "gameElement")
	public GameElementIdentifier getGameElementIdentifier() {
		return gameElementIdentifier;
	}

	public void setGameElementIdentifier(
			GameElementIdentifier gameElementIdentifier) {
		this.gameElementIdentifier = gameElementIdentifier;
	}

	/**
	 * Default Implementation for classes that do not override this method
	 */
	public void gameElementStart(GameState gameState) {
		setChanged();
		gameState.setMessage(Message.Start);

		gameState.setGameElement(this);
		notifyObservers(gameState);
	}

	/**
	 * Default Implementation for classes that do not override this method
	 */
	public void gameElementPlay(GameState gameState) {

		System.out.println("Gamestate is " + gameState);
		setChanged();
		gameState.setMessage(Message.Play);
		notifyObservers(gameState);

	}

	/**
	 * Default Implementation for classes that do not override this method
	 */
	public void gameElementEnd(GameState gameState) {
		System.out.println("Gamestate is " + gameState);
		setChanged();
		gameState.setMessage(Message.End);
		notifyObservers(gameState);
	}

	public void gameElementPause() {
		throw new UnsupportedOperationException();
	}

	public void gameElementResume() {
		throw new UnsupportedOperationException();
	}

	public void gameElementSave() {
		throw new UnsupportedOperationException();
	}
}

/**
 * Game model class - Game contains acts and character
 * This file defines Game model class
 */
package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.Act;
import edu.utdallas.gamePlayEngine.model.GameConstants;
import edu.utdallas.gamePlayEngine.model.GameCharacter;
import edu.utdallas.gamePlayEngine.model.Prop;
import edu.utdallas.gamePlayEngine.model.Player;
import edu.utdallas.gamePlayEngine.view.GameView;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Game model class is being observed by view
 * 
 */
@XmlRootElement(name = "game")
public class GameModel extends Observable {

	private static GameModel gameModelObj;
	public static boolean isSequence = true;

	private GameConstants gameConstants;

	@XmlElement(name = "gameConstants")
	public GameConstants getGameConstants() {
		return gameConstants;
	}

	public void setGameConstants(GameConstants gameConstants) {
		this.gameConstants = gameConstants;
	}

	public static GameModel getGameModelObject() {
		return gameModelObj;
	}

	public static List<Act> acts;
	private static int currentAct = 0; // keeps track of current Act that is
										// being processed.
	private GameCharacter character;
	private GameView gameView;
	private Player player;

	@XmlElement(name = "gameElement")
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
		System.out.println("model knows about view");
	}

	/**
	 * default constructor
	 */
	/*
	 * public GameModel() { System.out.println("model has been initialized"); }
	 */

	public void startGame(String xmlFilePath) throws Exception {
		System.out.println("calling load in GameModel: " + xmlFilePath);
		GameModel gameModel = load(xmlFilePath);
		// TO check whether next tag is present in the gameelement. If yes.
		// Follow the value in the next tag.
		// Otherwise do sequencing.
		for(int i = 0; i < gameModel.getActs().size(); i++) {  //added by rithika
			System.out.println("inside acts loop");
			Prop prop = null;
			try {
			 prop = (Prop) gameModel.getActs().get(0).getScenes().get(0)
						.getScreens().get(0).getGameElements().get(0);
			/*for(int j = 0; j < gameModel.getActs().get(i).getScenes().size(); j++) {
				System.out.println("inside scenes loop");
			for(int k = 0; k < gameModel.getActs().get(i).getScenes().get(j).getScreens().size(); k++) {
				System.out.println("inside screens loop");
			for(int z = 0; z < gameModel.getActs().get(i).getScenes().get(j).getScreens().get(k).getGameElements().size();z++) {
				System.out.println("inside gameElements loop");
				prop = (Prop) gameModel.getActs().get(i).getScenes().get(j)
						 .getScreens().get(k).getGameElements().get(z);
			  }
			 }
			}*/
			} catch (Exception e) {
				System.out.println("Invalid xml" + e);
			}
	
			if (prop != null) {
				if (prop.getNext() == null)
					isSequence = true;
			else {
				if (!prop.getNext().equals("NONE"))
					isSequence = false;
				else
					isSequence = true;
			}
		}

		System.out.println("isSequence value is: " + isSequence);
		gameModelObj = gameModel;
		setValues(gameModel);
		addObserver(gameView);
		}
	}

	/**
	 * This is private method Unmarshalls Game XML to create Game object
	 * 
	 * @param xmlFilePath
	 * @return
	 * @throws Exception
	 */
	private GameModel load(String xmlFilePath) throws Exception {
		try {
			// Load Game from XML
			JAXBContext context = JAXBContext.newInstance(GameModel.class);
			System.out.println("instance passed ");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			System.out.println("marshaller created");
			return (GameModel) unmarshaller.unmarshal(new File(xmlFilePath));

		} catch (Exception ex) {
			// log the exception, show the error message on UI
			System.out.println(ex.getMessage());
			throw ex;
		}
	}

	@XmlElements({ @XmlElement(name = "actStructure"),
			@XmlElement(name = "Includes-Acts") })
	public List<Act> getActs() {
		return acts;
	}

	public void setActs(List<Act> acts) {
		this.acts = acts;
	}

	@XmlElement(name = "gameElement")
	public GameCharacter getCharacter() {
		return character;
	}

	public void setCharacter(GameCharacter character) {
		this.character = character;
	}
	
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		for (Act act : acts) {
			act.addObserver(o);
		}
		character.addObserver(o);
	}

	public void gameModelStart(GameState gameState) {
		// Handle Game specific initializations if any for complex games
		setChanged();
		gameState.setMessage(Message.Start);
		notifyObservers(gameState);
	}

	/**
	 * Game Play method goes through each of the contained acts and plays those.
	 * 
	 * @throws InterruptedException
	 */
	public void gameModelPlay(GameState gameState) throws InterruptedException {
		setChanged();
		gameState.setMessage(Message.Play);
		notifyObservers(gameState);

		try {
			Act actToPlay = acts.get(currentAct);
			actToPlay.actStart(gameState);
		} catch (Exception e) {
			System.out.println("Hello!!!Here it comes!!!");
			e.printStackTrace();
		}

		// Go through each Act
		/*
		 * while (currentAct < acts.size()) { Act actToPlay =
		 * acts.get(currentAct); actToPlay.actStart(); actToPlay.actPlay();
		 * actToPlay.actEnd(); currentAct += 1; }
		 */
	}

	public static void ControllerListener(Observable observable, Message message) {

	}

	public void gameModelEnd() {
		// Handle Game specific clean up if any for complex games
		setChanged();
		deleteObservers();
		notifyObservers(Message.End);
	}

	public void gameModelPause() {
		throw new UnsupportedOperationException();
	}

	public void gameModelResume() {
		throw new UnsupportedOperationException();
	}

	public void gameModelSave() {
		throw new UnsupportedOperationException();
	}

	/* set all the values in the game model */
	public void setValues(GameModel gameModel) {
		this.acts = gameModel.acts;
		this.character = gameModel.character;

	}
}

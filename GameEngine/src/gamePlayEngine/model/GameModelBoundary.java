/**
 * This file contains the Boundary class for Game Model. All messages flow through this boundary
 */
package gamePlayEngine.model;

import javax.swing.JFrame;

import gamePlayEngine.TestGameEngine;
import gamePlayEngine.controller.GameState;
import gamePlayEngine.view.GameView;

/**
 * Boundary class for the Game
 */
public class GameModelBoundary {

	private GameModelControl gameModelControl;

	public GameModelBoundary(GameModel gameModel) {
		gameModelControl = new GameModelControl(gameModel);
	}

	public void addObserver(GameView view) {
		gameModelControl.gmcAddObserver(view);

	}

	public void startGame(String xmlFilePath, JFrame myMenuFrame) throws Exception {
		gameModelControl.startGame(xmlFilePath);
		//testing whether the values have been loaded in to model or not
	    new TestGameEngine().testGame(gameModelControl.getGameModel());
	    //above piece of code is for testing
	    GameState gameState = new GameState(myMenuFrame);
	    gmbStart(gameState);
		//gmbPlay();
		//gmbEnd();
	}

	public void gmbStart(GameState gameState) {
		gameModelControl.gmcStart(gameState);
	}
	
	public void setView(GameView gameView){
		gameModelControl.setGameView(gameView);
	}

	public void gmbPlay() throws InterruptedException {
		gameModelControl.gmcPlay();
	}

	public void gmbEnd() {
		gameModelControl.gmcEnd();
	}

	public void gmbPause() {
		throw new UnsupportedOperationException();
	}

	public void gmbResume() {
		throw new UnsupportedOperationException();
	}

	public void gmbSave() {
		throw new UnsupportedOperationException();
	}
	/*this method adds view as the observer*/
}

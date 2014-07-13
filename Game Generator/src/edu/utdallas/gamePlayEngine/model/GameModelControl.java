/**
 * Controller class for Game Model - defines process on Game model
 * This file contains the Control class for Game Model
 */
package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.view.GameView;

/**
 * GameControl class - mainly defines play process for Game
 */
public class GameModelControl {

	private GameModel gameModel;

	public GameModelControl(GameModel gameModel) {
		this.gameModel = gameModel;
		//just for testing , remove it afterwards
		//System.out.println("model has been initialized");
	}
	
	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public void gmcAddObserver(GameView view) {
		gameModel.addObserver(view);
	}

	public void startGame(String xmlFilePath) throws Exception {
		 gameModel.startGame(xmlFilePath);
		
	}
    
	public void gmcStart(GameState gameState) {
		gameModel.gameModelStart(gameState);
	}
	
	/**
	 * Game Play process consists of start, play and end 
	 * @throws InterruptedException 
	 */
	public void gmcPlay() throws InterruptedException {
		//gameModel.gameModelPlay();
	}

	public void gmcEnd() {
		gameModel.gameModelEnd();
	}

	public void gmcPause() {
		throw new UnsupportedOperationException();
	}

	public void gmcResume() {
		throw new UnsupportedOperationException();
	}

	public void gmcSave() {
		throw new UnsupportedOperationException();
	}
	
	public void setGameView(GameView gameView){
		gameModel.setGameView(gameView);
		
	}
	
}

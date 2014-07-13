/*
*Quiz class extends Challenge base class
*/
package edu.utdallas.gamePlayEngine.model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.ChallengeStructure;
import edu.utdallas.gamePlayEngine.model.GameElement;
import edu.utdallas.gamePlayEngine.model.StemDescription;
import edu.utdallas.gamePlayEngine.model.StemOption;
import edu.utdallas.gamePlayEngine.model.StemQuestion;

import javax.xml.bind.annotation.XmlElement;

public class Quiz extends ChallengeStructure {
	
		private StemDescription description;
		private StemOption option;
		private StemQuestion question;
		private String timer;
		private String identifier;
		private  List<GameElement> gameElements;
	
		@XmlElement(name="gameElement")
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

		public void quizStart(GameState gameState) {
			// Handle Act specific activities in complex games
			setChanged();
			gameState.setQuiz(this);
			gameState.setMessage(Message.Start);
			notifyObservers(gameState);
		}

		public void quizPlay(GameState gameState) throws InterruptedException {
			setChanged();
			gameState.setQuiz(this);
			gameState.setMessage(Message.Play);
			notifyObservers(gameState);
			
		}
		
		@XmlElement(name = "identifier")
		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}
		
		public void quizEnd(GameState gameState) {
			setChanged();
			gameState.setMessage(Message.End);
			notifyObservers(gameState);
		}

		public void quizpause() {
			throw new UnsupportedOperationException();
		}

		public void quizResume() {
			throw new UnsupportedOperationException();
		}

		public void quizSave() {
			throw new UnsupportedOperationException();
		}

	}


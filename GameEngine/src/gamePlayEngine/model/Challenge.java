/*
 * Challenge class - used as a base class for other derived classes like Quiz
 */
package gamePlayEngine.model;

import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public class Challenge extends Observable {
	
	    private List<Quiz> quizzes;		
	    
	    
	    @XmlElement(name = "quizStructure")
		public List<Quiz> getQuiz() {
			return quizzes;
		}

	    public void setQuizzes(List<Quiz> quizzes) {
			this.quizzes = quizzes;
		}		

		@Override
		public synchronized void addObserver(Observer o) {
			super.addObserver(o);
			for (Quiz quiz : quizzes) {
				quiz.addObserver(o);
			}
		}

		public void challengeStart(GameState gameState) {
			// Handle Act specific activities in complex games
			setChanged();
			//gameState.setChallenge(this);
			gameState.setMessage(Message.Start);
			notifyObservers(gameState);
		}

		
		public void challengePlay(GameState gameState) throws InterruptedException {
			setChanged();
			//gameState.setChallenge(this);
			gameState.setMessage(Message.Play);
			notifyObservers(gameState);
			
		}

		public void challengeEnd(GameState gameState) {
			// Handle Act specific cleanups in complex games
			setChanged();
			gameState.setMessage(Message.End);
			notifyObservers(gameState);
		}

		public void challengePause() {
			throw new UnsupportedOperationException();
		}

		public void challengeResume() {
			throw new UnsupportedOperationException();
		}

		public void challengeSave() {
			throw new UnsupportedOperationException();
		}

	}




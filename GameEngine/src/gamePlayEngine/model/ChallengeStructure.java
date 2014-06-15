package gamePlayEngine.model;

import gamePlayEngine.controller.GameState;
import gamePlayEngine.controller.Message;
import gamePlayEngine.model.Quiz;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;

public class ChallengeStructure extends Observable{

	private  List<GameElement> gameElements;
	private Quiz quiz;

	@XmlElement(name="gameElement")
	public List<GameElement> getGameElements() {
		return gameElements;
	}

	public void setGameElements(List<GameElement> gameElements) {
		this.gameElements = gameElements;
	}
	
	@XmlElement(name = "quiz")
	public Quiz getQuiz() {
		return quiz;
	}
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		 quiz.addObserver(o);		
	}

	public void challengeStart(GameState gameState) {
		
		setChanged();
		gameState.setChallengeStructure(this);
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
		//backdrop.gameElementEnd();
		setChanged();
		gameState.setMessage(Message.End);		
		gameState.setQuiz(null);
		gameState.setGameElement(null);
		notifyObservers(gameState);
	}
	
	
	
	
}

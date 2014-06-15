package gamePlayEngine.model;

import gamePlayEngine.model.DeclarationsInitializations;
import gamePlayEngine.model.GamePlay;

import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;

public class NewScreen extends Observable{
	
	
	private String identifier;
	private String purpose;
	private String learningObj;
	private GamePlay gamePlay;
	
	private DeclarationsInitializations declarationsInitializations;
	private String alternateFlow;
	
	@XmlElement(name="Game-Play")
	public GamePlay getGamePlay() {
		return gamePlay;
	}

	public void setGamePlay(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
	}

	

	@XmlElement(name="Alternate-Flow")
	public String getAlternateFlow() {
		return alternateFlow;
	}

	public void setAlternateFlow(String alternateFlow) {
		this.alternateFlow = alternateFlow;
	}

	@XmlElement(name="Identifier")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@XmlElement(name="Purpose")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@XmlElement(name="Learning-Objectives")
	public String getLearningObj() {
		return learningObj;
	}

	public void setLearningObj(String learningObj) {
		this.learningObj = learningObj;
	}

	@XmlElement(name="Declarations-Initializations")
	public DeclarationsInitializations getDeclarationsInitializations() {
		return declarationsInitializations;
	}

	public void setDeclarationsInitializations(
			DeclarationsInitializations declarationsInitializations) {
		this.declarationsInitializations = declarationsInitializations;
	}
	
	
	

}

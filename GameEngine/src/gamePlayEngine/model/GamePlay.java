package gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;

public class GamePlay {

	private StartOfScreen startScreen;
	private Interactions interactions;
	private EndOfScreen descriptions;
	
	@XmlElement(name="Start-of-Screen")
	public StartOfScreen getStartScreen() {
		return startScreen;
	}
	public void setStartScreen(StartOfScreen startScreen) {
		this.startScreen = startScreen;
	}
	
	@XmlElement(name="Interactions")
	public Interactions getInteractions() {
		return interactions;
	}
	public void setInteractions(Interactions interactions) {
		this.interactions = interactions;
	}
	
	@XmlElement(name="End-of-Screen")
	public EndOfScreen getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(EndOfScreen descriptions) {
		this.descriptions = descriptions;
	}
	
	
}

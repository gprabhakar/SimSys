package edu.utdallas.gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;

import edu.utdallas.gamePlayEngine.model.GenericInteraction;

public class Props {

	private GenericInteraction genericInteraction;
	private String setDecorations;

	@XmlElement(name="Generic-Interaction")
	public GenericInteraction getGenericInteraction() {
		return genericInteraction;
	}

	public void setGenericInteraction(GenericInteraction genericInteraction) {
		this.genericInteraction = genericInteraction;
	}

	public String getSetDecorations() {
		return setDecorations;
	}

	public void setSetDecorations(String setDecorations) {
		this.setDecorations = setDecorations;
	}

}

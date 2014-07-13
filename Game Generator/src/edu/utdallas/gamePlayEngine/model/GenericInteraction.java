package edu.utdallas.gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Generic-Interaction")
public class GenericInteraction {
	
	private InformationBox informationBox;

	@XmlElement(name="Information-Box")
	public InformationBox getInformationBox() {
		return informationBox;
	}

	public void setInformationBox(InformationBox informationBox) {
		this.informationBox = informationBox;
	}
	
	
	 
	
}

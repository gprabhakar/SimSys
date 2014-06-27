package edu.utdallas.gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;

public class ActGameElement {
	
	
	private String actId;

	@XmlElement(name = "id")
	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	
}

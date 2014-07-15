package edu.utdallas.gamewizard;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Screen2 {
	String type;
	//String name;
	
	public String getType(){
		return type;
	}
	
	@XmlElement
	public void setType(String type){
		this.type = type;
	}
	
}

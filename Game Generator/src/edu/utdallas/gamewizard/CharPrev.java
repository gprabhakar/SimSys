package edu.utdallas.gamewizard;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



public class CharPrev {
	String name;
	String fileName;
	String type;
	
	
	public String getName(){
		return name;
	}
	@XmlAttribute
	public void setName(String name){
		this.name = name;
	}
	
	public String getFileName(){
		return fileName;
	}
	@XmlElement
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getType(){
		return type;
	}
	@XmlElement
	public void setType(String type){
		this.type = type;
	}
}

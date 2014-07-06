package edu.utdallas.gamewizard;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GameTemplate {
	@XmlElementWrapper(name="acts")
    @XmlElement(name="act")
	ArrayList <Act> actList;
	String name;
	
	public GameTemplate(){
		actList = new ArrayList<Act>();
	}
	
	public String getName(){
		return name;
	}
	
	@XmlAttribute
	public void setName(String name){
		this.name = name;
	}
	
	public ArrayList <Act> getActs(){
		return actList;
	}
	
	@XmlElement
	public void setActs(ArrayList <Act> actList){
		this.actList = actList;
	}
	
	
}

package edu.utdallas.gamewizard;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

public class Act {
	@XmlElementWrapper(name="scenes")
    @XmlElement(name="scene")
	ArrayList<Scene> sceneList;
	String name;
	public Act(){
		sceneList = new ArrayList<Scene>();
	}
	
	public Act(Act a){
		this.sceneList = a.sceneList;
	}
	public String getName(){
		return name;
	}
	
	@XmlAttribute
	public void setName(String name){
		this.name = name;
	}
	
	public ArrayList<Scene> getScenes(){
		return sceneList;
	}
	
	@XmlElement
	public void setScenes(ArrayList<Scene> sceneList){
		this.sceneList = sceneList;
	}
}

package edu.utdallas.gamewizard;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import edu.utdallas.gamespecification.*;
public class Act2 {
	@XmlElementWrapper(name="scenes")
    @XmlElement(name="scene")
	ArrayList<Scene2> sceneList;
	String name;
	ArrayList<LearningObjectiveType> learningObjective;
	public Act2(){
		sceneList = new ArrayList<Scene2>();
	}
	
	public Act2(Act2 a){
		this.sceneList = a.sceneList;
	}
	public String getName(){
		return name;
	}
	
	@XmlAttribute
	public void setName(String name){
		this.name = name;
	}
	
	public ArrayList<Scene2> getScenes(){
		return sceneList;
	}
	
	@XmlElement
	public void setScenes(ArrayList<Scene2> sceneList){
		this.sceneList = sceneList;
	}
	
	public void setLearningObjective(ArrayList<LearningObjectiveType> lo)
	{
		this.learningObjective = lo;
	}
	
	public ArrayList<LearningObjectiveType> getLearningObjective()
	{
		 if (learningObjective == null) {
			 learningObjective = new ArrayList<LearningObjectiveType>();
	     }
		return learningObjective;
	}
}

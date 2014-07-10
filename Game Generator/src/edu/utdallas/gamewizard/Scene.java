package edu.utdallas.gamewizard;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


public class Scene {
	@XmlElementWrapper(name="screens")
    @XmlElement(name="screen")
	ArrayList<Screen> screenList;
	String transitionType;
//	String name;
//	
//	public String getName(){
//		return name;
//	}
//	
//	@XmlAttribute
//	public void setName(String name){
//		this.name = name;
//	}
	public Scene(){
		screenList = new ArrayList<Screen>();
	}
	public Scene(Scene scene)
	{
		this.screenList = scene.screenList;
	}
	public ArrayList<Screen> getScreens(){
		return screenList;
	}
	
	@XmlElement
	public void setScreen(ArrayList<Screen> screenList){
		this.screenList = screenList;
	}
}

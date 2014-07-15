package edu.utdallas.gamewizard;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


public class Scene2 {
	@XmlElementWrapper(name="screens")
    @XmlElement(name="screen")
	ArrayList<Screen2> screenList;
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
	public Scene2(){
		screenList = new ArrayList<Screen2>();
	}
	public Scene2(Scene2 scene)
	{
		this.screenList = scene.screenList;
	}
	public ArrayList<Screen2> getScreens(){
		return screenList;
	}
	
	@XmlElement
	public void setScreen(ArrayList<Screen2> screenList){
		this.screenList = screenList;
	}
}

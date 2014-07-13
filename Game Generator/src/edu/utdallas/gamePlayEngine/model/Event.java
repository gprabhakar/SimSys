package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;

import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;

public class Event extends Observable{

	private EventName eventName;
	private Animation animation ;
	private Time time;
	
	@XmlElement(name = "eventName")
	public EventName getEventName() {
		return eventName;
	}
	public void setEventName(EventName eventName) {
		this.eventName = eventName;
	}
	@XmlElement(name = "animation")
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	@XmlElement(name = "time")
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public void eventStart(GameState gameState){
		setChanged();
		notifyObservers(Message.Start);
		
	}

	public void eventPlay(GameState gameState){
		setChanged();
		notifyObservers(Message.Play);
		
	}
	
	public void eventEnd(GameState gameState){
		setChanged();
		notifyObservers(Message.End);
	}
	
}

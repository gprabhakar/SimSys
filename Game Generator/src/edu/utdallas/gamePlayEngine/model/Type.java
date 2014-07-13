package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.GameElement;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;

public class Type extends Observable{

	private String typeName;
	private List<Event> events;
	private int currentEvent = 0;
	private List<If> ifs;
	
	@XmlElement(name = "if")
	public List<If> getIfs() {
		return ifs;
	}
	
	public void setIfs(List<If> ifs) {
		this.ifs = ifs;
	}
	
	//@XmlElement(name = "typeName")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@XmlElement(name = "event")
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);		
		if(events!=null){
		for (Event event : events) {
			if(event!=null)
			event.addObserver(o);
		}
		}
	}
	
	public void typeStart(GameState gameState){
		setChanged();
		notifyObservers(Message.Start);
		
	}

	public void typePlay(GameState gameState){
		setChanged();
		notifyObservers(Message.Play);
		if(events!=null){
		while (currentEvent < events.size()) {
			Event event = events.get(currentEvent);
			event.eventStart(gameState);
			event.eventPlay(gameState);
			event.eventEnd(gameState);			
			currentEvent += 1;
		}
		}
		
	}
	
	public void typeEnd(GameState gameState){
		setChanged();
		notifyObservers(Message.End);
	}
	
}

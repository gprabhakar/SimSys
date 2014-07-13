/**
 * Player model class. Game contains Player.
 * This file contains the implementation of Player 
 */
package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.model.GameCharacter;
import edu.utdallas.gamePlayEngine.model.Location;
import java.util.Observer;

import javax.xml.bind.annotation.XmlElement;

/**
 * Player class derives from GameCharacter
 */
public class Player extends GameCharacter {
	private Location location;
	private String name;
	private Profile profile;
	private Size size;

	@Override
	public synchronized void addObserver(Observer observer) {
		super.addObserver(observer);		
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="profile")
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@XmlElement(name="size")
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}	
}

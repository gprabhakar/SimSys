/**
 * Reward model for GameCharacter
 */
package edu.utdallas.gamePlayEngine.model;

import java.util.Observable;

public class Reward extends Observable {
	private int certificates;
	private int hint;	
	private int points;
	private int promotions;
	private int trophies;
	private String title;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCertificates() {
		return certificates;
	}

	public int getHint() {
		return hint;
	}	

	public int getPoints() {
		return points;
	}

	public int getPromotions() {
		return promotions;
	}

	public int getTrophies() {
		return trophies;
	}

	public void setCertificates(int certificates) {
		this.certificates = certificates;
	}

	public void setHint(int hint) {
		this.hint = hint;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public void setPromotions(int promotions) {
		this.promotions = promotions;
	}

	public void setTrophies(int trophies) {
		this.trophies = trophies;
	}

	public void addPoints(int points) {
		this.points += points;
		System.out.println("Model Reward Updated");
		setChanged();
		//notifyObservers(Message.Play);
	}
}

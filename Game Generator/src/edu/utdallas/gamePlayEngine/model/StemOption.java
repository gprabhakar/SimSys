package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.model.Color;
import edu.utdallas.gamePlayEngine.model.Location;
import edu.utdallas.gamePlayEngine.model.Type;
import edu.utdallas.gamePlayEngine.model.Size;

import javax.xml.bind.annotation.XmlElement;

public class StemOption {
	private Type type;
	private Color color;
	private Location location;
	private Size size;
	private String text;
	private String answerValue;
	private String hint;
	
	
	@XmlElement(name="hint")
	public String getHint() {		
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	@XmlElement(name="evaluation")
	public String getAnswerValue() {
		return answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	@XmlElement(name="color")
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@XmlElement(name="location")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@XmlElement(name="size")
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@XmlElement(name="text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@XmlElement(name="type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}

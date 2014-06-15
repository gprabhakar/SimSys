package gamePlayEngine.model;

import gamePlayEngine.model.Color;
import gamePlayEngine.model.Location;
import gamePlayEngine.model.Type;
import gamePlayEngine.model.Size;

import javax.xml.bind.annotation.XmlElement;

public class StemQuestion {

	private Type type;
	private Color color;
	private Location location;
	private Size size;
	private String text;
	
	

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

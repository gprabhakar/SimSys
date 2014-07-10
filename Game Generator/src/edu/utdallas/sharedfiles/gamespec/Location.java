package edu.utdallas.sharedfiles.gamespec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
	"x",
	"y"	
})

public class Location {
	@XmlElement(name = "x", required = true)
	private int x;
	@XmlElement(name = "y", required = true)
	private int y;
	
	public Location() {
		
	}
	
	public Location(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
	
	
}

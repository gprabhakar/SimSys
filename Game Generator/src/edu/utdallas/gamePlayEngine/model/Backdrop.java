/**
 * This file contains Backdrop implementation which extends Graphic
 */
package edu.utdallas.gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;



public class Backdrop extends Graphic {

	String name;	
	
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

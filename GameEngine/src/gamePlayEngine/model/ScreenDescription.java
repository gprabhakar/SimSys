
package gamePlayEngine.model;

import javax.xml.bind.annotation.XmlElement;

public class ScreenDescription {

	private String 	screen;
	private String description;
	
	@XmlElement(name="Screen")
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

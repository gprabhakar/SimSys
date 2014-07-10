package edu.utdallas.sharedfiles.gamespec;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Size", propOrder = {
	"width",
	"height"	
})

public class Size {
	@XmlElement(name = "width", required = true)
	private int width;
	@XmlElement(name = "height", required = true)
	private int height;
	
	public Size() {
		
	}

	public Size(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}

package edu.utdallas.gamegeneratorcollection.ComponentCreation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:27 PM
 */
@XmlRootElement(name = "GameObject")
public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private String pathToAsset;
    private String text;

    public GameObject() {

    }

    public GameObject(int locX, int locY, int width, int height, String pathToAsset) {
    	System.out.println("I set X to: " + locX);
        this.x = locX;
        this.y = locY;
        this.width = width;
        this.height = height;
        this.pathToAsset = pathToAsset;
    }

    public int getX() {
        return x;
    }

    @XmlElement(name = "Height")
    public void setHeight(int height) {
        this.height = height;
    }
    
    @XmlElement(name = "LocX")
    public void setX(int x) {
        this.x = x;
    }

    
    public int getY() {
        return y;
    }

    @XmlElement(name = "LocY")
    public void setY(int y) {
        this.y =  y;
    }
    
    public int getWidth() {
        return width;
    }

    @XmlElement(name = "Width")
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    

    public String getPathToAsset() {
        return pathToAsset;
    }

    @XmlElement(name = "PathToAsset")
    public void setPathToAsset(String pathToAsset) {
        this.pathToAsset = pathToAsset;
    }

    public String getText() {
        return text;
    }

    @XmlElement(name = "Text")
    public void setText(String text) {
        this.text = text;
    }
}

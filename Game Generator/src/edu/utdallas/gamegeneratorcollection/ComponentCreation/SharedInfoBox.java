package edu.utdallas.gamegeneratorcollection.ComponentCreation;

/**
 * User: clocke
 * Date: 3/7/13
 * Time: 8:57 PM
 */

public class SharedInfoBox extends GameObject {
    private String name;

    public SharedInfoBox() {
        super();
    }

    public SharedInfoBox(int locX, int locY, int width, int height, String pathToAsset, String name) {
        super(locX, locY, width, height, pathToAsset);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

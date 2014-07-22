package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Sean
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Location", propOrder = {
    "x",
    "y",
    "z"
})

public class Location {

    /**
     * x value for an item's location.
     */
    @XmlElement(name = "x", required = true)
    private int x;
    /**
     * y value for an item's location.
     */
    @XmlElement(name = "y", required = true)
    private int y;
    /**
     * z value for an item's location.
     */
    @XmlElement(name = "z", required = true)
    private int z;

    /**
     * Default constructor.
     */
    public Location() {

    }

    /**
     *
     * @param xPos
     * allowed object is
     * {@link int}
     * @param yPos
     * allowed object is
     * {@link int}
     */
    public Location(final int xPos, final int yPos) {
        super();
        this.x = xPos;
        this.y = yPos;
        this.z = 0;
    }
    
    /**
    *
    * @param xPos
    * allowed object is
    * {@link int}
    * @param yPos
    * allowed object is
    * {@link int}
    * @param zPos
    * allowed object is
    * {@link int}
    */
   public Location(final int xPos, final int yPos, final int zPos) {
       super();
       this.x = xPos;
       this.y = yPos;
       this.z = zPos;
   }

    /**
     *
     * @return
     * possible object is
     * {@link int}
     */
    public final int getX() {
        return x;
    }

    /**
     *
     * @param xPos
     * allowed object is
     * {@link int}
     */
    public final void setX(final int xPos) {
        this.x = xPos;
    }

    /**
    *
    * @return
    * allowed object is
    * {@link int}
    */
    public final int getY() {
        return y;
    }

    /**
    *
    * @param yPos
    * allowed object is
    * {@link int}
    */
    public final void setY(final int yPos) {
        this.y = yPos;
    }

    /**
    *
    * @return
    * allowed object is
    * {@link int}
    */
    public final int getZ() {
        return z;
    }

    /**
    *
    * @param zPos
    * allowed object is
    * {@link int}
    */
    public final void setZ(final int zPos) {
        this.y = zPos;
    }
}

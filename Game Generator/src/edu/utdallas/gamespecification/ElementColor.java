package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Holds any background and foreground colors
 * for GameElements.
 * @author Sean
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElementColors", propOrder = {
    "backgroundColor",
    "foregroundColor"
})
public class ElementColor {
    /**
     * Holds the background color.
     */
    @XmlElement(name = "BGColor")
    private Color backgroundColor;
    /**
     * Holds the foreground color.
     */
    @XmlElement(name = "FGColor")
    private Color foregroundColor;

    /**
     *
     * @return
     * {@link Color}
     */
    public final Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     *
     * @param value
     * {@link Color}
     */
    public final void setBackgroundColor(final Color value) {
        this.backgroundColor = value;
    }

    /**
     *
     * @return
     * {@link Color}
     */
    public final Color getForegroundColor() {
        return foregroundColor;
    }

    /**
     *
     * @param value
     * {@link Color}
     */
    public final void setForegroundColor(final Color value) {
        this.foregroundColor = value;
    }


}

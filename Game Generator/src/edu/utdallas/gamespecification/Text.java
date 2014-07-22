package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Contains information on the text
 * associated with GameElement objects.
 * @author Sean
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Text", propOrder = {
        "text",
        "font",
        "fontSize"
})
public class Text {

    /**
     * Holds the text to be displayed.
     */
    @XmlElement(name = "Text")
    private String text;
    /**
     * Holds the font.
     */
    @XmlElement(name = "Font")
    private String font;
    /**
     * Holds the text size.
     */
    @XmlElement(name = "FontSize")
    private int fontSize;

    /**
     * Default constructor.
     */
    public Text() {

    }

    /**
     *
     * @param textVal
     * {@link String}
     */
    public Text(final String textVal) {
        this.text = textVal;
    }

    /**
     *
     * @param textVal
     * {@link String}
     * @param fontVal
     * {@link String}
     * @param sizeVal
     * {@link int}
     */
    public Text(final String textVal, final String fontVal,
            final int sizeVal) {
        this.text = textVal;
        this.font = fontVal;
        this.fontSize = sizeVal;
    }

    /**
     *
     * @return
     * {@link String}
     */
    public final String getText() {
        return text;
    }

    /**
     *
     * @param value
     * {@link String}
     */
    public final void setText(final String value) {
        this.text = value;
    }

    /**
     *
     * @return
     * {@link String}
     */
    public final String getFont() {
        return font;
    }

    /**
     *
     * @param value
     * {@link String}
     */
    public final void setFont(final String value) {
        this.font = value;
    }

    /**
     *
     * @return
     * {@link int}
     */
    public final int getFontSize() {
        return fontSize;
    }

    /**
     *
     * @param value
     * {@link int}
     */
    public final void setFontSize(final int value) {
        this.fontSize = value;
    }


}

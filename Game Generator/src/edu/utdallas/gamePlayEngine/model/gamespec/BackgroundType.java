//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.04.25 at 07:45:04 PM CDT
//


package edu.utdallas.gamePlayEngine.model.gamespec;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BackgroundType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="BackgroundType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Background" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BackgroundType", propOrder = {
    "background"
})
public class BackgroundType {

    @XmlElement(name = "Background", required = true)
    protected String background;

    /**
     * Gets the value of the background property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the value of the background property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBackground(String value) {
        this.background = value;
    }

}

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
 * <p>Java class for LearningObjectiveType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="LearningObjectiveType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LearningObjective" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LearningObjectiveType", propOrder = {
    "learningObjective"
})
public class LearningObjectiveType {

    @XmlElement(name = "LearningObjective", required = true)
    private String learningObjective;

    /**
     * Gets the value of the learningObjective property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public final String getLearningObjective() {
        return learningObjective;
    }

    /**
     * Sets the value of the learningObjective property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setLearningObjective(final String value) {
        this.learningObjective = value;
    }

}

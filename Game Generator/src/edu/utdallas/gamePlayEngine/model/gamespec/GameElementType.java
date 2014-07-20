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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GameElementType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="GameElementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnimationEffect" type="{http://www.example.org/Game/}AnimationEffectType" minOccurs="0"/>
 *         &lt;element name="SoundEffect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GameElementType", propOrder = {
    "animationEffect",
    "soundEffect"
})
@XmlSeeAlso({
    Prop.class,
    Character.class
})
public class GameElementType {

    @XmlElement(name = "AnimationEffect")
    private AnimationEffectType animationEffect;
    @XmlElement(name = "SoundEffect")
    private String soundEffect;

    /**
     * Gets the value of the animationEffect property.
     *
     * @return
     *     possible object is
     *     {@link AnimationEffectType }
     *
     */
    public final AnimationEffectType getAnimationEffect() {
        return animationEffect;
    }

    /**
     * Sets the value of the animationEffect property.
     *
     * @param value
     *     allowed object is
     *     {@link AnimationEffectType }
     *
     */
    public final void setAnimationEffect(final AnimationEffectType value) {
        this.animationEffect = value;
    }

    /**
     * Gets the value of the soundEffect property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public final String getSoundEffect() {
        return soundEffect;
    }

    /**
     * Sets the value of the soundEffect property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setSoundEffect(final String value) {
        this.soundEffect = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
//Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
//schema.
// Generated on: 2014.04.25 at 07:45:04 PM CDT
//


package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Sean
 * Class type for Character. Inherits from GameElement
 * for Location and Size elements.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Character", propOrder = {
    "autonomousBehaviour",
    "profile"

})
@XmlSeeAlso({
    Player.class,
    NonPlayer.class
})
public abstract class Character extends GameElementType {

    /**
     * A character's behavior. the Behavior class is not currently
     * implemented.
     */
    @XmlElement(name = "AutonomousBehaviour", required = true)
    private String autonomousBehaviour;
    /**
     * The character's profile.
     */
    @XmlElement(name = "Profile", required = true)
    private Profile profile;
    /**
     * The rewards for the character. The Rewards class is not
     * currently implemented.
     */
    @XmlElement(name = "Rewards", required = true)
    private String rewards;

    /**
     * Gets the value of the autonomousBehaviour property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public final String getAutonomousBehaviour() {
        return autonomousBehaviour;
    }

    /**
     * Sets the value of the autonomousBehaviour property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setAutonomousBehaviour(final String value) {
        this.autonomousBehaviour = value;
    }

    /**
     * Gets the value of the profile property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public final Profile getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     *
     * @param profileToSet
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setProfile(final Profile profileToSet) {
        this.profile = profileToSet;
    }


}

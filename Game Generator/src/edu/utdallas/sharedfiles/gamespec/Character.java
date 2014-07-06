/*package edu.utdallas.gamegenerator.Character;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

//TODO: eventually use enumerations

@XmlRootElement(name = "Character")
@XmlSeeAlso({PlayerCharacter.class, NonPlayerCharacter.class})
public abstract class Character
{
	private String name;
	private Profile profile;
	private String behavior; //TODO: Eventually use Behavior class
	private int characterID;
	private List<Reward> rewards;

    @XmlElement(name = "Name")
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
    @XmlElement(name = "Behavior")
	public String getBehavior()
	{
		return behavior;
	}
	public void setBehavior(String behavior)
	{
		this.behavior = behavior;
	}
    @XmlElement(name = "Profile")
	public Profile getProfile()
	{
		return profile;
	}
	public void setProfile(Profile profile)
	{
		this.profile = profile;
	}
    @XmlElement(name = "CharacterID")
	public int getCharacterID()
	{
		return characterID;
	}
	public void setCharacterID(int characterID)
	{
		this.characterID = characterID;
	}
	@XmlElementWrapper(name = "Rewards")
    @XmlElement(name = "Reward")
	public List<Reward> getRewards()
	{
		return rewards;
	}
	public void setRewards(List<Reward> rewards)
	{
		this.rewards = rewards;
	}
}
*/

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.04.25 at 07:45:04 PM CDT
//


package edu.utdallas.sharedfiles.gamespec;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import edu.utdallas.gamegenerator.Character.Profile;


/**
 * <p>Java class for Character complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Character">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/Game/}GameElementType">
 *       &lt;sequence>
 *         &lt;element name="AutonomousBehaviour" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Profile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Rewards" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Character", propOrder = {
	"name",
    "autonomousBehaviour",
    "profile",
    "rewards"
})
@XmlSeeAlso({
    Player.class,
    NonPlayer.class
})
public abstract class Character extends GameElementType {
	
	@XmlElement(name = "Name", required = true)
	private String name;
    @XmlElement(name = "AutonomousBehaviour", required = true)
    private String autonomousBehaviour;
    @XmlElement(name = "Profile", required = true)

    private String profile;
    @XmlElement(name = "Rewards", required = true)
    private String rewards;
    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the value of the autonomousBehaviour property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setName(final String value) {
        this.name = value;
    }
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
    public final String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setProfile(final String profile) {
        this.profile = profile;
    }

    /**
     * Gets the value of the rewards property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public final String getRewards() {
        return rewards;
    }

    /**
     * Sets the value of the rewards property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public final void setRewards(final String value) {
        this.rewards = value;
    }

}

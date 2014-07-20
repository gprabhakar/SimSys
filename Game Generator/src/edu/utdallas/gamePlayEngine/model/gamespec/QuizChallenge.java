package edu.utdallas.gamePlayEngine.model.gamespec;

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.04.25 at 07:45:04 PM CDT
//


//package org.example.game;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Quiz complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Quiz">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/Game/}ChallengeType">
 *       &lt;sequence>
 *         &lt;element name="Introduction" type="{http://www.example.org/Game/}Introduction"/>
 *         &lt;element name="Pedagogy" type="{http://www.example.org/Game/}PedagogyType"/>
 *         &lt;element name="Item" type="{http://www.example.org/Game/}Item"/>
 *         &lt;element name="Summary" type="{http://www.example.org/Game/}Summary"/>
 *         &lt;element name="Layout" type="{http://www.example.org/Game/}Layout"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Quiz", propOrder = {
    "introduction",
    "pedagogy",
    "item",
    "summary",
    "layout"
})
public class QuizChallenge
    extends Challenge {

    @XmlElement(name = "Introduction", required = true)
    private Introduction introduction;
    @XmlElement(name = "Pedagogy", required = true)
    private PedagogyType pedagogy;
    @XmlElement(name = "Item", required = true)
    private Item item;
    @XmlElement(name = "Summary", required = true)
    private Summary summary;
    @XmlElement(name = "Layout", required = true)
    private Layout layout;

    /**
     * Gets the value of the introduction property.
     *
     * @return
     *     possible object is
     *     {@link Introduction }
     *
     */
    public final Introduction getIntroduction() {
        return introduction;
    }

    /**
     * Sets the value of the introduction property.
     *
     * @param value
     *     allowed object is
     *     {@link Introduction }
     *
     */
    public final void setIntroduction(final Introduction value) {
        this.introduction = value;
    }

    /**
     * Gets the value of the pedagogy property.
     *
     * @return
     *     possible object is
     *     {@link PedagogyType }
     *
     */
    public final PedagogyType getPedagogy() {
        return pedagogy;
    }

    /**
     * Sets the value of the pedagogy property.
     *
     * @param value
     *     allowed object is
     *     {@link PedagogyType }
     *
     */
    public final void setPedagogy(final PedagogyType value) {
        this.pedagogy = value;
    }

    /**
     * Gets the value of the item property.
     *
     * @return
     *     possible object is
     *     {@link Item }
     *
     */
    public final Item getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     *
     * @param value
     *     allowed object is
     *     {@link Item }
     *
     */
    public final void setItem(final Item value) {
        this.item = value;
    }

    /**
     * Gets the value of the summary property.
     *
     * @return
     *     possible object is
     *     {@link Summary }
     *
     */
    public final Summary getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     *
     * @param value
     *     allowed object is
     *     {@link Summary }
     *
     */
    public final void setSummary(final Summary value) {
        this.summary = value;
    }

    /**
     * Gets the value of the layout property.
     *
     * @return
     *     possible object is
     *     {@link Layout }
     *
     */
    public final Layout getLayout() {
        return layout;
    }

    /**
     * Sets the value of the layout property.
     *
     * @param value
     *     allowed object is
     *     {@link Layout }
     *
     */
    public final void setLayout(final Layout value) {
        this.layout = value;
    }

}

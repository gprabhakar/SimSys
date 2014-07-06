package edu.utdallas.sharedfiles.gamespec;

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
 * <p>Java class for StemType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="StemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StemText" type="{http://www.example.org/Game/}StemTextType"/>
 *         &lt;element name="StemQuestion" type="{http://www.example.org/Game/}StemQuestionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StemType", propOrder = {
    "stemText",
    "stemQuestion"
})
public class Stem {

    @XmlElement(name = "StemText", required = true)
    private StemText stemText;
    @XmlElement(name = "StemQuestion", required = true)
    private StemQuestion stemQuestion;

    /**
     * Gets the value of the stemText property.
     *
     * @return
     *     possible object is
     *     {@link StemTextType }
     *
     */
    public final StemText getStemText() {
        return stemText;
    }

    /**
     * Sets the value of the stemText property.
     *
     * @param value
     *     allowed object is
     *     {@link StemTextType }
     *
     */
    public final void setStemText(final StemText value) {
        this.stemText = value;
    }

    /**
     * Gets the value of the stemQuestion property.
     *
     * @return
     *     possible object is
     *     {@link StemQuestionType }
     *
     */
    public final StemQuestion getStemQuestion() {
        return stemQuestion;
    }

    /**
     * Sets the value of the stemQuestion property.
     *
     * @param value
     *     allowed object is
     *     {@link StemQuestionType }
     *
     */
    public final void setStemQuestion(final StemQuestion value) {
        this.stemQuestion = value;
    }

}

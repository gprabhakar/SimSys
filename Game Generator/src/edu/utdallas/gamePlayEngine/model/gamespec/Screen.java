package edu.utdallas.gamePlayEngine.model.gamespec;
//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
//Any modifications to this file will be lost upon recompilation of the source schema.
//Generated on: 2014.04.25 at 07:45:04 PM CDT
//


//package org.example.game;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
* <p>Java class for ScreenType complex type.
*
* <p>The following schema fragment specifies the expected content contained within this class.
*
* <pre>
* &lt;complexType name="ScreenType">
*   &lt;complexContent>
*     &lt;extension base="{http://www.example.org/Game/}GameUnitType">
*       &lt;sequence>
*         &lt;element name="LearningObjective" type="{http://www.example.org/Game/}LearningObjectiveType"/>
*         &lt;element name="Challenge" type="{http://www.example.org/Game/}ChallengeType" maxOccurs="unbounded" minOccurs="0"/>
*         &lt;element name="GameElement" type="{http://www.example.org/Game/}GameElementType" maxOccurs="unbounded" minOccurs="0"/>
*       &lt;/sequence>
*     &lt;/extension>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
*
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScreenType", propOrder = {
 "learningObjective",
 "challenge",
 "gameElement"
})
public class Screen
     extends GameUnitType {

 @XmlElement(name = "LearningObjective", required = true)
 private LearningObjectiveType learningObjective;
 @XmlElement(name = "Challenge")
 private List<Challenge> challenge;
 @XmlElement(name = "GameElement")
 private List<GameElementType> gameElement;

 /**
  * Gets the value of the learningObjective property.
  *
  * @return
  *     possible object is
  *     {@link LearningObjectiveType }
  *
  */
 public final LearningObjectiveType getLearningObjective() {
     return learningObjective;
 }

 /**
  * Sets the value of the learningObjective property.
  *
  * @param value
  *     allowed object is
  *     {@link LearningObjectiveType }
  *
  */
 public final void setLearningObjective(final LearningObjectiveType value) {
     this.learningObjective = value;
 }

 /**
  * Gets the value of the challenge property.
  *
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the
  * challenge property.
  *
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getChallenge().add(newItem);
  * </pre>
  *
  *
  * <p>
  * @return
  *      possible object is
  *      {@link ChallengeType }
  *
  *
  */
 public final List<Challenge> getChallenge() {
     if (challenge == null) {
         challenge = new ArrayList<Challenge>();
     }
     return this.challenge;
 }

 /**
  * Gets the value of the gameElement property.
  *
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the
  * gameElement property.
  *
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getGameElement().add(newItem);
  * </pre>
  *
  *
  * <p>
  * @return
  *      possible object is
  *      {@link GameElementType }
  *
  *
  */
 public final List<GameElementType> getGameElement() {
     if (gameElement == null) {
         gameElement = new ArrayList<GameElementType>();
     }
     return this.gameElement;
 }

}
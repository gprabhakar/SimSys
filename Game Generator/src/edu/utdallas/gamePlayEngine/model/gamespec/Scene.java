
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
* <p>Java class for SceneType complex type.
*
* <p>The following schema fragment specifies the expected content contained within this class.
*
* <pre>
* &lt;complexType name="SceneType">
*   &lt;complexContent>
*     &lt;extension base="{http://www.example.org/Game/}GameUnitType">
*       &lt;sequence>
*         &lt;element name="LearningObjective" type="{http://www.example.org/Game/}LearningObjectiveType"/>
*         &lt;element name="Screen" type="{http://www.example.org/Game/}ScreenType" maxOccurs="unbounded"/>
*         &lt;element name="background" type="{http://www.example.org/Game/}BackgroundType"/>
*         &lt;element name="Music" type="{http://www.example.org/Game/}MusicType" minOccurs="0"/>
*       &lt;/sequence>
*     &lt;/extension>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
*
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SceneType", propOrder = {
 "learningObjective",
 "screen",
 "background",
 "music"
})
public class Scene
   extends GameUnitType {

 @XmlElement(name = "LearningObjective", required = true)
 private LearningObjectiveType learningObjective;
 @XmlElement(name = "Screen", required = true)
 private List<Screen> screen;
 @XmlElement(name = "Background", required = true)
 private BackgroundType background;
 @XmlElement(name = "Music")
 private MusicType music;

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
  * Gets the value of the screen property.
  *
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the screen property.
  *
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getScreen().add(newItem);
  * </pre>
  *
  *
  * <p>
  * @return
  *      possible object is
  *      {@link ScreenType }
  *
  *
  */
 public final List<Screen> getScreen() {
     if (screen == null) {
         screen = new ArrayList<Screen>();
     }
     return this.screen;
 }
 /**
  * Sets the value of the music property.
  *
  * @param value
  *     allowed object is
  *     {@link ScreenType }
  *
  */
 public final void setScreen(final List<Screen> value) {
     this.screen = value;
 }
 /**
  * Gets the value of the background property.
  *
  * @return
  *     possible object is
  *     {@link Background }
  *
  */
 public final BackgroundType getBackground() {
     return background;
 }

 /**
  * Sets the value of the background property.
  *
  * @param value
  *     allowed object is
  *     {@link Background }
  *
  */
 public final void setBackground(final BackgroundType value) {
     this.background = value;
 }

 /**
  * Gets the value of the music property.
  *
  * @return
  *     possible object is
  *     {@link MusicType }
  *
  */
 public final MusicType getMusic() {
     return music;
 }

 /**
  * Sets the value of the music property.
  *
  * @param value
  *     allowed object is
  *     {@link MusicType }
  *
  */
 public final void setMusic(final MusicType value) {
     this.music = value;
 }
}
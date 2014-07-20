
//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB)
//Reference Implementation, v2.2.4-2
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
//Any modifications to this file will be lost upon recompilation of the source
//schema.
//Generated on: 2014.04.25 at 07:45:04 PM CDT
//

package edu.utdallas.gamePlayEngine.model.gamespec;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
*<p>Java class for anonymous complex type.*
*<p>The following schema fragment specifies the expected content contained
*within this class.
* <pre>
* &lt;complexType>
* &lt;complexContent>
* &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
* &lt;sequence>
* &lt;element name="Act" type="{http://www.example.org/Game/}ActType" maxOccurs="unbounded"/>
* &lt;element name="LearningObjective" type="{http://www.example.org/Game/}LearningObjectiveType" maxOccurs="unbounded"/>
* &lt;element name="Character" type="{http://www.example.org/Game/}Character" maxOccurs="unbounded"/>
* &lt;/sequence>
* &lt;/restriction>
* &lt;/complexContent>
* &lt;/complexType>
* </pre>
*
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
 "act",
 "learningObjective",
 "character"
})
@XmlRootElement(name = "Game")
public class GameModel extends Observable{

 @XmlElement(name = "Act", required = true)
 private List<Act> act;
@XmlElement(name = "LearningObjective", required = true)
 private List<LearningObjectiveType> learningObjective;
 @XmlElement(name = "Character", required = true)
 private List<Character> character;

 /**
  * Gets the value of the act property.
  *
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the act property.
  *
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getAct().add(newItem);
  * </pre>
  *
  *
  * <p>
  * @return 
  *      possible object is
  *      {@link ActType }
  *
  *
  */
 public final List<Act> getAct() {
     if (act == null) {
         act = new ArrayList<Act>();
     }
     return this.act;
 }
 
//Sets a list of Act for the game
public void setAct(List<Act> act) {
	this.act = act;
}
 /**
  * Gets the value of the learningObjective property.
  *
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the
  * learningObjective property.
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getLearningObjective().add(newItem);
  * </pre>
  *
  *
  * <p>
  * @return
  *      possible object is
  *      {@link LearningObjectiveType }
  *
  *
  */
 public final List<LearningObjectiveType> getLearningObjective() {
     if (learningObjective == null) {
         learningObjective = new ArrayList<LearningObjectiveType>();
     }
     return this.learningObjective;
 }

 /**
  * Gets the value of the character property.
  *
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the
  * character property.
  *
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getCharacter().add(newItem);
  * </pre>
  *
  *
  * <p>
  * @return
  *      possible object is
  *      {@link Character }
  *
  *
  */
 public final List<Character> getCharacter() {
     if (character == null) {
         character = new ArrayList<Character>();
     }
     return this.character;
 }
	public static void main(String[] args) {
		try {
			// Load Game from XML
			JAXBContext context = JAXBContext.newInstance(GameModel.class);
			System.out.println("instance passed ");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			System.out.println("marshaller created");
			GameModel gm= (GameModel) unmarshaller.unmarshal(new File("C:\\Users\\priya_sree\\Desktop\\xmls\\NewTestGame1.xml"));
			System.out.println("Test cout ---------->"+gm.getLearningObjective().get(0).getLearningObjective());
		} catch (Exception ex) {
			// log the exception, show the error message on UI
			System.out.println(ex.getMessage());
			
			//throw ex;
		}
	}
}


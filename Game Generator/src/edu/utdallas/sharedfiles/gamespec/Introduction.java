package edu.utdallas.sharedfiles.gamespec;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
* <p>Java class for Introduction complex type.
*
* <p>The following schema fragment specifies the expected content contained within this class.
*
* <pre>
* &lt;complexType name="Introduction">
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;element name="IntroductionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
*
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Introduction", propOrder = {
 "introductionName"
})
public class Introduction {

 @XmlElement(name = "IntroductionName", required = true)
 private String introductionName;

 /**
  * Gets the value of the introductionName property.
  *
  * @return
  *     possible object is
  *     {@link String }
  *
  */
 public final String getIntroductionName() {
     return introductionName;
 }

 /**
  * Sets the value of the introductionName property.
  *
  * @param value
  *     allowed object is
  *     {@link String }
  *
  */
 public final void setIntroductionName(final String value) {
     this.introductionName = value;
 }

}

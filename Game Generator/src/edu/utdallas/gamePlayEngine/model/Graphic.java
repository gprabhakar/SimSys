/**
 * Graphic base class extending from GameElement and is derived by Backdrop and Prop
 */
package edu.utdallas.gamePlayEngine.model;

import edu.utdallas.gamePlayEngine.model.GameElement;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlSeeAlso({ Backdrop.class, Identifier.class, Prop.class })


@XmlTransient
public class Graphic extends GameElement {
	
}

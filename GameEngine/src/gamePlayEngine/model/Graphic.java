/**
 * Graphic base class extending from GameElement and is derived by Backdrop and Prop
 */
package gamePlayEngine.model;

import gamePlayEngine.model.GameElement;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlSeeAlso({ Backdrop.class, Identifier.class, Prop.class })


@XmlTransient
public class Graphic extends GameElement {
	
}

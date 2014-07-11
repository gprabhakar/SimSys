package edu.utdallas.gamespecification;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NonPlayer complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="NonPlayer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.example.org/Game/}Character">
 *       &lt;sequence>
 *         &lt;element name="NonPlayerID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonPlayer", propOrder = {
    "nonPlayerID"
})
public class NonPlayer
    extends Character
{

    @XmlElement(name = "NonPlayerID")
    private List<String> nonPlayerID;

    /**
     * Gets the value of the nonPlayerID property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nonPlayerID property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNonPlayerID().add(newItem);
     * </pre>
     *
     *
     * <p>
     * @return
     *    possible object is
     *    {@link String }
     *
     *
     */
    public final List<String> getNonPlayerID() {
        if (nonPlayerID == null) {
            nonPlayerID = new ArrayList<String>();
        }
        return this.nonPlayerID;
    }

}

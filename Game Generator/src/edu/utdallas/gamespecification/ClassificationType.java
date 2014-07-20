//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
//Reference Implementation, v2.2.4-2
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the
//source schema.
// Generated on: 2014.04.25 at 07:45:04 PM CDT
//


package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Sean
 * Class file for the enumerated type ClassificationType.
 * Please set values in the style of ClassificationType.INTERACTIVE
 * (versus localVariableName.INTERACTIVE).
 */
@XmlType(name = "ClassificationType")
@XmlEnum
public enum ClassificationType {

    /**
     * Interactive type. Typical for Quiz-style challenges.
     */
    @XmlEnumValue("Interactive")
    INTERACTIVE("Interactive"),
    /**
     * Deliberation type.
     */
    @XmlEnumValue("Deliberation")
    DELIBERATION("Deliberation"),
    /**
     * Composition type.
     */
    @XmlEnumValue("Composition")
    COMPOSITION("Composition");
    /**
     * Holds the value itself.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    ClassificationType(final String v) {
        value = v;
    }

    /**
     * @return
     * {@link String}
     */
    public String value() {
        return value;
    }

    /**
     * @param v
     * {@link String}
     * @return
     * {@link ClassificationType}
     */
    public static ClassificationType fromValue(final String v) {
        for (ClassificationType c: ClassificationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

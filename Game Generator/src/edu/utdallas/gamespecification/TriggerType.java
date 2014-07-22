package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lists the types of triggers associated
 * with behaviors.
 * @author Sean
 *
 */
@XmlType(name = "TriggerType")
@XmlEnum
public enum TriggerType {

    /**
     * For actions that are triggered with a trick.
     */
    @XmlEnumValue("click")
    CLICKTRIGGER("click"),
    /**
     * For actions that should execute when a timer
     * reaches a particular value.
     */
    @XmlEnumValue("timer")
    TIMERTRIGGER("timer");

    /**
     * Holds the value of the enumerated type.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    TriggerType(final String v) {
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
     * Returns the trigger type.
     * Will throw an illegal argument exception if
     * a type not listed in this file is passed.
     * @param v
     * {@link String}
     * @return
     * {@link TriggerType}
     */
    public static TriggerType fromValue(final String v) {
        for (TriggerType c: TriggerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

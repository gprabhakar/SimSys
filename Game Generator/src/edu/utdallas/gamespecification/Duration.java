package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Stores the enumerated types for
 * transition and cut durations.
 * @author Sean
 *
 */
public enum Duration {

    /**
     * Fade in transition.
     */
    @XmlEnumValue("slow")
    SLOW("slow"),
    /**
     * Fade out transition.
     */
    @XmlEnumValue("medium")
    MEDIUM("medium"),
    /**
     * Dissolve transition.
     */
    @XmlEnumValue("fast")
    FAST("fast");
    /**
     * Holds the value of the duration type.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    Duration(final String v) {
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
     * Returns the duration type.
     * Will throw an illegal argument exception if
     * a type not listed in this file is passed.
     * @param v
     * {@link String}
     * @return
     * {@link Duration}
     */
    public static Duration fromValue(final String v) {
        for (Duration c: Duration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

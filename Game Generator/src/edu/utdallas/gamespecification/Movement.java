package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lists the various types of animation movements
 * Movements are marked with MOVEMENT at the end of
 * name.
 * @author Sean
 *
 */
@XmlType(name = "Movement")
@XmlEnum
public enum Movement {

    /**
     * Walk movement.
     */
    @XmlEnumValue("walkMovement")
    WALKMOVEMENT("walkMovement"),
    /**
     * Glide movement.
     */
    @XmlEnumValue("glideMovement")
    GLIDEMOVEMENT("glideMovement"),
    /**
     * Sit down movement.
     */
    @XmlEnumValue("sitDownMovement")
    SITDOWNMOVEMENT("sitDownMovement"),
    /**
     * Stand up movement.
     */
    @XmlEnumValue("standUpMovement")
    STANDUPMOVEMENT("standUpMovement"),
    /**
     * Talk movement.
     */
    @XmlEnumValue("talkMovement")
    TALKMOVEMENT("talkMovement"),
    /**
     * Hand wave movement.
     */
    @XmlEnumValue("handWaveMovement")
    HANDWAVEMOVEMENT("handWaveMovement"),
    /**
     * Handshake movement.
     */
    @XmlEnumValue("handShakeMovement")
    HANDSHAKEMOVEMENT("handShakeMovement"),
    /**
     * Dance movement.
     */
    @XmlEnumValue("danceMovement")
    DANCEMOVEMENT("danceMovement");

    /**
     * Holds the value of the movement type.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    Movement(final String v) {
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
     * Returns the movement type.
     * Will throw an illegal argument exception if
     * a type not listed in this file is passed.
     * @param v
     * {@link String}
     * @return
     * {@link TransitionType}
     */
    public static Movement fromValue(final String v) {
        for (Movement c: Movement.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

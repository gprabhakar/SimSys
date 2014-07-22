package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lists the various types of Animation Effects.
 * These are divided into movements and effects.
 * All movement names have "MOVEMENT as the suffix.
 * All effect names have "EFFECT" as the suffix.
 * @author Sean
 *
 */
@XmlType(name = "Effect")
@XmlEnum
public enum Effect {

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
    DANCEMOVEMENT("danceMovement"),
    /**
     * Fade in effect.
     */
    @XmlEnumValue("fadeInEffect")
    FADEINEFFECT("fadeInEffect"),
    /**
     * Fade out effect.
     */
    @XmlEnumValue("fadeOutEffect")
    FADEOUTEFFECT("fadeOutEffect"),
    /**
     * Twinkle Effect.
     */
    @XmlEnumValue("twinkleEffect")
    TWINKLEEFFECT("twinkleEffect"),
    /**
     * Shimmer effect.
     */
    @XmlEnumValue("shimmerEffect")
    SHIMMEREFFECT("shimmerEffect");

    /**
     * Holds the value of the effect type.
     */

    private final String value;

    /**
     * @param v
     * {@link String}
     */
    Effect(final String v) {
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
     * Returns the effect type.
     * Will throw an illegal argument exception if
     * a type not listed in this file is passed.
     * @param v
     * {@link String}
     * @return
     * {@link Effect}
     */
    public static Effect fromValue(final String v) {
        for (Effect c: Effect.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

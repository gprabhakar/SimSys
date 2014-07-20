package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lists the various types of transitions
 * and cuts between Acts, Scenes, and Screens.
 * @author Sean
 *
 */
@XmlType(name = "TransitionType")
@XmlEnum
public enum TransitionType {

    /**
     * Fade in transition.
     */
    @XmlEnumValue("fadeIn")
    FADEIN("fadeIn"),
    /**
     * Fade out transition.
     */
    @XmlEnumValue("fadeOut")
    FADEOUT("fadeOut"),
    /**
     * Dissolve transition.
     */
    @XmlEnumValue("dissolve")
    DISSOLVE("dissolve"),
    /**
     * Wipe transition.
     */
    @XmlEnumValue("wipe")
    WIPE("wipe"),
    /**
     * Morph transition.
     */
    @XmlEnumValue("morph")
    MORPH("morph"),
    /**
     * Straight cut.
     */
    @XmlEnumValue("straightCut")
    STRAIGHTCUT("straightCut"),
    /**
     * Contrast cut.
     */
    @XmlEnumValue("contrastCut")
    CONTRASTCUT("contrastCut"),
    /**
     * L Cut.
     */
    @XmlEnumValue("lCut")
    LCUT("lCut"),
    /**
     * Form cut.
     */
    @XmlEnumValue("formCut")
    FORMCUT("formCut"),
    /**
     * Match cut.
     */
    @XmlEnumValue("matchCut")
    MATCHCUT("matchCut"),
    /**
     * Parallel Editing Cut.
     */
    @XmlEnumValue("parallelEditingCut")
    PARALLELEDITINGCUT("parallelEditingCut"),
    /**
     * Jump cut.
     */
    @XmlEnumValue("jumpCut")
    JUMPCUT("jumpCut");
    /**
     * Holds the value of the transition type.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    TransitionType(final String v) {
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
     * Returns the transition type.
     * Will throw an illegal argument exception if
     * a type not listed in this file is passed.
     * @param v
     * {@link String}
     * @return
     * {@link TransitionType}
     */
    public static TransitionType fromValue(final String v) {
        for (TransitionType c: TransitionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

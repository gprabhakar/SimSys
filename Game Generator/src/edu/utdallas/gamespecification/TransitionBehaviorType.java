package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lists the types of transition behaviors that can be associated with
 * a behavior.
 * @author Sean
 *
 */
@XmlType(name = "TransitionBehaviorType")
@XmlEnum
public enum TransitionBehaviorType {

    /**
     * Indicates that the behavior should return the player
     * to the start of the lesson.
     */
    @XmlEnumValue("beginningOfLesson")
    BEGINNING_OF_LESSON("beginningOfLesson"),
    /**
     * Indicates that the behavior should return the player
     * to the start of the challenge.
     */
    @XmlEnumValue("beginningOfChallenge")
    BEGINNING_OF_CHALLENGE("beginningOfChallenge"),
    /**
     * Indicates that the behavior should shift the
     * player to the current challenge.
     */
    @XmlEnumValue("currentChallenge")
    CURRENT_CHALLENGE("currentChallenge"),
    /**
     * Indicates that the behavior should shift the player
     * to the start of the next challenge.
     */
    @XmlEnumValue("nextChallenge")
    NEXT_CHALLENGE("nextChallenge"),
    /**
     * Indicates that the behavior should shift the player
     * to the end of the story.
     */
    @XmlEnumValue("endOfStory")
    END_OF_STORY("endOfStory"),
    /**
     * Indicates that the behavior should trigger an additional
     * screen.
     */
    @XmlEnumValue("additional")
    ADDITIONAL("additional"),
    /**
     * Indicates that the behavior should shift the player to the
     * next screen.
     */
    @XmlEnumValue("nextScreen")
    NEXT_SCREEN("nextScreen"),
    /**
     * Indicates that the behavior should shift the player to the
     * next act.
     */
    @XmlEnumValue("nextAct")
    NEXT_ACT("nextAct");

    /**
     * Holds the value of the enumerated type.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    TransitionBehaviorType(final String v) {
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
     * Returns the transition behavior type.
     * Will throw an illegal argument exception if
     * a type not listed in this file is passed.
     * @param v
     * {@link String}
     * @return
     * {@link TransitionBehaviorType}
     */
    public static TransitionBehaviorType fromValue(final String v) {
        for (TransitionBehaviorType c: TransitionBehaviorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}



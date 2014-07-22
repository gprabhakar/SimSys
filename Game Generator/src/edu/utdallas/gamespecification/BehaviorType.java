package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lists the types of behaviors that can be associated with
 * a GameElement.
 * @author Sean
 *
 */
@XmlType(name = "BehaviorType")
@XmlEnum
public enum BehaviorType {

    /**
     * Reward behaviors should take the Reward value(s)
     * associated with the game element the behavior is attached
     * to, and add those reward elements to the character's rewards.
     */
    @XmlEnumValue("rewardBehavior")
    REWARD_BEHAVIOR("rewardBehavior"),
    /**
     * Transition behaviors should cause some sort of shift
     * from Act to Act, Scene to Scene, Screen to Screen, or
     * Challenge to Challenge.
     */
    @XmlEnumValue("transitionBehavior")
    TRANSITION_BEHAVIOR("transitionBehavior"),
    /**
     * Visibility behaviors should change the visibility of the game element
     * the behavior associated with.
     */
    @XmlEnumValue("visibilityBehavior")
    VISIBILITY_BEHAVIOR("visibilityBehavior"),
    /**
     * End game behaviors should end the current game.
     */
    @XmlEnumValue("endGameBehavior")
    END_GAME_BEHAVIOR("endGameBehavior");

    /**
     * Holds the value of the enumerated type.
     */
    private final String value;

    /**
     * @param v
     * {@link String}
     */
    BehaviorType(final String v) {
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
     * {@link BehaviorType}
     */
    public static BehaviorType fromValue(final String v) {
        for (BehaviorType c: BehaviorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

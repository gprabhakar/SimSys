package edu.utdallas.gamespecification;

import edu.utdallas.gamegeneratorcollection.ComponentCreation.EndGameBehavior;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Contains information related to behaviors. Behaviors specify
 * possible actions a gameElement might take, and the conditions for
 * those elements happening.
 * @author Sean
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Behavior", propOrder = {
    "behaviorType",
    "trigger",
    "timer",
    "transition"
})
@XmlSeeAlso({TransitionBehaviorType.class, EndGameBehavior.class })
public class Behavior {
    /**
     * An enumerated type to describe certain behaviors.
     */
    @XmlElement(name = "BehaviorType", required = true)
    private BehaviorType behaviorType;
    /**
     * An enumerated type to describe certain triggers.
     */
    @XmlElement(name = "TriggerType", required = true)
    private TriggerType trigger;
    /**
     * Holds the maximum value of a timer.
     */
    @XmlElement(name = "Timer")
    private int timer;
    /**
     * TransitionType details how a Transition-type behavior
     * should execute. This is also an enumerated type.
     */
    @XmlElement(name = "TransitionBehaviorType")
    private TransitionBehaviorType transition;

    /**
     * Default constructor.
     */
    public Behavior() {
    }

    /**
     * Takes the values of a new behavior from an already
     * existing behavior.
     * @param behavior
     * {@link Behavior}
     */
    public Behavior(final Behavior behavior) {
        this.behaviorType = behavior.getBehaviorType();
        this.trigger = TriggerType.CLICKTRIGGER;
        this.timer = behavior.getTimer();
        this.transition = behavior.getTransition();

    }

    /**
     * Creates a behavior with the specified BehaviorType.
     * @param value
     * {@link BehaviorType}
     */
    public Behavior(final BehaviorType value) {
        this.behaviorType = value;
    }

    /**
     * Returns the type of the behavior.
     * @return
     * {@link BehaviorType}
     */
    public final BehaviorType getBehaviorType() {
        return behaviorType;
    }

    /**
     * Sets the type of behavior.
     * @param value
     * {@link BehaviorType}
     */
    public final void setBehaviorType(final BehaviorType value) {
        this.behaviorType = value;
    }

    /**
     * Returns the way a behavior is triggered.
     * @return
     * {@list TriggerType}
     */
    public final TriggerType getTrigger() {
        return trigger;
    }

    /**
     * Sets the way a behavior is triggered.
     * @param value
     * {@list TriggerType}
     */
    public final void setTrigger(final TriggerType value) {
        this.trigger = value;
    }

    /**
     * Returns the value of the timer. A non-zero number indicates
     * the maximum value of the timer.
     * @return timer
     * {@link int}
     */
    public final int getTimer() {
        return timer;
    }

    /**
     * Sets the value of the timer.
     * @param value
     * {@link int}
     */
    public final void setTimer(final int value) {
        this.timer = value;
    }

    /**
     * Returns the transition type.
     * @return
     * {@link TransitionBehaviorType}
     */
    public final TransitionBehaviorType getTransition() {
        return transition;
    }

    /**
     * Sets the transition type.
     * @param value
     * {@link TransitionBehaviorType}
     */
    public final void setTransition(final TransitionBehaviorType value) {
        this.transition = value;
    }

}

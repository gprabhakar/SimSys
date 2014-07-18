package edu.utdallas.gamespecification;

/**
 *
 * @author Sean
 * Defines the transition between Act, Scene, and
 * Screen objects in terms of transition type and
 * duration.
 */
public class Transition {

    /**
     * Enumerated type detailing the kind of
     * transition.
     */
    private TransitionType type;
    /**
     * Enumerated type detailing the duration of
     * transition.
     */
    private Duration duration;

    /**
     * Default contructor. Creates a transition of type
     * STRAIGHTCUT, duration MEDIUM.
     */
    public Transition() {
        this.type = TransitionType.STRAIGHTCUT;
        this.duration = Duration.MEDIUM;
    }
    /**
     * @param transType
     * {@link TransitionType}
     * @param durationValue
     * {@link Duration}
     */
    public Transition(final TransitionType transType,
            final Duration durationValue) {
        this.type = transType;
        this.duration = durationValue;
    }
    /**
     * @return
     * {@link TransitionType}
     */
    public final TransitionType getType() {
        return type;
    }
    /**
     * @param transType
     * {@link TransitionType}
     */
    public final void setType(final TransitionType transType) {
        this.type = transType;
    }
    /**
     * @return
     * {@link Duration}
     */
    public final Duration getDuration() {
        return duration;
    }
    /**
     * @param transDuration
     * {@link Duration}
     */
    public final void setDuration(final Duration transDuration) {
        this.duration = transDuration;
    }


}

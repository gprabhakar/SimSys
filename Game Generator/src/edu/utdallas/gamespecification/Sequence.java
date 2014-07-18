package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Sean
 * Class to provide information to aid in the transition
 * between acts, scenes, and screens.
 * sequenceNumber should be set when composing a game.
 * nextSequence is intended to be set at runtime (when a button
 * is clicked for example), allowing the transition of the screen
 * to the defined sequence number.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sequence", propOrder = {
 "sequenceNumber",
 "nextSequence"
})
public class Sequence {
    /**
     * Holds the sequence number. This will probably just
     * be added from the index in whatever list an act, scene,
     * or screen exists in.
     */
    @XmlElement(name = "SequenceNumber")
    private int sequenceNumber;
    /**
     * Holds the next sequence number.
     */
    @XmlElement(name = "NextSequence")
    private int nextSequence;

    /**
     * Default constructor.
     */

    public Sequence() {

    }

    /**
     * Creates a Sequence object with the sequence number set.
     * nextSequence is not created, since that should be set by
     * button presses or other conditions at runtime.
     * @param value
     * {@link int}
     */
    public Sequence(final int value) {
        super();
        this.sequenceNumber = value;
    }

    /**
     *
     * @return
     * {@link int}
     */
    public final int getNextSequence() {
        return nextSequence;
    }

    /**
     * @param nextVal
     * {@link int}
     */
    public final void setNextSequence(final int nextVal) {
        this.nextSequence = nextVal;
    }

    /**
     * @return
     * {@link int}
     */
    public final int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @param value
     * {@link int}
     */
    public final void setSequenceNumber(final int value) {
        this.sequenceNumber = value;
    }


}

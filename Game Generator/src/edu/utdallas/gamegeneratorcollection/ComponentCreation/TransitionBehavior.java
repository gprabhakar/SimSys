package edu.utdallas.gamegeneratorcollection.ComponentCreation;

import javax.xml.bind.annotation.XmlType;

import edu.utdallas.gamespecification.Behavior;

/**
 * User: clocke
 * Date: 4/13/13
 * Time: 4:37 PM
 */
@XmlType(name = "TransitionBehavior")
public class TransitionBehavior extends Behavior {
    public TransitionBehavior() {
    }

    public TransitionBehavior(Behavior behavior) {
        super(behavior);
        setTransition(null);
    }
}

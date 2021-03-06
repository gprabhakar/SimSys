package edu.utdallas.gamegeneratorcollection.ComponentCreation;

import javax.xml.bind.annotation.XmlType;

import edu.utdallas.gamespecification.Behavior;

/**
 * User: clocke
 * Date: 4/13/13
 * Time: 4:41 PM
 */
@XmlType(name = "EndGameBehavior")
public class EndGameBehavior extends Behavior {
    public EndGameBehavior() {
    }

    public EndGameBehavior(Behavior behavior) {
        super(behavior);
        behavior.setDisplayName("End Game Behavior");
    }
}

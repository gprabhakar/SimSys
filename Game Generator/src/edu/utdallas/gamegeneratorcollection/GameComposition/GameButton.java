package edu.utdallas.gamegeneratorcollection.GameComposition;

import edu.utdallas.gamespecification.Reward;
import edu.utdallas.gamespecification.TransitionBehaviorType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.ButtonLocationType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:55 PM
 */
@XmlRootElement(name = "Button")
public class GameButton {
    private ButtonLocationType buttonLocationType;
    private String text;
    private int timer;
    private TransitionBehaviorType type;
    private Reward reward;

    public ButtonLocationType getButtonLocationType() {
        return buttonLocationType;
    }

    @XmlElement(name = "ButtonLocationType")
    public void setButtonLocationType(ButtonLocationType buttonLocationType) {
        this.buttonLocationType = buttonLocationType;
    }

    public String getText() {
        return text;
    }

    @XmlElement(name = "Text")
    public void setText(String text) {
        this.text = text;
    }

    public int getTimer() {
        return timer;
    }

    @XmlElement(name = "Timer")
    public void setTimer(int timer) {
        this.timer = timer;
    }

    public TransitionBehaviorType getTransitionType() {
        return type;
    }

    @XmlElement(name = "TransitionType")
    public void setTransitionType(TransitionBehaviorType type) {
        this.type = type;
    }

    public Reward getReward() {
        return reward;
    }

    @XmlElement(name = "Reward")
    public void setReward(Reward reward) {
        this.reward = reward;
    }
}

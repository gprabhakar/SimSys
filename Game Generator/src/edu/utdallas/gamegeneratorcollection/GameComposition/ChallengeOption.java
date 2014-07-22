package edu.utdallas.gamegeneratorcollection.GameComposition;

import edu.utdallas.gamespecification.Reward;
import edu.utdallas.gamespecification.TransitionBehaviorType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.ButtonLocationType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.List;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:13 PM
 */
public class ChallengeOption extends GameButton {
    private ChallengeOptionType challengeOptionType;
    private List<BaseScreen> additionalScreens;

    public ChallengeOption() {
    }

    public ChallengeOption(ChallengeOptionType challengeOptionType, String text, Reward reward,
                           TransitionBehaviorType transitionType, List<BaseScreen> additionalScreens,
                           ButtonLocationType buttonLocationType) {
        this.challengeOptionType = challengeOptionType;
        setText(text);
        setReward(reward);
        setTransitionType(transitionType);
        setButtonLocationType(buttonLocationType);
        this.additionalScreens = additionalScreens;
    }

    public ChallengeOptionType getChallengeOptionType() {
        return challengeOptionType;
    }

    @XmlElement(name = "ChallengeOptionType")
    public void setChallengeOptionType(ChallengeOptionType challengeOptionType) {
        this.challengeOptionType = challengeOptionType;
    }

    public List<BaseScreen> getAdditionalScreens() {
        return additionalScreens;
    }

    @XmlElementWrapper(name = "AdditionalScreens")
    @XmlElement(name = "AdditionalScreen")
    public void setAdditionalScreens(List<BaseScreen> additionalScreens) {
        this.additionalScreens = additionalScreens;
    }
}

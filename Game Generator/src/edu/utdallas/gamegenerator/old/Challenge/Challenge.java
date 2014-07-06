package edu.utdallas.gamegenerator.old.Challenge;

import edu.utdallas.gamegenerator.LearningAct.Screen.ChallengeScreen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: clocke
 * Date: 4/28/13
 * Time: 7:51 PM
 */
@XmlRootElement(name = "Challenge")
public class Challenge {
    private List<ChallengeScreen> lessonChallenges;

    /**
     *
     * @return a list of type ChallengeScreen
     */

    public final List<ChallengeScreen> getLessonChallenges() {
        return lessonChallenges;
    }

    /**
     *
     * @param lessonChallenges, a List of type lessonChallenge
     */
    @XmlElementWrapper(name = "LessonChallenges")
    @XmlElement(name = "LessonChallenge")
    public final void setLessonChallenges(
    		final List<ChallengeScreen> lessonChallenges) {
        this.lessonChallenges = lessonChallenges;
    }
}

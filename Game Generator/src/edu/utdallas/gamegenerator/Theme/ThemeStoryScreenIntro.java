package edu.utdallas.gamegenerator.Theme;

import edu.utdallas.gamegeneratorcollection.GameComposition.BaseScreen;
import edu.utdallas.gamegeneratorcollection.GameComposition.ScreenType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 3/12/13
 * Time: 9:26 PM
 */
@XmlRootElement(name = "StoryIntroScreen")
public class ThemeStoryScreenIntro extends BaseScreen {
    @Override
    public ScreenType getType() {
        return ScreenType.LESSON_STORY_INTRO;
    }
}

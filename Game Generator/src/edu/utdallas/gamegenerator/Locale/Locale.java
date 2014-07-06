package edu.utdallas.gamegenerator.Locale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import edu.utdallas.gamegenerator.Characters.Characters;
import edu.utdallas.gamegenerator.Characters.GameCharacter;
import edu.utdallas.gamegenerator.LearningAct.LearningAct;
import edu.utdallas.gamegenerator.LearningAct.LessonAct;
import edu.utdallas.gamegenerator.LearningAct.Challenge.ChallengeOption;
import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacter;
import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;
import edu.utdallas.gamegenerator.LearningAct.Prop.GameButton;
import edu.utdallas.gamegenerator.LearningAct.Prop.GameText;
import edu.utdallas.gamegenerator.LearningAct.Prop.TextType;
import edu.utdallas.gamegenerator.LearningAct.Screen.BaseScreen;
import edu.utdallas.gamegenerator.LearningAct.Screen.ChallengeScreen;
import edu.utdallas.gamegenerator.LearningAct.Screen.ScreenType;
import edu.utdallas.gamegenerator.LearningAct.Screen.TransitionType;
import edu.utdallas.gamegenerator.Theme.Theme;
import edu.utdallas.gamegenerator.Theme.ThemeStory;
import edu.utdallas.sharedfiles.Shared.Asset;
import edu.utdallas.sharedfiles.Shared.Behavior;
import edu.utdallas.sharedfiles.Shared.BehaviorType;
import edu.utdallas.sharedfiles.Shared.ButtonLocationType;
import edu.utdallas.sharedfiles.Shared.GameObject;
import edu.utdallas.sharedfiles.Shared.SharedButton;
import edu.utdallas.sharedfiles.Shared.SharedCharacter;
import edu.utdallas.sharedfiles.Shared.SharedInformationBox;
import edu.utdallas.sharedfiles.gamespec.Act;
import edu.utdallas.sharedfiles.gamespec.BackgroundType;
import edu.utdallas.sharedfiles.gamespec.Challenge;
import edu.utdallas.sharedfiles.gamespec.Scene;
import edu.utdallas.sharedfiles.gamespec.Screen;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 5:54 PM
 */
@XmlRootElement(name = "Locale")
public class Locale {
    private List<LearningAct> learningActs;
    private Characters characters;
    private Theme theme;
    private Map<ScreenType, LocaleScreen> localeScreens;

    private Map<TransitionType, UUID> screenTransitions = new HashMap<TransitionType, UUID>();;

    public List<LearningAct> getLearningActs() {
        return learningActs;
    }

    @XmlTransient
    public void setLearningActs(List<LearningAct> learningActs) {
        this.learningActs = learningActs;
    }

    public Characters getCharacters() {
        return characters;
    }

    @XmlTransient
    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public Theme getTheme() {
        return theme;
    }

    @XmlTransient
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /**
     * Creates a list of ScreenNode representing the act corresponding to the
     * passed in learning act
     * @param learningActId the index of the learning act
     * @return a list of ScreenNode
     */
    public Act getAct(int learningActId) {
    	Act currentAct = new Act();
        List<Scene> actScenes = new ArrayList<Scene>();
        actScenes.addAll(buildScreens(learningActId, ScreenType.LESSON_STORY_INTRO));
        actScenes.addAll(buildLesson(learningActId));
        actScenes.addAll(buildChallenges(learningActId));
        actScenes.addAll(buildScreens(learningActId, ScreenType.LESSON_STORY_OUTRO));
        return currentAct;
    }

    /**
     * Builds all challenge screens for the learning act
     * @param learningActId the id of the learning act to build
     * @return a list of ScreenNode representing the built challenge screens
     */
    private List<Scene> buildChallenges(int learningActId) {
        UUID currentScreen = screenTransitions.get(TransitionType.BEGINNING_OF_CHALLENGE);
        LessonAct lessonAct = learningActs.get(learningActId).getLessonActs().get(0);
        List<Screen> screenNodes = new ArrayList<Screen>();
        List<ChallengeScreen> challenges = lessonAct.getLessonChallenges();
        List<Scene> sceneNodes = new ArrayList<Scene>();
        for(ChallengeScreen challenge : challenges) {
            UUID nextChallenge = UUID.randomUUID();
            screenTransitions.put(TransitionType.NEXT_CHALLENGE, nextChallenge);
            screenTransitions.put(TransitionType.CURRENT_CHALLENGE, currentScreen);
            sceneNodes.add(buildChallenge(learningActId, challenge, currentScreen));
            currentScreen = nextChallenge;
        }

        return sceneNodes;
    }

    /**
     * Builds a single challenge including any additional screens
     * @param learningActId the id of the learning act
     * @param challenge the ChallengeScreen screen
     * @param currentScreen the UUID of the screen to be built
     * @return a list of ScreenNode containing the challenge
     */
    private Scene buildChallenge(int learningActId, ChallengeScreen challenge, UUID currentScreen) {
        UUID nextScreen = UUID.randomUUID();
        
        List<Screen> screenNodes = new ArrayList<Screen>();
        Scene challengeScene = buildScreen(learningActId, challenge, localeScreens.get(ScreenType.CHALLENGE),
                currentScreen, nextScreen); 
        return challengeScene;
    }

    /**
     * Builds a list of ScreenNode for the passed learningActId and ScreenType
     * @param learningActId the index of the learning act used to build the screens
     * @param screenType the type of screens to build
     * @return a list of ScreenNode
     */
    private List<Scene> buildScreens(int learningActId, ScreenType screenType) {
        List<Screen> lessonScreens = new ArrayList<Screen>();
        List<Scene> allScenes = new ArrayList<Scene>();
        Scene testScene = new Scene();
        Scene lastScene = new Scene();
        lastScene.setBackground(new BackgroundType());
        lastScene.getBackground().setBackground(null);
        testScene = null;
        UUID currentScreen = UUID.randomUUID();
        UUID nextScreen = null;
        ThemeStory themeStory = theme.getThemeStories().get(learningActId);
        List<BaseScreen> themeStoryScreen;
        if(screenType.equals(ScreenType.LESSON_STORY_INTRO)) {
            themeStoryScreen = new ArrayList<BaseScreen>(themeStory.getIntro());
        } else {
            screenTransitions.put(TransitionType.END_OF_STORY, currentScreen);
            themeStoryScreen = new ArrayList<BaseScreen>(themeStory.getOutro());
        }

        for(BaseScreen screen : themeStoryScreen) {
            nextScreen = UUID.randomUUID();
            testScene = buildScreen(learningActId, screen, localeScreens.get(screenType),
                    currentScreen, nextScreen);
            if(testScene.getBackground().getBackground().equals(testScene.getBackground().getBackground())) {
            	testScene.getScreen().addAll(testScene.getScreen()); //Just add the screens in this case.
            	
            }
            else
            {
            	allScenes.add(testScene);
            	
            }
            lastScene = testScene; //Next loop, we test against the last scene we fetched.
        }

        if(screenType.equals(ScreenType.LESSON_STORY_INTRO)) {
            screenTransitions.put(TransitionType.BEGINNING_OF_LESSON, nextScreen);
        }

        return allScenes;
    }

    /**
     * Builds all lesson screens for a learning act
     * @param learningActId the id of the learning act
     * @return a list of ScreenNode containing the built lesson screens
     */
    private List<Scene> buildLesson(int learningActId) {
        List<Screen> lessonScreens = new ArrayList<Screen>();
        List<Scene> allScenes = new ArrayList<Scene>();
        Scene testScene = new Scene();
        Scene lastScene = new Scene();
        UUID currentScreen = screenTransitions.get(TransitionType.BEGINNING_OF_LESSON);
        UUID nextScreen = null;
        LessonAct lessonAct = learningActs.get(learningActId).getLessonActs().get(0);
        List<? extends BaseScreen> screens = lessonAct.getLessonScreens();
        for(BaseScreen screen : screens) {
            nextScreen = UUID.randomUUID();
            testScene = buildScreen(learningActId, screen,
                    localeScreens.get(screen.getType()), currentScreen, nextScreen);
            if(testScene.getBackground().getBackground().equals(testScene.getBackground().getBackground())) {
            	testScene.getScreen().addAll(testScene.getScreen()); //Just add the screens in this case.
            	
            }
            else
            {
            	allScenes.add(testScene);
            	
            }
            lastScene = testScene; //Next loop, we test against the last scene we fetched.
            
        }

        screenTransitions.put(TransitionType.BEGINNING_OF_CHALLENGE, nextScreen);


        return allScenes;
    }

    /**
     * Builds a list of ScreenNode from the requested screen.  Will return a single screen unless the screen
     * has additional screens
     * @param learningActId the id of the learning act
     * @param screen the screen used to create the ScreenNode
     * @param localeScreen the LocaleScreen used to create the ScreenNode
     * @param screenId the UUID of the current screen
     * @param nextScreenId the UUID of the next screen
     * @return a list of ScreenNode
     */
    private Scene buildScreen(int learningActId, BaseScreen screen,
                                         LocaleScreen localeScreen, UUID screenId, UUID nextScreenId) {
        List<Screen> screenNodes = new ArrayList<Screen>();
        Scene currentScene = new Scene();
        BackgroundType currentBackground = new BackgroundType();
        Screen screenNode = new Screen();
        
        //screenNode.setId(screenId);
        currentBackground.setBackground(localeScreen.getBackground());
        currentScene.setBackground(currentBackground);
        //screenNode.setName("LESSON_" + learningActId + " - ");

        List<Asset> assets = new ArrayList<Asset>();
        //screenNode.setAssets(assets);
        List<GameObject> localeObjects = localeScreen.getGameObjects();
        if(localeObjects != null) {
            for(GameObject object : localeObjects) {
                assets.add(new Asset(object));
            }
        }
        List<LearningActCharacter> screenCharacters = screen.getCharacters();
        if(screenCharacters != null) {
            for(LearningActCharacter themeCharacter : screenCharacters) {
                LearningActCharacterType characterType = themeCharacter.getCharacterType();
                SharedCharacter localeCharacter = localeScreen.getCharacters().get(characterType);
                GameCharacter gameCharacter = characters.getCharacter(characterType);
                assets.add(new Asset(localeCharacter, gameCharacter, themeCharacter));
            }
        }

        List<GameText> screenInformationBoxes = screen.getInformationBoxes();
        Map<TextType, SharedInformationBox> localeInformationBoxes = localeScreen.getInformationBoxes();
        if(screenInformationBoxes != null) {
            for(GameText gameText : screenInformationBoxes) {
                assets.add(new Asset(localeInformationBoxes.get(gameText.getTextType()), gameText));
            }
        }

        List<GameButton> gameButtons = new ArrayList<GameButton>(screen.getButtons());
        if(screen instanceof ChallengeScreen) {
            ChallengeScreen challenge = (ChallengeScreen) screen;
            if(challenge.getChallengeOptions() != null) {
                gameButtons.addAll(challenge.getChallengeOptions());
            }
            screenNode.getChallenge().add(new Challenge());
        }
        Map<ButtonLocationType, SharedButton> localeButtons = localeScreen.getButtons();
        if(gameButtons != null) {
            for(GameButton gameButton : gameButtons) {
                Asset asset = new Asset(localeButtons.get(gameButton.getButtonLocationType()), gameButton);
                Behavior behavior = null;
                for(Behavior b : asset.getBehaviors()) {
                    if(b.getBehaviorType() == BehaviorType.TRANSITION_BEHAVIOR) {
                        behavior = b;
                    }
                }
                if(behavior != null) {
                    switch (gameButton.getTransitionType()) {
                        case BEGINNING_OF_LESSON:
                            behavior.setTransitionId(screenTransitions.get(TransitionType.BEGINNING_OF_LESSON));
                            break;
                        case NEXT_SCREEN:
                            behavior.setTransitionId(nextScreenId);
                            break;
                        case BEGINNING_OF_CHALLENGE:
                            behavior.setTransitionId(screenTransitions.get(TransitionType.BEGINNING_OF_CHALLENGE));
                            break;
                        case CURRENT_CHALLENGE:
                            behavior.setTransitionId(screenTransitions.get(TransitionType.CURRENT_CHALLENGE));
                            break;
                        case NEXT_CHALLENGE:
                            behavior.setTransitionId(screenTransitions.get(TransitionType.NEXT_CHALLENGE));
                            break;
                        case END_OF_STORY:
                            behavior.setTransitionId(screenTransitions.get(TransitionType.END_OF_STORY));
                            break;
                        case ADDITIONAL:
                            if(gameButton instanceof ChallengeOption) {
                                ChallengeOption challengeOption = (ChallengeOption) gameButton;
                                UUID additionalScreenId = UUID.randomUUID();
                                currentScene.getScreen().addAll(buildAdditionalScreens(challengeOption.getAdditionalScreens(), additionalScreenId));
                                behavior.setTransitionId(additionalScreenId);
                            }

                            break;
                        case NEXT_ACT:
                            behavior.setTransitionId(null);
                            break;
                    }
                }
                assets.add(asset);
            }
        }

        screenNodes.add(screenNode);
        //return screenNodes;
        return currentScene;
    }

    /**
     * Builds the additional screens tied to a challenge option
     * @param additionalScreens a list of additional screens
     * @param additionalScreenId the id of the first screen in the additional screens
     * @return a list of ScreenNode representing the built additional screens
     */
    private List<Screen> buildAdditionalScreens(List<BaseScreen> additionalScreens, UUID additionalScreenId) {
        List<Screen> screenNodes = new ArrayList<Screen>();
        List<Scene> sceneNodes = new ArrayList<Scene>();
        UUID nextScreen = UUID.randomUUID();
        for(BaseScreen screen : additionalScreens) {
        	Scene extraScene = buildScreen(0, screen, localeScreens.get(screen.getType()), additionalScreenId, nextScreen);
        	screenNodes.addAll(extraScene.getScreen());
            additionalScreenId = nextScreen;
            nextScreen = UUID.randomUUID();
        }

        return screenNodes;
    }

    public Map<ScreenType, LocaleScreen> getLocaleScreens() {
        return localeScreens;
    }

    @XmlElementWrapper(name = "LocaleScreens")
    public void setLocaleScreens(Map<ScreenType, LocaleScreen> localeScreens) {
        this.localeScreens = localeScreens;
    }

    public Map<TransitionType, UUID> getScreenTransitions() {
        return screenTransitions;
    }

    @XmlTransient
    public void setScreenTransitions(Map<TransitionType, UUID> screenTransitions) {
        this.screenTransitions = screenTransitions;
    }
}

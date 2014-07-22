package edu.utdallas.gamegeneratorcollection.GameComposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import edu.utdallas.gamespecification.StemQuestion;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.Asset;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.Behavior;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.BehaviorType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.ButtonLocationType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.GameObject;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedButton;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedCharacter;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedInfoBox;
import edu.utdallas.gamegeneratorcollection.GameComposition.TextType;
import edu.utdallas.gamespecification.Act;
import edu.utdallas.gamespecification.BackgroundType;
import edu.utdallas.gamespecification.ClassificationType;
import edu.utdallas.gamespecification.GameElementType;
import edu.utdallas.gamespecification.GenericInteraction;
import edu.utdallas.gamespecification.Hint;
import edu.utdallas.gamespecification.Layout;
import edu.utdallas.gamespecification.LearningObjectiveType;
import edu.utdallas.gamespecification.Location;
import edu.utdallas.gamespecification.MultipleChoiceItem;
import edu.utdallas.gamespecification.Option;
import edu.utdallas.gamespecification.QuizChallenge;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;
import edu.utdallas.gamespecification.Size;
import edu.utdallas.gamespecification.Stem;
import edu.utdallas.gamespecification.StemText;

/**
 * User: clocke.
 * Date: 2/17/13
 * Time: 5:54 PM
 */
@XmlRootElement(name = "Locale")
public class Locale {
    private List<LearningAct> learningActs;
    private Characters characters;
    private Theme theme;
    private Map<ScreenType, LocaleScreen> localeScreens;

    private Map<TransitionType, UUID> screenTransitions =
            new HashMap<TransitionType, UUID>();;

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
     * passed in learning act.
     * @param learningActId the index of the learning act
     * @return a list of ScreenNode
     */
    public final Act getAct(final int learningActId) {
        Act currentAct = new Act();
        List<Scene> actScenes = new ArrayList<Scene>();
        actScenes.addAll(
                buildScreens(learningActId, ScreenType.LESSON_STORY_INTRO));
        actScenes.addAll(buildLesson(learningActId));
        actScenes.addAll(buildChallenges(learningActId));
        actScenes.addAll(
                buildScreens(learningActId, ScreenType.LESSON_STORY_OUTRO));
        currentAct.getScene().addAll(actScenes);
        return currentAct;
    }

    /**
     * Builds all challenge screens for the learning act.
     * @param learningActId the id of the learning act to build
     * @return a list of ScreenNode representing the built challenge screens
     */
    private List<Scene> buildChallenges(final int learningActId) {
        UUID currentScreen = screenTransitions.get(
                TransitionType.BEGINNING_OF_CHALLENGE);
        LessonAct lessonAct = learningActs.get(
                learningActId).getLessonActs().get(0);
        Scene testScene = new Scene();
        testScene = null;
        List<Screen> screenNodes = new ArrayList<Screen>();
        List<ChallengeScreen> challenges = lessonAct.getLessonChallenges();
        List<Scene> allScenes = new ArrayList<Scene>();
        for (ChallengeScreen challenge : challenges) {
            UUID nextChallenge = UUID.randomUUID();
            testScene = buildChallenge(learningActId, challenge, currentScreen);
            int numScenes = allScenes.size();
            if (numScenes >= 1) {
                if (allScenes.get(numScenes - 1).getBackground().getBackground()
                        .equals(testScene.getBackground().getBackground())) {
                    allScenes.get(numScenes - 1).getScreen().addAll(
                            testScene.getScreen());
                } else {
                    allScenes.add(testScene);
                }
            } else {
                allScenes.add(testScene);
            }

            screenTransitions.put(TransitionType.NEXT_CHALLENGE, nextChallenge);
            screenTransitions.put(
                    TransitionType.CURRENT_CHALLENGE, currentScreen);
            currentScreen = nextChallenge;
        }

        return allScenes;
    }

    /**
     * Builds a single challenge including any additional screens.
     * @param learningActId the id of the learning act
     * @param challenge the ChallengeScreen screen
     * @param currentScreen the UUID of the screen to be built
     * @return a list of ScreenNode containing the challenge
     */
    private Scene buildChallenge(final int learningActId,
            final ChallengeScreen challenge, final UUID currentScreen) {
        UUID nextScreen = UUID.randomUUID();

        List<Screen> screenNodes = new ArrayList<Screen>();
        Scene challengeScene = buildScreen(
                learningActId, challenge,
                localeScreens.get(ScreenType.CHALLENGE),
                currentScreen, nextScreen);
        return challengeScene;
    }

    /**
     * Builds a list of ScreenNode for the passed learningActId and ScreenType.
     * @param learningActId index of the learning act used to build the screens
     * @param screenType the type of screens to build
     * @return a list of ScreenNode
     */
    private List<Scene> buildScreens(final int learningActId,
            final ScreenType screenType) {
        List<Screen> lessonScreens = new ArrayList<Screen>();
        List<Scene> allScenes = new ArrayList<Scene>();
        Scene testScene = new Scene();
        testScene = null;
        UUID currentScreen = UUID.randomUUID();
        UUID nextScreen = null;
        ThemeStory themeStory = theme.getThemeStories().get(learningActId);
        List<BaseScreen> themeStoryScreen;
        if (screenType.equals(ScreenType.LESSON_STORY_INTRO)) {
            themeStoryScreen = new ArrayList<BaseScreen>(themeStory.getIntro());
        } else {
            screenTransitions.put(TransitionType.END_OF_STORY, currentScreen);
            themeStoryScreen = new ArrayList<BaseScreen>(themeStory.getOutro());
        }

        for (BaseScreen screen : themeStoryScreen) {
            nextScreen = UUID.randomUUID();
            testScene = buildScreen(learningActId, screen,
                    localeScreens.get(screenType),
                    currentScreen, nextScreen);
            int numScenes = allScenes.size();
            if (numScenes >= 1) {
                if (allScenes.get(numScenes - 1).getBackground().getBackground()
                        .equals(testScene.getBackground().getBackground())) {
                    allScenes.get(numScenes - 1).getScreen().addAll(
                            testScene.getScreen());
                } else {
                    allScenes.add(testScene);
                }
            } else {
                allScenes.add(testScene);
            }
        }

        if (screenType.equals(ScreenType.LESSON_STORY_INTRO)) {
            screenTransitions.put(
                    TransitionType.BEGINNING_OF_LESSON, nextScreen);
        }

        return allScenes;
    }

    /**
     * Builds all lesson screens for a learning act.
     * @param learningActId the id of the learning act
     * @return a list of ScreenNode containing the built lesson screens
     */
    private List<Scene> buildLesson(final int learningActId) {
        List<Screen> lessonScreens = new ArrayList<Screen>();
        List<Scene> allScenes = new ArrayList<Scene>();
        Scene testScene = new Scene();
        UUID currentScreen = screenTransitions.get(
                TransitionType.BEGINNING_OF_LESSON);
        UUID nextScreen = null;
        LessonAct lessonAct = learningActs.get(
                learningActId).getLessonActs().get(0);
        List<? extends BaseScreen> screens = lessonAct.getLessonScreens();
        for (BaseScreen screen : screens) {
            nextScreen = UUID.randomUUID();
            nextScreen = UUID.randomUUID();
            testScene = buildScreen(learningActId, screen,
                    localeScreens.get(screen.getType()),
                    currentScreen, nextScreen);
            int numScenes = allScenes.size();
            if (numScenes >= 1) {
                if (allScenes.get(numScenes - 1).getBackground().getBackground()
                        .equals(testScene.getBackground().getBackground())) {
                    allScenes.get(numScenes - 1).getScreen().addAll(
                            testScene.getScreen());
                } else {
                    allScenes.add(testScene);
                }
            } else {
                allScenes.add(testScene);

            }
        }

        screenTransitions.put(
                TransitionType.BEGINNING_OF_CHALLENGE, nextScreen);


        return allScenes;
    }

    /**
     * Builds a list of ScreenNode from the requested screen.
     * Will return a single screen unless the screen
     * has additional screens.
     * @param learningActId the id of the learning act
     * @param screen the screen used to create the ScreenNode
     * @param localeScreen the LocaleScreen used to create the ScreenNode
     * @param screenId the UUID of the current screen
     * @param nextScreenId the UUID of the next screen
     * @return a list of ScreenNode
     */
    private Scene buildScreen(int learningActId, BaseScreen screen,
            LocaleScreen localeScreen, UUID screenId, UUID nextScreenId) {

        Scene currentScene = new Scene();
        List<Screen> screenNodes = new ArrayList<Screen>();
        List<Asset> assets = new ArrayList<Asset>();
        Screen screenNode = new Screen();
        List<Option> gameOptions = new ArrayList<Option>();
        List<GameObject> localeObjects = localeScreen.getGameObjects();
        List<LearningActCharacter> screenCharacters = screen.getCharacters();
        Boolean isChallenge = (screen instanceof ChallengeScreen);
        QuizChallenge currentChallenge = new QuizChallenge();
        MultipleChoiceItem currentItem = new MultipleChoiceItem();

        if (isChallenge) {
            currentChallenge.setClassification(ClassificationType.INTERACTIVE);
            currentChallenge.setLayout(new Layout());
            currentItem.setStem(new Stem());
            currentItem.getStem().setStemText(new StemText());
            currentItem.getStem().setStemQuestion(new StemQuestion());
            currentChallenge.setItem(currentItem);
            currentChallenge.getLayout().setLayoutName("Placeholder name");

        }

        currentScene.setBackground(
                new BackgroundType(localeScreen.getBackground()));

        if (localeObjects != null) {
            for (GameObject object : localeObjects) {
                GameElementType nextElement = convertGameObjects(object);
                screenNode.getGameElement().add(nextElement);
            }
        }

        if (screenCharacters != null) {
            for (LearningActCharacter themeCharacter : screenCharacters) {
                screenNode.getGameElement().add(
                        convertCharacter(themeCharacter, localeScreen));
            }
        }

        List<GameText> screenInformationBoxes = screen.getInformationBoxes();
        Map<TextType, SharedInfoBox> localeInformationBoxes =
                localeScreen.getInformationBoxes();
        if (screenInformationBoxes != null) {
            for (GameText gameText : screenInformationBoxes) {
                Asset infoBoxAsset = new Asset(
                        localeInformationBoxes.get(gameText.getTextType()),
                        gameText);
                assets.add(infoBoxAsset);
                screenNode.getGameElement().add(
                        convertInfoBoxes(gameText, infoBoxAsset));
                if (isChallenge) {
                    switch (gameText.getTextType()) {
                        case CHALLENGE_QUESTION:
                            currentItem.getStem().getStemText().setHint(
                                    new Hint(gameText.getText()));
                            break;
                        case CHALLENGE_DESCRIPTION:
                            currentItem.getStem().getStemQuestion().setHint(
                                    new Hint(gameText.getText()));
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        List<GameButton> gameButtons =
                new ArrayList<GameButton>(screen.getButtons());
        if (isChallenge) {
            ChallengeScreen challenge = (ChallengeScreen) screen;
            List<ChallengeOption> options = challenge.getChallengeOptions();
            if (options != null) {

                gameButtons.addAll(options);
                for (ChallengeOption option : options) {
                    Option nextOption = new Option();
                    nextOption.setHint(new Hint());
                    nextOption.getHint().setHint(option.getText());
                    //Need to change this to a proper reward
                    nextOption.setReward(option.getReward().toString());
                    currentItem.getOption().add(nextOption);
                }
            currentChallenge.setItem(currentItem);
            }

            screenNode.getChallenge().add(currentChallenge);
        }

        Map<ButtonLocationType, SharedButton> localeButtons =
                localeScreen.getButtons();
        if (gameButtons != null) {
            for (GameButton gameButton : gameButtons) {
                Asset asset = new Asset(
                        localeButtons.get(gameButton.getButtonLocationType()),
                        gameButton);
                Behavior behavior = null;
                for (Behavior b : asset.getBehaviors()) {
                    if (b.getBehaviorType()
                            == BehaviorType.TRANSITION_BEHAVIOR) {
                        behavior = b;
                    }
                }
                if (behavior != null) {
                    switch (gameButton.getTransitionType()) {
                        case BEGINNING_OF_LESSON:
                            behavior.setTransitionId(screenTransitions.get(
                                    TransitionType.BEGINNING_OF_LESSON));
                            break;
                        case NEXT_SCREEN:
                            behavior.setTransitionId(nextScreenId);
                            break;
                        case BEGINNING_OF_CHALLENGE:
                            behavior.setTransitionId(screenTransitions.get(
                                    TransitionType.BEGINNING_OF_CHALLENGE));
                            break;
                        case CURRENT_CHALLENGE:
                            behavior.setTransitionId(screenTransitions.get(
                                    TransitionType.CURRENT_CHALLENGE));
                            break;
                        case NEXT_CHALLENGE:
                            behavior.setTransitionId(screenTransitions.get(
                                    TransitionType.NEXT_CHALLENGE));
                            break;
                        case END_OF_STORY:
                            behavior.setTransitionId(screenTransitions.get(
                                    TransitionType.END_OF_STORY));
                            break;
                        case ADDITIONAL:
                            if (gameButton instanceof ChallengeOption) {
                                ChallengeOption challengeOption =
                                        (ChallengeOption) gameButton;
                                UUID additionalScreenId = UUID.randomUUID();
                                currentScene.getScreen().addAll(
                                        buildAdditionalScreens(
                                                challengeOption.
                                                getAdditionalScreens()
                                                , additionalScreenId));
                                behavior.setTransitionId(additionalScreenId);
                            }

                            break;
                        case NEXT_ACT:
                            behavior.setTransitionId(null);
                            break;
                        default:
                            break;
                    }
                }
                GenericInteraction nextElement = new GenericInteraction();
                nextElement.setName("Button Type");
                nextElement.setLocation(
                        new Location(asset.getX(), asset.getY()));
                nextElement.setSize(
                        new Size(asset.getWidth(), asset.getHeight()));
                nextElement.setText(gameButton.getText());
                screenNode.getGameElement().add(nextElement);
                assets.add(asset);
            }
        }

        screenNodes.add(screenNode);
        currentScene.getScreen().addAll(screenNodes);

        return currentScene;
    }

    /**
     * Builds the additional screens tied to a challenge option.
     * @param additionalScreens a list of additional screens
     * @param additionalScreenId id of first screen in the additional screens
     * @return a list of ScreenNode representing the built additional screens
     */
    private List<Screen> buildAdditionalScreens(
            final List<BaseScreen> additionalScreens,
            UUID additionalScreenId) {
        List<Screen> screenNodes = new ArrayList<Screen>();
        List<Scene> sceneNodes = new ArrayList<Scene>();
        UUID nextScreen = UUID.randomUUID();
        for (BaseScreen screen : additionalScreens) {
            Scene extraScene = buildScreen(
                    0, screen, localeScreens.get(screen.getType()),
                    additionalScreenId, nextScreen);
            screenNodes.addAll(extraScene.getScreen());
            additionalScreenId = nextScreen;
            nextScreen = UUID.randomUUID();
        }

        return screenNodes;
    }
    /**
     *
     * @param rawCharacter
     * Expected object type is
     * {@link LearningActCharacter}
     * @param screen
     * Expected object type is
     * {@link LocaleScreen}
     * @return
     * Will return
     * {@link GameElementType}
     */
    public final GameElementType convertCharacter(
            final LearningActCharacter rawCharacter,
            final LocaleScreen screen) {

        LearningActCharacterType characterType =
                rawCharacter.getCharacterType();
        SharedCharacter localeCharacter =
                screen.getCharacters().get(characterType);
        GameCharacter gameCharacter =
                characters.getCharacter(characterType);
        Asset characterAsset =
                new Asset(localeCharacter, gameCharacter,
                        rawCharacter);
        //assets.add(characterAsset);
        GameElementType convertedCharacter =
                convertGameObjects(localeCharacter);
        convertedCharacter.setName(characterAsset.getDisplayImage());
        return convertedCharacter;
    }

    public GameElementType convertGameObjects(GameObject rawObject) {
        GameElementType nextElement = new GameElementType();
        nextElement.setLocation(
                new Location(rawObject.getX(), rawObject.getY()));
        nextElement.setSize(
                new Size(rawObject.getWidth(), rawObject.getHeight()));
        nextElement.setName(rawObject.getPathToAsset());
        return nextElement;
    }
    
    public final GameElementType convertInfoBoxes(GameText boxText, Asset infoBoxAsset) {
        //TODO: May cause typing problems, consider altering this.
        GenericInteraction infoBox = new GenericInteraction();
        infoBox.setText(boxText.getText());
        infoBox.setName("Infobox");
        infoBox.setLocation(
                new Location(infoBoxAsset.getX(), infoBoxAsset.getY()));
        infoBox.setSize(
                new Size(infoBoxAsset.getWidth(),
                        infoBoxAsset.getHeight()));
        return infoBox;
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

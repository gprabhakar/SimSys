package edu.utdallas.gamegeneratorcollection.GameComposition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import edu.utdallas.gamegeneratorcollection.ComponentCreation.Asset;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.Behavior;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.BehaviorType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.GameObject;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedButton;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedCharacter;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedInfoBox;
import edu.utdallas.gamespecification.BackgroundType;
import edu.utdallas.gamespecification.GameElementType;
import edu.utdallas.gamespecification.GenericInteraction;
import edu.utdallas.gamespecification.Location;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;
import edu.utdallas.gamespecification.Size;
import edu.utdallas.gamespecification.Transition;

/**
 * User: clocke.
 * Date: 2/17/13
 * Time: 6:04 PM
 */
@XmlRootElement(name = "Theme")
public class Theme {
    /**
     * Subject object, pulled from Subject XML.
     */
    private Subject subject;
    /**
     * Characters object, pulled from Characters XML.
     */
    private Characters characters;
    /**
     * A list of screens, typically listed in a
     * Theme XML file.
     */
    private List<ThemeScreen> introScreens;
    /**
     * A list of screens, typically listed in a
     * Theme XML file.
     */
    private List<ThemeScreen> outroScreens;
    /**
     * A list of story screens.
     */
    private List<ThemeStory> themeStories;

    /**
     * Returns a list of ScreenNode built from the intro screens.
     * If there are no intro screens it will return an empty list
     * @return the list of intro screens
     */
    public final Scene getIntro() {
        if (introScreens != null) {
            return getScreens(introScreens);
        } else {
            return new Scene();
        }
    }

    /**
     * Returns a list of ScreenNode built from the outro screens.
     * If there are no outro screens it will return an empty list
     * @return the list of outro screens
     */
    public final Scene getOutro() {
        if (outroScreens != null) {
            return getScreens(outroScreens);
        } else {
            return new Scene();
        }
    }

    /**
     * Returns a list of ScreenNode built from the passed list of ThemeScreen.
     * @param screens a list of ThemeScreen
     * @return a list of ScreenNode
     */
    private Scene getScreens(final List<ThemeScreen> screens) {
        Scene currentScene = new Scene();
        BackgroundType currentBackground = new BackgroundType();

        UUID nextScreen = UUID.randomUUID();
        for (ThemeScreen screen : screens) {
            Screen screenNode = new Screen();
            currentBackground.setBackground(screen.getBackground());
            currentScene.setBackground(currentBackground);
            List<Asset> assets = new ArrayList<Asset>();
            if (screen.getGameObjects() != null) {
                for (GameObject object : screen.getGameObjects()) {
                    assets.add(new Asset(object));
                    GameElementType nextElement = convertGameObjects(object);
                    screenNode.getGameElement().add(nextElement);
                }
            }
            if (screen.getThemeCharacters() != null) {
                for (SharedCharacter character
                         : screen.getThemeCharacters().values()) {
                    LearningActCharacterType characterType =
                             character.getCharacterType();
                    GameCharacter gameCharacter =
                             characters.getCharacter(characterType);
                    Asset characterAsset = new Asset(character, gameCharacter);
                    assets.add(characterAsset);
                    GameElementType nextElement =
                             convertGameObjects(character);
                    //Add the image from the asset class item.
                    nextElement.setName(characterAsset.getDisplayImage());
                    screenNode.getGameElement().add(nextElement);
                }
            }
            if (screen.getInformationBoxes() != null) {
                for (SharedInfoBox informationBox
                          : screen.getInformationBoxes()) {
                    assets.add(new Asset(informationBox));
                    GameElementType nextElement =
                            new GenericInteraction(informationBox);
                    screenNode.getGameElement().add(nextElement);
                }
            }
            if (screen.getButtons() != null) {
                for (SharedButton button : screen.getButtons().values()) {
                    Asset asset = new Asset(button);
                    GameElementType nextElement =
                             new GenericInteraction(button);

                    if (asset.getBehaviors() != null) {
                        for (Behavior behavior : asset.getBehaviors()) {
                            if (BehaviorType.TRANSITION_BEHAVIOR
                                    == behavior.getBehaviorType()
                                    && TransitionType.NEXT_SCREEN
                                    == behavior.getTransition()) {
                                behavior.setTransitionId(nextScreen);
                            }
                        }
                    }
                    assets.add(asset);
                    nextElement.setName("Button Type");
                    screenNode.getGameElement().add(nextElement);
                }
            }
            screenNode.setTransition(new Transition());
            currentScene.getScreen().add(screenNode);

            nextScreen = UUID.randomUUID();
        }
        currentScene.setTransition(new Transition());
        return currentScene;
    }

    /**
     *
     * @param rawObject
     * {@link GameObject}
     * @return
     * {@link GameElementType}
     */
    public final GameElementType convertGameObjects(
            final GameObject rawObject) {
        GameElementType nextElement = new GameElementType();
        nextElement.setLocation(
                new Location(rawObject.getX(), rawObject.getY()));
        nextElement.setSize(
                new Size(rawObject.getWidth(), rawObject.getHeight()));
        nextElement.setName(rawObject.getPathToAsset());
        return nextElement;
    }

    /**
     * @return
     * {@link Subject}
     */
    public final Subject getSubject() {
        return subject;
    }

    /**
     * @param subjectToBeSet
     * {@link Subject}
     */
    @XmlTransient
    public final void setSubject(final Subject subjectToBeSet) {
        this.subject = subjectToBeSet;
    }

    /**
     * @return
     * {@link Characters}
     */
    public final Characters getCharacters() {
        return characters;
    }

    /**
     * @param charactersToBeSet
     * {@link Characters}
     */
    @XmlTransient
    public final void setCharacters(final Characters charactersToBeSet) {
        this.characters = charactersToBeSet;
    }

    /**
     * @return
     * {@link ThemeScreen}
     */
    public final List<ThemeScreen> getIntroScreens() {
        return introScreens;
    }

    /**
     * @param screens
     * {@link ThemeScreen}
     */
    @XmlElementWrapper(name = "IntroScreens")
    @XmlElement(name = "IntroScreen")
    public final void setIntroScreens(final List<ThemeScreen> screens) {
        this.introScreens = screens;
    }

    /**
     * @return
     * {@link ThemeScreen}
     */
    public final List<ThemeScreen> getOutroScreens() {
        return outroScreens;
    }

    /**
     * @param screens
     * {@link ThemeScreen}
     */
    @XmlElementWrapper(name = "OutroScreens")
    @XmlElement(name = "OutroScreen")
    public final void setOutroScreens(final List<ThemeScreen> screens) {
        this.outroScreens = screens;
    }

    /**
     * @return
     * {@link ThemeStory}
     */
    public final List<ThemeStory> getThemeStories() {
        return themeStories;
    }

    /**
     * @param stories
     * {@link ThemeStory}
     */
    @XmlElementWrapper(name = "ThemeStories")
    @XmlElement(name = "ThemeStory")
    public final void setThemeStories(final List<ThemeStory> stories) {
        this.themeStories = stories;
    }
}

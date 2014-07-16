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
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedInformationBox;
import edu.utdallas.gamespecification.BackgroundType;
import edu.utdallas.gamespecification.GameElementType;
import edu.utdallas.gamespecification.GenericInteraction;
import edu.utdallas.gamespecification.Location;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;
import edu.utdallas.gamespecification.Size;

/**
 * User: clocke.
 * Date: 2/17/13
 * Time: 6:04 PM
 */
@XmlRootElement(name = "Theme")
public class Theme {
    private Subject subject;
    private Characters characters;
    private List<ThemeScreen> introScreens;
    private List<ThemeScreen> outroScreens;
    private List<ThemeStory> themeStories;

    /**
     * Returns a list of ScreenNode built from the intro screens.
     * If there are no intro screens it will return an empty list
     * @return the list of intro screens
     */
    public final Scene getIntro() {
        return (introScreens != null ? getScreens(introScreens) : new Scene());
    }

    /**
     * Returns a list of ScreenNode built from the outro screens.
     * If there are no outro screens it will return an empty list
     * @return the list of outro screens
     */
    public final Scene getOutro() {
        return (outroScreens != null ? getScreens(outroScreens) : new Scene());
    }

    /**
     * Returns a list of ScreenNode built from the passed list of ThemeScreen.
     * @param screens a list of ThemeScreen
     * @return a list of ScreenNode
     */
    private Scene getScreens(final List<ThemeScreen> screens) {
        //List<Screen> screenNodes = new ArrayList<Screen>();
        Scene currentScene = new Scene();
        BackgroundType currentBackground = new BackgroundType();
        //UUID currentScreen = UUID.randomUUID();
        UUID nextScreen = UUID.randomUUID();
        for (ThemeScreen screen : screens) {
            Screen screenNode = new Screen();
            //screenNode.setId(currentScreen);
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
                    //nextElement.setName(character.getText());

                    screenNode.getGameElement().add(nextElement);
                }
            }
            if (screen.getInformationBoxes() != null) {
                for (SharedInformationBox informationBox
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
            //screenNode.setAssets(assets);
            currentScene.getScreen().add(screenNode);
            //currentScreen = nextScreen;
            nextScreen = UUID.randomUUID();
        }
        return currentScene;
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

    public Subject getSubject() {
        return subject;
    }

    @XmlTransient
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Characters getCharacters() {
        return characters;
    }

    @XmlTransient
    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public List<ThemeScreen> getIntroScreens() {
        return introScreens;
    }

    @XmlElementWrapper(name = "IntroScreens")
    @XmlElement(name = "IntroScreen")
    public void setIntroScreens(List<ThemeScreen> introScreens) {
        this.introScreens = introScreens;
    }

    public List<ThemeScreen> getOutroScreens() {
        return outroScreens;
    }

    @XmlElementWrapper(name = "OutroScreens")
    @XmlElement(name = "OutroScreen")
    public void setOutroScreens(List<ThemeScreen> outroScreens) {
        this.outroScreens = outroScreens;
    }

    public List<ThemeStory> getThemeStories() {
        return themeStories;
    }

    @XmlElementWrapper(name = "ThemeStories")
    @XmlElement(name = "ThemeStory")
    public void setThemeStories(List<ThemeStory> themeStories) {
        this.themeStories = themeStories;
    }
}

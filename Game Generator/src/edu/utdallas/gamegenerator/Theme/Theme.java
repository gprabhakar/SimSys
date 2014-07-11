package edu.utdallas.gamegenerator.Theme;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import edu.utdallas.gamegenerator.Characters.Characters;
import edu.utdallas.gamegenerator.Characters.GameCharacter;
import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;
import edu.utdallas.gamegenerator.LearningAct.Screen.TransitionType;
import edu.utdallas.gamegenerator.Subject.Subject;
import edu.utdallas.gamespecification.BackgroundType;
import edu.utdallas.gamespecification.GameElementType;
import edu.utdallas.gamespecification.GenericInteraction;
import edu.utdallas.gamespecification.Location;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;
import edu.utdallas.gamespecification.Size;
import edu.utdallas.sharedfiles.Shared.Asset;
import edu.utdallas.sharedfiles.Shared.Behavior;
import edu.utdallas.sharedfiles.Shared.BehaviorType;
import edu.utdallas.sharedfiles.Shared.GameObject;
import edu.utdallas.sharedfiles.Shared.SharedButton;
import edu.utdallas.sharedfiles.Shared.SharedCharacter;
import edu.utdallas.sharedfiles.Shared.SharedInformationBox;

/**
 * User: clocke
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
    public Scene getIntro() {
        return (introScreens != null ? getScreens(introScreens) : new Scene());
    }

    /**
     * Returns a list of ScreenNode built from the outro screens.
     * If there are no outro screens it will return an empty list
     * @return the list of outro screens
     */
    public Scene getOutro() {
        return (outroScreens != null ? getScreens(outroScreens) : new Scene());
    }

    /**
     * Returns a list of ScreenNode built from the passed list of ThemeScreen
     * @param screens a list of ThemeScreen
     * @return a list of ScreenNode
     */
    private Scene getScreens(List<ThemeScreen> screens) {
        //List<Screen> screenNodes = new ArrayList<Screen>();
        Scene currentScene = new Scene();
        BackgroundType currentBackground = new BackgroundType();
        //UUID currentScreen = UUID.randomUUID();
        UUID nextScreen = UUID.randomUUID();
        for(ThemeScreen screen : screens) {
            Screen screenNode = new Screen();
            //screenNode.setId(currentScreen);
            currentBackground.setBackground(screen.getBackground());
            currentScene.setBackground(currentBackground);
            List<Asset> assets = new ArrayList<Asset>();
            if(screen.getGameObjects() != null) {
                for(GameObject object : screen.getGameObjects()) {
                    assets.add(new Asset(object));
                	GameElementType nextElement = convertGameObjects(object);
                	screenNode.getGameElement().add(nextElement);
                }
            }
            if(screen.getThemeCharacters() != null) {
                for(SharedCharacter character : screen.getThemeCharacters().values()) {
                    LearningActCharacterType characterType = character.getCharacterType();
                    GameCharacter gameCharacter = characters.getCharacter(characterType);
                    assets.add(new Asset(character, gameCharacter));
                    GameElementType nextElement = convertGameObjects(character);
                    nextElement.setName(character.getText());
                    screenNode.getGameElement().add(nextElement);
                }
            }
            if(screen.getInformationBoxes() != null) {
                for(SharedInformationBox informationBox : screen.getInformationBoxes()) {
                	assets.add(new Asset(informationBox));
                    GameElementType nextElement = new GenericInteraction(informationBox);
                    screenNode.getGameElement().add(nextElement);
                }
            }
            if(screen.getButtons() != null) {
                for(SharedButton button : screen.getButtons().values()) {
                    Asset asset = new Asset(button);
                    GameElementType nextElement = new GenericInteraction(button);
                    screenNode.getGameElement().add(nextElement);
                    if(asset.getBehaviors() != null) {
                        for(Behavior behavior : asset.getBehaviors()) {
                            if(BehaviorType.TRANSITION_BEHAVIOR == behavior.getBehaviorType() &&
                                    TransitionType.NEXT_SCREEN == behavior.getTransition()) {
                                behavior.setTransitionId(nextScreen);
                            }
                        }
                    }
                    assets.add(asset);
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
    	nextElement.setLocation(new Location(rawObject.getX(), rawObject.getY()));
    	nextElement.setSize(new Size(rawObject.getWidth(), rawObject.getHeight()));
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

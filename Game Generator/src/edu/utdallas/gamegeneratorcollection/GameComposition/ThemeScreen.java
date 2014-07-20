package edu.utdallas.gamegeneratorcollection.GameComposition;

import edu.utdallas.gamegeneratorcollection.ComponentCreation.ButtonLocationType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.GameObject;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedButton;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedCharacter;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedInfoBox;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import java.util.List;
import java.util.Map;

/**
 * User: clocke
 * Date: 3/3/13
 * Time: 6:29 PM
 */
public class ThemeScreen {
    private String background;
    private Map<LearningActCharacterType, SharedCharacter> themeCharacters;
    private List<GameObject> gameObjects;
    private Map<ButtonLocationType, SharedButton> buttons;
    private List<SharedInfoBox> informationBoxes;

    public String getBackground() {
        return background;
    }

    @XmlElement(name = "Background")
    public void setBackground(String background) {
        this.background = background;
    }

    public Map<LearningActCharacterType, SharedCharacter> getThemeCharacters() {
        return themeCharacters;
    }

    @XmlElementWrapper(name = "ThemeCharacters")
    public void setThemeCharacters(Map<LearningActCharacterType, SharedCharacter> themeCharacters) {
        this.themeCharacters = themeCharacters;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    @XmlElementWrapper(name = "GameObjects")
    @XmlElement(name = "GameObject")
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Map<ButtonLocationType, SharedButton> getButtons() {
        return buttons;
    }

    @XmlElementWrapper(name = "ThemeButtons")
    public void setButtons(Map<ButtonLocationType, SharedButton> buttons) {
        this.buttons = buttons;
    }

    public List<SharedInfoBox> getInformationBoxes() {
        return informationBoxes;
    }

    @XmlElementWrapper(name = "InformationBoxes")
    @XmlElement(name = "InformationBox")
    public void setInformationBoxes(List<SharedInfoBox> informationBoxes) {
        this.informationBoxes = informationBoxes;
    }
}

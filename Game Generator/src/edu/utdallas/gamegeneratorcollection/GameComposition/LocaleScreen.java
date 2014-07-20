package edu.utdallas.gamegeneratorcollection.GameComposition;

import edu.utdallas.gamegeneratorcollection.ComponentCreation.ButtonLocationType;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.GameObject;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedButton;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedCharacter;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.SharedInfoBox;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Map;

/**
 * User: clocke
 * Date: 3/17/13
 * Time: 9:28 PM
 */
@XmlRootElement(name = "LocaleScreen")
public class LocaleScreen {
    private String background;
    private List<GameObject> gameObjects;
    private Map<LearningActCharacterType, SharedCharacter> characters;
    private Map<TextType, SharedInfoBox> informationBoxes;
    private Map<ButtonLocationType, SharedButton> buttons;

    public String getBackground() {
        return background;
    }

    @XmlElement(name = "Background")
    public void setBackground(String background) {
        this.background = background;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    @XmlElementWrapper(name = "GameObjects")
    @XmlElement(name = "GameObject")
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Map<LearningActCharacterType, SharedCharacter> getCharacters() {
        return characters;
    }

    @XmlElementWrapper(name = "Characters")
    public void setCharacters(Map<LearningActCharacterType, SharedCharacter> characters) {
        this.characters = characters;
    }

    public Map<TextType, SharedInfoBox> getInformationBoxes() {
        return informationBoxes;
    }

    @XmlElementWrapper(name = "InformationBoxes")
    public void setInformationBoxes(Map<TextType, SharedInfoBox> informationBoxes) {
        this.informationBoxes = informationBoxes;
    }

    public Map<ButtonLocationType, SharedButton> getButtons() {
        return buttons;
    }

    @XmlElementWrapper(name = "Buttons")
    public void setButtons(Map<ButtonLocationType, SharedButton> buttons) {
        this.buttons = buttons;
    }
}

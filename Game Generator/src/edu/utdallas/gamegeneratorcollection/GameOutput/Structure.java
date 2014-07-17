package edu.utdallas.gamegeneratorcollection.GameOutput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.utdallas.gamegeneratorcollection.GameComposition.Characters;
import edu.utdallas.gamegeneratorcollection.GameComposition.Locale;
import edu.utdallas.gamegeneratorcollection.GameComposition.Theme;
import edu.utdallas.gamespecification.Act;
import edu.utdallas.gamespecification.Character;
import edu.utdallas.gamespecification.Decoration;
import edu.utdallas.gamespecification.EducationInteraction;
import edu.utdallas.gamespecification.Game;
import edu.utdallas.gamespecification.GameElementType;
import edu.utdallas.gamespecification.GenericInteraction;
import edu.utdallas.gamespecification.NonPlayer;
import edu.utdallas.gamespecification.Player;
import edu.utdallas.gamespecification.Profile;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;

/**
 * User: clocke.
 * Date: 2/17/13
 * Time: 6:52 PM
 */
public class Structure {
    /**
     * Holds data pulled from Theme XML data.
     */
    private Theme theme;
    /**
     * Holds data pulled from Locale XML data.
     */
    private Locale locale;
    /**
     * Holds data pulled from the Characters XML data.
     */
    private Characters characters;
    /**
     * Set to 4 currently. Before operating with this as the
     * constant, should verify that each Characters XML file
     * has only 4 characters possible.
     */


    /**
     * The list of acts used to build the game.
     */
    private List<Act> acts;
    /**
     * The game object to be output in XML format.
     */
    private Game game;

    /**
     * Builds the game from the Acts, Characters, and LearningObjectives.
     * @return
     * expected object is of type
     * {@link Game}
     */
    public final Game createGame() {

        acts = new ArrayList<Act>();
        acts.add(createActFromScreens(theme.getIntro()));
        for (int i = 0; i < locale.getLearningActs().size(); i++) {
            acts.add(locale.getAct(i));
        }

        acts.add(createActFromScreens(theme.getOutro()));
        game = new Game();

        wireUpActs(acts);
        game.getCharacter().addAll(convertCharacters(characters));
        game.getAct().addAll(acts);

        convertAssetsAndBehaviors();

        return game;
    }

    /**
     *
     * @param chars
     * allowed object types is
     * {@link Characters}
     * @return
     * expected object types
     * {@link Character}
     * {@link Player}
     * {@link NonPlayer}
     */

    private Collection<? extends Character> convertCharacters(
            final Characters chars) {
        List allCharacters = new ArrayList<Character>();
        Player player = new Player();
        player.setCharacterName(chars.getPlayer().getName());
        player.setName(player.getCharacterName());
        player.setProfile(new Profile());
        player.setAutonomousBehaviour("Behavior");
        allCharacters.add(player);
        NonPlayer hero = new NonPlayer();
        hero.setCharacterName(chars.getHero().getName());
        hero.setName(hero.getCharacterName());
        hero.setProfile(new Profile());
        hero.setAutonomousBehaviour("Behavior");
        allCharacters.add(hero);
        NonPlayer villain = new NonPlayer();
        villain.setCharacterName(chars.getVillain().getName());
        villain.setName(villain.getCharacterName());
        villain.setProfile(new Profile());
        villain.setAutonomousBehaviour("Behavior");
        allCharacters.add(villain);
        NonPlayer alt = new NonPlayer();
        alt.setCharacterName(chars.getAlt().getName());
        alt.setName(alt.getCharacterName());
        alt.setProfile(new Profile());
        alt.setAutonomousBehaviour("Behavior");
        allCharacters.add(alt);

        return allCharacters;
    }

    /**
     * Wires the transition behaviors between acts together.
     * It will search for transition behaviors with a null transitionId
     * and set it to the next act's first scene's first screen's id
     * Currently the choice seems to be between Transition and Sequence;
     * We have defaulted to setting a transition only. Also, Transitions
     * are currently string values. When this is updated to reflect a proper
     * transition class, the transitions will need to be set properly.
     * @param actList
     * Allowed types are
     * {@link Act}
     */

    private void wireUpActs(final List<Act> actList) {
        for (int i = 0; i < actList.size() - 1; i++) {
            Act act = actList.get(i);
            for (int j = 0; j < act.getScene().size() - 1; j++) {
                Scene scene = act.getScene().get(j);
                for (int k = 0; k < scene.getScreen().size() - 1; k++) {
                    Screen screen = scene.getScreen().get(k);
                    if (screen.getTransition() == null) {
                        act.getScene().get(j).getScreen().get(k)
                        .setTransition("Straight Cut");

                    }

                }
            }
        }
    }


    /**
     * Converts every asset into it a new object of the correct type.
     */

    private void convertAssetsAndBehaviors() {
        for (int a = 0; a < game.getAct().size(); a++) {
            Act act = game.getAct().get(a);
            for (int b = 0; b < act.getScene().size(); b++) {
                Scene scene = act.getScene().get(b);
                for (int c = 0; c < scene.getScreen().size(); c++) {
                    Screen screen = scene.getScreen().get(c);

                    for (int d = 0; d < screen.getGameElement().size(); d++) {
                        GameElementType element = screen.getGameElement()
                                .get(d);
                        GameElementType newElement = null;
                        System.out.println("Element is: " + element.getClass()
                                .getName());
                        if (element.getClass().getName()
                                == "GenericInteraction") {
                            newElement = new GenericInteraction();
                        }
                        if (element.getClass().getName() == "Decoration") {
                            newElement = new Decoration();
                        }
                        if (element.getClass().getName()
                                == "EducationInteraction") {
                            newElement = new EducationInteraction();
                        }
                        if (element.getClass().getName() == "Player") {
                            newElement = new Player();
                        }
                        if (element.getClass().getName() == "NonPlayer") {
                            newElement = new NonPlayer();
                        }
                    }
                }
            }
        }
    }


    /**
     * Creates an Act object from a list of ScreenNode.
     * Sets learning objective from the scene.
     * @param screenNodes
     * Allowed types are
     * {@link Scene}
     * @return
     * {@link Act}
     */
    private Act createActFromScreens(final Scene screenNodes) {
        Act act = new Act();
        act.getScene().add(screenNodes);
        return act;
    }

    /**
     * @return
     * {@link Theme}
     */
    public final Theme getTheme() {
        return theme;
    }

    /**
     * @param themeStructure
     * {@link Theme}
     */
    public final void setTheme(final Theme themeStructure) {
        this.theme = themeStructure;
    }

    /**
     * @return
     * {@link Locale}
     */
    public final Locale getLocale() {
        return locale;
    }

    /**
     * @param localeStructure
     * {@link Locale}
     */
    public final void setLocale(final Locale localeStructure) {
        this.locale = localeStructure;
    }

    /**
     * @return
     * {@link Act}
     */
    public final List<Act> getActs() {
        return acts;
    }

    /**
     * @param actList
     * {@link Act}
     */
    public final void setActs(final List<Act> actList) {
        this.acts = actList;
    }

    /**
     * @return
     * {@link Characters}
     */
    public final Characters getCharacters() {
        return characters;
    }

    /**
     * @param charactersStructure
     * {@link Characters}
     */
    public final void setCharacters(final Characters charactersStructure) {
        this.characters = charactersStructure;
    }
}

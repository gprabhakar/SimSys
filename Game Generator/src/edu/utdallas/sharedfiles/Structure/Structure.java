package edu.utdallas.sharedfiles.Structure;

import java.util.ArrayList;
import java.util.List;




import edu.utdallas.gamegenerator.Locale.Locale;
import edu.utdallas.gamegenerator.Theme.Theme;
import edu.utdallas.sharedfiles.Shared.Asset;
import edu.utdallas.sharedfiles.Shared.Behavior;
import edu.utdallas.sharedfiles.Shared.BehaviorType;
import edu.utdallas.sharedfiles.Shared.ButtonAsset;
import edu.utdallas.sharedfiles.Shared.CharacterAsset;
import edu.utdallas.sharedfiles.Shared.ConversationBubbleAsset;
import edu.utdallas.sharedfiles.Shared.EndGameBehavior;
import edu.utdallas.sharedfiles.Shared.ImageAsset;
import edu.utdallas.sharedfiles.Shared.InformationBoxAsset;
import edu.utdallas.sharedfiles.Shared.ThoughtBubbleAsset;
import edu.utdallas.sharedfiles.Shared.TransitionBehavior;
import edu.utdallas.sharedfiles.gamespec.Act;
import edu.utdallas.sharedfiles.gamespec.BackgroundType;
import edu.utdallas.sharedfiles.gamespec.Challenge;
import edu.utdallas.sharedfiles.gamespec.Game;
import edu.utdallas.sharedfiles.gamespec.Character;
import edu.utdallas.sharedfiles.gamespec.GameElementType;
import edu.utdallas.sharedfiles.gamespec.LearningObjectiveType;
import edu.utdallas.sharedfiles.gamespec.MusicType;
import edu.utdallas.sharedfiles.gamespec.Scene;
import edu.utdallas.sharedfiles.gamespec.Screen;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:52 PM
 */
public class Structure {
    private Theme theme;
    private Locale locale;
    private List<Act> acts;
    private Game game;

    /**
     * Builds the game from the Acts, Characters, and LearningObjectives
     * @param objectives a list of LearningObjectiveType
     * @param characters a list of Character
     * @param acts a list of Act
     * @return a Game object representing the created game
     */
    public Game createGame(List<LearningObjectiveType> objectives, List<Character> characters,
    		List<Act> acts) {
    	
    	Game game = new Game();
    	for (int i = 0; i < objectives.size(); i++){
    		game.getLearningObjective().add(objectives.get(i));
    	}
    	for (int i = 0; i < objectives.size(); i++){
    		game.getCharacter().add(characters.get(i));
    	}
    	for (int i = 0; i < objectives.size(); i++){
    		game.getAct().add(acts.get(i));
    	}
    	
    	return game;
    }

    /**
     * Wires the transition behaviors between acts together.
     * It will search for transition behaviors with a null transitionId and set it to
     * the next act's first scene's first screen's id
     * @param acts a list of Acts
     */
    /*
    private void wireUpActs(List<Act> acts) {
        for(int i = 0; i < acts.size() - 1; i++) {
            Act act = acts.get(i);
            // Commenting out this line as new spec does not have Id field for Screen
            //UUID nextActId = acts.get(i+1).getScene().get(0).getScreen().get(0).getId();
            for(Scene scene : act.getScene()) {
                Screen screenNode = scene.getScreen().get(0);

                // Commenting out this line as new spec does not have Asset field for Screen
                // Need to set transition here I guess - Prabha

                if(screenNode.getAssets() != null) {
                    for(Asset asset : screenNode.getAssets()) {
                        if(asset.getBehaviors() != null) {
                            for(Behavior behavior : asset.getBehaviors()) {
                               if(behavior != null &&
                                        BehaviorType.TRANSITION_BEHAVIOR.equals(behavior.getBehaviorType()) &&
                                        behavior.getTransitionId() == null) {
                                    behavior.setTransitionId(nextActId);
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/
    
    /**
     * Names all acts, scenes, and screens in the game
     */
    // This whole method seems unnecessary as it Names game, acts, scenes, screen but they don't have name field
    /*
    private void nameEverything() {
        // No Name field for Game, Act, Scene, Screen in new spec
    	//  game.setName("Game");
        for(int a = 0; a < game.getAct().size(); a++) {
            Act act = game.getAct().get(a);
           // act.setName("Act" + a);
            for(int b = 0; b < act.getScene().size(); b++) {
                Scene scene = act.getScene().get(b);
                //scene.setName("Act" + a + " Scene" + b);
                for(int c = 0; c < scene.getScreen().size(); c++) {
                   // Screen screen = scene.getScreen().get(c);
                   // screen.setName("Act" + a + " Scene" + b + " Screen" + c);
                }
            }
        }
    }*/

    /**
     * Converts every asset into it a new object of the correct type
     */
    /*
    private void convertAssetsAndBehaviors() {
        for(int a = 0; a < game.getAct().size(); a++) {
            Act act = game.getAct().get(a);
            for(int b = 0; b < act.getScene().size(); b++) {
                Scene scene = act.getScene().get(b);
                for(int c = 0; c < scene.getScreen().size(); c++) {
                    Screen screen = scene.getScreen().get(c);
                    // Assets are not a part in the new game spec. I'm not sure what replaces assets in screen
                    for(int d = 0; d < screen.getAssets().size(); d++) {
                        Asset asset = screen.getAssets().get(d);
                        Asset newAsset = null;
                        if("ImageAsset".equals(asset.getType())) {
                            newAsset = new ImageAsset(asset);
                        } else if ("ButtonAsset".equals(asset.getType())) {
                            newAsset = new ButtonAsset(asset);
                        } else if ("CharacterAsset".equals(asset.getType())) {
                            newAsset = new CharacterAsset(asset);
                        } else if ("InformationBoxAsset".equals(asset.getType())) {
                            newAsset = new InformationBoxAsset(asset);
                        } else if ("ConversationBubbleAsset".equals(asset.getType())) {
                            newAsset = new ConversationBubbleAsset(asset);
                        } else if ("ThoughtBubbleAsset".equals(asset.getType())) {
                            newAsset = new ThoughtBubbleAsset(asset);
                        }

                        if(newAsset != null) {
                            screen.getAssets().set(d, newAsset);
                        }
                        if(newAsset.getBehaviors() != null) {
                            for(int e = 0; e < newAsset.getBehaviors().size(); e++) {
                                Behavior behavior = newAsset.getBehaviors().get(e);
                                Behavior newBehavior = null;
                                if(BehaviorType.TRANSITION_BEHAVIOR == behavior.getBehaviorType()) {
                                    newBehavior = new TransitionBehavior(behavior);
                                } else if (BehaviorType.END_GAME_BEHAVIOR == behavior.getBehaviorType()) {
                                    newBehavior = new EndGameBehavior(behavior);
                                }
                                if(newBehavior != null) {
                                    newAsset.getBehaviors().set(e, newBehavior);
                                }
                            }
                        }
                    }
                }
            }
        }

    }*/

    /**
     * Creates an Act object from a list of ScreenNode
     * Sets learning objective from the scene
     * @param sceneNode a list of Scene
     * @return an Act object containing all ScreenNodes from the list
     */
    private Act createActFromScenes(List<Scene> sceneNodes) {
        Act act = new Act();
        for(int i = 0; i < sceneNodes.size(); i++) {
            act.getScene().add(sceneNodes.get(i));
        }
        act.setLearningObjective(act.getScene().get(0).getLearningObjective());
        return act;
    }
    
    /**
     * Creates a Scene object from a list of Screens, Backgrounds, and music.
     * Takes the learning objective from the screens.
     * @param screenNodes a list of Screen
     * @param background a BackgroundType
     * @param music a MusicType
     * @return
     */
    private Scene createSceneFromScreens(List<Screen> screenNodes, BackgroundType background, 
    		MusicType music) {
    	Scene scene = new Scene();
    	for(int i = 0; i < screenNodes.size(); i++) {
    		scene.getScreen().add(screenNodes.get(i));
    	}
    	scene.setBackground(background);
    	scene.setMusic(music);
    	scene.setLearningObjective(scene.getScreen().get(0).getLearningObjective());
    	
    	return scene;
    }
    
    /**
     * Creates a Screen object from a list of Challenges, GameElements, and LearningObjectives
     * @param challengeNodes a list of Challenge
     * @param elementNodes a list of GameElement
     * @param objective a LearningObjectiveType
     * @return a Screen object containing all Challenges from the list
     */
    
    private Screen createScreenFromChallenges(List<Challenge> challengeNodes, 
    		List<GameElementType> elementNodes, LearningObjectiveType objective) {
    	Screen screen = new Screen();
    	for(int i = 0; i < challengeNodes.size(); i++) {
    		screen.getChallenge().add(challengeNodes.get(i));
    	}
    	for(int i = 0; i < elementNodes.size(); i++) {
    		screen.getGameElement().add(elementNodes.get(i));
    	}
    	screen.setLearningObjective(objective);
    	
    	return screen;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

	public Game createGame() {
		// TODO Auto-generated method stub
		Game game = new Game();
		return game;
	}
	

	
}

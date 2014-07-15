package edu.utdallas.gamegeneratorcollection.GameOutput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.utdallas.gamegeneratorcollection.GameComposition.Characters;
import edu.utdallas.gamegeneratorcollection.GameComposition.Locale;
import edu.utdallas.gamegeneratorcollection.GameComposition.Theme;
import edu.utdallas.gamespecification.Act;
import edu.utdallas.gamespecification.BackgroundType;
import edu.utdallas.gamespecification.Behavior;
import edu.utdallas.gamespecification.Challenge;
import edu.utdallas.gamespecification.Character;
import edu.utdallas.gamespecification.Decoration;
import edu.utdallas.gamespecification.EducationInteraction;
import edu.utdallas.gamespecification.Game;
import edu.utdallas.gamespecification.GameElementType;
import edu.utdallas.gamespecification.GenericInteraction;
import edu.utdallas.gamespecification.LearningObjectiveType;
import edu.utdallas.gamespecification.MusicType;
import edu.utdallas.gamespecification.NonPlayer;
import edu.utdallas.gamespecification.Player;
import edu.utdallas.gamespecification.Profile;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:52 PM
 */
public class Structure {
    private Theme theme;
    private Locale locale;
    private Characters characters;
    static int NUMCHARACTERS = 4;
   

	private List<Act> acts;
    private Game game;

    /**
     * Builds the game from the Acts, Characters, and LearningObjectives
     * @param objectives a list of LearningObjectiveType
     * @param characters a list of Character
     * @param acts a list of Act
     * @return a Game object representing the created game
     */
    public Game createGame(/*List<LearningObjectiveType> objectives, List<Character> characters,
    		List<Act> acts*/) {
        acts = new ArrayList<Act>();
        acts.add(createActFromScreens(theme.getIntro()));
        for(int i = 0; i < locale.getLearningActs().size(); i++) {
            acts.add(locale.getAct(i));
        }
        acts.add(createActFromScreens(theme.getOutro()));
        game = new Game();
        //game.getCharacter().add(theme.getCharacters());
        
        //System.out.println("Size of acts is: " + acts.size());
        

        wireUpActs(acts);
        game.getCharacter().addAll(ConvertCharacters(characters));
        game.getAct().addAll(acts);	//Should get adjusted to not use setAct, since that needs removal.
        
        convertAssetsAndBehaviors();

        return game; /*
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
    	
    	return game;*/
    }

    private Collection<? extends Character> ConvertCharacters(
			Characters chars) {
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
     * It will search for transition behaviors with a null transitionId and set it to
     * the next act's first scene's first screen's id
     * Currently the choice seems to be between Transition and Sequence;
     * We have defaulted to setting a transition only. Also, Transitions
     * are currently string values. When this is updated to reflect a proper
     * transition class, the transitions will need to be set properly.
     * @param acts a list of Acts
     */
    
    private void wireUpActs(List<Act> acts) {
        for(int i = 0; i < acts.size() - 1; i++) {
            Act act = acts.get(i);
            // Commenting out this line as new spec does not have Id field for Screen
            //UUID nextActId = acts.get(i+1).getScene().get(0).getScreen().get(0).getId();
            for (int j = 0; j < act.getScene().size() - 1; j++) {
            	Scene scene = act.getScene().get(j); 
            	for (int k = 0; k < scene.getScreen().size() - 1; k++) {
            		Screen screen = scene.getScreen().get(k);
            		if (screen.getTransition() == null) {
            			act.getScene().get(j).getScreen().get(k).setTransition("Straight Cut"); 
            			
            	}
            	
            }
            /*
            for(Scene scene : act.getScene()) {
                Screen screenNode = scene.getScreen().get(0);
             
                // Commenting out this line as new spec does not have Asset field for Screen
                // Need to set transition here I guess - Prabha

                //if(screenNode.getAssets() != null) {
                if (screenNode.getGameElement() != null) {
                    //for(Asset asset : screenNode.getAssets()) {
                	for(GameElementType element: screenNode.getGameElement()) {
                        //if(element.getBehaviors() != null) {
                		if (element.getAnimationEffect() != null)
                            for(Behavior behavior : asset.getBehaviors()) {
                               if(behavior != null &&
                                        BehaviorType.TRANSITION_BEHAVIOR.equals(behavior.getBehaviorType()) &&
                                        behavior.getTransitionId() == null) {
                                    behavior.setTransitionId(nextActId);
                                }
                            }
                        }
                    }
                }*/
            }
        }
    }
    

    /**
     * Converts every asset into it a new object of the correct type
     */
    
    private void convertAssetsAndBehaviors() {
        for(int a = 0; a < game.getAct().size(); a++) {
            Act act = game.getAct().get(a);
            for(int b = 0; b < act.getScene().size(); b++) {
                Scene scene = act.getScene().get(b);
                for(int c = 0; c < scene.getScreen().size(); c++) {
                    Screen screen = scene.getScreen().get(c);
                    // Assets are not a part in the new game spec. I'm not sure what replaces assets in screen
                    //for(int d = 0; d < screen.getAssets().size(); d++) {
                    for(int d = 0; d < screen.getGameElement().size(); d++) {
                        GameElementType element = screen.getGameElement().get(d);
                        GameElementType newElement = null;
                        System.out.println("Element is: " + element.getClass().getName());
                        if(element.getClass().getName() == "GenericInteraction") {
                        	newElement = new GenericInteraction();
                        }
                        if(element.getClass().getName() == "Decoration") {
                        	newElement = new Decoration();
                        }
                        if(element.getClass().getName() == "EducationInteraction") {
                        	newElement = new EducationInteraction();
                        }
                        if(element.getClass().getName() == "Player") {
                        	newElement = new Player();
                        }
                        if(element.getClass().getName() == "NonPlayer") {
                        	newElement = new NonPlayer();
                        }
                        
                        //newElement.setAnimationEffect(element.getAnimationEffect());
                    	//newElement.setSoundEffect(element.getSoundEffect());
                        /*
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
                        }*/
                        /*
                        if(newAsset != null) {
                            screen.getAssets().set(d, newAsset);
                        }*/
                        
                        /*if(newAsset.getBehaviors() != null) {
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
                        }*/
                    }
                }
            }
        }

    }

    
    /**
     * Creates an Act object from a list of ScreenNode
     * Sets learning objective from the scene
     * @param sceneNode a list of Scene
     * @return an Act object containing all ScreenNodes from the list
     */
    private Act createActFromScreens(Scene screenNodes) {
        Act act = new Act();
        /*
        List<Scene> scenes = new ArrayList<Scene>();
        for(int i = 0; i < screenNodes.size(); i++) {
            Scene scene = new Scene();
            scene.setScreen(screenNodes.subList(i,i+1));
           // scene.setBackground(screenNodes.get(i).getBackground());
            scenes.add(scene);
        }*/
        act.getScene().add(screenNodes);
        return act;
    }
    /*
    private Act createActFromScenes(List<Scene> sceneNodes) {
        Act act = new Act();
        for(int i = 0; i < sceneNodes.size(); i++) {
            act.getScene().add(sceneNodes.get(i));
        }
        act.setLearningObjective(act.getScene().get(0).getLearningObjective());
        return act;
    }*/
    
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
    	screen.getLearningObjective().add(objective);
    	
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

    public Characters getCharacters() {
		return characters;
	}

	public void setCharacters(Characters characters) {
		this.characters = characters;
	}
    /*
	public Game createGame() {
		// TODO Auto-generated method stub
		Game game = new Game();
		return game;
	}*/
	

	
}

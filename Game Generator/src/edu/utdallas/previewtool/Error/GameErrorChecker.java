package edu.utdallas.previewtool.Error;

import java.util.List;

import edu.utdallas.previewtool.Error.PreviewError.Level;
import edu.utdallas.previewtool.Error.PreviewError.Severity;
import edu.utdallas.gamespecification.Act;
import edu.utdallas.gamespecification.Challenge;
import edu.utdallas.gamespecification.Character;
import edu.utdallas.gamespecification.Game;
import edu.utdallas.gamespecification.Introduction;
import edu.utdallas.gamespecification.Item;
import edu.utdallas.gamespecification.MultipleChoiceItem;
import edu.utdallas.gamespecification.Option;
import edu.utdallas.gamespecification.QuizChallenge;
import edu.utdallas.gamespecification.Scene;
import edu.utdallas.gamespecification.Screen;
import edu.utdallas.gamespecification.Stem;
import edu.utdallas.gamespecification.Summary;
import edu.utdallas.gamespecification.Profile;
/**
* The Class GameErrorChecker.
*/
public final class GameErrorChecker {
// Check entire Game hierarchy for errors and return a list of errors
    static GameErrorList errors = new GameErrorList();
/**
* Instantiates a new game error checker.
*/
private GameErrorChecker() {

}
/**
* Check errors.
*
* @param game the game
* @param panelWidth the panel width
* @param panelHeight the panel height
* @return the game error list
*/
public static GameErrorList checkErrors(final Game game, final int panelWidth, final int panelHeight) {
//don't save if no game file open

//Check for Game-level errors
      if (game == null) {
       errors.add(new PreviewError(Level.GAME, Severity.HIGH,
                                        "No <Game> detected in XML file") {
          public void fixError() { } });  // can't fix this
       } else { // Check for global character errors
        if (game.getCharacter() == null || game.getCharacter().size() == 0) {
         errors.add(new PreviewError(
         Level.GAME, Severity.HIGH, "No <Characters> detected in Game") {
         public void fixError() { }
         });
        } else {
        List<Character> characters = game.getCharacter();
        for (int i = 0; i < characters.size(); i++) {
         // Need a way to refer to which Character has an error
         String cName = characters.get(i).getName();

         if (isNullOrEmpty(cName)) {
          errors.add(new PreviewError(Level.GAME, Severity.LOW,
          "The <Name> property of Character found in position " + (i + 1)
          + " is not specified") {
           public void fixError() { }
           });
         cName = "[in position " + (i + 1) + "]";
         }

         if (isNullOrEmpty(characters.get(i).getAutonomousBehaviour())) {
         errors.add(new PreviewError(Level.GAME, Severity.LOW,
         "The <Behavior> property of Character " + cName + "is not specified") {
           public void fixError() { }
          });
         }

         if (characters.get(i).getProfile() == null) {
         errors.add(new PreviewError(Level.GAME, Severity.LOW,
         "The <Profile> property of Character " + cName + " is not specified") {
          public void fixError() { }
          });
         } else {
           checkProfileProperties(characters.get(i));
           }
        }
       }
// Check for Act-level errors
//Acts wrapper check needed, or if this is not possible
//may need to change error to be more general
         checkActProperties(game);
         }
  return errors;
 }



public static void checkProfileProperties(Character character) {

        Profile profile = character.getProfile();
        String cName = character.getName();

if (profile.getDemographics() == null || profile.getDemographics().size() == 0)
        {
         errors.add(new PreviewError(Level.GAME, Severity.LOW,
         "The <Demographics> property of Character " + cName
         + " profile is missing or empty") {
         public void fixError() { }
         });
        }

        if (profile.getDegrees() == null || profile.getDegrees().size() == 0) {
         errors.add(new PreviewError(Level.GAME, Severity.LOW,
         "The <Degrees> property of Character " + cName
         + " profile is missing or empty") {
          public void fixError() { }
          });
         }

        if (profile.getSkills() == null || profile.getSkills().size() == 0) {
         errors.add(new PreviewError(Level.GAME, Severity.LOW,
         "The <Skills> property of Character " + cName
         + " profile is missing or empty") {
          public void fixError() { }
          });
         }

        if (isNullOrEmpty(profile.getTitle())) {
         errors.add(new PreviewError(Level.GAME, Severity.LOW,
"The <Title> property of Character " + cName + " profile is missing or empty") {
          public void fixError() { }
          });
        }

         if (profile.getYearsOfExperience() == 0) {
errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <YearsOfExperience> "
         + "property of Character " + cName + " profile is missing or empty") {
         public void fixError() { }
         });
        }
 }

private static void checkActProperties(Game game) {
    // TODO Auto-generated method stub
    if (game.getAct() == null || game.getAct().size() == 0) {
        errors.add(new PreviewError(
        Level.ACT, Severity.HIGH, "No <Acts> detected in Game") {
         public void fixError() { } //TODO
         });
         } else {
           List<Act> acts = game.getAct();

           //Check for Scene-level errors
           //Scenes wrapper check needed, or if this is not
           //possible may need to change error to be more general
           checkSceneProperties(acts);
           }
}

private static void checkSceneProperties(List<Act> acts) {
    // TODO Auto-generated method stub
    for (int i = 0; i < acts.size(); i++) {
        // Need a way to refer to which Act has an error
        String aName = "Act [in position " + (i + 1) + "]";

    List<Scene> scenes = acts.get(i).getScene();
    if (scenes == null || scenes.size() == 0) {
    errors.add(new PreviewError(Level.SCENE, Severity.HIGH,
    "No <Scenes> detected for act in position " + aName) {
      public void fixError() { } //TODO
      });
     } else {
       for (int j = 0; j < scenes.size(); j++) {
       // Need a way to refer to which Scene has an error
       String sName = aName + " " + "Scene [position " + (j + 1) + "]";
       if (scenes.get(j).getBackground() == null) {
        errors.add(new PreviewError(Level.SCENE, Severity.LOW,
        "The <Background> property of " + sName + " is not specified") {
         public void fixError() { } //
         });
       }
//should we warn about no background audio?
//Ryan 4/8 10AM "Don't think so,
//Longstreet never specified that background music is mandatory"
//Check for Screen-level errors
       checkScreenProperties(sName, scenes.get(j));
   }
  }
 }
}

private static void checkScreenProperties(String sName, Scene scene) {
    // TODO Auto-generated method stub
    List<Screen> screens = scene.getScreen();
    if (screens == null || screens.size() == 0) {
    errors.add(new PreviewError(Level.SCREEN, Severity.HIGH,
    "No <Screens> detected for " + sName) {
     public void fixError() { } //TODO
     });
    } else {
      for (int k = 0; k < screens.size(); k++) {
      //Need a way to refer to which Screen has an error
      String srName = sName + " " + "Screen [position " + (k + 1) + "]";

//check challenges
//Challenge checking
//Need a way to check if their are two Challenges in a screen. First thought was
//getting a list and checking if size was> 1 but the Screen class doesn't return
//Challenges as lists cause it assumes their is only one.
    List<Challenge> challenges = screens.get(k).getChallenge();
    for (int l = 0; l < challenges.size(); l++) {
     Challenge challenge = challenges.get(l);
     if (challenge != null) {
      if (challenge instanceof QuizChallenge) {
       QuizChallenge qChallenge = (QuizChallenge) challenge;
       if (qChallenge.getLayout() == null) {
        errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
        "Layout in Challenge in " + srName + " is missing or empty") {
          public void fixError() { } //TODO
         });
       }

       Item item = qChallenge.getItem();
       //Catches C_13 & C_14
       if (item == null) {
        errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
        "There are no Questions in the Challenge in " + srName) {
         public void fixError() { } //TODO
         });
        } else {
          for (int u = 0; u < 1; u++) {
          if (item instanceof MultipleChoiceItem) {
           MultipleChoiceItem mcItem = (MultipleChoiceItem) item;
 //Changing this to get the first option only.This will need changing--Sean
           Option option = mcItem.getOption().get(0);
           if (option == null) {
            errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "There "
 + "are no answers to choose from in Question " + (u + 1) + " in " + srName) {
             public void fixError() { } //TODO
             });
            } else {
              if (option == null || isNullOrEmpty(option.getHint().getHint())) {
               errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
               "Option in Challenge Question in " + srName + " has no hint") {
                public void fixError() { } //TODO
                });
               }
              }
 /*Editing this out, since there's only one option right now.
 if(options.size() == 1)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "Only one response in Challenge Question in " + srName) {
 public void fixError() { } //TODO
 });
 }
 else if(options.size() > 6)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "More than 6 responses in Challenge Question in " + srName) {
 public void fixError() { } //TODO
 });
 }
 */
           Stem stem = mcItem.getStem();
           if (stem == null) {
            errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
            "No STEM Collection in Challenge in " + srName) {
             public void fixError() { } //TODO
             });
            } else {
              if (stem.getStemQuestion() == null) {
              errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
              "No STEM question in Challenge in " + srName) {
               public void fixError() { } //TODO
               });
 } else if (isNullOrEmpty(stem.getStemQuestion().getHint().getHint())) {
                errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "STEM Question with empty Text field in Challenge in " + srName) {
                 public void fixError() { } //TODO
                 });
                }
                if (stem.getStemText() == null) {
                 errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
                 "No STEM text in Challenge in " + srName) {
                  public void fixError() { } //TODO
                  });
 } else if (isNullOrEmpty(stem.getStemText().getHint().getHint())) {
                  errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
                  "STEM Text with empty Text field in Challenge in " + srName) {
                    public void fixError() { } //TODO
                    });
                   }
                  }
                 } else { System.out.println(); }
           }
          }
 //Only one summary for a Quiz Challenge.
 //So replacing List with a single Summary object
          Summary summary = qChallenge.getSummary();
          if (summary == null) {
           errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
           "No Summaries in Challenge in " + srName) {
            public void fixError() { } //TODO
           });
          }
          Introduction intro = qChallenge.getIntroduction();
          if (intro == null) {
           errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM,
           "No Introduction in Challenge in " + srName) {
            public void fixError() { } //TODO
            });
           }
      } else { //TODO May need enum for Level.CHALLENGE?
 errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "The xsi:type "
 + "isn't specified for the challenge of Screen found in position " + (k + 1)) {
         public void fixError() { } //TODO
         });
        }
       } else {
 //TODO At this point the screen could either have no challenge at all or a null
 //challenge tag correct?
 //One needing an error, the other being a normal occurrence
 //Any ways to distinguish
          System.out.println();
         }
        }
 //Check for Asset-level errors
 /*
 final List<Asset> assets = screens.get(k).getAssets();
 if(assets == null || assets.size() == 0)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "No <Assets> detected for " + srName) {
 public void fixError() { } //TODO
 });
 }
 else
 {
 for(int m = 0; m < assets.size(); m++)
 {
 final Asset asset = assets.get(m);

 //Need a way to refer to which Asset has an error
 String asName = srName + " " + "Asset [in position " + (m+1) + "]";

 if(asset.getWidth() <= 0)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "The <Width> property of " + asName + " is zero or not specified") {
 public void fixError() { asset.setWidth(100); } //TODO
 });
 }
 if(asset.getHeight() <= 0)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "The <Height> property of " + asName +
 " is zero or not specified") {
 public void fixError() { asset.setHeight(100); } //TODO
 });
 }

 if(asset.getX() > panelWidth)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 asName + " is not visible, right of the coordinate system") {
 public void fixError() { asset.setX(panelWidth - 100); }
 //TODO
 });
 }
 if(asset.getX() + asset.getWidth() <= 0)// too far left
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 asName + " is not visible, left of the coordinate system") {
 public void fixError() { asset.setX(100); } //TODO
 });
 }
 if(asset.getY() > panelHeight)// too far down
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 asName + " is not visible, below the coordinate system") {
 public void fixError() { asset.setY(panelHeight - 100);
 }//TODO
 });
 }
 if(asset.getY() + asset.getHeight() <= 0)// too far up
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 asName + " is not visible, above the coordinate system") {
 public void fixError() { asset.setY(100); } //TODO
 });
 }

 if(asset instanceof CharacterAsset || asset instanceof ImageAsset)
 {
 if(isNullOrEmpty(asset.getDisplayImage()))
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "The <DisplayImage> property of " + asName + "
 is not specified") {
 public void fixError() {
 asset.setDisplayImage("error_button.png"); }
 });
 }
 else if(asset instanceof CharacterAsset) {
 String baseDir = "Office, Classroom\\Characters\\";
 try{
 BufferedImage image = ImageIO.read(
 new File(baseDir + asset.getDisplayImage()));
 int width = image.getWidth();
 final double desiredWidth = asset.getWidth();
 double scaleFactor = desiredWidth / width;
 BufferedImage scaledImage ;
 scaledImage = ImageHelper.getScaledImage(
 image, scaleFactor);
 } catch (IOException ex) {
 errors.add(new PreviewError( Level.SCREEN, Severity.MEDIUM, "The <DisplayImage>
 property of " + asName + " is missing from the repository") {
 public void fixError() { asset.setDisplayImage("error_button.png"); }
 });
 }
 } else if(assets.get(m) instanceof ImageAsset) {
 String baseDir = "Office, Classroom\\";
 try{
 BufferedImage image = ImageIO.read(new File(
 baseDir + assets.get(m).getDisplayImage()));
 int width = image.getWidth();
 final double desiredWidth = asset.getWidth();
 double scaleFactor = desiredWidth / width;
 BufferedImage scaledImage = ImageHelper.getScaledImage(
 image, scaleFactor);
 } catch (IOException ex) {
 errors.add(new PreviewError( Level.SCREEN, Severity.MEDIUM,
 "The <DisplayImage> property of " + asName
 + " is missing from the repository") {
 public void fixError() {} //TODO this fix error is below
 });
 asset.setDisplayImage("error_button.png");
 }
 }
 }
 if(asset instanceof ButtonAsset || asset instanceof ThoughtBubbleAsset
 || asset instanceof ConversationBubbleAsset
 || asset instanceof InformationBoxAsset)
 {
 if(isNullOrEmpty(asset.getName()))
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW, asName
 + " contains no text, will be filled with null") {
 public void fixError() { asset.setName("null"); }
 });
 }
 if(asset instanceof ThoughtBubbleAsset)
 {
 ThoughtBubbleAsset tbAsset = (ThoughtBubbleAsset)asset;
 if(tbAsset.getPointDirection() == null)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "ThoughtBubble in " + asName + " requires a PointDirection field") {
 public void fixError() { } //TODO
 });
 }
 }
 if(asset instanceof ConversationBubbleAsset)
 {
 ConversationBubbleAsset cbAsset = (ConversationBubbleAsset)asset;
 if(cbAsset.getPointDirection() == null)
 {
 errors.add(new PreviewError(Level.SCREEN, Severity.LOW,
 "ConversationBubble in " + asName + " requires a PointDirection field") {
 public void fixError() { } //TODO
 });
 }
 }
 }
 }
 }*/
      }
     }
}
/**
* Checks if is null or empty.
*
* @param s the s
* @return true, if is null or empty
*/
private static boolean isNullOrEmpty(final String s) {
return s == null || s.equals("");
}


}


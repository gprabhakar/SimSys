package edu.utdallas.gamePlayEngine.view;

import edu.utdallas.gamePlayEngine.menuFrame;
import edu.utdallas.gamePlayEngine.controller.GameController;
import edu.utdallas.gamePlayEngine.controller.GameState;
import edu.utdallas.gamePlayEngine.controller.Message;
import edu.utdallas.gamePlayEngine.model.Act;
import edu.utdallas.gamePlayEngine.model.Quiz;
import edu.utdallas.gamePlayEngine.model.ChallengeStructure;
import edu.utdallas.gamePlayEngine.model.GameElement;
import edu.utdallas.gamePlayEngine.model.StemDescription;
import edu.utdallas.gamePlayEngine.model.StemOption;
import edu.utdallas.gamePlayEngine.model.StemQuestion;
import edu.utdallas.gamePlayEngine.model.Backdrop;
import edu.utdallas.gamePlayEngine.model.If;
import edu.utdallas.gamePlayEngine.model.Prop;
import edu.utdallas.gamePlayEngine.model.Time;
import edu.utdallas.gamePlayEngine.model.Reward;
import edu.utdallas.gamePlayEngine.model.GameModel;
import edu.utdallas.gamePlayEngine.model.Scene;
import edu.utdallas.gamePlayEngine.model.Screen;
import edu.utdallas.gamePlayEngine.model.GameElementTimer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

public class GameView implements Observer {
	int count;
	GameViewFrame gameViewFrame;
	private String displayInfo;
	public static final String Button1 = "Button1";
	public static final String Button2 = "Button2";
	public static final String Button = "Button";
	public static final String Image = "Image";
	public static final String png = "png";
	public static final String jpg = "jpg";
	public int noCount = 0;
	private List<Timer> timerList; // Zac ZHANG: Add for removing all timers

	public GameView() {
		timerList = new ArrayList<Timer>();
		gameViewFrame = new GameViewFrame(timerList);
	}

	public void resetView() {
		// Zac ZHANG added: stop all timers
		for (int i = 0; i < timerList.size(); i++)
		{
			timerList.get(i).stop();
		}
		
		this.count = 0;
		gameViewFrame.setController(null);
		this.timerList = new ArrayList<Timer>();
		this.noCount = 0;
	}
	
	//@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof GameState) { // Zac ZHANG added to run gameEnd function successfully
			count++;
			GameState gameState = (GameState) arg1;
			Message msg = gameState.getMessage();
			System.out.println("Gameview update message is: " + msg);
	
			if (GameModel.class.isInstance(arg0)) {
				if (msg == Message.Start) {
					// Do something for GameModel Start
					// TODO Find why it is called in the second time.
					System.out.println("View: Message GameModelStartComplete sent");
					gameState.setMessage(Message.StartComplete);
					GameController.viewListener(arg0, gameState);
				}
				else if (msg == Message.Play) {
					// Do something for GameModel Play
					System.out.println("View: Message GameModelPlayComplete sent");
					gameViewFrame.viewStartAct(gameState.getMenuFrame());
					gameState.setMessage(Message.PlayComplete);
					GameController.viewListener(arg0, gameState);
				}
			}
	
			else if (Act.class.isInstance(arg0)) {
				if (msg == Message.Start) {
					// Do something for Act Start.
	
					System.out.println("View: Message ActStartComplete sent");
					gameState.setMessage(Message.StartComplete);
					GameController.viewListener(arg0, gameState);
	
				}
				if (msg == Message.Play) {
					// Do something for Act Play.
					System.out.println("View: Message ActPlayComplete sent");
					gameState.setMessage(Message.PlayComplete);
					GameController.viewListener(arg0, gameState);
	
				}
				if (msg == Message.End) {
					// Do something for Act Play.
					System.out.println("View: Message ActEndComplete sent");
					gameState.setMessage(Message.EndComplete);
					GameController.viewListener(arg0, gameState);
	
				}
			}
	
			else if (Scene.class.isInstance(arg0)) {
				if (msg == Message.Start) {
					System.out.println("View: Message SceneStartComplete sent");
					String backDrop = ((Scene) (arg0)).getBackdrop();
					if (backDrop != null)
						gameViewFrame.setBackgroundImage(backDrop);
					gameState.setMessage(Message.StartComplete);
					GameController.viewListener(arg0, gameState);
				} else if (msg == Message.Play) {
					System.out.println("View: Message ScenePlayComplete sent");
					gameState.setMessage(Message.PlayComplete);
					GameController.viewListener(arg0, gameState);
				} else if (msg == Message.End) {
					System.out.println("View: Message SceneEndComplete sent");
					gameState.setMessage(Message.EndComplete);
					GameController.viewListener(arg0, gameState);
				}
			}
	
			else if (Screen.class.isInstance(arg0)) {
				if (msg == Message.Start) {
					System.out.println("View: Message ScreenStartComplete sent");
					gameState.setMessage(Message.StartComplete);
					GameController.viewListener(arg0, gameState);
				} else if (msg == Message.Play) {
					System.out.println("View: Message ScreenPlayComplete sent");
					gameState.setMessage(Message.PlayComplete);
					GameController.viewListener(arg0, gameState);
				} else if (msg == Message.End) {
					System.out.println("View: Message ScreenEndComplete sent");
					gameState.setMessage(Message.EndComplete);
					GameController.viewListener(arg0, gameState);
				}
			}

			else if (GameElement.class.isInstance(arg0)) {// Zac ZHANG: added "else"
				if (msg == Message.Start) {
					System.out
							.println("View: Message GameElementStartComplete sent");
					gameState.setMessage(Message.StartComplete);
					GameController.viewListener(arg0, gameState);
				} else if (msg == Message.Play) {
					System.out
							.println("View: Message GameElementPlayComplete sent");
					gameState.setMessage(Message.PlayComplete);
	
					if (Backdrop.class.isInstance(arg0)) {
						Backdrop bd = (Backdrop) (gameState.getGameElement());
					}
	
					if (Prop.class.isInstance(arg0)) {
						Prop currentProp = (Prop) (gameState.getGameElement());
						System.out.println("Current prop is instance of Prop");
						ChallengeStructure challengeStructure = currentProp
								.getChallengeStructure();
						
						if (challengeStructure != null) {
							//Prop prop = (Prop) challengeStructure.getGameElements()
								//	.get(0);
							Quiz quiz = challengeStructure.getQuiz();
							
							Prop prop = (Prop) quiz.getGameElements().get(0);
							StemDescription stemDescription = prop
									.getStemDescription();
							
							Prop descProp = new Prop();
							descProp.setType(stemDescription.getType());
							descProp.setColor(stemDescription.getColor());
							descProp.setSize(stemDescription.getSize());
							descProp.setText(stemDescription.getText());
							descProp.setTextSize("8");
							descProp.setLocation(stemDescription.getLocation());
							System.out.println("Stem description is"
									+ stemDescription.getType().getTypeName());
							// TODO Display elements according to the type.
							//if(type == informationbox)
							gameViewFrame.addinformationBox(descProp,
									gameState);
							
							StemQuestion stemQuestion = prop.getStemQuestion();
							Prop questionProp = new Prop();
							questionProp.setType(stemQuestion.getType());
							questionProp.setColor(stemQuestion.getColor());
							questionProp.setSize(stemQuestion.getSize());
							questionProp.setText(stemQuestion.getText());
							questionProp.setLocation(stemQuestion.getLocation());
							System.out.println("Stem Question is"
									+ stemQuestion.getType().getTypeName());
							gameViewFrame.addinformationBox(questionProp,
									gameState);
							
							
							//Need to display options.
							List<StemOption> stemOptionList = prop.getStemOption();
							for(StemOption stemOption : stemOptionList)
							{
								Prop optionProp = new Prop();
								optionProp.setType(stemOption.getType());
								optionProp.setColor(stemOption.getColor());
								optionProp.setSize(stemOption.getSize());
								optionProp.setText(stemOption.getText());
								optionProp.setLocation(stemOption.getLocation());
								optionProp.setHint(stemOption.getHint());
								System.out.println("Stem Option is"
										+ stemOption.getAnswerValue());
								gameViewFrame.addCheckBox(optionProp,
										gameState,stemOption.getAnswerValue().charAt(0));
							}						
							
							gameState.setMessage(Message.PlayComplete);
							GameController.viewListener(arg0, gameState);
						}
	
						else {
							if (currentProp.getType() != null) {
								if (currentProp.getType().getTypeName().trim()
										.startsWith(Button)) {
									gameViewFrame.addButton(gameState);
								}
								if (currentProp.getType().getTypeName()
										.startsWith("InformationBox")) {
									System.out
											.println("Calling method to add information box!!"
													+ currentProp);
									gameViewFrame.addinformationBox(currentProp,
											gameState);
								}
								
								// Zac ZHANG: Added for ConversationBubble
								if (currentProp.getType().getTypeName()
										.startsWith("ConversationBubble")) {
									System.out
											.println("Calling method to add Conversation Bubble! "
													+ currentProp);
									gameViewFrame.addConversationBubble(currentProp,
											gameState);
								}
								
								if (currentProp.getType().getTypeName()
										.startsWith("FeedbackBox") && noCount == 0) {
									noCount++;
									
									Prop newProp = currentProp;
									List<If> lif = currentProp.getType().getIfs();
									boolean isCorrect = gameState.getIsCorrect();
									
									Reward reward = GameModel.getGameModelObject()
									.getCharacter().getReward();								
									// Set the reward back to the game character.
									
									
									String eval = "Incorrect";
									if(isCorrect){
										eval = "Correct";
									}
									for(If if1 : lif){
										System.out.println(if1.getEvaluation());
										if(if1.getEvaluation().equals(eval)){
											System.out.println("Inside if!!");
											String title = if1.getRewardTitle();
											reward.setTitle(title);
											newProp.setText(if1.getText());
											if(if1.getRewardPointsToAdd() != null){
												System.out.println("Reward points are......" + reward.getPoints());
												reward.addPoints(Integer.parseInt(if1.getRewardPointsToAdd()));
											}
											GameModel.getGameModelObject().getCharacter()
											.setReward(reward);
											break;
										}
									}		
									System.out.println("Calling feedback box!!!");
									gameViewFrame.addinformationBox(newProp, gameState);
									System.out.println("Return from feedback box!!!");
								}
								
								if (currentProp.getType().getTypeName().trim()
										.startsWith(Button)) {
									gameViewFrame.addButton(gameState);								
								}
								
								if ((currentProp.getType().getTypeName().trim())
										.endsWith(png)) {
									 Prop prop = (Prop) gameState.getGameElement();
									 gameViewFrame.addImage(gameState);
									System.out.println("Displaying Image.");
								}
							}
						}
					}
					gameState.setMessage(Message.PlayComplete);
					GameController.viewListener(arg0, gameState);
				} else if (msg == Message.End) {
	
					/**
					 * Write code for removing the game elements in the current
					 * screen.
					 * 
					 */
	
					// Read timer from xml and set it.
					// Iterate through the set of events. and do according to the
					// event.
	
					// If the xml contains challenge structure we need to handle it
					// separately.
	
					Prop currentProp = (Prop) gameState.getGameElement();
					try{ // Zac ZHANG: added "try" part to make sure programming still running
						ChallengeStructure challengeStructure = currentProp
							.getChallengeStructure();
					
						if (challengeStructure != null) {
		
							// Displayed all the stem description stem questions and
							// stem option.
							gameState.setMessage(Message.EndComplete);
							GameController.viewListener(arg0,gameState);
							
						} else {
							try { // Zac ZHANG: added "try" part to make sure programming still running
								Time time = currentProp.getType().getEvents().get(0)
										.getTime();
								System.out.println("Time is: " + time.name());
								
								// Read the value for the time found above.
								List<edu.utdallas.gamePlayEngine.model.Timer> timers = GameModel
										.getGameModelObject().getGameConstants()
										.getTimers();
								int timerValue = 0;
								for (edu.utdallas.gamePlayEngine.model.Timer timer : timers) {
									System.out
											.println("Timer name is..." + timer.getName());
									if (timer.getName() != null) {
										if (timer.getName().trim().equals(time.name())) {
											timerValue = Integer.parseInt(timer.getValue());
											System.out.println("Timer value is"
													+ timerValue);
										}
									}
								}
			
								final GameState localGameState = gameState;
								final Observable localArg = arg0;
								Timer timer = new Timer(timerValue, new ActionListener() {
									//@Override
									public void actionPerformed(ActionEvent arg1) {
										System.out.println("Timer expired");
										GameViewFrame.resetLayeredPane();
										System.out
												.println("View: Message GameElementEndComplete sent");
										localGameState.setMessage(Message.EndComplete);
			
										GameController.viewListener(localArg,
												localGameState);
									}
								});
								timer.setRepeats(false);
								timer.start();
								timerList.add(timer);
							}
							catch(Exception e){
								System.out.println("GameView Class: Exception Point 1: "
										+ e.getMessage());
								final GameState localGameState = gameState;
								final Observable localArg = arg0;
								localGameState.setMessage(Message.EndComplete);
								GameController.viewListener(localArg,
										localGameState);
							}
						}
					}catch(Exception e){
						System.out.println("GameView Class: Exception Point 2: "
								+ e.getMessage());
						final GameState localGameState = gameState;
						final Observable localArg = arg0;
						localGameState.setMessage(Message.EndComplete);
						GameController.viewListener(localArg,
								localGameState);
					}
				}
			}
		}
	}
	
	@Override
	protected void finalize()
	{
		for (int i = 0; i < timerList.size(); i++)// Zac ZHANG: stop all timers
		{
			timerList.get(i).stop();
		}
		
		this.count = 0;
		this.gameViewFrame = null;
		this.displayInfo = null;
		this.noCount = 0;
		this.timerList = null;
	}
}
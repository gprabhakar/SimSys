
//Ryan Naugle and David Savoia
package edu.utdallas.gamewizard;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.tree.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;










import edu.utdallas.gamespecification.*;
import edu.utdallas.gamegeneratorcollection.*;
import edu.utdallas.gamegeneratorcollection.ComponentSelection.GameGenerator;







/*
 import javax.xml.bind.JAXBContext;
 import javax.xml.bind.JAXBException;
 import javax.xml.bind.Marshaller;
 import edu.utdallas.gamegenerator.RepoUpdate.Updates;
 import edu.utdallas.sharedfiles.Challenge.Challenge;
 import edu.utdallas.sharedfiles.Challenge.Introduction;
 import edu.utdallas.sharedfiles.Challenge.Item;
 import edu.utdallas.sharedfiles.Challenge.Layout;
 import edu.utdallas.sharedfiles.Challenge.MultipleChoiceItem;
 import edu.utdallas.sharedfiles.Challenge.QuizChallenge;
 import edu.utdallas.sharedfiles.Challenge.Summary;
 import edu.utdallas.sharedfiles.Shared.*;
 import edu.utdallas.sharedfiles.Structure.*;
 import edu.utdallas.previewtool.View.BackgroundSelectWindow;
 import edu.utdallas.previewtool.View.CharacterProfileWindow;
 import edu.utdallas.previewtool.View.CharacterSelectWindow;
 import edu.utdallas.previewtool.View.PropSelectWindow;
 import edu.utdallas.previewtool.View.Scene2Panel;
 import edu.utdallas.previewtool.View.SoundSelectWindow;
 import edu.utdallas.gamegenerator.Character.Character;
 import edu.utdallas.previewtool.Error.PreviewError;
 import edu.utdallas.previewtool.Error.GameErrorChecker;
 import edu.utdallas.previewtool.Error.GameErrorList;
 import Jama.Matrix;
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
public class Wizard implements ActionListener {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 700;

	private JFrame window = new JFrame();
	private JTree wizardTree;
	private JPanel treePanel;
	private WizardPanel mainPanel;

	private final JComboBox<String> institutionBox;
	private JComboBox<String> domainBox;
	private final JComboBox<String> gradeBox;
	private final JComboBox<String> subjectBox;
	private final JComboBox<String> taxBox;
	private final JComboBox<String> backgroundBox;
	private final JComboBox<String> actBox;
	private JComboBox[] firstQuesBox;
	private JComboBox[] secondQuesBox;
	private final String [] institutions = {"Please Choose Your Institution","West Allis Independent",
			"Milwaukee Public Schools","Marquette University","University of Texas-Dallas","Johnson-Controls","IBM"};
	private String [] domains = {"Please Choose Your Domain"};
	private String [] grades = {"Please Choose Your Level"};
	private String [] subjects = {"Please Choose Your Subject"};


	final DefaultTreeModel model;
	final DefaultMutableTreeNode mainRoot;
	final DefaultMutableTreeNode rootNode;
	final DefaultMutableTreeNode learningObjectiveNode;
	final DefaultMutableTreeNode learningTaxonomyNode;
	final DefaultMutableTreeNode subTaxNode;
	final DefaultMutableTreeNode typeOfChallengeNode;
	final DefaultMutableTreeNode conditionsNode;
	final DefaultMutableTreeNode lowerSummaryNode;
	final DefaultMutableTreeNode topNode;
	final DefaultMutableTreeNode characterNode;
	final DefaultMutableTreeNode locationNode;
	final DefaultMutableTreeNode actNode;
	final DefaultMutableTreeNode addActNode;
	final DefaultMutableTreeNode actSumNode;
	final DefaultMutableTreeNode loActNode;
	final DefaultMutableTreeNode quesTableNode;
	final DefaultMutableTreeNode finalSumNode;
	final DefaultMutableTreeNode conclusionNode;
	DefaultMutableTreeNode [] learningObjectives;

	//Initial introduction button
	private final JButton welcomeButton;

	//Controls Text That Prompts User What To Do
	private final JTextArea speechBubble;

	//After User Selects Domain
	private final JButton submitButton;

	//After User Selects Knowledge Area
	private final JButton subLOButton;

	//After User Selects sub-Learning Objectives
	private final JButton submitLOButton;

	//Button for learning objective summary
	private final JButton summaryContinue;

	//If the user has errors in learning objective selection
	private final JButton summaryBack;

	//Learning Taxonomy Button
	private final JButton subTaxContButton;

	//If the user has errors in learning taxonomy selection
	private final JButton taxBackButton;

	//Condition button
	private final JButton conditionContinue;

	//IF the user has errors in the condition selection
	private final JButton conditionErrorBack;

	//Challeng selection button
	private final JButton challengeButton;

	//If the user has errors in challenge selection
	private final JButton challengeErrorBack;

	//Summary button of lower half of selection
	private final JButton fullSumContinue;

	//The next introduction page button
	private final JButton introTwoButton;

	//Learning Taxonomy Continue Button
	private final JButton taxContButton;

	//Button where user selects theme
	private final JButton locationButton;

	//Button where user is notified of common characters for theme
	private final JButton charButton;

	//Shows user initial set of acts 
	private final JButton actButton;

	//Allows user to add acts/questions
	private final JButton addActButton;

	//After user finishes adding acts/questions
	private final JButton contActButton;

	//Allows user to choose what learning objectives go with each act they make
	private final JButton loActButton;

	//User Selects Type of Questions
	private final JButton quesButton;  

	//Shows User Final Summary
	private final JButton finalSumButton;

	//Allows user to save their game
	private final JButton saveButton;


	private JButton[] loButtons;

	private boolean bloomSelected;
	private boolean learningError;

	private JScrollPane bloomsScroll;
	private JScrollPane templateScroll;

	private JTextPane taxPane;
	private JTextPane finalSumPane;
	private JTextPane backgroundPane;
	private JTextPane challengePane;
	private JTextPane summary;
	private JTextPane template;

	int index=0;
	int index2 = 0;

	int progressActs = 0;
	private int numLO = 0;
	int loAct = 1;
	ArrayList<String> loList;
	private String pathName;
	private String backGroundChoice;

	private final Font font = new Font("Comic Sans MS",Font.CENTER_BASELINE,28);
	private final Font font2 = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
	private final Font font3 = new Font("Comic Sans MS",Font.CENTER_BASELINE,18);
	private final Font font4 = new Font("Comic Sans MS",Font.PLAIN,18);

	private GameTemplate gametemplate;

	String LearningObjective;
	JTable table;
	JTable loTable;
	JTable conditionsTable;
	JTable subTaxTable;
	JTable challengeTable;
	JTable charTable;
	JTable loActTable;
	JTable questionTable;

	CharList charList;

	public Wizard()
	{
		window.setSize(WIDTH,HEIGHT);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setTitle("Game Design");
		mainPanel = new WizardPanel("introBackground2.png", 0, 0);
		treePanel = new JPanel();
		mainPanel.setLayout(null);

		//Begin Making Tree
		wizardTree = new JTree();
		mainRoot = new DefaultMutableTreeNode("Start/Introduction");
		model = new DefaultTreeModel(mainRoot);
		wizardTree.setRootVisible(false);
		wizardTree.setModel(model);
		rootNode = new DefaultMutableTreeNode("Start/Introduction");
		learningObjectiveNode = new DefaultMutableTreeNode("Knowledge Areas");
		learningTaxonomyNode = new DefaultMutableTreeNode("Learning Standard");
		subTaxNode = new DefaultMutableTreeNode("Select Taxonomies");
		typeOfChallengeNode = new DefaultMutableTreeNode("Type of Challenge");
		conditionsNode = new DefaultMutableTreeNode("Conditions");
		lowerSummaryNode = new DefaultMutableTreeNode("Summary");
		topNode = new DefaultMutableTreeNode("Game Components");
		locationNode = new DefaultMutableTreeNode("Location");
		characterNode = new DefaultMutableTreeNode("Typical Characters");
		actNode = new DefaultMutableTreeNode("First Acts");
		addActNode = new DefaultMutableTreeNode("Add An Act/Acts");
		actSumNode = new DefaultMutableTreeNode("Act Summary");
		loActNode = new DefaultMutableTreeNode("Specify Acts");
		quesTableNode = new DefaultMutableTreeNode("Question Selection");
		finalSumNode = new DefaultMutableTreeNode("Final Summary");
		conclusionNode = new DefaultMutableTreeNode("Save Game");
		treePanel.add(wizardTree);
		treePanel.setBackground(Color.white);


		//Pane for Summaries
		finalSumPane = new JTextPane();
		taxPane = new JTextPane();
		//Learning Objective Table
		try
		{
			File file = new File("Office, Classroom\\template.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(GameTemplate.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			gametemplate = (GameTemplate) jaxbUnmarshaller.unmarshal(file);
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		wizardTree.addTreeSelectionListener(new TreeSelectionListener()
		{
			@SuppressWarnings("deprecation")
			public void valueChanged(TreeSelectionEvent e)
			{
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) wizardTree.getLastSelectedPathComponent();
				if (selectedNode != null && selectedNode == rootNode)
				{
					mainPanel.removeAll();
					mainPanel.changeCoord(0, 0);
					speechBubble.setFont(font);
					speechBubble.setBounds(400,100,420,120);
					speechBubble.setText("   Who is This Game For?");
					institutionBox.setBounds(625,300,425,50);
					institutionBox.setFont(font2);
					institutionBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					mainPanel.add(institutionBox);
					domainBox.setBounds(625,375,425,50);
					domainBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					domainBox.setFont(font2);
					domainBox.setEnabled(false);
					mainPanel.add(domainBox);
					gradeBox.setBounds(625,450,425,50);
					gradeBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					gradeBox.setFont(font2);
					gradeBox.setEnabled(false);
					mainPanel.add(gradeBox);
					subjectBox.setBounds(625,525,425,50);
					subjectBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					subjectBox.setFont(font2);
					subjectBox.setEnabled(false);
					submitButton.setFont(font2);
					submitButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					mainPanel.add(speechBubble);
					mainPanel.add(subjectBox);
					mainPanel.add(submitButton);
					mainPanel.changeFileName("introBackground2.png");
					mainPanel.updateUI();
				}
				else if (selectedNode != null && selectedNode == learningObjectiveNode )
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, -70);
					speechBubble.setFont(font);
					speechBubble.setBounds(415,45,400,90);
					speechBubble.setText("Please Select Your Knowledge\n Areas and Continue");
					JScrollPane scroll = JTable.createScrollPaneForTable(table);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(100,300,950,350);
					subLOButton.setFont(font2);
					mainPanel.add(scroll);
					mainPanel.add(speechBubble);
					mainPanel.add(submitLOButton);
					mainPanel.updateUI();
				}
				else if(selectedNode !=null && selectedNode == learningTaxonomyNode)
				{
					if(isErrorLearningObjective())
					{
						printErrorLearningObjective();
					}
					else if(isErrorLO())
					{
						printErrorLO();
					}
					else
					{
						mainPanel.removeAll();
						speechBubble.setFont(font);
						speechBubble.setBounds(347,65,400,78);
						speechBubble.setText("     Please Select Your\n     Learning Taxonomy");
						bloomsScroll = new JScrollPane(taxPane);
						taxPane.setCaretPosition(0);
						bloomsScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						bloomsScroll.setBounds(70,300,400,260);
						mainPanel.add(bloomsScroll);
						mainPanel.add(taxBox);
						mainPanel.add(speechBubble);
						mainPanel.changeFileName("learningTaxonomy.png");
						mainPanel.changeCoord(-100, 0);
						treePanel.add(wizardTree);
						mainPanel.add(taxContButton);
						mainPanel.updateUI();
					}
				}
				else if(selectedNode !=null && selectedNode == subTaxNode)
				{

					if(isErrorLearningObjective())
					{
						printErrorLearningObjective();
						return;
					}

					else if(taxBox.getSelectedIndex() == 0){
						printErrorLearningTaxonomy();
						return;
					}

					else if(isErrorLO())
					{
						printErrorLO();
					}

					else
					{
						mainPanel.removeAll();
						mainPanel.changeFileName("wizardBackground.png");
						mainPanel.changeCoord(0,-70);
						JScrollPane scroll = JTable.createScrollPaneForTable(subTaxTable);
						scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						scroll.setBounds(85,300,950,260);
						speechBubble.setFont(font);
						speechBubble.setBounds(415,45,400,90);
						speechBubble.setText("      Please Select Type\n         Taxonomies");
						mainPanel.add(subTaxContButton);
						mainPanel.add(speechBubble);
						mainPanel.add(scroll);
						mainPanel.updateUI();
						mainPanel.updateUI();
					}
				}
				else if(selectedNode != null && selectedNode == typeOfChallengeNode)
				{
					if(isErrorLearningObjective())
					{
						printErrorLearningObjective();
					}

					else if(isErrorLO())
					{
						printErrorLO();
					}

					else if(taxBox.getSelectedIndex() == 0){
						printErrorLearningTaxonomy();
						return;
					}
					else
					{
						mainPanel.changeFileName("wizardBackground.png");
						mainPanel.changeCoord(0,-70);
						mainPanel.removeAll();
						Vector<Vector<Object>> v = generateChallengeTable();
						challengeTable = new JTable(new MyTableModel(v,false,false, false));
						challengeTable.setRowHeight(45);
						challengeTable.setShowHorizontalLines(true);
						challengeTable.setRowSelectionAllowed(true);
						challengeTable.setColumnSelectionAllowed(true);
						challengeTable.getColumnModel().getColumn(1).setMaxWidth(75);
						challengeTable.getTableHeader().setFont(font3);
						challengeTable.setFont(font2);
						challengeTable.setGridColor(Color.black);
						@SuppressWarnings("deprecation")
						JScrollPane scroll = JTable.createScrollPaneForTable(challengeTable);
						scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						scroll.setBounds(85,300,950,260);
						challengeButton.setFont(font2);
						challengeButton.setBounds(650,225,150,50);
						challengeButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						challengeButton.setEnabled(true);
						//place button here
						speechBubble.setFont(font);
						speechBubble.setBounds(415,45,400,90);
						speechBubble.setText("      Please Select Type\n         of Challenge");
						mainPanel.add(challengeButton);
						mainPanel.add(scroll);
						mainPanel.add(speechBubble);
						treePanel.updateUI();
						mainPanel.updateUI();
					}
				}
				else if(selectedNode != null && selectedNode == conditionsNode)
				{
					if(isErrorLearningObjective())
					{
						printErrorLearningObjective();
					}
					else if(isErrorChallenge())
					{
						printErrorChallenge();
					}

					else if(isErrorLO())
					{
						printErrorLO();
					}

					else if(taxBox.getSelectedIndex() == 0){
						printErrorLearningTaxonomy();
						return;
					}
					else
					{
						mainPanel.removeAll();
						speechBubble.setFont(font);
						speechBubble.setBounds(415,45,400,90);
						speechBubble.setText("      Please Select Your\n        Game Conditions");
						mainPanel.changeFileName("wizardBackground.png");
						mainPanel.changeCoord(0, -70);
						JScrollPane scroll = JTable.createScrollPaneForTable(conditionsTable);
						scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						scroll.setBounds(85,300,950,260);
						conditionContinue.setFont(font2);
						mainPanel.add(conditionContinue);   
						mainPanel.add(scroll);
						mainPanel.add(speechBubble);
						mainPanel.updateUI();
					}
				}
				else if(selectedNode != null && selectedNode == lowerSummaryNode)
				{
					mainPanel.removeAll();
					if(isErrorChallenge())
					{
						printErrorChallenge();
						mainPanel.updateUI();
					}
					else if(isErrorLearningObjective())
					{
						printErrorLearningObjective();
						mainPanel.updateUI();
					}

					else if(isErrorLO())
					{
						printErrorLO();
						mainPanel.updateUI();
					}

					else
					{
						generateSummary();
						mainPanel.updateUI();
					}
				}

				else if(selectedNode != null && selectedNode == locationNode)
				{
					mainPanel.removeAll();
					speechBubble.setFont(font);
					speechBubble.setBounds(347,65,400,78);
					speechBubble.setText("     Please Select Your\n        Location");
					mainPanel.changeFileName("learningTaxonomy.png");
					mainPanel.changeCoord(-100, 0);
					treePanel.add(wizardTree);
					mainPanel.add(speechBubble);
					mainPanel.add(backgroundBox);
					mainPanel.add(backgroundPane);
					mainPanel.add(locationButton);
					mainPanel.updateUI();
				}
				else if(selectedNode != null && selectedNode == characterNode)
				{
					mainPanel.removeAll();
					JScrollPane scroll = JTable.createScrollPaneForTable(charTable);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(85,300,950,260);
					speechBubble.setFont(font);
					speechBubble.setBounds(415,45,400,90);
					speechBubble.setText("      Here are Your\n       Typical Characters!");
					charButton.setBounds(715,225,150,50);
					charButton.setFont(font2);
					mainPanel.add(scroll);
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, -70);
					mainPanel.add(speechBubble);
					mainPanel.add(charButton);
					treePanel.updateUI();
					mainPanel.updateUI();
					mainPanel.updateUI();

				}
				else if(selectedNode != null && selectedNode == actNode)
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, -70);
					template = new JTextPane();
					showActs();
					templateScroll = new JScrollPane(template);
					template.setCaretPosition(0);
					templateScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					templateScroll.setBounds(180,300,720,330);
					template.updateUI();
					templateScroll.updateUI();
					speechBubble.setFont(font2);
					speechBubble.setBounds(425,30,345,100);
					speechBubble.setText("     Let's Begin With Creating\n         The First Acts of\n             Your Game!");
					actButton.setText("Continue");
					actButton.setFont(font2);
					actButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					actButton.setBounds(715, 225, 150, 50);
					actButton.setVisible(true);
					mainPanel.add(actButton);
					mainPanel.add(speechBubble);
					mainPanel.add(templateScroll);
					mainPanel.updateUI();

				}
				else if(selectedNode != null && selectedNode == addActNode)
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, 0);
					speechBubble.setFont(font3);
					speechBubble.setBounds(455,100,345,105);
					speechBubble.setText("There Are Two Choices To Change\n         Your Game:\n 1. Multiply The Previous Set-Up\n2. Add An Act To the Summary");
					actBox.setFont(font2);
					mainPanel.add(addActButton);
					mainPanel.add(actBox);
					mainPanel.add(speechBubble);
					mainPanel.updateUI();

				}

				else if(selectedNode != null && selectedNode == actSumNode)
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, -70);
					template = new JTextPane();
					showActs();
					templateScroll = new JScrollPane(template);
					template.setCaretPosition(0);
					templateScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					templateScroll.setBounds(180,300,720,330);
					template.updateUI();
					templateScroll.updateUI();
					speechBubble.setFont(font2);
					speechBubble.setBounds(425,30,345,100);
					speechBubble.setText("     Let's Begin With Creating\n         The First Acts of\n             Your Game!");
					actButton.setText("Add More");
					actButton.setFont(font2);
					actButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					actButton.setBounds(515, 225, 150, 50);
					actButton.setVisible(true);
					mainPanel.add(actButton);
					contActButton.setText("Continue");
					contActButton.setFont(font2);
					contActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					contActButton.setBounds(715, 225, 150, 50);
					mainPanel.add(contActButton);
					mainPanel.add(speechBubble);
					mainPanel.add(templateScroll);
					mainPanel.updateUI();

				}

				else if(selectedNode != null && selectedNode == loActNode)
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, -70);
					speechBubble.setFont(font2);
					speechBubble.setBounds(415,45,400,90);
					speechBubble.setText("    Please Select Which Learning\n      Objectives You Would Like\n            For Each Act!");

					makeLOActTable();
					JScrollPane scroll = JTable.createScrollPaneForTable(loActTable);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(40,300,1000,350);
					loActButton.setText("Continue");
					loActButton.setFont(font2);
					loActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					loActButton.setBounds(715, 225, 150, 50);
					mainPanel.add(scroll);
					mainPanel.add(speechBubble);
					mainPanel.add(loActButton);
					mainPanel.updateUI();
				}

				else if(selectedNode != null && selectedNode == quesTableNode)
				{
					if(!checkLOActTable())
					{
						printLOActTableError();
					}
					else
					{
						mainPanel.removeAll();
						mainPanel.changeFileName("wizardBackground.png");
						mainPanel.changeCoord(0, -70);
						speechBubble.setFont(font);
						speechBubble.setBounds(415,45,400,90);
						speechBubble.setText("Please Select Your Types\n Of Questions!");
						@SuppressWarnings("deprecation")
						JScrollPane scroll = JTable.createScrollPaneForTable(questionTable);
						scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						scroll.setBounds(100,300,950,350);
	
						mainPanel.add(scroll);
						mainPanel.add(speechBubble);
						mainPanel.add(quesButton);
						mainPanel.updateUI();
					}
				}

				else if(selectedNode != null && selectedNode == finalSumNode)
				{
					if(!checkLOActTable())
					{
						printLOActTableError();
					}
					else
					{
						mainPanel.removeAll();
						mainPanel.changeFileName("wizardBackround.png");
						mainPanel.changeCoord(0,-70);
						generateFinalSum();
						JScrollPane scroll = new JScrollPane(finalSumPane);
						scroll.setBounds(180,300,720,330);
						scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
						wizardTree.expandRow(1);
						speechBubble.setFont(font);
						speechBubble.setBounds(415,45,400,90);
						speechBubble.setText("Here Is A Final Summary!"); 
						mainPanel.add(scroll);
						mainPanel.add(speechBubble);
						mainPanel.add(finalSumButton);
						mainPanel.updateUI();
					}
				}

				else if(selectedNode != null && selectedNode == conclusionNode)
				{
					if(!checkLOActTable())
					{
						printLOActTableError();
					}
					else
					{
						mainPanel.removeAll();
						mainPanel.changeFileName("introBackground2.png");
						mainPanel.changeCoord(0,0);
						speechBubble.setFont(font);
						speechBubble.setBounds(400,100,420,120);
						speechBubble.setText("Congratulations You Are Done!\nPress Save To Save The Game");
						mainPanel.add(speechBubble);
						mainPanel.add(saveButton);
						mainPanel.updateUI();
					}
				}
			}
		});

		JSplitPane main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treePanel, mainPanel);
		main.setDividerLocation(160);
		institutionBox = new JComboBox<String>(institutions);
		domainBox = new JComboBox<String>(domains);
		gradeBox = new JComboBox<String>(grades);
		subjectBox = new JComboBox<String>(subjects);
		speechBubble = new JTextArea("  Welcome to the SimSYS Game \n            Design Tool! \n  Please Click Continue To Begin!");
		speechBubble.setFont(font);
		speechBubble.setEditable(false);
		speechBubble.setBounds(375,80,479,140);   
		mainPanel.add(speechBubble);
		submitButton = new JButton("Continue");
		submitButton.setVisible(false);
		submitButton.setEnabled(false);
		submitButton.setBounds(400,475,150,50);

		submitLOButton = new JButton("Continue");
		submitLOButton.setVisible(true);
		submitLOButton.setEnabled(true);
		submitLOButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.GRAY));
		submitLOButton.setFont(font2);

		submitLOButton.setBounds(650,225,150,50);

		subLOButton = new JButton("Continue");
		subLOButton.setVisible(true);
		subLOButton.setEnabled(true);
		subLOButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));

		subLOButton.setBounds(650,225,150,50);

		summaryContinue = new JButton();
		summaryBack = new JButton();
		welcomeButton = new JButton("Continue");
		welcomeButton.setVisible(true);
		welcomeButton.setEnabled(true);
		welcomeButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		welcomeButton.setFont(font2);
		welcomeButton.setBounds(550,475,150,50);
		subTaxContButton = new JButton("Continue");
		taxContButton = new JButton("Continue");

		challengeButton = new JButton("Continue");
		mainPanel.add(welcomeButton);
		String[] taxonomy = {"Please Choose Your Taxonomy","Bloom's","Wisconsin Standard"};
		taxBox = new JComboBox<String>(taxonomy);

		conditionContinue = new JButton("Continue");
		conditionContinue.setVisible(true);
		conditionContinue.setEnabled(true);
		conditionContinue.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		conditionContinue.setBounds(650,225,150,50);
		conditionErrorBack = new JButton();
		fullSumContinue =  new JButton("Continue");
		challengeErrorBack = new JButton("Back");
		challengeErrorBack.setFont(font2);
		challengeErrorBack.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));

		taxBackButton = new JButton("Back");
		taxBackButton.setFont(font2);
		taxBackButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));

		actButton = new JButton("Continue");
		actButton.setFont(font2);
		actButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		actButton.setBounds(715, 225, 150, 50);
		actButton.setVisible(true);

		introTwoButton = new JButton("Continue");
		introTwoButton.setVisible(true);
		introTwoButton.setEnabled(true);
		introTwoButton.setBounds(600,475,150,50);
		introTwoButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		charButton = new JButton("Continue");
		charButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		charButton.setBounds(715,225,150,50);
		charButton.setFont(font2);

		loList = new ArrayList<String>();

		String[] backgrounds = {"Please Choose A Location","Classroom","Enchanted Forest"};
		backgroundBox = new JComboBox <String>(backgrounds);

		String[] actChoices = {"Keep The Current Set-Up","Add Another Set of Acts","Add a Question","Back to Original"};
		actBox = new JComboBox <String>(actChoices);
		actBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		actBox.setBounds(400,370,425,50);

		locationButton = new JButton("Continue");
		locationButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		template = new JTextPane();
		template.setEditable(false);

		addActButton = new JButton("Continue");
		addActButton.setFont(font2);
		addActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		addActButton.setBounds(550, 450, 150, 50);
		addActButton.setVisible(true);

		contActButton = new JButton("Continue");
		contActButton.setFont(font2);
		contActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		contActButton.setBounds(715, 225, 150, 50);
		contActButton.setVisible(true);

		loActButton = new JButton("Continue");
		loActButton.setFont(font2);
		loActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		loActButton.setBounds(715, 225, 150, 50);
		loActButton.setVisible(true);

		quesButton = new JButton("Continue");
		quesButton.setFont(font2);
		quesButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		quesButton.setBounds(650,225,150,50);

		finalSumButton = new JButton("Continue");
		finalSumButton.setFont(font2);
		finalSumButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		finalSumButton.setBounds(650,225,150,50);

		saveButton = new JButton("Save");
		saveButton.setFont(font2);
		saveButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		saveButton.setBounds(600,475,150,50);


		institutionBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = institutionBox.getSelectedIndex();
				gradeBox.setEnabled(false);
				subjectBox.setEnabled(false);
				submitButton.setVisible(false);
				rootNode.removeAllChildren();
				model.reload();
				treePanel.add(wizardTree);
				treePanel.updateUI();
				//record choice
				if(institutions[i] != institutions[0]){
					domainBox.setEnabled(true);
					speechBubble.setText("   Thanks. Who is This Game\n   For Specifically?");
					mainPanel.changeFileName("introBackground2.png");
					mainPanel.updateUI();
					//Set specific domain
					if(institutions[i] == institutions[1] || institutions[i] == institutions[2]){
						String [] milSchools  = {"Please Choose Your Domain","K-6","Middle School","High School"};
						domainBox.setModel(new JComboBox<>(milSchools).getModel());
						domains = milSchools;
						domainBox.setEnabled(true);
						mainPanel.updateUI();
					}
					else if(institutions[i] == institutions[3] || institutions[i] == institutions[4])
					{
						String [] universities  = {"Please Choose Your Domain","Math Department",
								"Biology Department","Communications Department"};
						domains = universities;
						domainBox.setModel(new JComboBox<>(universities).getModel());
						domainBox.setEnabled(true);
						mainPanel.updateUI();
					}
					else
					{
						String [] workplace  = {"Please Choose Your Domain","Compliance Training",
						"Recruitment"};
						domainBox.setModel(new JComboBox<>(workplace).getModel());
						domains = workplace;
						domainBox.setEnabled(true);
						mainPanel.updateUI();
					}
				}else{
					domainBox.setEnabled(false);
					gradeBox.setEnabled(false);
					submitButton.setVisible(false);
					subjectBox.setEnabled(false);
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.updateUI();
					speechBubble.setText("Oops. Please Enter Institution");
				}
			}
		});
		domainBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = domainBox.getSelectedIndex();
				int j = institutionBox.getSelectedIndex();
				subjectBox.setEnabled(false);
				submitButton.setVisible(false);
				rootNode.removeAllChildren();
				model.reload();
				treePanel.add(wizardTree);
				treePanel.updateUI();
				//record choice
				if(domains[i] != domains[0]){
					gradeBox.setEnabled(true);
					speechBubble.setText("   Gotcha. What Level will \n   be playing this game?");
					mainPanel.changeFileName("introBackground2.png");
					mainPanel.updateUI();
					//Set specific level
					if(institutions[j] == institutions[1] || institutions[j] == institutions[2]){
						//String [] schoolLevel;
						if(i == 1){
							String []  schoolLevel = {"Please Choose Your Level","Kindergarten","First Grade","Second Grade","Third Grade",
									"Fourth Grade","Fifth Grade","Sixth Grade"};
							gradeBox.setModel(new JComboBox<>(schoolLevel).getModel());
							grades = schoolLevel;
							gradeBox.setEnabled(true);
							mainPanel.updateUI();
						}else if(i == 2){
							String []  schoolLevel = {"Please Choose Your Level","Seventh Grade","Eighth Grade","Ninth Grade"};
							gradeBox.setModel(new JComboBox<>(schoolLevel).getModel());
							grades = schoolLevel;
							gradeBox.setEnabled(true);
							mainPanel.updateUI();
						}else{
							String []  schoolLevel = {"Please Choose Your Level","Tenth Grade","Eleventh Grade","Twelfth Grade"};
							gradeBox.setModel(new JComboBox<>(schoolLevel).getModel());
							grades = schoolLevel;
							gradeBox.setEnabled(true);
							mainPanel.updateUI();
						}
					}
					else if(institutions[j] == institutions[3] || institutions[j] == institutions[4])
					{
						String []  schoolLevel = {"Please Choose Your Level","1000","2000","3000","4000"};
						gradeBox.setModel(new JComboBox<>(schoolLevel).getModel());
						grades = schoolLevel;
						gradeBox.setEnabled(true);
						mainPanel.updateUI();                        
					}
					else{
						if(i == 1)
						{
							String []  schoolLevel = {"Please Choose Your Level","New Hires","Continuing Employees"};
							gradeBox.setModel(new JComboBox<>(schoolLevel).getModel());
							grades = schoolLevel;
							gradeBox.setEnabled(true);
							mainPanel.updateUI();
						}
						else{
							String []  schoolLevel = {"Please Choose Your Level","Job Fair","Hiring Practices"};
							gradeBox.setModel(new JComboBox<>(schoolLevel).getModel());
							grades = schoolLevel;
							gradeBox.setEnabled(true);
							mainPanel.updateUI();
						}
					}
				}else{   
					gradeBox.setEnabled(false);
					submitButton.setVisible(false);
					subjectBox.setEnabled(false);
					speechBubble.setText("   Oops. Please Enter Domain");
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.updateUI();
				}
			}
		});
		gradeBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = gradeBox.getSelectedIndex();
				int j = institutionBox.getSelectedIndex();
				int k = domainBox.getSelectedIndex();
				submitButton.setVisible(false);
				rootNode.removeAllChildren();
				model.reload();
				treePanel.add(wizardTree);
				treePanel.updateUI();
				//record choice
				if(grades[i] != grades[0]){
					subjectBox.setEnabled(true);
					speechBubble.setText("   Sounds Good. What Subject \n   will this game teach?");
					mainPanel.changeFileName("introBackground2.png");
					mainPanel.updateUI();
					if(institutions[j] == institutions[1] || institutions[j] == institutions[2]){
						String []  schoolSubject = {"Please Choose Your Subject","Math","Reading","Science"};
						subjectBox.setModel(new JComboBox<>(schoolSubject).getModel());
						subjects = schoolSubject;
						subjectBox.setEnabled(true);
						mainPanel.updateUI();
					}
					else if(j==3||j==4)
					{
						if(k==1)
						{
							String []  course = {"Please Choose Your Subject","Calculus 3","Statistics","Probability"};
							subjectBox.setModel(new JComboBox<>(course).getModel());
							subjects = course;
							subjectBox.setEnabled(true);
							mainPanel.updateUI();
						}
						else if(k==2)
						{
							String []  course = {"Please Choose Your Subject","Molecular Biology","General Biology","Cell Biology"};
							subjectBox.setModel(new JComboBox<>(course).getModel());
							subjects = course;
							subjectBox.setEnabled(true);
							mainPanel.updateUI();
						}
						else
						{
							String []  course = {"Please Choose Your Subject","Public Relations","Advertising","Corporate Communications"};
							subjectBox.setModel(new JComboBox<>(course).getModel());
							subjects = course;
							subjectBox.setEnabled(true);
							mainPanel.updateUI();
						}
					}
					else
					{
						if(k==1)
						{
							String []  workSubject = {"Please Choose Your Subject","Ethics","Conflicts of Interest","Sexual Harassment"};
							subjectBox.setModel(new JComboBox<>(workSubject).getModel());
							subjects = workSubject;
							subjectBox.setEnabled(true);
							mainPanel.updateUI();
						}
						else
						{
							String []  workSubject = {"Please Choose Your Subject","Equal Opportunity Hiring","Guidelines for Internships"};
							subjectBox.setModel(new JComboBox<>(workSubject).getModel());
							subjects = workSubject;
							subjectBox.setEnabled(true);
							mainPanel.updateUI();
						}
					}
					//Set specific subject
				}else{   
					subjectBox.setEnabled(false);
					submitButton.setVisible(false);
					speechBubble.setText("   Oops. Please Enter Level");
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.updateUI();
				}
			}
		});
		subjectBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = subjectBox.getSelectedIndex();
				rootNode.removeAllChildren();
				model.reload();
				treePanel.add(wizardTree);
				treePanel.updateUI();   
				//record choice
				if(subjects[i] != subjects[0])
				{
					//MOVE ON To next section
					submitButton.setEnabled(true);
					speechBubble.setText("   Thanks! Click the button to\n   continue!");
					mainPanel.changeFileName("introBackground2.png");
					submitButton.setVisible(true);
					mainPanel.updateUI();
				}
				else
				{
					submitButton.setEnabled(false);
					submitButton.setVisible(false);
					speechBubble.setText("   Oops. Please Enter Subject");
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.updateUI();
				}
			}
		});
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				submitButton.setEnabled(false);
				rootNode.add(learningObjectiveNode);
				rootNode.setUserObject(gradeBox.getSelectedItem() + " " + subjectBox.getSelectedItem());
				model.nodeChanged(rootNode);
				wizardTree.expandRow(0);
				Vector <Vector <Object>> v = new Vector<Vector <Object>>();
				Vector <Object> v1 = new Vector<Object>();
				Vector <Object> v2 = new Vector<Object>();
				Vector <Object> v3= new Vector<Object>();
				Vector <Object> v4 = new Vector<Object>();
				Vector <Object> v5 = new Vector<Object>();
				v1.add("Operations and Algebraic Thinking");
				v1.add( new Boolean(false));
				v2.add("Number and Operations - Base 10");
				v2.add( new Boolean(false));
				v3.add("Number and Operations - Fractions");
				v3.add( new Boolean(false));
				v4.add("Measurement and Data");
				v4.add( new Boolean(false));
				v5.add("Geometry");
				v5.add( new Boolean(false));
				v.add(v1);
				v.add(v2);
				v.add(v3);
				v.add(v4);
				v.add(v5);
				table = new JTable(new MyTableModel(v,false,false, false));
				treePanel.add(wizardTree);
				mainPanel.removeAll();
				//
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				table.setRowHeight(45);
				table.setShowHorizontalLines(true);
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(true);
				table.getColumnModel().getColumn(1).setMaxWidth(75);
				table.getTableHeader().setFont(font3);
				table.setFont(font2);
				table.setGridColor(Color.black);
				DefaultTableCellRenderer r = new DefaultTableCellRenderer()
				{
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;
					@Override
					public Component getTableCellRendererComponent(JTable table,
							Object value, boolean isSelected, boolean hasFocus,
							int row, int column) {
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
								row, column);
						if(((String)value).charAt(0)=='-')
						{
							setFont(font4);
						}
						return this;
					}
				};
				table.getColumnModel().getColumn(0).setCellRenderer(r);
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("    Please Select Your \n      Knowledge Area");
				@SuppressWarnings("deprecation")
				JScrollPane scroll = JTable.createScrollPaneForTable(table);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(100,300,950,260);
				subLOButton.setFont(font2);
				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				mainPanel.add(submitLOButton);
				mainPanel.updateUI();
				//
				treePanel.updateUI();
			}
		});

		submitLOButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				mainPanel.removeAll();
				wizardTree.expandRow(0);
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				speechBubble.setFont(font);
				speechBubble.setBounds(413,45,403,90);
				speechBubble.setText("Please Select Your Knowledge\n Areas and Continue");
				ArrayList<String> subBases = new ArrayList<String>();
				Vector<Vector<Object>> lastTable = ((MyTableModel)table.getModel()).getData();
				for(int i = 0; i < lastTable.size(); i++){
					if(((boolean)lastTable.get(i).get(1))){
						if(((String)lastTable.get(i).get(0)).charAt(0) == '-'){
							subBases.add(((String)lastTable.get(i).get(0)).substring(2,((String)lastTable.get(i).get(0)).length()));

						}
					}
				}

				Vector<Vector<Object>> newData = new Vector<Vector<Object>>();
				for(int i = 0; i < subBases.size(); i++){
					if(subBases.get(i).equals("Use The Four Operations With Whole Numbers To Solve Problems")){
						Vector<Object> v1 = new Vector<Object>();
						v1.add("Use The Four Operations With Whole Numbers To Solve Problems");
						v1.add(new Boolean(true));
						Vector<Object> v2 = new Vector<Object>();
						v2.add("---Interpret a multiplication equation as a comparison, e.g., interpret 35 = 5 × 7 as a "
								+ "statement that 35 is 5 times as many as 7 and 7 times as many as 5. "
								+ "Represent verbal statements of multiplicative comparisons as multiplication equations.");
						v2.add(new Boolean(false));
						Vector<Object> v3 = new Vector<Object>();
						v3.add("---Multiply or divide to solve word problems involving multiplicative comparison, "
								+ "e.g., by using drawings and equations with a symbol for the "
								+ "unknown number to represent the problem, distinguishing multiplicative comparison from additive comparison.");
						v3.add(new Boolean(false));
						Vector<Object> v4 = new Vector<Object>();
						v4.add("---Solve multistep word problems posed with whole numbers and having whole-number answers using the four operations, "
								+ "including problems in which remainders must be interpreted. Represent these problems using equations with a "
								+ "letter standing for the unknown quantity. Assess the reasonableness of answers using mental"
								+ " computation and estimation strategies including rounding");
						v4.add(new Boolean(false));
						newData.add(v1);
						newData.add(v2);
						newData.add(v3);
						newData.add(v4);
					}
				}
				DefaultTableCellRenderer r = new DefaultTableCellRenderer()
				{
					/**
					 *
					 */
					private static final long serialVersionUID = 1L;
					@Override
					public Component getTableCellRendererComponent(JTable table,
							Object value, boolean isSelected, boolean hasFocus,
							int row, int column) {
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
								row, column);
						if(((String)value).charAt(0)=='-')
						{
							JTextArea text = new JTextArea();
							text.setText((String)value);
							text.setLineWrap(true);
							text.setWrapStyleWord(true);
							text.setFont(font4);
							return text;
						}
						else
						{
							this.setFont(font2);
						}
						return this;
					}
				};
				loTable = new JTable(new MyTableModel(newData,false,true,false));
				loTable.getColumnModel().getColumn(0).setCellRenderer(r);

				loTable.setRowHeight(120);
				loTable.setShowHorizontalLines(true);
				loTable.setRowSelectionAllowed(true);
				loTable.setColumnSelectionAllowed(true);
				loTable.getColumnModel().getColumn(1).setMaxWidth(75);
				loTable.getTableHeader().setFont(font3);
				loTable.setFont(font3);
				loTable.setGridColor(Color.black);
				JScrollPane scroll = JTable.createScrollPaneForTable(loTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(40,300,1000,350);

				subLOButton.setFont(font2);
				mainPanel.add(scroll);
				mainPanel.add(subLOButton);
				mainPanel.add(speechBubble);
				treePanel.updateUI();
				mainPanel.updateUI();
			}
		});
		subLOButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){   
				mainPanel.removeAll();
				loList = new ArrayList<String>();
				JTextPane selectedLO = new JTextPane();
				selectedLO.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				StyledDocument doc = selectedLO.getStyledDocument();
				javax.swing.text.Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
				javax.swing.text.Style normal = doc.addStyle("font 2", def);
				StyleConstants.setFontFamily(def, "Comic Sans MS");
				StyleConstants.setFontSize(def, 22);
				StyleConstants.setUnderline(def, false);
				StyleConstants.setBold(def, true);
				javax.swing.text.Style s = doc.addStyle("font 3", normal);
				StyleConstants.setFontSize(s, 18);
				StyleConstants.setBold(s, false);
				javax.swing.text.Style s2 = doc.addStyle("font 4", s);
				StyleConstants.setFontSize(s2, 15);
				MyTableModel tm = (MyTableModel) table.getModel();
				MyTableModel tm2 = (MyTableModel) loTable.getModel();
				Vector <Vector <Object>> v = tm.getData();
				Vector <Vector <Object>> v2 = tm2.getData();
				int mainCount = 0;
				int subCount = 0;
				boolean finalError = false;
				learningError = false;
				for(int i=0;i<table.getRowCount();i++)
				{
					if((boolean)v.get(i).get(1)==true)
					{
						if(((String)(v.get(i).get(0))).charAt(0) == '-'){
							try{
								doc.insertString(doc.getLength(), v.get(i).get(0)+"\n", doc.getStyle("font 3"));
								for(int j=0; j<loTable.getRowCount();j++)
								{
									int length = ((String)(v.get(i).get(0))).length();
									String sub = ((String)(v.get(i).get(0))).substring(2,length);
									String lo = ((String)(v2.get(j).get(0)));
									if(sub.equals(lo))
									{
										int k=j;
										int l=k;
										while( j+1<loTable.getRowCount()&&((String)(v2.get(j+1).get(0))).charAt(0) == '-')
										{
											if((boolean)v2.get(j+1).get(1)==true)
											{
												numLO++;
												doc.insertString(doc.getLength(), "----"+v2.get(j+1).get(0)+"\n", doc.getStyle("font 4"));
												loList.add((String) v2.get(j+1).get(0));
												k++;
											}
											j++;
										}
										if(k==l)
										{
											learningError = true;
										}
									}
								}
							}
							catch(Exception e1)
							{
								System.out.println("Font does not Exist!");
							}
							subCount++;
						}else{
							try{
								doc.insertString(doc.getLength(), v.get(i).get(0)+"\n", doc.getStyle("font 2"));
								int j=1;
								boolean error = true;
								while(((String)(v.get(i+j).get(0))).charAt(0) == '-')
								{
									if((boolean)v.get(i+j).get(1) == true)
									{
										error=false;
									}
									j++;
								}
								if(error == true){
									finalError =true;
									mainCount++;
									break;
								}
							}
							catch(Exception e1)
							{
								System.out.println("Font does not Exist!");
							}
							mainCount++;
						}
						//selected = selected+v.get(i).get(0)+"\n";
					}
				}
				summaryContinue.setText("Continue");
				summaryBack.setText("Back");
				summaryContinue.setFont(font2);
				summaryBack.setFont(font2);
				summaryContinue.setBounds(700,225,150,50);
				summaryBack.setBounds(520,225,150,50);
				summaryContinue.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				summaryBack.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				if(mainCount==0)
				{
					mainPanel.removeAll();
					speechBubble.setBounds(415,45,400,170);
					speechBubble.setText("\n      You Must Select A \n         Knowledge Area!\n       Please Click Back!");
					speechBubble.setFont(font);
					summaryBack.setBounds(700,550,150,50);
					mainPanel.changeCoord(0, 0);
					mainPanel.add(speechBubble);
					mainPanel.add(summaryBack);
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.updateUI();
				}
				else if(subCount==0 && mainCount!=0 || finalError)
				{
					mainPanel.removeAll();
					speechBubble.setBounds(415,45,400,170);
					speechBubble.setText("\n        You Must Select \n        Sub-Knowledge Area!\n        Please Click Back!");
					speechBubble.setFont(font);
					summaryBack.setBounds(700,550,150,50);
					mainPanel.changeCoord(0, 0);
					mainPanel.add(speechBubble);
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.add(summaryBack);
					mainPanel.updateUI();
				}
				else if(learningError)
				{
					mainPanel.removeAll();
					speechBubble.setBounds(415,45,400,170);
					speechBubble.setText("\n        You Must Select \n       Learning Objectives!\n        Please Click Back!");
					speechBubble.setFont(font);
					summaryBack.setBounds(700,550,150,50);
					mainPanel.changeCoord(0, 0);
					mainPanel.add(speechBubble);
					mainPanel.changeFileName("errorBackground.png");
					mainPanel.add(summaryBack);
					mainPanel.updateUI();
				}
				else
				{
					selectedLO.setEditable(false);
					selectedLO.setCaretPosition(0);
					JScrollPane scroll = new JScrollPane(selectedLO);
					scroll.setBounds(100,300,950,260);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					speechBubble.setBounds(415,45,400,90);
					speechBubble.setText("Here is a Summary of Your \nLearning Objectives Selected");
					speechBubble.setFont(font);
					mainPanel.add(speechBubble);
					mainPanel.add(summaryContinue);
					mainPanel.add(summaryBack);
					mainPanel.add(scroll);
					mainPanel.updateUI();
				}
			}
		});

		welcomeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				mainPanel.changeCoord(0, 0);
				mainRoot.add(rootNode);
				DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
				model.reload(mainRoot);
				wizardTree.expandRow(0);
				speechBubble.setFont(font);
				speechBubble.setBounds(400,100,420,120);
				speechBubble.setText("   Who is This Game For?");
				institutionBox.setBounds(625,300,425,50);
				institutionBox.setMaximumRowCount(5);
				institutionBox.setFont(font2);
				institutionBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				mainPanel.add(institutionBox);
				domainBox.setBounds(625,375,425,50);
				domainBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				domainBox.setMaximumRowCount(5);
				domainBox.setFont(font2);
				domainBox.setEnabled(false);
				mainPanel.add(domainBox);
				gradeBox.setBounds(625,450,425,50);
				gradeBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				gradeBox.setFont(font2);
				gradeBox.setMaximumRowCount(5);
				gradeBox.setEnabled(false);
				mainPanel.add(gradeBox);
				subjectBox.setBounds(625,525,425,50);
				subjectBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				subjectBox.setFont(font2);
				subjectBox.setMaximumRowCount(5);
				subjectBox.setEnabled(false);
				submitButton.setFont(font2);
				submitButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				mainPanel.add(speechBubble);
				mainPanel.add(subjectBox);
				mainPanel.add(submitButton);
				mainPanel.changeFileName("introBackground2.png");
				mainPanel.updateUI();
			}
		});
		summaryBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("Please Select Your Learning\n Objectives and Continue");
				@SuppressWarnings("deprecation")
				JScrollPane scroll = JTable.createScrollPaneForTable(table);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(100,300,950,260);
				subLOButton.setFont(font2);
				subLOButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				numLO=0;
				loList = new ArrayList<String>();
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				mainPanel.add(submitLOButton);
				mainPanel.updateUI();
			}
		});
		summaryContinue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(learningObjectiveNode.getNextSibling()!=learningTaxonomyNode)
				{
					rootNode.add(learningTaxonomyNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(0);
				mainPanel.removeAll();
				taxPane.setEditable(false);


				//import learning taxonomy

				speechBubble.setFont(font);
				speechBubble.setBounds(347,65,400,78);
				speechBubble.setText("     Please Select Your\n     Learning Taxonomy");
				mainPanel.changeFileName("learningTaxonomy.png");
				mainPanel.changeCoord(-100, 0);
				taxContButton.setBounds(750,475,165,50);
				taxContButton.setVisible(true);
				taxContButton.setEnabled(false);
				taxContButton.setFont(font2);
				taxContButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				taxBox.setFont(font2);
				taxBox.setBounds(625,375,425,50);
				taxPane.setBounds(70,300,400,260);
				taxPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				mainPanel.add(taxPane);
				mainPanel.add(taxBox);
				mainPanel.add(taxContButton);
				mainPanel.add(speechBubble);

				treePanel.add(wizardTree);
				mainPanel.updateUI();
				treePanel.updateUI();
			}
		});
		taxBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i = taxBox.getSelectedIndex();
				mainPanel.removeAll();
				mainPanel.add(taxBox);
				taxBox.setSelectedIndex(i);
				mainPanel.add(taxContButton);
				mainPanel.updateUI();
				if(i==0)
				{
					taxContButton.setEnabled(false);
					speechBubble.setBounds(347,65,400,78);
					speechBubble.setText("     Please Select Your\n       Standard");
					speechBubble.setFont(font);
					taxPane = new JTextPane();
					taxPane.setBounds(70,300,400,260);
					taxPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					mainPanel.add(taxContButton);
					mainPanel.add(taxPane);
					mainPanel.add(speechBubble);
					mainPanel.updateUI();
				}
				else
				{
					//taxPane.removeAll();
					taxPane = new JTextPane();
					taxPane.updateUI();
					taxPane.setEditable(false);
					StyledDocument doc = taxPane.getStyledDocument();
					javax.swing.text.Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
					javax.swing.text.Style normal = doc.addStyle("font 2", def);
					StyleConstants.setFontFamily(def, "Comic Sans MS");
					StyleConstants.setFontSize(def, 22);
					StyleConstants.setBold(def, true);
					StyleConstants.setUnderline(def, true);
					javax.swing.text.Style s = doc.addStyle("font 3", normal);
					StyleConstants.setUnderline(s, false);
					StyleConstants.setFontSize(s, 18);
					StyleConstants.setBold(s, false);
					if(taxBox.getSelectedItem().equals("Bloom's")){
						bloomSelected = true;
						speechBubble.setFont(font);
						speechBubble.setBounds(347,65,400,78);
						speechBubble.setText(" Great! You Have Selected \n           Blooms!");
						taxContButton.setEnabled(true);
						try{                       
							doc.insertString(doc.getLength(), "Bloom's\n", doc.getStyle("font 2"));
							doc.insertString(doc.getLength(), "-Knowledge (K)\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Comprehension (C)\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Application (AP)\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Analysis (An)\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Synthesis (S)\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Evaluation (E)\n", doc.getStyle("font 3"));
						}
						catch(Exception e2)
						{
							System.out.println("catch blooms");
						}
						mainPanel.updateUI();
					}
					else
					{
						bloomSelected = false;
						speechBubble.setFont(font);
						speechBubble.setBounds(347,65,400,78);
						speechBubble.setText(" Great! You Have Selected\n    The Wisconsion Standard!");
						taxContButton.setEnabled(true);
						mainPanel.updateUI();
						try{
							doc.insertString(doc.getLength(), "Wisconsin State Standards\n", doc.getStyle("font 2"));
							doc.insertString(doc.getLength(), "-Make sense of problems and persevere in solving them\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Reason abstractly and quantitatively\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Construct viable arguments and critique the reasoning of others\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Model with mathematics\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Use appropriate tools strategy\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Attend to precision\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Look for and make use of structure\n", doc.getStyle("font 3"));
							doc.insertString(doc.getLength(), "-Look for and express regularity in repeated reasoning.\n", doc.getStyle("font 3"));
						}
						catch(Exception e2)
						{
							System.out.println("catch other");
						}
					}
					//taxPane.setStyledDocument(doc);
					bloomsScroll = new JScrollPane(taxPane);
					taxPane.setCaretPosition(0);
					bloomsScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					bloomsScroll.setBounds(70,300,400,260);
					taxPane.updateUI();
					bloomsScroll.updateUI();
					mainPanel.add(bloomsScroll);
					mainPanel.add(speechBubble);
					mainPanel.add(taxContButton);
					mainPanel.updateUI();
				}
			}
		});
		taxContButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(learningTaxonomyNode.getNextSibling()!=subTaxNode)
				{
					rootNode.add(subTaxNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(0);
				mainPanel.removeAll();
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0,-70);
				JScrollPane scroll;
				if(taxBox.getSelectedItem().equals("Bloom's")){
					Vector <Vector <Object>> v = new Vector<Vector <Object>>();
					Vector <Object> v1 = new Vector<Object>();
					Vector <Object> v2 = new Vector<Object>();
					Vector <Object> v3= new Vector<Object>();
					Vector <Object> v4 = new Vector<Object>();
					Vector <Object> v5 = new Vector<Object>();
					Vector <Object> v6 = new Vector<Object>();
					v1.add("-Knowledge (K)");
					v1.add( new Boolean(false));
					v2.add("-Comprehension (C)");
					v2.add( new Boolean(false));
					v3.add("-Application (AP)");
					v3.add( new Boolean(false));
					v4.add("-Analysis (An)");
					v4.add( new Boolean(false));
					v5.add("-Synthesis (S)");
					v5.add( new Boolean(false));
					v6.add("-Evaluation (E)");
					v6.add( new Boolean(false));
					v.add(v1);
					v.add(v2);
					v.add(v3);
					v.add(v4);
					v.add(v5);
					v.add(v6);
					subTaxTable = new JTable(new MyTableModel(v,false,false, false));
					subTaxTable.setRowHeight(45);
					subTaxTable.setShowHorizontalLines(true);
					subTaxTable.setRowSelectionAllowed(true);
					subTaxTable.setColumnSelectionAllowed(true);
					subTaxTable.getColumnModel().getColumn(1).setMaxWidth(75);
					subTaxTable.getTableHeader().setFont(font3);
					subTaxTable.setFont(font2);
					subTaxTable.setGridColor(Color.black);
					scroll = JTable.createScrollPaneForTable(subTaxTable);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(85,300,950,260);


				}else{
					Vector <Vector <Object>> v = new Vector<Vector <Object>>();
					Vector <Object> v1 = new Vector<Object>();
					Vector <Object> v2 = new Vector<Object>();
					Vector <Object> v3= new Vector<Object>();
					Vector <Object> v4 = new Vector<Object>();
					Vector <Object> v5 = new Vector<Object>();
					Vector <Object> v6 = new Vector<Object>();
					Vector <Object> v7 = new Vector<Object>();
					v1.add("-Make sense of problems and persevere in solving them");
					v1.add( new Boolean(false));
					v2.add("-Reason abstractly and quantitatively");
					v2.add( new Boolean(false));
					v3.add("-Model with mathematics");
					v3.add( new Boolean(false));
					v4.add("-Use appropriate tools strategy");
					v4.add( new Boolean(false));
					v5.add("-Attend to precision");
					v5.add( new Boolean(false));
					v6.add("-Look for and make use of structure");
					v6.add( new Boolean(false));
					v7.add("-Look for and express regularity in repeated reasoning");
					v7.add( new Boolean(false));
					v.add(v1);
					v.add(v2);
					v.add(v3);
					v.add(v4);
					v.add(v5);
					v.add(v6);
					v.add(v7);
					subTaxTable = new JTable(new MyTableModel(v,false,false,false));
					subTaxTable.setRowHeight(45);
					subTaxTable.setShowHorizontalLines(true);
					subTaxTable.setRowSelectionAllowed(true);
					subTaxTable.setColumnSelectionAllowed(true);
					subTaxTable.getColumnModel().getColumn(1).setMaxWidth(75);
					subTaxTable.getTableHeader().setFont(font3);
					subTaxTable.setFont(font2);
					subTaxTable.setGridColor(Color.black);
					scroll = JTable.createScrollPaneForTable(subTaxTable);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(85,300,950,260);
				}
				subTaxContButton.setVisible(true);
				subTaxContButton.setEnabled(true);
				subTaxContButton.setFont(font2);
				subTaxContButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				subTaxContButton.setBounds(650,225,150,50);
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("      Please Select Type\n         Taxonomies");
				mainPanel.add(subTaxContButton);
				mainPanel.add(speechBubble);
				mainPanel.add(scroll);
				mainPanel.updateUI();
			}
		});
		subTaxContButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(subTaxNode.getNextSibling()!=typeOfChallengeNode){
					rootNode.add(typeOfChallengeNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}

				wizardTree.expandRow(0);
				mainPanel.removeAll();
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0,-70);
				Vector<Vector<Object>> v = generateChallengeTable();
				challengeTable = new JTable(new MyTableModel(v,false,false,false));
				challengeTable.setRowHeight(45);
				challengeTable.setShowHorizontalLines(true);
				challengeTable.setRowSelectionAllowed(true);
				challengeTable.setColumnSelectionAllowed(true);
				challengeTable.getColumnModel().getColumn(1).setMaxWidth(75);
				challengeTable.getTableHeader().setFont(font3);
				challengeTable.setFont(font2);
				challengeTable.setGridColor(Color.black);
				@SuppressWarnings("deprecation")
				JScrollPane scroll = JTable.createScrollPaneForTable(challengeTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(85,300,950,260);


				challengeButton.setFont(font2);
				challengeButton.setBounds(650,225,150,50);
				challengeButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				challengeButton.setEnabled(true);
				//place button here
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("      Please Select Type\n         of Challenge");
				mainPanel.add(challengeButton);
				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				treePanel.updateUI();
				mainPanel.updateUI();
			}
		});

		challengeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MyTableModel tm = (MyTableModel) challengeTable.getModel();
				Vector<Vector<Object>> v = tm.getData();
				if(typeOfChallengeNode.getNextSibling()!=conditionsNode)
				{
					rootNode.add(conditionsNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(0);
				mainPanel.removeAll();
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("      Please Select Your\n        Game Conditions");
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				if(isErrorChallenge())
				{
					printErrorChallenge();
				}
				else
				{
					Vector <Vector <Object>> vNew = new Vector<Vector <Object>>();
					Vector <Object> v1 = new Vector<Object>();
					Vector <Object> v2 = new Vector<Object>();
					Vector <Object> v3= new Vector<Object>();
					Vector <Object> v4 = new Vector<Object>();
					Vector <Object> v5 = new Vector<Object>();
					v1.add("Timed");
					v1.add( new Boolean(false));
					v2.add("Limited Resources");
					v2.add( new Boolean(false));
					v3.add("Reward/punishment");
					v3.add( new Boolean(false));
					v4.add("Collaborative");
					v4.add( new Boolean(false));
					v5.add("Antagonistic");
					v5.add( new Boolean(false));
					vNew.add(v1);
					vNew.add(v2);
					vNew.add(v3);
					vNew.add(v4);
					vNew.add(v5);
					conditionsTable = new JTable(new MyTableModel(vNew, false,false,false));
					conditionsTable.setRowHeight(45);
					conditionsTable.setShowHorizontalLines(true);
					conditionsTable.setRowSelectionAllowed(true);
					conditionsTable.setColumnSelectionAllowed(true);
					conditionsTable.getColumnModel().getColumn(1).setMaxWidth(75);
					conditionsTable.getTableHeader().setFont(font3);
					conditionsTable.setFont(font2);
					conditionsTable.setGridColor(Color.black);
					@SuppressWarnings("deprecation")
					JScrollPane scroll = JTable.createScrollPaneForTable(conditionsTable);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(85,300,950,260);
					conditionContinue.setFont(font2);
					mainPanel.add(conditionContinue);               
					mainPanel.add(scroll);
					mainPanel.add(speechBubble);
					mainPanel.updateUI();
					treePanel.updateUI();
				}
			}
		});
		challengeErrorBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0,-70);
				mainPanel.removeAll();
				JScrollPane scroll = JTable.createScrollPaneForTable(challengeTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(85,300,950,260);
				challengeButton.setFont(font2);
				challengeButton.setBounds(650,225,150,50);
				challengeButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("      Please Select Type\n         of Challenge");
				mainPanel.add(challengeButton);
				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				treePanel.updateUI();
				mainPanel.updateUI();
			}
		});
		conditionContinue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				generateSummary();
			}
		});
		conditionErrorBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				mainPanel.add(conditionContinue);   
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("      Please Select Your\n        Game Conditions");
				JScrollPane scroll = JTable.createScrollPaneForTable(conditionsTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(85,300,950,260);
				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				mainPanel.updateUI();
				treePanel.updateUI();

			}
		});

		taxBackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				speechBubble.setFont(font);
				speechBubble.setBounds(347,65,400,78);
				speechBubble.setText("     Please Select Your\n     Learning Taxonomy");
				mainPanel.changeFileName("learningTaxonomy.png");
				mainPanel.changeCoord(-100, 0);
				mainPanel.removeAll();
				bloomsScroll.updateUI();
				mainPanel.add(bloomsScroll);
				mainPanel.add(speechBubble);
				taxContButton.setEnabled(false);
				mainPanel.add(taxContButton);
				mainPanel.add(taxBox);
				mainPanel.updateUI();
			}
		});

		fullSumContinue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				if(rootNode.getNextSibling()!=topNode)
				{
					mainRoot.add(topNode);
					topNode.add(locationNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
					wizardTree.expandRow(1);
					wizardTree.collapseRow(0);
				}
				else
				{
					wizardTree.setSelectionPath(new TreePath(locationNode));
					wizardTree.expandRow(1);
					wizardTree.collapseRow(0);
				}
				backgroundPane = new JTextPane();
				backgroundPane.removeAll();
				backgroundPane.updateUI();
				speechBubble.setFont(font);
				speechBubble.setBounds(347,65,400,78);
				speechBubble.setText("     Please Select Your\n        Location");
				backgroundBox.setBounds(625,375,425,50);
				locationButton.setBounds(750,475,175,50);
				locationButton.setEnabled(false);
				locationButton.setFont(font2);
				backgroundPane.setBounds(50,300,400,260);
				backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				backgroundBox.setFont(font2);
				mainPanel.changeFileName("learningTaxonomy.png");
				mainPanel.changeCoord(-100, 0);
				mainPanel.add(speechBubble);
				mainPanel.add(backgroundBox);
				mainPanel.add(backgroundPane);
				mainPanel.add(locationButton);
				treePanel.updateUI();
				mainPanel.updateUI();
			}
		});


		locationButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				if(locationNode.getNextSibling() != characterNode)
				{
					topNode.add(characterNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}

				wizardTree.expandRow(1);
				Vector <Vector <Object>> v = new Vector<Vector <Object>>();
				try
				{
					File file = new File("Office, Classroom\\"+backgroundBox.getSelectedItem()+"_Chars.xml");
					JAXBContext jaxbContext = JAXBContext.newInstance(CharList.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					charList = (CharList) jaxbUnmarshaller.unmarshal(file);
					int numCharacters = charList.getCharacters().size();
					for(int i=0; i<numCharacters; i++)
					{
						Vector <Object> v1 = new Vector<Object>();
						ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Characters/"+charList.getCharacters().get(i).getFileName())));
						String charName = charList.getCharacters().get(i).getName();
						String charType = charList.getCharacters().get(i).getType();
						v1.add(charName);
						v1.add(charType);
						v1.add(icon);
						v.add(v1);
					}
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}

				charTable = new JTable(new MyTableModel(v,true,false,false));
				charTable.getColumnModel().getColumn(1).setPreferredWidth(150);
				charTable.getColumnModel().getColumn(0).setPreferredWidth(150);
				charTable.getColumnModel().getColumn(2).setPreferredWidth(375);
				charTable.setRowHeight(300);
				charTable.setCellSelectionEnabled(false);
				charTable.getTableHeader().setFont(font3);
				charTable.setFont(font4);
				JScrollPane scroll = JTable.createScrollPaneForTable(charTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(85,300,950,260);
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("      Here are Your\n       Typical Characters!");
				charButton.setBounds(715,225,150,50);
				charButton.setFont(font2);
				mainPanel.add(scroll);
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				mainPanel.add(speechBubble);
				mainPanel.add(charButton);
				treePanel.updateUI();
				mainPanel.updateUI();
			}
		});
		backgroundBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String background = (String)backgroundBox.getSelectedItem();
				if(background.equals("Please Choose A Location"))
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("learningTaxonomy.png");
					mainPanel.changeCoord(-100,0);
					backgroundPane = new JTextPane();
					backgroundPane.removeAll();
					backgroundPane.updateUI();
					backgroundPane.setBounds(50,300,400,260);
					backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					speechBubble.setFont(font);
					speechBubble.setBounds(347,65,400,78);
					speechBubble.setText("     Please Select Your\n        Location");
					locationButton.setEnabled(false);
					mainPanel.add(locationButton);
					mainPanel.add(speechBubble);
					mainPanel.add(backgroundBox);
					mainPanel.add(backgroundPane);
					mainPanel.updateUI();
				}

				else if(background.equals("Classroom"))
				{
					mainPanel.removeAll();
					mainPanel.changeFileName("learningTaxonomy.png");
					backgroundPane = new JTextPane();
					backgroundPane.removeAll();
					backgroundPane.updateUI();
					backgroundPane.setBounds(50,300,400,260);
					backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					try {
						ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/Classroom_1.png")));
						backgroundPane.insertIcon(icon);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					backGroundChoice = "Office, Classroom\\Backdrops/Classroom_1.png";
					speechBubble.setFont(font);
					speechBubble.setBounds(347,65,400,78);
					speechBubble.setText("   You Chose a Classroom!");
					locationButton.setEnabled(true);
					mainPanel.add(locationButton);
					mainPanel.add(speechBubble);
					mainPanel.add(backgroundBox);
					mainPanel.add(backgroundPane);
					mainPanel.updateUI();
				}
				else if(background.equals("Enchanted Forest"))
				{
					mainPanel.removeAll();
					speechBubble.setFont(font);
					speechBubble.setBounds(347,65,400,78);
					backgroundPane = new JTextPane();
					backgroundPane.removeAll();
					backgroundPane.updateUI();
					backgroundPane.setBounds(50,300,400,260);
					backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					try {
						ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/Forest night.png")));
						backgroundPane.insertIcon(icon);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					speechBubble.setText("  You Chose the Enchanted \n          Forest!");
					locationButton.setEnabled(true);
					mainPanel.add(locationButton);
					mainPanel.add(speechBubble);
					mainPanel.add(backgroundBox);
					mainPanel.add(backgroundPane);
					mainPanel.updateUI();
					backGroundChoice = "Office, Classroom\\Backdrops/Forest night.png";
				}
			}
		});
		charButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				if(characterNode.getNextSibling() != actNode)
				{
					topNode.add(actNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(1);
				template = new JTextPane();
				template.setEditable(false);
				showActs();
				templateScroll = new JScrollPane(template);
				template.setCaretPosition(0);
				templateScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				templateScroll.setBounds(180,300,720,330);
				template.updateUI();
				templateScroll.updateUI();
				speechBubble.setFont(font2);
				speechBubble.setBounds(425,30,345,100);
				speechBubble.setText("     Let's Begin With Creating\n         The First Acts of\n             Your Game!");
				actButton.setText("Continue");
				actButton.setFont(font2);
				actButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				actButton.setBounds(715, 225, 150, 50);
				actButton.setVisible(true);
				mainPanel.add(actButton);
				mainPanel.add(speechBubble);
				mainPanel.add(templateScroll);
				mainPanel.updateUI();
			}
		});
		actButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				mainPanel.changeCoord(0, 0);
				if(actNode.getNextSibling()!=addActNode)
				{
					topNode.add(addActNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(1);
				mainPanel.removeAll();
				speechBubble.setFont(font3);
				speechBubble.setBounds(455,100,345,105);
				speechBubble.setText("There Are Two Choices To Change\n         Your Game:\n 1. Add Another Set of Acts\n2. Add Additional Questions");
				actBox.setFont(font2);
				mainPanel.add(addActButton);
				mainPanel.add(actBox);
				mainPanel.add(speechBubble);
				mainPanel.updateUI();
			}
		});

		addActButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainPanel.removeAll();
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				actButton.setText("Add More");
				actButton.setFont(font2);
				actButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				actButton.setBounds(515, 225, 150, 50);
				actButton.setVisible(true);
				contActButton.setText("Continue");
				contActButton.setFont(font2);
				contActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				contActButton.setBounds(715, 225, 150, 50);
				contActButton.setVisible(true);


				if(addActNode.getNextSibling()!=actSumNode)

				{
					topNode.add(actSumNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(1);
				if(actBox.getSelectedItem().equals("Add Another Set of Acts"))
				{
					template = new JTextPane();
					template.setEditable(false);
					addMultiply();
					showActs();
					templateScroll = new JScrollPane(template);
					template.setCaretPosition(0);
					templateScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					templateScroll.setBounds(180,300,720,330);
					template.updateUI();
					templateScroll.updateUI();
					speechBubble.setFont(font2);
					speechBubble.setBounds(425,30,345,100);
					speechBubble.setText("\n       Here is How It Looks!");
					mainPanel.add(actButton);
					mainPanel.add(speechBubble);
					mainPanel.add(templateScroll);
					mainPanel.add(contActButton);
					mainPanel.updateUI();
				}
				else if(actBox.getSelectedItem().equals("Add a Question"))
				{
					template = new JTextPane();
					template.setEditable(false);
					addQuestion();
					templateScroll = new JScrollPane(template);
					template.setCaretPosition(0);
					templateScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					templateScroll.setBounds(180,300,720,330);
					template.updateUI();
					templateScroll.updateUI();
					showActs();
					speechBubble.setFont(font2);
					speechBubble.setBounds(425,30,345,100);
					speechBubble.setText("\n       Here is How It Looks!");
					mainPanel.add(actButton);
					mainPanel.add(speechBubble);
					mainPanel.add(templateScroll);
					mainPanel.add(contActButton);
					mainPanel.updateUI();
				}
				else if(actBox.getSelectedItem().equals("Back to Original"))
				{
					File file = new File("Office, Classroom\\template.xml");
					JAXBContext jaxbContext;
					try {
						jaxbContext = JAXBContext.newInstance(GameTemplate.class);
						Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
						gametemplate = (GameTemplate) jaxbUnmarshaller.unmarshal(file);
					} catch (JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					template = new JTextPane();
					template.setEditable(false);
					showActs();
					templateScroll = new JScrollPane(template);
					template.setCaretPosition(0);
					templateScroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					templateScroll.setBounds(180,300,720,330);
					template.updateUI();
					templateScroll.updateUI();
					speechBubble.setFont(font2);
					speechBubble.setBounds(425,30,345,100);
					speechBubble.setText("\n       Here is How It Looks!");
					mainPanel.add(actButton);
					mainPanel.add(speechBubble);
					mainPanel.add(templateScroll);
					mainPanel.add(contActButton);
					mainPanel.updateUI();
				}
				else
				{
					mainPanel.removeAll();
					if(actSumNode.getNextSibling()!=loActNode)
					{
						topNode.add(loActNode);
						DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
						model.reload(mainRoot);
					}
					wizardTree.expandRow(1);
					mainPanel.changeFileName("wizardBackground.png");
					mainPanel.changeCoord(0, -70);
					speechBubble.setFont(font2);
					speechBubble.setBounds(415,45,400,90);
					speechBubble.setText("    Please Select Which Learning\n      Objectives You Would Like\n            For Each Act!");
					makeLOActTable();
					JScrollPane scroll = JTable.createScrollPaneForTable(loActTable);
					scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					scroll.setBounds(40,300,1000,350);
					loActButton.setText("Continue");
					loActButton.setFont(font2);
					loActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
					loActButton.setBounds(715, 225, 150, 50);
					mainPanel.add(scroll);
					mainPanel.add(loActButton);
					mainPanel.add(speechBubble);
					mainPanel.updateUI();
				}
				mainPanel.updateUI();
			}
		});

		contActButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.removeAll();
				if(actSumNode.getNextSibling()!=loActNode)
				{
					topNode.add(loActNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(1);
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				speechBubble.setFont(font2);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("    Please Select Which Learning\n      Objectives You Would Like\n            For Each Act!");
				makeLOActTable();
				JScrollPane scroll = JTable.createScrollPaneForTable(loActTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(40,300,1000,350);
				loActButton.setText("Continue");
				loActButton.setFont(font2);
				loActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				loActButton.setBounds(715, 225, 150, 50);
				mainPanel.add(scroll);
				mainPanel.add(loActButton);
				mainPanel.add(speechBubble);
				mainPanel.updateUI();

			}
		});

		loActButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.removeAll();

				if(!checkLOActTable()){
					printLOActTableError();
					return;
				}

				if(loActNode.getNextSibling()!=quesTableNode)
				{
					topNode.add(quesTableNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}

				wizardTree.expandRow(1);
				mainPanel.changeFileName("wizardBackground.png");
				mainPanel.changeCoord(0, -70);
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("Please Select Your Types\n Of Questions!");

				int numOfQuestions = numOfQuestions();
				makeQuesTable(numOfQuestions);

				@SuppressWarnings("deprecation")
				JScrollPane scroll = JTable.createScrollPaneForTable(questionTable);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				scroll.setBounds(100,300,950,350);

				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				mainPanel.add(quesButton);
				mainPanel.updateUI();

			}
		});

		quesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				mainPanel.removeAll();
				mainPanel.changeFileName("wizardBackround.png");
				mainPanel.changeCoord(0,-70);
				if(quesTableNode.getNextSibling()!=finalSumNode)
				{
					topNode.add(finalSumNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				generateFinalSum();
				JScrollPane scroll = new JScrollPane(finalSumPane);
				scroll.setBounds(180,300,720,330);
				scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
				wizardTree.expandRow(1);
				speechBubble.setFont(font);
				speechBubble.setBounds(415,45,400,90);
				speechBubble.setText("Here Is A Final Summary!"); 
				mainPanel.add(scroll);
				mainPanel.add(speechBubble);
				mainPanel.add(finalSumButton);
				mainPanel.updateUI();
			}
		});

		finalSumButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				mainPanel.removeAll();
				mainPanel.changeFileName("introBackground2.png");
				mainPanel.changeCoord(0,0);
				if(finalSumNode.getNextSibling()!=conclusionNode)
				{
					topNode.add(conclusionNode);
					DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
					model.reload(mainRoot);
				}
				wizardTree.expandRow(1);
				speechBubble.setFont(font);
				speechBubble.setBounds(400,100,420,120);
				speechBubble.setText("Congratulations You Are Done!\nPress Save To Save The Game");
				mainPanel.add(speechBubble);
				mainPanel.add(saveButton);
				mainPanel.updateUI();
			}
		});

		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Used from InputWizard.java
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Enter a name for the file");
				chooser.setFileFilter(new FileNameExtensionFilter("Game XML", "XML"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retval = chooser.showSaveDialog(null);

				if(retval == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					// check for .xml (of any case variation) at the end ($) of the filename
					if(!file.getName().matches(".*[.][Xx][Mm][Ll]$"))
					{
						System.out.println("didn't match");
						file = new File(file.getPath() + ".XML");
					}
					pathName = file.getPath();
					System.out.println("saved as " + file.getPath());
				}
				else
				{
					System.out.println("Save command cancelled by user.");
				}
				createGame2(1);
			}

		});
		window.add(main);
		window.setVisible(true);

	}

	public void addMultiply(){

		for(int i=0;i<3;i++)
		{
			Act2 a = new Act2();
			a = gametemplate.getActs().get(i);
			gametemplate.getActs().add(a);
		}
	}

	public void addQuestion(){
		//reference thing
		for(int i = 0; i < 3; i++){
			if(gametemplate.getActs().get(i).getName().equals("Progress"))
			{
				Scene2 scene = new Scene2();
				scene = gametemplate.getActs().get(i).getScenes().get(0);
				gametemplate.getActs().get(i).getScenes().add(scene);
			}
		}
		
	}

	public int numOfQuestions()
	{
		int multiply = gametemplate.getActs().size()/3;
		int ques = gametemplate.getActs().get(1).getScenes().size();
		return multiply*ques;
	}

	public void createGame(int numOfMuls){
		Vector<Vector<Object>> v = ((MyTableModel)questionTable.getModel()).getData();
		int quesIndex = 0;
		Game g = new Game();
		//Learing Objective for Game
		for(int i = 0; i< loList.size(); i++){
			LearningObjectiveType lo = new LearningObjectiveType();
			lo.setLearningObjective(loList.get(i));
			g.getLearningObjective().add(lo);
		}
		//Characters for Game
		Vector<Vector<Object>> v2 = ((MyTableModel)charTable.getModel()).getData();
		//for(int i = 0; i< v2.size(); i++){
		Player p = new Player();
		p.setPlayerID("TOM");
		p.setCharacterName("Tom");
		p.setAutonomousBehaviour("n/a");
		p.setRewards("none");
		p.setName("Tom");
		p.setSize(new Size(100,100));
		p.setLocation(new Location(25,25));
		Profile prof = new Profile();
		prof.setProfilePhoto("none");
		prof.setTitle("Dr");
		prof.setYearsOfExperience(8);
		prof.setSkills(new ArrayList<String>() {{
			add("Excellent Mult. Tables");
			add("Nice Eyes");
		}});
		prof.setDemographics(new ArrayList<String>() {{
			add("Alaskan");
			add("Nice Eyes");
		}});
		prof.setDegrees(new ArrayList<String>() {{
			add("Associates in Econmonics");
			add("Celsius");
		}});
		p.setProfile(prof);
		g.getCharacter().add(p);
		for(int i=0; i<charList.getCharacters().size();i++)
		{
			ImageIcon icon;
			try {
				icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Characters/"+charList.getCharacters().get(i).getFileName())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NonPlayer np = new NonPlayer();
			np.setCharacterName(charList.getCharacters().get(i).getName());
			np.setAutonomousBehaviour(charList.getCharacters().get(i).getAutonomousBehavior());
			np.setRewards(charList.getCharacters().get(i).getRewards());
			np.setName(charList.getCharacters().get(i).getName());
			np.setSize(new Size(100,100));
			np.setLocation(new Location(25,25));
			Profile prof2 = new Profile();
			prof2.setProfilePhoto("Office, Classroom\\Characters/"+charList.getCharacters().get(i).getFileName());
			prof2.setTitle(charList.getCharacters().get(i).getProfile().getTitle());
			prof2.setYearsOfExperience(charList.getCharacters().get(i).getProfile().getYearsOfExperience());
			prof2.setSkills(charList.getCharacters().get(i).getProfile().getSkills());
			prof2.setDemographics(charList.getCharacters().get(i).getProfile().getDemographics());
			prof2.setDegrees(charList.getCharacters().get(i).getProfile().getDegrees());
			np.setProfile(prof2);
			g.getCharacter().add(np);
		}

		//Acts for Game    
		ArrayList <Act> actList = new ArrayList<Act>();
		for(int h = 0; h < numOfMuls; h++){
			for(int i = 0; i < gametemplate.getActs().size(); i++){
				Act a = new Act();
				LearningObjectiveType lo = new LearningObjectiveType();
				lo.setLearningObjective(loList.get(h));
				ArrayList<LearningObjectiveType> los = new ArrayList<LearningObjectiveType>();
				los.add(lo);
				a.setLearningObjective(los);
				//Scene for Act
				for(int j = 0; j < gametemplate.getActs().get(i).getScenes().size(); j++){
					Scene s = new Scene();
					s.setLearningObjective(los);
					BackgroundType bg = new BackgroundType();
					bg.setBackground(backGroundChoice);
					for(int k = 0; k < gametemplate.getActs().get(i).getScenes().get(j).getScreens().size(); k++){
						Screen scr = new Screen();
						scr.setLearningObjective(los);
						if(gametemplate.getActs().get(i).getScenes().get(j).getScreens().get(k).getType().equals("Question"))
						{
							QuizChallenge ch  = new QuizChallenge();
							if(v.get(quesIndex).get(1).equals("Dialog (K,C,Ap) -question/answer/& follow-up")){
								ch.setClassification(ClassificationType.INTERACTIVE);
								Introduction intro = new Introduction();
								intro.setIntroductionName("Welcome");
								ch.setIntroduction(intro);
								Layout layout = new Layout();
								layout.setLayoutName("Multiple Choice Layout");
								ch.setLayout(layout);
								Summary summary = new Summary();
								summary.setSummaryName("intro");
								ch.setSummary(summary);
								MultipleChoiceItem it = new MultipleChoiceItem();

								try
								{
									//Only First Learning Objective
									String str = loList.get(0);
									str = str.substring(3, 33);
									File file = new File("Office, Classroom\\QuestionRepo/"+ str+"_Dialog.xml");
									JAXBContext jaxbContext = JAXBContext.newInstance(SuggQuestion.class);

									Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
									SuggQuestion sugg = (SuggQuestion) jaxbUnmarshaller.unmarshal(file);
									//it = sugg.getQuesList().get(0);
									System.out.println(sugg);
									System.out.println(it.getOption().get(0).getAssessment());
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
								//it.setItemname("intro");
								ch.setItem(it);
								scr.getChallenge().add(ch);
								s.getScreen().add(scr);
							}else if(v.get(quesIndex).get(1).equals("Deliberation (K,C,Ap,S,E) -drag and drop & consequence")){
								ch.setClassification(ClassificationType.INTERACTIVE);
								Introduction intro = new Introduction();
								intro.setIntroductionName("Welcome");
								ch.setIntroduction(intro);
								Layout layout = new Layout();
								layout.setLayoutName("Intro");
								ch.setLayout(layout);
								Summary summary = new Summary();
								summary.setSummaryName("intro");
								ch.setSummary(summary);
								Item it = new Item();
								it.setItemname("intro");
								ch.setItem(it);
								scr.getChallenge().add(ch);
								s.getScreen().add(scr);
							}

						}
						else
						{
							//scr.getChallenge();
							s.getScreen().add(scr);
						}

					}
					s.setBackground(bg);
					a.getScene().add(s);
				}


				actList.add(a);
			}
		}
		g.setAct(actList);
		GameGenerator noth = new GameGenerator();
		try {
			noth.exportGame(g, pathName);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createGame2(int numOfMuls){
		Game g = new Game();
		try {
			File file = new File("WizardRepo\\EnvTemplates/"+ backgroundBox.getSelectedItem()+"_Template.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			g = (Game) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 1; i<gametemplate.getActs().get(1).getScenes().size(); i++){
			g.getAct().get(1).getScene().add(g.getAct().get(1).getScene().get(0));
		}

		for(int i = 1; i < gametemplate.getActs().size()/3; i++){
			g.getAct().add(g.getAct().get(0));
			g.getAct().add(g.getAct().get(1));
			g.getAct().add(g.getAct().get(2));
		}

		int quesIndex = 0;
		for(int i = 0; i < gametemplate.getActs().size(); i++){

			ArrayList<LearningObjectiveType> los= new ArrayList<LearningObjectiveType>();
			for(int numOfLO =0; numOfLO < gametemplate.getActs().get(i).getLearningObjective().size(); numOfLO++){
				LearningObjectiveType toAdd = new LearningObjectiveType();
				toAdd.setLearningObjective(gametemplate.getActs().get(i).getLearningObjective().get(numOfLO).getLearningObjective());
				System.out.println("LO ===" + gametemplate.getActs().get(i).getLearningObjective().get(numOfLO).getLearningObjective());
				los.add(toAdd);
			}

			g.getAct().get(i).setLearningObjective(los);
			for(int j = 0; j < gametemplate.getActs().get(i).getScenes().size(); j++){
				g.getAct().get(i).getScene().get(j).setLearningObjective(los);


				for(int k = 0; k < gametemplate.getActs().get(i).getScenes().get(j).getScreens().size(); k++){
					g.getAct().get(i).getScene().get(j).getScreen().get(k).setLearningObjective(los);

					if(gametemplate.getActs().get(i).getName().equals("Progress")){
						//Question additon
						QuizChallenge ch = new QuizChallenge();
						Vector<Vector<Object>> v = ((MyTableModel)(questionTable.getModel())).getData();
						String challengeType = ((String)v.get(i).get(1)).substring(0,6);
						//just gets first Learning OBjective  of act
						String str = los.get(0).getLearningObjective();
						str = str.substring(3, 33);


						try {
							File file = new File("WizardRepo\\QuestionRepo/"+ str+"_"+challengeType+".xml");
							JAXBContext jaxbContext;
							jaxbContext = JAXBContext.newInstance(SuggQuestion.class);
							Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
							SuggQuestion sugg = (SuggQuestion) jaxbUnmarshaller.unmarshal(file);
							//Just get first question
							ch = sugg.getQuesList().get(0);
						} catch (JAXBException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(((MultipleChoiceItem)ch.getItem()).getOption().get(0).getAssessment());
						g.getAct().get(i).getScene().get(j).getScreen().get(k).getChallenge().add(ch);

					}
				}
			}
		}
		GameGenerator noth = new GameGenerator();
		try {
			noth.exportGame(g, pathName);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void makeLOActTable(){
		int progressActs = gametemplate.getActs().size()/3;
		Vector<Vector<Object>> v = new Vector<Vector<Object>>();
		for(int i = 0; i < progressActs; i++){
			Vector<Object> v1 = new Vector<Object>();
			v1.add("Progress "+(i+1));
			v1.add(new Boolean(true));
			v.add(v1);
			for(int j=0; j<loList.size();j++)
			{
				Vector<Object> v2 = new Vector<Object>();
				v2.add(loList.get(j));
				v2.add(new Boolean(false));
				v.add(v2);
			}
		}
		DefaultTableCellRenderer r = new DefaultTableCellRenderer()
		{
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				if(((String)value).charAt(0)=='-')
				{
					JTextArea text = new JTextArea();
					text.setText((String)value);
					text.setLineWrap(true);
					text.setWrapStyleWord(true);
					text.setFont(font4);
					return text;
				}
				else
				{
					this.setFont(font2);
				}
				return this;
			}
		};
		loActTable = new JTable(new MyTableModel(v,false,true,false));
		loActTable.getColumnModel().getColumn(0).setCellRenderer(r);

		loActTable.setRowHeight(120);
		loActTable.setShowHorizontalLines(true);
		loActTable.setRowSelectionAllowed(true);
		loActTable.setColumnSelectionAllowed(true);
		loActTable.getColumnModel().getColumn(1).setMaxWidth(75);
		loActTable.getTableHeader().setFont(font3);
		loActTable.setFont(font3);
		loActTable.setGridColor(Color.black);
	}

	public void printLOActTableError(){
		mainPanel.removeAll();

		speechBubble.setBounds(415,45,400,170);
		speechBubble.setText("\n   You Must Select Atleast One\n      Learning Objective for\n Each Progress! Please Click Back!");
		speechBubble.setFont(font2);
		contActButton.setText("Back");
		contActButton.setFont(font2);
		contActButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		contActButton.setBounds(700,550,150,50);
		mainPanel.changeCoord(0, 0);
		mainPanel.add(speechBubble);
		mainPanel.add(contActButton);
		mainPanel.changeFileName("errorBackground.png");
		mainPanel.updateUI();
	}

	public boolean checkLOActTable(){
		Vector<Vector<Object>> v = ((MyTableModel)(loActTable.getModel())).getData();
		boolean selected = false;
		for(int i = 1; i < v.size(); i++){

			if((boolean)v.get(i).get(1) == true && !((String)v.get(i).get(0)).substring(0,8).equals("Progress")){
				selected = true;
			}
			else if(((String)v.get(i).get(0)).substring(0,8).equals("Progress") || i == v.size()-1 || i == v.size()){
				if(selected  == false){
					return false;
				}else{
					selected = false;
				}
			}
		}

		return true;
	}

	public void makeQuesTable(int numOfQuestions)
	{
		ArrayList<String> challengesSelected = new ArrayList<String>();
		Vector<Vector<Object>> data2 = ((MyTableModel)challengeTable.getModel()).getData();
		for(int i = 0; i<data2.size(); i++ ){
			if((boolean)data2.get(i).get(1) == true){
				challengesSelected.add((String)data2.get(i).get(0));
			}
		}

		ArrayList<String> conditionsSelected = new ArrayList<String>();
		Vector<Vector<Object>> data = ((MyTableModel)conditionsTable.getModel()).getData();
		for(int i = 0; i<data.size(); i++ ){
			if((boolean)data.get(i).get(1) == true){
				conditionsSelected.add((String)data.get(i).get(0));
			}
		}

		firstQuesBox = new JComboBox[numOfQuestions];
		secondQuesBox = new JComboBox[numOfQuestions];

		String[] challenges = new String[challengesSelected.size()+1];
		String[] conditions = new String[conditionsSelected.size()+1];
		challenges[0] = "Please Select Challenge";
		conditions[0] = "Please Select Condition";
		
		for(int j=0; j<challengesSelected.size(); j++)
		{
			challenges[j+1] = challengesSelected.get(j);
		}

		for(int j=0; j<conditionsSelected.size(); j++)
		{
			conditions[j+1] = conditionsSelected.get(j);
		}

		Vector <Vector <Object>> v = new Vector<Vector <Object>>();

		for(int i=0; i<numOfQuestions; i++)
		{
			firstQuesBox[i] = new JComboBox(challenges);
			secondQuesBox[i] = new JComboBox(conditions);
			firstQuesBox[i].setFont(font2);
			secondQuesBox[i].setFont(font2);
			Vector <Object> v1 = new Vector<Object>();
			v1.add("Question " + (i+1));
			v1.add(challenges[0]);
			v1.add(conditions[0]);
			v.add(v1);
		}

		questionTable = new JTable(new MyTableModel(v, false,false,true));
		TableColumn challengeColumn = questionTable.getColumnModel().getColumn(1);
		TableColumn conditionsColumn = questionTable.getColumnModel().getColumn(2);
		challengeColumn.setCellEditor(new DefaultCellEditor(firstQuesBox[0]));
		conditionsColumn.setCellEditor(new DefaultCellEditor(secondQuesBox[0]));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		challengeColumn.setCellRenderer(renderer);
		conditionsColumn.setCellRenderer(renderer);
		questionTable.setRowHeight(45);
		questionTable.setShowHorizontalLines(true);
		questionTable.setRowSelectionAllowed(true);
		questionTable.setColumnSelectionAllowed(true);
		questionTable.getTableHeader().setFont(font3);
		questionTable.setFont(font2);
		questionTable.setGridColor(Color.black);
		questionTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		questionTable.getColumnModel().getColumn(1).setPreferredWidth(490);
		questionTable.getColumnModel().getColumn(2).setPreferredWidth(300);
	}

	public void showActs()
	{
		StyledDocument doc = template.getStyledDocument();
		javax.swing.text.Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		javax.swing.text.Style normal = doc.addStyle("font 2", def);
		StyleConstants.setFontFamily(def, "Comic Sans MS");
		StyleConstants.setFontSize(def, 22);
		StyleConstants.setBold(def, true);
		StyleConstants.setUnderline(def, true);
		javax.swing.text.Style s = doc.addStyle("font 3", normal);
		StyleConstants.setUnderline(s, false);
		StyleConstants.setFontSize(s, 18);
		StyleConstants.setBold(s, false);
		ArrayList<String> challengesSelected = new ArrayList<String>();
		Vector<Vector<Object>> data2 = ((MyTableModel)challengeTable.getModel()).getData();
		for(int i = 0; i<data2.size(); i++ ){
			if((boolean)data2.get(i).get(1) == true){
				challengesSelected.add((String)data2.get(i).get(0));
			}
		}
		int challengeIndex = 0;
		try
		{
			int count =1;
			int numActs = gametemplate.getActs().size();
			for(int i=0;i<numActs;i++)
			{
				doc.insertString(doc.getLength(), gametemplate.getActs().get(i).getName()+"\n", doc.getStyle("font 2"));
				int numScene2s = gametemplate.getActs().get(i).getScenes().size();
				for(int j=0; j<numScene2s; j++)
				{
					doc.insertString(doc.getLength(), "\tScene "+ (j+1) + "\n", doc.getStyle("font 3"));
					int numScreens = gametemplate.getActs().get(i).getScenes().get(j).getScreens().size();
					for(int k=0; k<numScreens; k++)
					{
						doc.insertString(doc.getLength(), "\t"+"\tScreen "+(k+1)+"="
								+gametemplate.getActs().get(i).getScenes().get(j).getScreens()
								.get(k).getType(), doc.getStyle("font 3"));
						String type = gametemplate.getActs().get(i).getScenes().get(j).getScreens()
								.get(k).getType();
						if(type.equals("Question")){
							doc.insertString(doc.getLength(), "  "+count+"\n", doc.getStyle("font 3"));
							count++;
							doc.insertString(doc.getLength(), "\t\t\tChallenge: "+challengesSelected.get(challengeIndex)+"\n", doc.getStyle("font 3"));
							if(challengeIndex >= challengesSelected.size()-1){
								challengeIndex = 0;
							}else{
								challengeIndex++;
							}
						}
						else
						{
							doc.insertString(doc.getLength(),"\n", doc.getStyle("font 3"));
						}
					}
				}
			}
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		template.setCaretPosition(0);
	}
	
	public void generateFinalSum()
	{
		finalSumPane = new JTextPane();
		finalSumPane.updateUI();
		finalSumPane.setEditable(false);
		StyledDocument doc = finalSumPane.getStyledDocument();
		javax.swing.text.Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		javax.swing.text.Style normal = doc.addStyle("font 2", def);
		StyleConstants.setAlignment(normal, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontFamily(def, "Comic Sans MS");
		StyleConstants.setFontSize(def, 22);
		StyleConstants.setBold(def, true);
		doc.setParagraphAttributes(0, doc.getLength(), def, false);
		StyleConstants.setUnderline(def, false);
		javax.swing.text.Style s = doc.addStyle("font 1", normal);
		StyleConstants.setFontSize(s, 24);
		StyleConstants.setBold(s, true);
		StyleConstants.setUnderline(s, true);
		javax.swing.text.Style s2 = doc.addStyle("font 3", normal);
		StyleConstants.setUnderline(s2, false);
		StyleConstants.setFontSize(s2, 18);
		StyleConstants.setBold(s2, false);
		javax.swing.text.Style s3 = doc.addStyle("font 4", normal);
		StyleConstants.setUnderline(s3, false);
		StyleConstants.setFontSize(s3, 20);
		StyleConstants.setBold(s3, false);
		MyTableModel tm = (MyTableModel) loActTable.getModel();
		Vector<Vector<Object>> v = tm.getData();
		MyTableModel tm2 = (MyTableModel) questionTable.getModel();
		Vector<Vector<Object>> v2 = tm2.getData();
		int numActs = gametemplate.getActs().size();
		
		ArrayList<String> challengesSelected = new ArrayList<String>();
		Vector<Vector<Object>> data2 = ((MyTableModel)challengeTable.getModel()).getData();
		for(int i = 0; i<data2.size(); i++ ){
			if((boolean)data2.get(i).get(1) == true){
				challengesSelected.add((String)data2.get(i).get(0));
			}
		}

		ArrayList<String> conditionsSelected = new ArrayList<String>();
		Vector<Vector<Object>> data = ((MyTableModel)conditionsTable.getModel()).getData();
		for(int i = 0; i<data.size(); i++ ){
			if((boolean)data.get(i).get(1) == true){
				conditionsSelected.add((String)data.get(i).get(0));
			}
		}
		
		int count = 0;
		try {
			doc.insertString(doc.getLength(), "Location\n", doc.getStyle("font 1"));
			doc.insertString(doc.getLength(), (String) backgroundBox.getSelectedItem()+"\n\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "Game Layout\n", doc.getStyle("font 1"));
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<numActs;i++)
		{
			try {
				doc.insertString(doc.getLength(), gametemplate.getActs().get(i).getName()+"\n", doc.getStyle("font 2"));
				if(gametemplate.getActs().get(i).getName().equals("Conclusion"))
				{
					doc.insertString(doc.getLength(), "\n", doc.getStyle("font 2"));
				}
				if(gametemplate.getActs().get(i).getName().equals("Progress"))
				{
					doc.insertString(doc.getLength(), "\tLearning Objectives:\n", doc.getStyle("font 4"));
					for(int j = count*(loList.size()+1); j<=(loList.size()*(count+1)); j++)
					{
						if(((String)v.get(j).get(0)).substring(0,8).equals("Progress")){
							for(int n=0; n<=loList.size(); n++)
							{
								if((boolean)v.get(j+n).get(1) == true)
								{
									if(!((String)v.get(j+n).get(0)).substring(0,8).equals("Progress"))
									{
										LearningObjectiveType lo = new LearningObjectiveType();
										lo.setLearningObjective((String) v.get(j+n).get(0));
										gametemplate.getActs().get(i).getLearningObjective().add(lo);
										doc.insertString(doc.getLength(), "\t"+(String) v.get(j+n).get(0) +"\n", doc.getStyle("font 3"));
									}
								}
							}
						}
					}
					count++;
				}
				else
				{
					for(int k=0; k<loList.size();k++)
					{
						LearningObjectiveType lo = new LearningObjectiveType();
						lo.setLearningObjective(loList.get(k));
						gametemplate.getActs().get(i).getLearningObjective().add(lo);
					}
				}
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			doc.insertString(doc.getLength(), "Game Questions\n", doc.getStyle("font 1"));
			for(int i=0; i<v2.size();i++)
			{
				doc.insertString(doc.getLength(), "Question " +(i+1)+"\n", doc.getStyle("font 4"));
				if(v2.get(i).get(1).equals("Please Select Challenge"))
				{
					 v2.get(i).set(1, challengesSelected.get(0));
				}
				if(v2.get(i).get(2).equals("Please Select Condition"))
				{
					v2.get(i).set(2, conditionsSelected.get(0));
				}
				doc.insertString(doc.getLength(), "Challenge = " +v2.get(i).get(1) +"\n", doc.getStyle("font 3"));
				doc.insertString(doc.getLength(), "Condition = " +v2.get(i).get(2) + "\n\n", doc.getStyle("font 3"));
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finalSumPane.setEditable(false);
		finalSumPane.setCaretPosition(0);
	}
	
	public Scene2 makeScene2()
	{
		Scene2 scene = new Scene2();
		Screen2 screen = new Screen2();
		Screen2 screen2 = new Screen2();
		screen.setType("Question");
		screen2.setType("Transition: Response To Answer");
		scene.getScreens().add(screen);
		scene.getScreens().add(screen2);
		return scene;
	}
	public JTable constructCharTable(){
		JTable jt;
		Vector <Vector <Object>> v = new Vector <Vector <Object>>();
		ImageIcon character;
		for(int i = 1; i <= 29; i++){
			try{
				Vector <Object> v1 = new Vector<Object>();
				if(i == 16){
					character = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_"+i+"/char"+i+"_StandSmileOpen.png")));
					v1.add(character);
					v1.add( new Boolean(false));
					v.add(v1);
				}else{
					character = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_"+i+"/char"+i+"_StandSmileClosed.png")));
					v1.add(character);
					v1.add( new Boolean(false));
					v.add(v1);
				}

			}catch(Exception io){
				System.out.println("char table prob: " + i );

			}           
		}
		jt = new JTable(new MyTableModel(v,true,false,false));
		jt.setRowHeight(275);
		//jt.getColumnModel().getColumn(1).setPreferredWidth(440);
		//jt.getColumnModel().getColumn(0).setPreferredWidth(150);
		jt.setRowSelectionAllowed(false);
		jt.getTableHeader().setFont(font3);
		jt.setFont(font2);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.setGridColor(Color.black);

		return jt;

	}

	private boolean isErrorLO()
	{
		MyTableModel tm = (MyTableModel) loTable.getModel();
		Vector <Vector <Object>> v = tm.getData();
		for(int i=0;i<tm.getRowCount();i++)
		{
			if((boolean)v.get(i).get(1)==true)
			{
				if(((String)(v.get(i).get(0))).charAt(0) == '-'){
					return false;
				}
			}
		}
		return true;
	}

	private boolean isErrorLearningObjective()
	{
		MyTableModel tm = (MyTableModel) table.getModel();
		Vector <Vector <Object>> v = tm.getData();
		int mainCount = 0;
		int subCount = 0;
		boolean finalError = false;
		for(int i=0;i<table.getRowCount();i++)
		{
			if((boolean)v.get(i).get(1)==true)
			{
				if(((String)(v.get(i).get(0))).charAt(0) == '-'){
					subCount++;
				}else{
					int j=1;
					boolean error = true;
					while(((String)(v.get(i+j).get(0))).charAt(0) == '-')
					{
						if((boolean)v.get(i+j).get(1) == true)
						{
							error=false;
						}
						j++;
					}
					if(error == true){
						finalError =true;
						break;
					}
					mainCount++;
				}
			}
		}
		if(mainCount==0 ||finalError)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void printErrorLearningTaxonomy()
	{
		mainPanel.removeAll();
		speechBubble.setBounds(415,45,400,170);
		speechBubble.setText("\n      You Must Select A \n          Taxonomy!\n       Please Click Back!");
		speechBubble.setFont(font);
		taxBackButton.setBounds(700,550,150,50);
		mainPanel.changeCoord(0, 0);
		mainPanel.add(speechBubble);
		mainPanel.add(taxBackButton);
		mainPanel.changeFileName("errorBackground.png");
		mainPanel.updateUI();
	}
	private void printErrorLO()
	{
		mainPanel.removeAll();
		speechBubble.setBounds(415,45,400,170);
		speechBubble.setText("\n      You Must Select A \n      Learning Objective!\n       Please Click Back!");
		speechBubble.setFont(font);
		summaryBack.setBounds(700,550,150,50);
		mainPanel.changeCoord(0, 0);
		mainPanel.add(speechBubble);
		mainPanel.add(summaryBack);
		mainPanel.changeFileName("errorBackground.png");
		mainPanel.updateUI();
	}

	private void printErrorLearningObjective()
	{
		mainPanel.removeAll();
		speechBubble.setBounds(415,45,400,170);
		speechBubble.setText("\n      You Must Select A \n        Main/Sub Objective!\n       Please Click Back!");
		speechBubble.setFont(font);
		summaryBack.setBounds(700,550,150,50);
		mainPanel.changeCoord(0, 0);
		mainPanel.add(speechBubble);
		mainPanel.add(summaryBack);
		mainPanel.changeFileName("errorBackground.png");
		mainPanel.updateUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	private boolean isErrorChallenge()
	{
		MyTableModel tm = (MyTableModel) challengeTable.getModel();
		Vector<Vector<Object>> v = tm.getData();
		boolean empty = true;
		for(int i = 0; i < v.size(); i++){
			if((boolean)v.get(i).get(1) == true){
				empty = false;
			}
		}
		if(empty)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private void printErrorChallenge()
	{
		mainPanel.removeAll();
		speechBubble.setBounds(415,45,400,170);
		speechBubble.setText("\n      You Must Select A \n             Challenge!\n       Please Click Back!");
		speechBubble.setFont(font);
		challengeErrorBack.setBounds(700,550,150,50);
		mainPanel.changeCoord(0, 0);
		mainPanel.add(speechBubble);
		mainPanel.add(challengeErrorBack);
		mainPanel.changeFileName("errorBackground.png");
		mainPanel.updateUI();
	}
	private Vector<Vector<Object>> generateChallengeTable()
	{
		MyTableModel tm = (MyTableModel) subTaxTable.getModel();
		Vector <Vector <Object>> v = tm.getData();
		Vector <Vector <Object>> v2 = new Vector<Vector <Object>>();
		boolean hit = false;
		/*
         if((boolean)v.get(0).get(1)==true && (boolean)v.get(1).get(1)==true && (boolean)v.get(2).get(1)==true &&
                 (boolean)v.get(3).get(1)==true && (boolean)v.get(5).get(1)==true)
         {
             hit = true;
             Vector<Object> v1 = new Vector<Object>();
             v1.add("Composition (K,C,Ap,An,E) -evaluate, create & consequence");
             v1.add( new Boolean(false));
             v2.add(v1);
         }
		 */
		if((boolean)v.get(0).get(1)==true && (boolean)v.get(1).get(1)==true && (boolean)v.get(2).get(1)==true &&
				(boolean)v.get(4).get(1)==true && (boolean)v.get(5).get(1)==true)
		{
			hit = true;
			Vector<Object> v1 = new Vector<Object>();
			v1.add("Deliberation (K,C,Ap,S,E) -drag and drop & consequence");
			v1.add( new Boolean(false));
			v2.add(v1);
		}
		if((boolean)v.get(0).get(1)==true && (boolean)v.get(1).get(1)==true && (boolean)v.get(2).get(1)==true)
		{
			hit = true;
			Vector<Object> v1 = new Vector<Object>();
			v1.add("Dialog (K,C,Ap) -question/answer/& follow-up");
			v1.add( new Boolean(false));
			v2.add(v1);
		}
		if(!hit){
			Vector<Object> v1 = new Vector<Object>();
			v1.add("Dialog (K,C,Ap) -question/answer/& follow-up");
			v1.add( new Boolean(false));
			v2.add(v1);
		}
		return v2;
	}

	public static void main(String[] args)
	{
		Wizard wiz = new Wizard();
	}
	public void generateSummary(){
		mainPanel.removeAll();
		mainPanel.changeFileName("wizardBackground.png");
		mainPanel.changeCoord(0,-70);
		summary = new JTextPane();
		summary.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
		summary.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		StyledDocument doc = summary.getStyledDocument();
		javax.swing.text.Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		javax.swing.text.Style normal = doc.addStyle("font 2", def);
		StyleConstants.setAlignment(def, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), def, false);
		StyleConstants.setFontFamily(def, "Comic Sans MS");
		StyleConstants.setFontSize(def, 22);
		StyleConstants.setUnderline(def, false);
		StyleConstants.setBold(def, true);
		javax.swing.text.Style s = doc.addStyle("font 3", normal);
		StyleConstants.setFontSize(s, 18);
		StyleConstants.setBold(s, false);
		MyTableModel tm = (MyTableModel) conditionsTable.getModel();
		Vector <Vector <Object>> v = tm.getData();
		int mainCount = 0;
		try{
			doc.insertString(doc.getLength(), "Institution:\n", doc.getStyle("font 2"));
			doc.insertString(doc.getLength(), institutionBox.getSelectedItem()+"\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "Domain:\n", doc.getStyle("font 2"));
			doc.insertString(doc.getLength(), domainBox.getSelectedItem()+"\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "Level:\n", doc.getStyle("font 2"));
			doc.insertString(doc.getLength(), gradeBox.getSelectedItem()+"\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "Content:\n", doc.getStyle("font 2"));
			doc.insertString(doc.getLength(), subjectBox.getSelectedItem()+"\n", doc.getStyle("font 3"));
		}
		catch(Exception e1)
		{
			System.out.println("Font does not Exist!");
		}
		MyTableModel tmLO = (MyTableModel) table.getModel();
		Vector <Vector <Object>> vLO = tmLO.getData();
		for(int i=0;i<table.getRowCount();i++)
		{
			if((boolean)vLO.get(i).get(1)==true)
			{
				if(((String)(vLO.get(i).get(0))).charAt(0) == '-'){
					try{
						doc.insertString(doc.getLength(), vLO.get(i).get(0)+"\n", doc.getStyle("font 3"));
					}
					catch(Exception e1)
					{
						System.out.println("Font does not Exist!");
					}
				}else{
					try{
						doc.insertString(doc.getLength(), vLO.get(i).get(0)+"\n", doc.getStyle("font 2"));
					}
					catch(Exception e1)
					{
						System.out.println("Font does not Exist!");
					}
				}
			}
		}
		try{
			doc.insertString(doc.getLength(), "BLOOMS Learning Taxonomy\n", doc.getStyle("font 2"));
			doc.insertString(doc.getLength(), "-Knowledge (K)\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "-Comprehension (C)\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "-Application (AP)\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "-Analysis (An)\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "-Synthesis (S)\n", doc.getStyle("font 3"));
			doc.insertString(doc.getLength(), "-Evaluation (E)\n", doc.getStyle("font 3"));
		}
		catch(Exception e1)
		{
			System.out.println("Font does not Exist!");
		}

		try{
			doc.insertString(doc.getLength(), "Challenge\n", doc.getStyle("font 2"));
			MyTableModel tm2 = (MyTableModel) challengeTable.getModel();
			Vector<Vector<Object>> v1 = tm2.getData();
			for(int i = 0; i < v1.size(); i++){
				if((boolean)(v1.get(i).get(1)) == true){
					doc.insertString(doc.getLength(), (String)v1.get(i).get(0) + "\n", doc.getStyle("font 3"));
				}
			}
		}
		catch(Exception e1)
		{
			System.out.println("Font does not Exist!");
		}
		try{
			doc.insertString(doc.getLength(), "Conditions\n", doc.getStyle("font 2"));
		}
		catch(Exception e1)
		{
			System.out.println("Font does not Exist!");
		}
		for(int i=0;i<conditionsTable.getRowCount();i++)
		{
			if((boolean)v.get(i).get(1)==true)
			{
				try{
					doc.insertString(doc.getLength(), v.get(i).get(0)+"\n", doc.getStyle("font 3"));
				}
				catch(Exception e1)
				{
					System.out.println("Font does not Exist!");
				}
				mainCount++;
			}
		}
		conditionErrorBack.setText("Back");
		fullSumContinue.setFont(font2);
		conditionErrorBack.setFont(font2);
		fullSumContinue.setBounds(700,225,150,50);
		conditionErrorBack.setBounds(520,225,150,50);
		fullSumContinue.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		conditionErrorBack.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		if(mainCount==0)
		{
			mainPanel.removeAll();
			speechBubble.setBounds(415,45,400,170);
			speechBubble.setText("\n      You Must Select A \n             Condition!\n       Please Click Back!");
			speechBubble.setFont(font);
			conditionErrorBack.setBounds(700,550,150,50);
			mainPanel.changeCoord(0, 0);
			mainPanel.add(speechBubble);
			mainPanel.add(conditionErrorBack);
			mainPanel.changeFileName("errorBackground.png");
			mainPanel.updateUI();
		}
		else
		{
			if(conditionsNode.getNextSibling()!=lowerSummaryNode)
			{
				rootNode.add(lowerSummaryNode);
				DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
				model.reload(mainRoot);
			}
			wizardTree.expandRow(0);
			summary.setEditable(false);
			summary.setCaretPosition(0);
			JScrollPane scroll = new JScrollPane(summary);
			scroll.setBounds(180,300,720,330);
			scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
			speechBubble.setBounds(415,45,400,90);
			speechBubble.setText("Here is a Summary of Your \n      Game Thus Far!!!");
			speechBubble.setFont(font);
			mainPanel.add(speechBubble);
			mainPanel.add(fullSumContinue);
			mainPanel.add(conditionErrorBack);
			mainPanel.add(scroll);
			treePanel.updateUI();
			mainPanel.updateUI();
		}
	}
	class MyTableModel extends AbstractTableModel {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Learning Objective",
		"Include"};
		private String[] columnNamesImages = {"Image","Type",
		"Include"};
		private String[] columnNamesQuestions = {"Question","Challenge",
		"Condition"};
		private Vector<Vector<Object>> data;
		public boolean isLOTable;
		public boolean isImageTable;
		public boolean isQuesTable;

		public MyTableModel(Vector<Vector<Object>> v, boolean b, boolean b2, boolean b3){
			data = v;
			isImageTable = b;
			isLOTable = b2;
			isQuesTable = b3;
		}
		public Vector<Vector<Object>> getData()
		{
			return data;
		}
		public int getColumnCount() {
			if(isImageTable){
				return columnNamesImages.length;
			}
			if(isQuesTable){
				return columnNamesQuestions.length;
			}
			return columnNames.length;
		}
		public int getRowCount() {
			return data.size();
		}

		public boolean nothingSelected(){
			for(int i = 0; i<data.size(); i++){
				if((Boolean)data.get(i).get(1)){
					return false;
				}
			}
			return true;
		}
		public String getColumnName(int col) {
			if(isImageTable){
				return columnNamesImages[col];
			}
			if(isQuesTable){
				return columnNamesQuestions[col];
			}
			return columnNames[col];
		}
		public Object getValueAt(int row, int col) {
			return (data.get(row)).get(col);
		}
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			//Note that the data/cell address is constant,
			//no matter where the cell appears on screen.
			if(isImageTable){

				return false;

			}

			if(isQuesTable && col>0){

				return true;

			}

			if(isQuesTable && col==0)
			{
				return false;
			}

			if(isLOTable)
			{
				if(col==1 && row ==0)
				{
					return false;
				}
				else if(col==1 && ((String)(data.get(row)).get(0)).substring(0,8).equals("Progress"))
				{
					return false;
				}
			}
			if (col <1) {
				return false;
			} else {
				return true;
			}
		}

		public void makeImageTable(){
			isImageTable = true;
		}

		public void fillFalse(){
			for(int i = 0; i <data.size(); i++){
				data.get(i).set(1,new Boolean(false));
			}
		}
		public Object[][] findLearningObjective(String name)
		{
			if(name.equals("Operations and Algebraic Thinking"))
			{
				Object [][] data2 = {
						{"--Use The Four Operations With Whole Numbers To Solve Problems", new Boolean(false)},
						{"--Game Familiarity with Factors and Multiples", new Boolean(false)},
						{"--Generate and Analyze Patterns",new Boolean(false)}
				};
				return data2;
			}
			else if(name.equals("Number and Operations - Base 10"))
			{
				Object [][] data2 = {
						{"--Generalize Place Understanding for Multi-Digit Whole Numbers", new Boolean(false)},
						{"--Use Place Value Understanding and Properties of Operations to Perform Multi-Digit Arithmetic",new Boolean(false)}
				};
				return data2;
			}
			else if(name.equals("Number and Operations - Fractions"))
			{
				Object [][] data2 = {
						{"--Extend Understanding of Fraction Equivalence and Ordering", new Boolean(false)},
						{"--Build Fractions From Unit Fractions by Applying and Extending Previous Understandings of Operatoins on Whole Numbers", new Boolean(false)},
						{"--Understand Decimal Notation for Fractions, and Compare Decimal Fractions",new Boolean(false)}
				};
				return data2;
			}
			else if(name.equals("Measurement and Data"))
			{
				Object [][] data2 = {
						{"--Solve Problems Involving Measurement and Conversion of Measurments From a Larger Unit to a Smaller Unit", new Boolean(false)},
						{"--Represent and Interpret Data", new Boolean(false)},
						{"--Geometric Measurment: Understand Concepts of Angle and Measure Angles",new Boolean(false)}
				};
				return data2;
			}
			else if(name.equals("Geometry"))
			{
				Object [][] data2 = {
						{"--Draw and Identify Lines and Angles, and Classify Shapes by Properties of Their Lines and Angles", new Boolean(false)}
				};
				return data2;
			}
			else
			{
				return null;
			}
		}
		public void setValueAt(Object value, int row, int col) {
			if( isLOTable){
				if((Boolean)(data.get(row)).get(col))
				{
					data.get(row).set(col,new Boolean(false));
					fireTableCellUpdated(row, col);
					mainPanel.updateUI();
					return;
				}
				else
				{
					data.get(row).set(col,new Boolean(true));
					fireTableCellUpdated(row, col);
					mainPanel.updateUI();
					return;
				}
			}

			if( isQuesTable){
				if(col==0)
				{
					return;
				}
				else
				{
					data.get(row).set(col,value);
					fireTableCellUpdated(row, col);
					mainPanel.updateUI();
					return;
				}
			}

			if(col<1)
			{
				return;
			}
			else
			{
				String name = (String) data.get(row).get(0);
				if(false==(Boolean)(data.get(row)).get(col) && !(name.charAt(0) == '-')){
					data.get(row).set(col,new Boolean(true));
					Object[][] subLearning = findLearningObjective(name);
					if(subLearning !=null){
						for(int i = 0; i < subLearning.length; i++){
							Vector <Object> v = new Vector<Object>();
							v.add(subLearning[i][0]);
							v.add(subLearning[i][1]);
							data.add(row+1+i, v);
						}
					}
					mainPanel.updateUI();
				}
				else if(name.charAt(0)=='-')
				{
					if((Boolean)(data.get(row)).get(col))
					{
						data.get(row).set(col,new Boolean(false));
						fireTableCellUpdated(row, col);
						mainPanel.updateUI();
					}
					else
					{
						data.get(row).set(col,new Boolean(true));
						fireTableCellUpdated(row, col);
						mainPanel.updateUI();
					}
				}
				else{
					int i = 1;
					data.get(row).set(col, new Boolean(false));
					while(i+row<data.size() && ((String) data.get(row+i).get(0)).charAt(0) == '-')
					{
						data.remove(row+i);
					}
					fireTableCellUpdated(row, col);
					mainPanel.updateUI();
				}
			}
			fireTableCellUpdated(row, col);
		}
	}
}


//Ryan and David

 package edu.utdallas.gamewizard;

 import javax.imageio.ImageIO;
 import javax.swing.*;
 import javax.swing.event.*;
 import javax.swing.table.AbstractTableModel;
 import javax.swing.table.DefaultTableCellRenderer;
 import javax.swing.text.BadLocationException;
 import javax.swing.text.StyleConstants;
 import javax.swing.text.StyleContext;
 import javax.swing.text.StyledDocument;
 import javax.swing.tree.*;

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
 import edu.utdallas.previewtool.View.ScenePanel;
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
     private final String [] institutions = {"Please Choose Your Institution","West Allis Independent",
             "Milwaukee Public Schools","Marquette University","University of Texas-Dallas","Johnson-Controls","IBM"};
     private String [] domains = {"Please Choose Your Domain"};
     private String [] grades = {"Please Choose Your Level"};
     private String [] subjects = {"Please Choose Your Subject"};
     private JTextPane taxPane; 
     private JTextPane backgroundPane;
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
     final DefaultMutableTreeNode contextNode;
     private final JTextArea speechBubble;
     private final JButton submitButton;
     private final JButton submitLOButton;
     private final JButton summaryContinue;
     private final JButton summaryBack;
     private final JButton welcomeButton;
     private final JButton subTaxContButton;
     private final JButton taxBackButton;
     private final JButton conditionContinue;
     private final JButton conditionErrorBack;
     private final JButton challengeErrorBack;
     private final JButton fullSumContinue;
     private final JButton introTwoButton;
     private final JButton taxContButton;
     private JButton challengeButton;
     private final JButton charButton;
     private boolean bloomSelected;
     private JScrollPane bloomsScroll;
     private JTextPane challengePane;
     private JTextPane summary;
     final DefaultTreeModel model;

     private final Font font = new Font("Comic Sans MS",Font.CENTER_BASELINE,28);
     private final Font font2 = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
     private final Font font3 = new Font("Comic Sans MS",Font.CENTER_BASELINE,18);
     private final Font font4 = new Font("Comic Sans MS",Font.PLAIN,18);

     String LearningObjective;
     JTable table;
     JTable conditionsTable;
     JTable subTaxTable;
     JTable challengeTable;
     JTable characterTable;


     public Wizard()
     {

         window.setSize(WIDTH,HEIGHT);
         window.setResizable(true);
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setLocationRelativeTo(null);
         window.setTitle("Game Design");

         mainPanel = new WizardPanel("introBackground2.png", 0, 0);
         //learningObjectivePanel = new WizardPanel();
         treePanel = new JPanel();
         mainPanel.setLayout(null);

         //Begin Making Tree

         wizardTree = new JTree();
         mainRoot = new DefaultMutableTreeNode("Start/Introduction");

         model = new DefaultTreeModel(mainRoot);
         wizardTree.setRootVisible(false);
         wizardTree.setModel(model);
         rootNode = new DefaultMutableTreeNode("Start/Introduction");
         learningObjectiveNode = new DefaultMutableTreeNode("Learning Objectives");
         learningTaxonomyNode = new DefaultMutableTreeNode("Learning Standard");
         subTaxNode = new DefaultMutableTreeNode("Select Taxonomies");
         typeOfChallengeNode = new DefaultMutableTreeNode("Type of Challenge");
         conditionsNode = new DefaultMutableTreeNode("Conditions");
         lowerSummaryNode = new DefaultMutableTreeNode("Summary");
         topNode = new DefaultMutableTreeNode("Game Components");
         characterNode = new DefaultMutableTreeNode("Character Selection");
         contextNode = new DefaultMutableTreeNode("Context");
         treePanel.add(wizardTree);
         treePanel.setBackground(Color.white);

         taxPane = new JTextPane();
         //Learning Objective Table

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
                     speechBubble.setText("Please Select Your Learning\n Objectives and Continue");
                     JScrollPane scroll = JTable.createScrollPaneForTable(table);
                     scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     scroll.setBounds(100,300,950,350);
                     submitLOButton.setFont(font2);
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
                         challengeTable = new JTable(new MyTableModel(v,false));
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
                     if(isErrorChallenge())
                     {
                         printErrorChallenge();
                     }
                     else if(isErrorLearningObjective())
                     {
                         printErrorLearningObjective();
                     }
                     else
                     {
                         generateSummary();
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
         submitLOButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));


         submitLOButton.setBounds(650,225,150,50);

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

         introTwoButton = new JButton("Continue");
         introTwoButton.setVisible(true);
         introTwoButton.setEnabled(true);

         introTwoButton.setBounds(600,475,150,50);
         introTwoButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));

         charButton = new JButton("Continue");
         charButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
         
         String[] backgrounds = {"Please Choose A Location","Bank","Beach","Classroom","Gym","Medieval"};
         backgroundBox = new JComboBox <String>(backgrounds);
         
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

                 table = new JTable(new MyTableModel(v,false));
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
                 speechBubble.setText("Please Select Your Learning\n Objectives and Continue");
                 @SuppressWarnings("deprecation")
                 JScrollPane scroll = JTable.createScrollPaneForTable(table);
                 scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 scroll.setBounds(100,300,950,260);
                 submitLOButton.setFont(font2);
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
                             try{
                                 doc.insertString(doc.getLength(), v.get(i).get(0)+"\n", doc.getStyle("font 3"));
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
                     speechBubble.setText("\n      You Must Select A \n        Main Objective!\n       Please Click Back!");
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
                     speechBubble.setText("\n        You Must Select \n        Sub-Objectives!\n        Please Click Back!");
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
                 submitLOButton.setFont(font2);
                 submitLOButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
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
                 taxContButton.setBounds(750,575,165,50);
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

                     subTaxTable = new JTable(new MyTableModel(v,false));

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

                     subTaxTable = new JTable(new MyTableModel(v,false));

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
                 challengeTable = new JTable(new MyTableModel(v,false));
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

                     conditionsTable = new JTable(new MyTableModel(vNew, false));
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
                 mainRoot.add(topNode);
                 DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
                 model.reload(mainRoot);
                 wizardTree.expandRow(0);
                 mainPanel.changeFileName("introBackground2.png");
                 mainPanel.changeCoord(0,0);
                 speechBubble.setFont(font);
                 speechBubble.setBounds(400,100,420,120);
                 speechBubble.setText("       Now You Can Select\n          Internal Game \n           Components");
                 introTwoButton.setFont(font2);
                 mainPanel.add(introTwoButton);
                 mainPanel.add(speechBubble);
                 mainPanel.updateUI();
                 treePanel.updateUI();
             }
         });

         introTwoButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 mainPanel.removeAll();
                 topNode.add(characterNode);
                 DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
                 model.reload(mainRoot);
                 wizardTree.expandRow(1);
                 wizardTree.collapseRow(0);
                 Vector <Vector <Object>> v = new Vector <Vector <Object>>();
                 Vector <Object> v1 = new Vector<Object>();
                 Vector <Object> v2 = new Vector<Object>();
                 Vector <Object> v3 = new Vector<Object>();
                 Vector <Object> v4 = new Vector<Object>();
                 Vector <Object> v5 = new Vector<Object>();
                 Vector <Object> v6 = new Vector<Object>();
                 Vector <Object> v7 = new Vector<Object>();
                 ImageIcon protagonist;
                 ImageIcon antagonist;
                 ImageIcon interlocutor;
                 ImageIcon director;
                 ImageIcon constructor;
                 ImageIcon trickster;
                 ImageIcon prop;
                 try {
                     protagonist = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_10/char10_StandSmileClosed.png")));
                     v1.add("Protagonist");
                     v1.add(protagonist);
                     v1.add( new Boolean(false));
                     
                     antagonist = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_17/char17_LEvil.png")));
                     v2.add("Antagonist");
                     v2.add(antagonist);
                     v2.add( new Boolean(false));
                     
                     interlocutor = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_20/char20_StandSmileOpen.png")));
                     v3.add("Interlocutor");
                     v3.add(interlocutor);
                     v3.add( new Boolean(false));
                     
                     director = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_24/char24_StandSmileClosed.png")));
                     v4.add("Director");
                     v4.add(director);
                     v4.add( new Boolean(false));
                     
                     constructor = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_11/char11_StandSmileOpen.png")));
                     v5.add("Constructor");
                     v5.add(constructor);
                     v5.add( new Boolean(false));
                     
                     trickster = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_22/char22_StandSmileClosed.png")));
                     v6.add("Trickster");
                     v6.add(trickster);
                     v6.add( new Boolean(false));
                     
                     prop = new ImageIcon(ImageIO.read(new File("Office, Classroom\\characters/character_6/char6_StandSmileOpen.png")));
                     v7.add("Prop");
                     v7.add(prop);
                     v7.add( new Boolean(false));

                 } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                 }
                 
                 v.add(v1);
                 v.add(v2);
                 v.add(v3);
                 v.add(v4);
                 v.add(v5);
                 v.add(v6);
                 v.add(v7);
                 MyTableModel tm;
                 characterTable = new JTable(tm = new MyTableModel(v,true));
                 characterTable.setRowHeight(275);
                 characterTable.getColumnModel().getColumn(2).setPreferredWidth(85);
                 characterTable.getColumnModel().getColumn(1).setPreferredWidth(440);
                 characterTable.getColumnModel().getColumn(0).setPreferredWidth(150);
                 characterTable.getTableHeader().setFont(font3);
                 characterTable.setFont(font2);
                 characterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                 characterTable.setGridColor(Color.black);
                 JScrollPane scroll = JTable.createScrollPaneForTable(characterTable);
                 scroll.getVerticalScrollBar().setPreferredSize(new Dimension(25,25));
                 scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 scroll.setBounds(285,300,710,300);
                 speechBubble.setFont(font);
                 speechBubble.setBounds(415,45,400,90);
                 speechBubble.setText("      Please Select Type\n         of Characters");
                 charButton.setBounds(715,225,150,50);
                 charButton.setFont(font2);
                 mainPanel.changeFileName("wizardBackground.png");
                 mainPanel.changeCoord(0, -70);
                 mainPanel.add(scroll);
                 mainPanel.add(speechBubble);
                 mainPanel.add(charButton);
                 treePanel.updateUI();
                 mainPanel.updateUI();
             }
         });
         
         charButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 mainPanel.removeAll();
                 topNode.add(contextNode);
                 DefaultTreeModel model = (DefaultTreeModel)wizardTree.getModel();
                 model.reload(mainRoot);
                 wizardTree.expandRow(1);
                 mainPanel.removeAll();
                 backgroundPane = new JTextPane();
                 backgroundPane.removeAll();
                 backgroundPane.updateUI();
                 speechBubble.setFont(font);
                 speechBubble.setBounds(347,65,400,78);
                 speechBubble.setText("     Please Select Your\n        Location");
                 backgroundBox.setBounds(625,375,425,50);
                 backgroundPane.setBounds(50,300,400,260);
                 backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 backgroundBox.setFont(font2);
                 mainPanel.changeFileName("learningTaxonomy.png");
                 mainPanel.changeCoord(-100, 0);
                 treePanel.add(wizardTree);
                 mainPanel.updateUI();
                 mainPanel.add(speechBubble);
                 mainPanel.add(backgroundBox);
                 mainPanel.add(backgroundPane);
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
                     mainPanel.add(speechBubble);
                     mainPanel.add(backgroundBox);
                     mainPanel.add(backgroundPane);
                     mainPanel.updateUI();
                 }
                 else if(background.equals("Bank"))
                 {
                     mainPanel.removeAll();
                     mainPanel.changeFileName("learningTaxonomy.png");
                     backgroundPane = new JTextPane();
                     backgroundPane.removeAll();
                     backgroundPane.updateUI();
                     backgroundPane.setBounds(50,300,400,260);
                     backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     try {
                         ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/bank.png")));
                     backgroundPane.insertIcon(icon);
                     } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                     speechBubble.setFont(font);
                     speechBubble.setBounds(347,65,400,78);
                     speechBubble.setText("      You Chose a Bank!");
                     mainPanel.add(speechBubble);
                     mainPanel.add(backgroundBox);
                     mainPanel.add(backgroundPane);
                     mainPanel.updateUI();
                 }
                 else if(background.equals("Beach"))
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
                         ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/beach_Sunset.png")));
                     backgroundPane.insertIcon(icon);
                     } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                     speechBubble.setText("      You Chose a Beach!");
                     mainPanel.add(speechBubble);
                     mainPanel.add(backgroundBox);
                     mainPanel.add(backgroundPane);
                     mainPanel.updateUI();
                 }
                 else if(background.equals("Classroom"))
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
                         ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/Classroom_1.png")));
                     backgroundPane.insertIcon(icon);
                     } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                     speechBubble.setText("  You Chose a Classroom!");
                     mainPanel.add(speechBubble);
                     mainPanel.add(backgroundBox);
                     mainPanel.add(backgroundPane);
                     mainPanel.updateUI();
                 }
                 else if(background.equals("Gym"))
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
                         ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/Gym Center.png")));
                     backgroundPane.insertIcon(icon);
                     } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                     speechBubble.setText("      You Chose a Gym!");
                     mainPanel.add(speechBubble);
                     mainPanel.add(backgroundBox);
                     mainPanel.add(backgroundPane);
                     mainPanel.updateUI();
                 }
                 else if(background.equals("Medieval"))
                 {
                     mainPanel.removeAll();
                     speechBubble.setFont(font);
                     speechBubble.setBounds(347,65,400,78);
                     backgroundPane.removeAll();
                     backgroundPane.updateUI();
                     backgroundPane = new JTextPane();
                     backgroundPane.removeAll();
                     backgroundPane.updateUI();
                     backgroundPane.setBounds(50,300,400,260);
                     backgroundPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     speechBubble.setText("      You Chose Medieval!");
                     try {
                         ImageIcon icon = new ImageIcon(ImageIO.read(new File("Office, Classroom\\Backdrops/Medieval.png")));
                     backgroundPane.insertIcon(icon);
                     } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                     }
                     mainPanel.add(speechBubble);
                     mainPanel.add(backgroundBox);
                     mainPanel.add(backgroundPane);
                     mainPanel.updateUI();
                 }
             }
         });
         window.add(main);
         window.setVisible(true);
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
         if((boolean)v.get(0).get(1)==true && (boolean)v.get(1).get(1)==true && (boolean)v.get(2).get(1)==true && 
                 (boolean)v.get(3).get(1)==true && (boolean)v.get(5).get(1)==true)
         {

             hit = true;
             Vector<Object> v1 = new Vector<Object>();
             v1.add("Composition (K,C,Ap,An,E) -evaluate, create & consequence");
             v1.add( new Boolean(false));
             v2.add(v1);

         }

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
         private String[] columnNamesImages = {"Type","Image",
         "Include"};
         private Vector<Vector<Object>> data;
         public boolean isImageTable;

         public MyTableModel(Vector<Vector<Object>> v, boolean b){
             data = v;
             isImageTable = b;
         }
         public Vector<Vector<Object>> getData()
         {
             return data;
         }
         public int getColumnCount() {
             if(isImageTable){
                 return columnNamesImages.length;
             }
             return columnNames.length;
         }

         public int getRowCount() {
             return data.size();
         }

         public String getColumnName(int col) {
             if(isImageTable){
                 return columnNamesImages[col];
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
                 if (col <2) {
                     return false;
                 } else {
                     return true;
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
             
             if(isImageTable && col == 2){
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





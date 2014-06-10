package edu.utdallas.gamewizard;

 import javax.swing.*;
 import javax.swing.border.Border;
 import javax.swing.event.*;
 import javax.swing.filechooser.FileNameExtensionFilter;
 import javax.swing.table.AbstractTableModel;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.tree.*;
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

 import java.awt.*;
 import java.awt.Color;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.KeyEvent;
 import java.awt.event.WindowEvent;
 import java.awt.event.WindowListener;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;

 public class Wizard implements ActionListener {

     public static final int WIDTH = 1280;
     public static final int HEIGHT = 700;

     private JFrame window = new JFrame();
     private JTree wizardTree;
     private JPanel treePanel;
     private WizardPanel introPanel;
     private WizardPanel learningObjectivePanel;
     
     private final JComboBox institutionBox;
     private final JComboBox domainBox;
     private final JComboBox gradeBox;
     private final JComboBox subjectBox;

     private final String [] institutions = {"Please Choose Your Institution","K-12","Higher Education","Workplace"};
     private final String [] domains = {"Please Choose Your Domain","Students","Faculty","Employers","HR"};
     private final String [] grades = {"Please Choose Your Level","First","Second","Third","Fourth"};
     private final String [] subjects = {"Please Choose Your Subject","Math","Science","Reading","History"};
     
     final DefaultMutableTreeNode rootNode;
     final DefaultMutableTreeNode learningObjectiveNode;
     final DefaultMutableTreeNode learningTaxonomyNode;
     final DefaultMutableTreeNode typeOfChallengeNode;
     private final JTextArea speechBubble;
     private final JButton submitButton;
     private final JButton submitLOButton;
     private final JButton welcomeButton;
     final DefaultTreeModel model;
     
     JTable table;
     
     public Wizard()
     {

         window.setSize(WIDTH,HEIGHT);
         window.setResizable(true);
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setTitle("Game Design");

         introPanel = new WizardPanel("introBackground2.png", 0, 0);
         //learningObjectivePanel = new WizardPanel();
         treePanel = new JPanel();
         introPanel.setLayout(null);

         //Begin Making Tree

         wizardTree = new JTree();
         rootNode = new DefaultMutableTreeNode("Start/Introduction");
         
         model = new DefaultTreeModel(rootNode);
         wizardTree.setModel(model);
         learningObjectiveNode = new DefaultMutableTreeNode("Learning Objectives");
         learningTaxonomyNode = new DefaultMutableTreeNode("Learning Taxonomy");
         typeOfChallengeNode = new DefaultMutableTreeNode("Type of Challenge");
         treePanel.add(wizardTree);
         treePanel.setBackground(Color.white);
         //Learning Objective Table
         

         wizardTree.addTreeSelectionListener(new TreeSelectionListener()
         {
             public void valueChanged(TreeSelectionEvent e)
             {
                 DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) wizardTree.getLastSelectedPathComponent();
                 if (isRootNode(selectedNode))
                 {
                     introPanel.removeAll();
                     introPanel.changeCoord(0, 0);
                     Font font = new Font("Comic Sans MS",Font.BOLD,28);
                     Font font2 = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
                     speechBubble.setFont(font);
                     speechBubble.setBounds(400,100,420,120);
                     speechBubble.setText("   Who is This Game For?");
                     institutionBox.setBounds(625,300,425,50);
                     institutionBox.setFont(font2);
                     institutionBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     introPanel.add(institutionBox);
                     domainBox.setBounds(625,375,425,50);
                     domainBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     domainBox.setFont(font2);
                     domainBox.setEnabled(false);
                     introPanel.add(domainBox);
                     gradeBox.setBounds(625,450,425,50);
                     gradeBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     gradeBox.setFont(font2);
                     gradeBox.setEnabled(false);
                     introPanel.add(gradeBox);
                     subjectBox.setBounds(625,525,425,50);
                     subjectBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     subjectBox.setFont(font2);
                     subjectBox.setEnabled(false);
                     submitButton.setFont(font2);
                     submitButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     introPanel.add(speechBubble);
                     introPanel.add(subjectBox);
                     introPanel.add(submitButton);
                     introPanel.changeFileName("introBackground2.png");
                     introPanel.updateUI();

                 }
                 else if (selectedNode != null && selectedNode == learningObjectiveNode ){
                     introPanel.removeAll();
                     introPanel.changeFileName("wizardBackground.png");
                     introPanel.changeCoord(0, -70);
                     Font font = new Font("Comic Sans MS",Font.CENTER_BASELINE,28);
                     Font font2 = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
                     speechBubble.setFont(font);
                     speechBubble.setBounds(415,45,400,90);
                     speechBubble.setText("Please Select Your Learning\n Objectives and Continue");
                     JScrollPane scroll = JTable.createScrollPaneForTable(table);
                     scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                     scroll.setBounds(100,300,950,350);
                     submitLOButton.setFont(font2);
                     introPanel.add(scroll);
                     introPanel.add(speechBubble);
                     introPanel.add(submitLOButton);
                     introPanel.updateUI();
                 }

             }
         });



         JSplitPane main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treePanel, introPanel);
         main.setDividerLocation(160);

         institutionBox = new JComboBox(institutions);
         domainBox = new JComboBox(domains);
         gradeBox = new JComboBox(grades);
         subjectBox = new JComboBox(subjects);

         speechBubble = new JTextArea("Welcome to the Game Design Tool!\n Please Click Continue To Begin!");
         Font font = new Font("Comic Sans MS",Font.BOLD,28);
         speechBubble.setFont(font);
         speechBubble.setEditable(false);
         speechBubble.setBounds(375,80,479,140);    
             
         introPanel.add(speechBubble);
         
         submitButton = new JButton("Continue");
         submitButton.setVisible(false);
         submitButton.setEnabled(false);
         
         submitButton.setBounds(400,475,150,50);
         
         submitLOButton = new JButton("Continue");
         submitLOButton.setVisible(true);
         submitLOButton.setEnabled(true);
         submitLOButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
         
         submitLOButton.setBounds(650,225,150,50);
         
         welcomeButton = new JButton("Continue");
         welcomeButton.setVisible(true);
         welcomeButton.setEnabled(true);
         Font welcomeFont = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
         welcomeButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
         welcomeButton.setFont(welcomeFont);
         welcomeButton.setBounds(550,475,150,50);
         
         introPanel.add(welcomeButton);
         


         
         institutionBox.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 int i = institutionBox.getSelectedIndex();
                 rootNode.removeAllChildren();
                 model.reload();
                 treePanel.add(wizardTree);
                 treePanel.updateUI();
                 //record choice
                 if(institutions[i] != institutions[0]){
                     domainBox.setEnabled(true);
                     speechBubble.setText("   Thanks. Who is This Game\n   For Specifically?");
                     introPanel.changeFileName("introBackground2.png");
                     introPanel.updateUI();
                     //Set specific domain
                 }else{
                     domainBox.setEnabled(false);
                     gradeBox.setEnabled(false);
                     submitButton.setVisible(false);
                     subjectBox.setEnabled(false);
                     introPanel.changeFileName("errorBackground.png");
                     introPanel.updateUI();
                     speechBubble.setText("Oops. Please Enter Institution");
                 }
             }
         });

         domainBox.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 int i = domainBox.getSelectedIndex();
                 rootNode.removeAllChildren();
                 model.reload();
                 treePanel.add(wizardTree);
                 treePanel.updateUI();
                 //record choice
                 if(domains[i] != domains[0]){
                     gradeBox.setEnabled(true);
                     speechBubble.setText("   Gotcha. What Level will \n   be playing this game?");
                     introPanel.changeFileName("introBackground2.png");
                     introPanel.updateUI();
                     //Set specific level
                 }else{    
                     gradeBox.setEnabled(false);
                     submitButton.setVisible(false);
                     subjectBox.setEnabled(false);
                     speechBubble.setText("   Oops. Please Enter Domain");
                     introPanel.changeFileName("errorBackground.png");
                     introPanel.updateUI();
                 }
             }
         });

         gradeBox.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 int i = gradeBox.getSelectedIndex();
                 rootNode.removeAllChildren();
                 model.reload();
                 treePanel.add(wizardTree);
                 treePanel.updateUI();
                 //record choice
                 if(grades[i] != grades[0]){
                     subjectBox.setEnabled(true);
                     speechBubble.setText("   Sounds Good. What Subject \n   will this game teach?");
                     introPanel.changeFileName("introBackground2.png");
                     introPanel.updateUI();
                     //Set specific subject
                 }else{    
                     subjectBox.setEnabled(false);
                     submitButton.setVisible(false);
                     speechBubble.setText("   Oops. Please Enter Level");
                     introPanel.changeFileName("errorBackground.png");
                     introPanel.updateUI();
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
                     introPanel.changeFileName("introBackground2.png");
                     submitButton.setVisible(true);
                     introPanel.updateUI();
                 }
                 else
                 {
                     submitButton.setEnabled(false);
                     submitButton.setVisible(false);
                     speechBubble.setText("   Oops. Please Enter Subject");
                     introPanel.changeFileName("errorBackground.png");
                     introPanel.updateUI();
                 }
             }
         });
         
         submitButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 submitButton.setEnabled(false);
                 rootNode.add(learningObjectiveNode);
                 rootNode.add(learningTaxonomyNode);
                 rootNode.add(typeOfChallengeNode);
                 wizardTree.expandRow(0);
                 table = new JTable(new MyTableModel());
                 treePanel.add(wizardTree);
                 introPanel.removeAll();
                 //
                 introPanel.changeFileName("wizardBackground.png");
                 introPanel.changeCoord(0, -70);
                 Font font = new Font("Comic Sans MS",Font.CENTER_BASELINE,28);
                 Font font2 = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
                 Font font3 = new Font("Comic Sans MS",Font.CENTER_BASELINE,18);
                 table.setRowHeight(45);
                 table.setShowHorizontalLines(true);
                 table.setRowSelectionAllowed(true);
                 table.setColumnSelectionAllowed(true);
                 table.getColumnModel().getColumn(1).setMaxWidth(75);
                 table.getColumnModel().getColumn(2).setMaxWidth(75);
                 table.getTableHeader().setFont(font3);
                 table.setFont(font2);
                 table.setGridColor(Color.black);
                 speechBubble.setFont(font);
                 speechBubble.setBounds(415,45,400,90);
                 speechBubble.setText("Please Select Your Learning\n Objectives and Continue");
                 JScrollPane scroll = JTable.createScrollPaneForTable(table);
                 scroll.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 scroll.setBounds(100,300,950,350);
                 submitLOButton.setFont(font2);
                 introPanel.add(scroll);
                 introPanel.add(speechBubble);
                 introPanel.add(submitLOButton);
                 introPanel.updateUI();
                 //
                 treePanel.updateUI();
             }
         });
         
         submitLOButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
             }
         });
         
         welcomeButton.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 introPanel.removeAll();
                 introPanel.changeCoord(0, 0);
                 Font font = new Font("Comic Sans MS",Font.BOLD,28);
                 Font font2 = new Font("Comic Sans MS",Font.CENTER_BASELINE,22);
                 speechBubble.setFont(font);
                 speechBubble.setBounds(400,100,420,120);
                 speechBubble.setText("   Who is This Game For?");
                 institutionBox.setBounds(625,300,425,50);
                 institutionBox.setFont(font2);
                 institutionBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 introPanel.add(institutionBox);
                 domainBox.setBounds(625,375,425,50);
                 domainBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 domainBox.setFont(font2);
                 domainBox.setEnabled(false);
                 introPanel.add(domainBox);
                 gradeBox.setBounds(625,450,425,50);
                 gradeBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 gradeBox.setFont(font2);
                 gradeBox.setEnabled(false);
                 introPanel.add(gradeBox);
                 subjectBox.setBounds(625,525,425,50);
                 subjectBox.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 subjectBox.setFont(font2);
                 subjectBox.setEnabled(false);
                 submitButton.setFont(font2);
                 submitButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
                 introPanel.add(speechBubble);
                 introPanel.add(subjectBox);
                 introPanel.add(submitButton);
                 introPanel.changeFileName("introBackground2.png");
                 introPanel.updateUI();
             }
         });
         
         window.add(main);
         window.setVisible(true);
     }

     private boolean isRootNode(DefaultMutableTreeNode node)
     {
         return node != null && node.isRoot();
     }

     @Override
     public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub

     }

     public static void main(String[] args)
     {
         Wizard wiz = new Wizard();
     }
     
      class MyTableModel extends AbstractTableModel {
             private String[] columnNames = {"Learning Objective",
                                             "Level",
                                             "Include"};
             private Object[][] data = {
             {"Addition", "1",new Boolean(true)},
             {"Subtraction", "2",new Boolean(true)},
             {"Multiplication", "2",new Boolean(true)},
             {"Division", "3",new Boolean(true)},
             {"Long-Division", "3",new Boolean(true)},
             {"Algebra", "4",new Boolean(true)},
             {"Geometry", "5",new Boolean(true)},
             {"Calculus 1", "6",new Boolean(true)},
             {"Calculus 2", "6",new Boolean(true)},
             {"Calculus 3", "6",new Boolean(true)}
             };
      
             public int getColumnCount() {
                 return columnNames.length;
             }
      
             public int getRowCount() {
                 return data.length;
             }
      
             public String getColumnName(int col) {
                 return columnNames[col];
             }
      
             public Object getValueAt(int row, int col) {
                 return data[row][col];
             }
             public Class getColumnClass(int c) {
                 return getValueAt(0, c).getClass();
             }
             public boolean isCellEditable(int row, int col) {
                 //Note that the data/cell address is constant,
                 //no matter where the cell appears onscreen.
                 if (col < 2) {
                     return false;
                 } else {
                     return true;
                 }
             }
             public void setValueAt(Object value, int row, int col) {
                 if(col<2)
                 {
                     return;
                 }
                 else
                 {
                     data[row][col] = value;
                     fireTableCellUpdated(row, col);
                 }
      
             }
      
      }
      
 }


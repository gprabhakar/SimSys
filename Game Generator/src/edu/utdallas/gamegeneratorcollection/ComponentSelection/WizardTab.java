package edu.utdallas.gamegeneratorcollection.ComponentSelection;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Jama.Matrix;
import edu.utdallas.gamePlayEngine.menuFrame;
import edu.utdallas.gamePlayEngine.view.GameView;
import edu.utdallas.gamegenerator.RepoUpdate.Updates;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.Asset;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.AudioPlayer;
import edu.utdallas.gamegeneratorcollection.ComponentCreation.CharacterAsset;
import edu.utdallas.gamegeneratorcollection.ComponentSelection.InputWizard.gameLevel;
import edu.utdallas.previewtool.View.SoundSelectWindow;
import static java.util.Arrays.asList;

public class WizardTab extends JPanel implements ActionListener {

    private String gameGradeLevel = "none";
    
    /** The player gender. */
    private String playerGender= "none"; 
   
     /** The player age. */
     private String playerAge= "none"; 
   
     /** The player dress. */
     private String playerDress= "none";   
   
      /** The game theme. */
      private String gameTheme= "none";    
   
    /** The game subject. */
    private String gameSubject= "none";    
   
    /** The game setting. */
    private String gameSetting= "none";    
   
    /** The game difficulty. */
    private String gameDifficulty= "none"; 
   
    /** The save file chooser. */
    private JFileChooser saveFileChooser;
    
    private static String gameSavePath = "C:\\";
    
    private boolean submitClicked = false;
    
    /** The Currentfile. */
    private File Currentfile = null;
    
    private Matrix[] componentInputs;

    public WizardTab(LayoutManager layout) {
        super(layout);
       
        
    }

    public WizardTab(GridLayout layout, final Matrix[] input) {
        // TODO Auto-generated constructor stub
        super(layout);
        componentInputs = input;
        setSelectionRows();
    }

    /**
     * Adds the selection rows to the wizard panel. button labels and
     * action command values are in this method.
     */
    public final void setSelectionRows() {
        //JRadioButton nextButton;
        int nextOpenRow = 0;

        //ButtonGroup gradeGroup = new ButtonGroup();

        //JPanel gradePanel = generateLeftFlowPanel("Indended grade level:");
        List<String> labels = new LinkedList<String>(asList("Primary School",
                "Secondary School",
                "High School", "College", "Job Training", "No Preference"));
        List<String> commands = new LinkedList<String>(asList("primary",
                "secondary",
                "high", "college", "jobTraining", "no grade"));
        addPanelToRow("Intended grade level:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("Male", "Female", "No Preference"));
        commands.addAll(asList("Male", "Female",
                "no gender"));
        addPanelToRow("Player gender:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("Young", "Old", "No Preference"));
        commands.addAll(asList("Young", "Old",
                "no age"));
        addPanelToRow("Character age:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("Casual Dress", "Fancy Dress", "No Preference"));
        commands.addAll(asList("Casual", "Fancy",
                "no dress"));
        addPanelToRow("Character dress:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("Gooble", "Dream", "Virtual Tour", "Workplace",
                "Other", "No Preference"));
        commands.addAll(asList("Gooble", "Dream", "VirtualTour", "Workplace",
                "Other", "no theme"));
        addPanelToRow("Game Theme:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("English", "Math", "Science", "Social Studies",
                "Literature", "Professional", "No Preference"));
        commands.addAll(asList("English", "Math", "Science", "Social Studies",
                "Literature", "Professional", "no subject"));
        addPanelToRow("Game Subject:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("Professional", "Casual", "Natural", "Educational",
                "Non-terrestrial", "No Preference"));
        commands.addAll(asList("ProfessionalSetting", "CasualSetting",
                "NaturalSetting", "EducationalSetting",
                "Non-terrestrialSetting", "no setting"));
        addPanelToRow("Game Setting:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        labels.addAll(asList("Easy", "Medium", "Hard", "No Preference"));
        commands.addAll(asList("Easy", "Medium", "Hard", "no difficulty"));
        addPanelToRow("Challenge Difficulty:", labels, commands, nextOpenRow++);
        labels.clear();
        commands.clear();

        JPanel previewCheckPanel = new JPanel(new GridLayout(1, 1));
        JCheckBox tickBox = new JCheckBox("Preview after generating: ");
        previewCheckPanel.add(tickBox);
        this.add(previewCheckPanel, nextOpenRow++);
        
        JPanel submitPanel = new JPanel(new GridLayout(1,3));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setActionCommand("Submit");
        submitPanel.add(submitButton);
        this.add(submitPanel, nextOpenRow++);

        /*
        nextButton = setButton("Primary School", "primary");
        gradeGroup.add(nextButton);
        gradePanel.add(nextButton);
        nextButton = setButton("Secondary School", "secondary");
        gradeGroup.add(nextButton);
        gradePanel.add(nextButton);
        nextButton = setButton("High School", "high");
        gradeGroup.add(nextButton);
        gradePanel.add(nextButton);
        nextButton = setButton("College", "college");
        gradeGroup.add(nextButton);
        gradePanel.add(nextButton);
        nextButton = setButton("Job Training", "jobTraining");
        gradeGroup.add(nextButton);
        gradePanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no grade");
        gradeGroup.add(nextButton);
        gradePanel.add(nextButton);
        this.add(gradePanel, nextOpenRow++);

        ButtonGroup genderGroup = new ButtonGroup();
        JPanel genderPanel = generateLeftFlowPanel("Player Gender:");
        nextButton = setButton("Male", "Male");
        genderGroup.add(nextButton);
        genderPanel.add(nextButton);
        nextButton = setButton("Female", "Female");
        genderGroup.add(nextButton);
        genderPanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no gender");
        genderGroup.add(nextButton);
        genderPanel.add(nextButton);
        this.add(genderPanel, nextOpenRow++);

        ButtonGroup ageGroup = new ButtonGroup();
        JPanel agePanel = generateLeftFlowPanel("Character age:");
        nextButton = setButton("Young", "Young");
        ageGroup.add(nextButton);
        agePanel.add(nextButton);
        nextButton = setButton("Old", "Old");
        ageGroup.add(nextButton);
        agePanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no age");        
        ageGroup.add(nextButton);
        agePanel.add(nextButton);
        
        this.add(agePanel, nextOpenRow++);

        ButtonGroup dressGroup = new ButtonGroup();
        JPanel dressPanel = generateLeftFlowPanel("Character dress:");
        nextButton = setButton("Casual Dress", "Casual");
        dressGroup.add(nextButton);
        dressPanel.add(nextButton);
        nextButton = setButton("Fancy Dress", "Fancy");
        dressGroup.add(nextButton);
        dressPanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no age");
        dressGroup.add(nextButton);
        dressPanel.add(nextButton);
        this.add(dressPanel, nextOpenRow++);

        ButtonGroup themeGroup = new ButtonGroup();
        JPanel themePanel = generateLeftFlowPanel("Game Theme:");
        nextButton = setButton("Gooble", "Gooble");
        themeGroup.add(nextButton);
        themePanel.add(nextButton);
        nextButton = setButton("Dream", "Dream");
        themeGroup.add(nextButton);
        themePanel.add(nextButton);
        nextButton = setButton("Virtual Tour", "VirtualTour");
        themeGroup.add(nextButton);
        themePanel.add(nextButton);
        nextButton = setButton("Workplace", "Workplace");
        themeGroup.add(nextButton);
        themePanel.add(nextButton);
        nextButton = setButton("Other", "Other");
        themeGroup.add(nextButton);
        themePanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no theme");
        themeGroup.add(nextButton);
        themePanel.add(nextButton);
        this.add(themePanel, nextOpenRow++);

        ButtonGroup subjectGroup = new ButtonGroup();
        JPanel subjectPanel = generateLeftFlowPanel("Game subject:");
        nextButton = setButton("English", "English");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        nextButton = setButton("Math", "Math");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        nextButton = setButton("Science", "Science");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        nextButton = setButton("Social Studies", "Social Studies");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        nextButton = setButton("Literature", "Literature");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        nextButton = setButton("Professional", "Professional");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no subject");
        subjectGroup.add(nextButton);
        subjectPanel.add(nextButton);
        this.add(subjectPanel, nextOpenRow++);

        ButtonGroup settingGroup = new ButtonGroup();
        JPanel settingPanel = generateLeftFlowPanel("Game Setting:");
        nextButton = setButton("Professional", "ProfessionalSetting");
        settingGroup.add(nextButton);
        settingPanel.add(nextButton);
        nextButton = setButton("Casual", "CasualSetting");
        settingGroup.add(nextButton);
        settingPanel.add(nextButton);
        nextButton = setButton("Natural", "NaturalSetting");
        settingGroup.add(nextButton);
        settingPanel.add(nextButton);
        nextButton = setButton("Educational", "EducationalSetting");
        settingGroup.add(nextButton);
        settingPanel.add(nextButton);
        nextButton = setButton("Non-terrestrial", "Non-terrestrialSetting");
        settingGroup.add(nextButton);
        settingPanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no setting");
        settingGroup.add(nextButton);
        settingPanel.add(nextButton);
        this.add(settingPanel, nextOpenRow++);

        ButtonGroup difficultyGroup = new ButtonGroup();
        JPanel difficultyPanel = generateLeftFlowPanel(
                "Challenge difficulty:");
        nextButton = setButton("Easy", "Easy");
        difficultyGroup.add(nextButton);
        difficultyPanel.add(nextButton);
        nextButton = setButton("Easy", "Easy");
        difficultyGroup.add(nextButton);
        difficultyPanel.add(nextButton);
        nextButton = setButton("Easy", "Easy");
        difficultyGroup.add(nextButton);
        difficultyPanel.add(nextButton);
        nextButton = setButton("No Preference", true, "no difficulty");
        difficultyGroup.add(nextButton);
        difficultyPanel.add(nextButton);
        this.add(difficultyPanel, nextOpenRow++);

        JPanel previewCheckPanel = new JPanel(new GridLayout(1, 1));
        JCheckBox tickBox = new JCheckBox("Preview after generating: ");
        previewCheckPanel.add(tickBox);
        this.add(previewCheckPanel, nextOpenRow++); */

    }

    public final void addPanelToRow(String rowLabel,
            List<String> labels, List<String> commands, int rowNumber) {
        if (labels.size() != commands.size()) {
            System.out.println("Warning: the number of labels and "
                    + "commands given are not equal.");
            throw new IllegalArgumentException();
        }

        JRadioButton nextButton;
        ButtonGroup currentGroup = new ButtonGroup();
        JPanel currentPanel = generateLeftFlowPanel(rowLabel);

        for (int i = 0; i < labels.size(); i++) {
            if (i < labels.size() - 1) {
                nextButton = setButton(labels.get(i), commands.get(i));
            } else {
                nextButton = setButton(labels.get(i), true, commands.get(i));
            }
            currentGroup.add(nextButton);
            currentPanel.add(nextButton);
        }
        this.add(currentPanel, rowNumber);
    }

    /**
     * Returns a JPanel with a FlowLayout set to FlowLayout.LEFT and
     * adds the label to the panel. This is to clear up some repeated lines in
     * setSelectionRows.
     * @param label The row label.
     * {@link String}
     * @return The panel with a label added.
     * {@link JPanel}
     */
    public final JPanel generateLeftFlowPanel(final String label) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel panelLabel = new JLabel(label);
        panel.add(panelLabel);
        return panel;
    }

    /**
     * Returns a JRadioButton with the label and ActionCommand passed.
     * @param label The button's label.
     * {@link String}
     * @param command The ActionCommand string.
     * {@link String}
     * @return The returned button, with an ActionListener applied.
     * {@link JRadioButton}
     */
    public final JRadioButton setButton(final String label,
            final String command) {
        JRadioButton button = new JRadioButton(label);
        button.setActionCommand(command);
        button.addActionListener(this);
        return button;
    }

    /**
     * Override of setButton, to add a boolean value to signify that
     * this button will be checked as selected.
     * @param label
     * {@link String}
     * @param isDefault
     * {@link Boolean}
     * @param command
     * {@link String}
     * @return
     * {@link JRadioButton}
     */
    public final JRadioButton setButton(final String label,
            final Boolean isDefault, final String command) {
        JRadioButton button = new JRadioButton(label, isDefault);
        button.setActionCommand(command);
        button.addActionListener(this);
        return button;
    }
    
    /**
     * Prints the strings.
     */
    public final void printStrings()
    {
        System.out.println(gameGradeLevel);
        System.out.println(playerGender);
        System.out.println(playerAge);
        System.out.println(playerDress);
        System.out.println(gameTheme);
        System.out.println(gameSetting);
        System.out.println(gameDifficulty);
        System.out.println(gameSubject);
    }
    
    /**
     * Option matrix.
     *
     * @param optionNumber the option number
     * @param optionTotal the option total
     * @return the matrix
     */
    private Matrix optionMatrix(final int optionNumber, final int optionTotal)
    {
        double[][] outputArray = new double[optionTotal][optionTotal];
        outputArray = initializeArray(outputArray,1);
        for (int x = 0; x < optionTotal; x++) {
            if (x != optionNumber - 1) {
                outputArray[optionNumber-1][x]=optionTotal; //replace optionTotal here with weight
                outputArray[x][optionNumber-1]=(1.0 / optionTotal); //replace optionTotal here with weight
            }
        }
        return new Matrix(outputArray);
    }
    
    /**
     * Initialize array.
     *
     * @param inputMatrixArray the input matrix array
     * @param initValue the init value
     * @return the double[][]
     */
    private double[][] initializeArray(final double[][] inputMatrixArray, final double initValue)
    {
        for (int y = 0; y < inputMatrixArray.length; y++) {
            for (int z = 0; z < inputMatrixArray[y].length; z++) {
                inputMatrixArray[y][z] = initValue;
            }
        }
     return inputMatrixArray;       
    }
    /**
     * Distribute inputs.
     */
    private void distributeInputs() {
        //start with character component
        int socialRating = 0;
        int professionalRating = 0;
        int educationalRating = 0;
        System.out.println("distributing Inputs");
        switch(gameGradeLevel){
        //There is probably a better way to do this, with lists or whatnot but this was the quick
        //implementation that I(Kaleb) decided to go with
        case "primary":
            componentInputs[4].setMatrix(6,9,6,9,optionMatrix(1,4));
            educationalRating++;
            break;
        case "secondary":
            componentInputs[4].setMatrix(6,9,6,9,optionMatrix(2,4));
            educationalRating++;
            break;
        case "high":
            componentInputs[4].setMatrix(6,9,6,9,optionMatrix(3,4));
            educationalRating++;
            break;
        case "college":
            componentInputs[4].setMatrix(6,9,6,9,optionMatrix(4,4));
            educationalRating++;
            break;
        case "jobTraining":
            professionalRating++;
            break;
        case "no grade":
            socialRating++;
        break;
        default:
            break;
        }
        switch(playerAge){
        case "Young":
            componentInputs[0].setMatrix(0,1,0,1,optionMatrix(1,2));
            break;
        case "Old":
            componentInputs[0].setMatrix(0,1,0,1,optionMatrix(2,2));
            break;
        case "none":
            break;
        default:
            break;
        }
        switch(playerGender){
        case "Male":
            componentInputs[0].setMatrix(2,3,2,3,optionMatrix(1,2));
            break;
        case "Female":
            componentInputs[0].setMatrix(2,3,2,3,optionMatrix(2,2));
            break;
        case "none":
            break;
        default:
            break;
        }
        switch(playerDress){
        case "Casual":
            componentInputs[0].setMatrix(4,5,4,5,optionMatrix(1,2));
            //          socialRating++;
            break;
        case "Fancy":
            componentInputs[0].setMatrix(4,5,4,5,optionMatrix(2,2));
            break;
        case "none":
            break;
        default:
            break;
        }
        switch(gameTheme){
        case "Gooble":
            componentInputs[5].setMatrix(0,4,0,4,optionMatrix(1,5));
            educationalRating++;
            break;
        case "Dream":
            componentInputs[5].setMatrix(0,4,0,4,optionMatrix(2,5));
            socialRating++;
            break;
        case "VirtualTour":
            componentInputs[5].setMatrix(0,4,0,4,optionMatrix(3,5));
            professionalRating++;
            break;
        case "Workplace":
            componentInputs[5].setMatrix(0,4,0,4,optionMatrix(4,5));
            socialRating++;
            break;
        case "Other":
            componentInputs[5].setMatrix(0,4,0,4,optionMatrix(5,5));
            break;
        case "none":
            break;
        default:
            System.out.println("Unanticipated Input for gameTheme " + gameTheme);
            break;
        }
        switch(gameSubject)
        {
        //Subject
        case "English":
            componentInputs[4].setMatrix(0,5,0,5,optionMatrix(3,6));
            educationalRating++;
            break;
        case "Math":
            componentInputs[4].setMatrix(0,5,0,5,optionMatrix(1,6));
            educationalRating++;
            break;
        case "Science":
            componentInputs[4].setMatrix(0,5,0,5,optionMatrix(5,6));
            educationalRating++;
            break;
        case "Social Studies":
            componentInputs[4].setMatrix(0,5,0,5,optionMatrix(4,6));
            educationalRating++;
            break;
        case "Literature":
            componentInputs[4].setMatrix(0,5,0,5,optionMatrix(2,6));
            educationalRating++;
            break;
        case "Professional":
            componentInputs[4].setMatrix(0,5,0,5,optionMatrix(6,6));
            professionalRating++;
            break;
        case "none":
            break;
        default:
            System.out.println("Unanticipated Input for gameSubject " + gameSubject);
            break;
        }
        switch(gameSetting){
        //Setting
        case "Professional":
            professionalRating+=2;
            componentInputs[3].setMatrix(3,7,3,7,optionMatrix(1,5));
            break;
        case "Casual":
            socialRating+=2;
            componentInputs[3].setMatrix(3,7,3,7,optionMatrix(2,5));
            break;
        case "Natural":
            componentInputs[3].setMatrix(3,7,3,7,optionMatrix(3,5));
            break;
        case "Educational":
            componentInputs[3].setMatrix(3,7,3,7,optionMatrix(4,5));
            educationalRating+=2;
            break;
        case "Non-terrestrial":
            componentInputs[3].setMatrix(3,7,3,7,optionMatrix(5,5));
            break;
        case "none":
            break;
        default:
            System.out.println("Unanticipated Input for gameSetting " + gameSetting);
            break;
        }
        switch(gameDifficulty){
        //Difficulty
        case "Easy":
            componentInputs[2].setMatrix(0,2,0,2,optionMatrix(1,3));
            componentInputs[2].setMatrix(3,5,3,5,optionMatrix(1,3));
            break;
        case "Medium":
            componentInputs[2].setMatrix(0,2,0,2,optionMatrix(2,3));
            componentInputs[2].setMatrix(3,5,3,5,optionMatrix(2,3));
            break;
        case "Hard":
            componentInputs[2].setMatrix(0,2,0,2,optionMatrix(3,3));
            componentInputs[2].setMatrix(3,5,3,5,optionMatrix(3,3));
            break;
        case "none":
            break;
        default:
            System.out.println("Unanticipated Input for gameDifficulity " + gameDifficulty);
            break;
        }
        System.out.println("social: "+ socialRating + " Professional: "+ professionalRating + " Educational: " + educationalRating);

        if(!(socialRating == professionalRating && professionalRating == educationalRating))
        {
            if(socialRating>professionalRating && socialRating>educationalRating)
            {
                componentInputs[2].setMatrix(6,8,6,8,optionMatrix(1,3));
                componentInputs[1].setMatrix(0,2,0,2,optionMatrix(1,3));

            }
            if(professionalRating>=socialRating && professionalRating>educationalRating)
            {
                componentInputs[3].setMatrix(0,2,0,2,optionMatrix(2,3));
                componentInputs[2].setMatrix(6,8,6,8,optionMatrix(2,3));
                componentInputs[1].setMatrix(0,2,0,2,optionMatrix(3,3));

            }
            if(educationalRating>=socialRating && educationalRating>=professionalRating)
            {
                componentInputs[2].setMatrix(6,8,6,8,optionMatrix(3,3));
                componentInputs[1].setMatrix(0,2,0,2,optionMatrix(2,3));
            }
        }
    }
    
    /**
     * Check for xml.
     *
     * @param input the input
     */
    public final void checkForXML(final String input)
    {           
        if (!input.contains(".")) {
            setGameSavePath(input+".xml");
        }
        else { 
            String extension = input.substring(input.lastIndexOf(".") + 1, input.length());
            if (!extension.equalsIgnoreCase("XML")) {
                setGameSavePath(input.substring(0,input.lastIndexOf("."))+".xml");
            }
        }
    }
    
    public final void actionPerformed(final ActionEvent e) 
    {
        switch (e.getActionCommand()) {
        
        case "Submit":
            printStrings();
            distributeInputs();
            System.out.println("Submit Clicked");
            saveFileChooser = new JFileChooser("OutputGames//");
            int returnValue = saveFileChooser.showSaveDialog(saveFileChooser);
            if (returnValue==JFileChooser.APPROVE_OPTION) {
                File file = saveFileChooser.getSelectedFile();
                setGameSavePath(file.getAbsolutePath()); 
                checkForXML(getGameSavePath());
                System.out.println("Game Save Path: "+ getGameSavePath());
                submitClicked = true;
                Currentfile = file;
                //previewGame(file);
            }
            else if (returnValue == JFileChooser.CANCEL_OPTION) {
                System.out.println("Save cancelled by user. \n Returning.");
            }
            break;
        case "primary":
            gameGradeLevel = "primary";
            break;
        case "secondary":
            gameGradeLevel = "secondary";
            break;
        case "high":
            gameGradeLevel = "high";
            break;
        case "college":
            gameGradeLevel = "college";
            break;
        case "jobTraining":
            gameGradeLevel = "jobTraining";
            break;
        case "no grade":
            gameGradeLevel = "none";
        break;
//Gender            
        case "Male": 
            playerGender = "Male";
            break;
        case "Female":
            playerGender = "Female";
            break;
        case "no gender":
            playerGender = "none";
//Age                   
        case "Young":
            playerAge = "Young";
            break;
        case "Old":
            playerAge = "Old";
            break;
        case "no age":
            playerAge = "none";
            break;
//Dress
        case "Casual":
            playerDress = "Casual";
            break;
        case "Fancy":
            playerDress = "Fancy";
            break;
        case "no dress":
            playerDress = "none";
            break;
//Theme
        case "Gooble":
            gameTheme = "Gooble";
            break;
        case "Dream":
            gameTheme = "Dream";
            break;
        case "VirtualTour":
            gameTheme = "VirtualTour";
            break;
        case "Workplace":
            gameTheme = "Workplace";
            break;
        case "Other":
            gameTheme = "Other";
            break;
        case "no theme":
            gameTheme = "none";
            break;
//Subject
        case "English":
            gameSubject = "English";
            break;
        case "Math":
            gameSubject = "Math";
            break;
        case "Science":
            gameSubject = "Science";
            break;
        case "Social Studies":
            gameSubject = "Social Studies";
            break;
        case "Literature":
            gameSubject = "Literature";
            break;
        case "Professional":
            gameSubject = "Professional";
            break;
        case "no subject":
            gameSubject = "none";
            break;
//Setting
        case "ProfessionalSetting":
            gameSetting = "Professional";
            break;
        case "CasualSetting":
            gameSetting = "Casual";
            break;
        case "NaturalSetting":
            gameSetting = "Natural";
            break;
        case "EducationalSetting":
            gameSetting = "Educational";
            break;
        case "Non-terrestrialSetting":
            gameSetting = "Non-terrestrial";
            break;
        case "no setting":
            gameSetting = "none";
            break;
//Difficulty
        case "Easy":
            gameDifficulty = "Easy";
            break;
        case "Medium":
            gameDifficulty = "Medium";
            break;
        case "Hard":
            gameDifficulty = "Hard";
            break;
        case "no difficulty":
            gameDifficulty = "none";
            break;
        default:
        System.out.println("Unanticipated Input in ActionPerformed:" + e.getActionCommand());
        break;
        }
    }

    public static String getGameSavePath() {
        return gameSavePath;
    }

    public void setGameSavePath(String gameSavePath) {
        this.gameSavePath = gameSavePath;
    }

}

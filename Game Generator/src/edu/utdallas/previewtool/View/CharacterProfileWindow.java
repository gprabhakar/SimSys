package edu.utdallas.previewtool.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import edu.utdallas.gamespecification.Character;


/**
 * The Class CharacterProfileWindow.
 */
public class CharacterProfileWindow extends JDialog {

/** The Constant serialVersionUID. */
private static final long serialVersionUID = 1L;

/** The Constant HEIGHT. */
public static final int WIDTH = 640, HEIGHT = 480;

/** The font. */
private final Font font;

/** The element grid. */
private JPanel tagGrid, elementGrid;

/** The Constant FIVE. */
public static final int FIVE = 5;

/** The Constant FIFTEEN. */
public static final int FIFTEEN = 15;


/** The bottom of label. */
private Border bottomOfLabel = BorderFactory.createMatteBorder(
0, 0, FIVE, 0, Color.black);

/**
 * Instantiates a new character profile window.
 *
 * @param owner the owner
 * @param ch the ch
 */
public CharacterProfileWindow(final JFrame owner, final Character ch) {
super(owner, ch.getName() + "'s Profile", Dialog.DEFAULT_MODALITY_TYPE);
setSize(WIDTH, HEIGHT);
Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width / 2 - WIDTH / 2, d.height / 2 - HEIGHT / 2);
setBackground(Color.YELLOW);
font = new Font("Comic Sans MS", Font.BOLD, FIFTEEN);

tagGrid = new JPanel(new GridLayout(0 , 1));
tagGrid.setBackground(Color.YELLOW);
elementGrid = new JPanel(new GridLayout(0 , 1));
elementGrid.setBackground(Color.YELLOW);
JPanel profilePanel = new JPanel(new BorderLayout());
((BorderLayout) profilePanel.getLayout()).setHgap(2);

//Name
JLabel nameLabel = addProfileLabel("Name", ch.getName(), true);
nameLabel.setBorder(bottomOfLabel);

//Commented out the fields not in Profile Class
/*
//Attendance
JLabel attendanceLabel = addProfileLabel("Attendance",
ch.getProfile().getAttendance(), true);
attendanceLabel.setBorder(bottomOfLabel);

//Availability
JLabel availabilityLabel = addProfileLabel("Availability",
ch.getProfile().getAvailability(), true);
availabilityLabel.setBorder(bottomOfLabel);

//Communication
JLabel communicationLabel = addProfileLabel("Communication",
ch.getProfile().getCommunication(), true);
communicationLabel.setBorder(bottomOfLabel);

//Teamwork
JLabel teamworkLabel = addProfileLabel("Teamwork",
ch.getProfile().getTeamwork(), true);
teamworkLabel.setBorder(bottomOfLabel);*/

//Title
JLabel titleLabel = addProfileLabel("Title", ch.getProfile().getTitle(), true);
titleLabel.setBorder(bottomOfLabel);

//Experience
JLabel experienceLabel = addProfileLabel("Experience",
Integer.toString(ch.getProfile().getYearsOfExperience()), true);
experienceLabel.setBorder(bottomOfLabel);

//Skills
for (int i = 0; i < ch.getProfile().getSkills().size(); i++) {
JLabel skillsLabel;
if (i == 0) {
    skillsLabel = addProfileLabel("Skills",
                  ch.getProfile().getSkills().get(i), false);
    }
else if (i + 1 == (ch.getProfile().getSkills().size())) {
skillsLabel = addProfileLabel("", ch.getProfile().getSkills().get(i), true);
skillsLabel.setBorder(bottomOfLabel);
}
else { skillsLabel = addProfileLabel("",
ch.getProfile().getSkills().get(i), false); }
}

//Demographics
for (int i = 0; i < ch.getProfile().getDemographics().size(); i++) {
JLabel demographicsLabel;
if (i == 0) { demographicsLabel = addProfileLabel("Demographics",
ch.getProfile().getDemographics().get(i), false); }
else if (i + 1 == (ch.getProfile().getDemographics().size())) {
demographicsLabel = addProfileLabel("",
ch.getProfile().getDemographics().get(i), true);
demographicsLabel.setBorder(bottomOfLabel);
}
else { demographicsLabel = addProfileLabel("",
ch.getProfile().getDemographics().get(i), false); }
}

//Degrees
for (int i = 0; i < ch.getProfile().getDegrees().size(); i++)
{
JLabel degreesLabel;
if (i == 0) { degreesLabel = addProfileLabel("Degrees",
ch.getProfile().getDegrees().get(i), false); }
else if (i + 1 == (ch.getProfile().getDegrees().size())) {
degreesLabel = addProfileLabel("", ch.getProfile().getDegrees().get(i), true);
degreesLabel.setBorder(bottomOfLabel);
}
else { degreesLabel = addProfileLabel("",
ch.getProfile().getDegrees().get(i), false); }
}

profilePanel.add(tagGrid, BorderLayout.WEST);
profilePanel.add(elementGrid, BorderLayout.CENTER);
JScrollPane scrollPane = new JScrollPane(profilePanel);
add(scrollPane, BorderLayout.CENTER);
}

/**
 * Adds the profile label.
 *
 * @param title the title
 * @param text the text
 * @param isLastEntry the is last entry
 * @return the j label
 */
private JLabel addProfileLabel(final String title,
final String text, final boolean isLastEntry) {
JLabel label = new JLabel(title);
label.setFont(font);
JTextField experienceField = new JTextField(text);
experienceField.setBackground(Color.YELLOW);
experienceField.setEditable(false);
experienceField.setFont(font);
if (isLastEntry) {
experienceField.setBorder(bottomOfLabel);
}
((GridLayout) tagGrid.getLayout()).setRows(((GridLayout)
tagGrid.getLayout()).getRows() + 1);
((GridLayout) elementGrid.getLayout()).setRows(((GridLayout)
elementGrid.getLayout()).getRows() + 1);
tagGrid.add(label);
elementGrid.add(experienceField);

return label;
}
}

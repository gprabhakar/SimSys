package edu.utdallas.gamegeneratorcollection.ComponentCreation;

import edu.utdallas.gamegeneratorcollection.GameComposition.Characters;
import edu.utdallas.gamegeneratorcollection.GameComposition.LearningAct;
import edu.utdallas.gamegeneratorcollection.GameComposition.Locale;
import edu.utdallas.gamegeneratorcollection.GameComposition.Subject;
import edu.utdallas.gamegeneratorcollection.GameComposition.Theme;
import edu.utdallas.gamegeneratorcollection.GameOutput.Structure;

import java.util.List;

/**
 * User: clocke.
 * Date: 4/15/13
 * Time: 8:59 PM
 */
public class Layers {
    public static String CHARACTERS = "Characters";
    public static String LOCALE = "Locale";
    public static String SUBJECT = "Subject";
    public static String THEME = "Theme";
    public static String STRUCTURE = "Structure";
    public static String LESSONS = "Lessons";
    public static String CHALLENGES = "Challenges";

    /**
     * Holds character data pulled from the XML repository.
     */
    private Characters characters;
    /**
     * Holds structured act data in raw form. Generally, this
     * is pulled from theme and locale XML files, with some
     * data from Challenges.
     */
    private List<LearningAct> learningActs;
    /**
     * Holds data from Locale xml files.
     */
    private Locale locale;
    /**
     * Holds data from Subject XML files.
     */
    private Subject subject;
    /**
     * The structure class, which is used to output the final game object.
     */
    private Structure structure;
    /**
     * Holds data from Theme XML files.
     */
    private Theme theme;

    /**
     *
     * @return
     * {@link Characters}
     */
    public final Characters getCharacters() {
        return characters;
    }

    /**
     *
     * @param charsData
     * {@link Characters}
     */
    public final void setCharacters(final Characters charsData) {
        this.characters = charsData;
    }

    /**
     *
     * @return
     * {@link LearningAct}
     */
    public final List<LearningAct> getLearningActs() {
        return learningActs;
    }

    /**
     *
     * @param learnActData
     * {@link LearningAct}
     */
    public final void setLearningActs(final List<LearningAct> learnActData) {
        this.learningActs = learnActData;
    }

    /**
     *
     * @return
     * {@link Locale}
     */
    public final Locale getLocale() {
        return locale;
    }

    /**
     *
     * @param localeData
     * {@link Locale}
     */

    public final void setLocale(final Locale localeData) {
        this.locale = localeData;
    }

    /**
     *
     * @return
     * {@link Subject}
     */
    public final Subject getSubject() {
        return subject;
    }

    /**
     *
     * @param subjectData
     * {@link Subject}
     */
    public final void setSubject(final Subject subjectData) {
        this.subject = subjectData;
    }

    /**
     *
     * @return
     * {@link Structure}
     */
    public final Structure getStructure() {
        return structure;
    }

    /**
     *
     * @param structureData
     * {@link Structure}
     */
    public final void setStructure(final Structure structureData) {
        this.structure = structureData;
    }

    /**
     *
     * @return
     * {@link Theme}
     */
    public final Theme getTheme() {
        return theme;
    }

    /**
     *
     * @param themeData
     * {@link Theme}
     */
    public final void setTheme(final Theme themeData) {
        this.theme = themeData;
    }
}

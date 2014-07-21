package edu.utdallas.gamespecification;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Defines the Reward class, which handles game-related rewards
 * for a player. This class should persist through the entirety
 * of a game.
 *
 * Please note, it is not currently clear how promotions and
 * demotions work. Currently this item is listed as a String only.
 * @author Sean
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reward", propOrder = {
    "points",
    "trophies",
    "certificates",
    "promotionDemotion",
})
public class Reward {
    /**
     * Keeps track of the player's points.
     */
    @XmlElement(name = "Points")
    private int points;
    /**
     * Lists the trophies earned by a player.
     */
    @XmlElement(name = "Trophies")
    private List<String> trophies;
    /**
     * Lists the certificates earned by a player.
     */
    @XmlElement(name = "Certificates")
    private List<String> certificates;
    /**
     * Lists details whether a player is promoted or demoted.
     * When the functionality of promotion/demotion is fleshed
     * out more, this may be changed.
     */
    @XmlElement(name = "PromotionDemotion")
    private String promotionDemotion;

    /**
     * Default constructor.
     */
    public Reward() {
    }

    /**
     * Initializes a reward with a number of points.
     * @param startPoints
     * {@link int}
     */
    public Reward(final int startPoints) {
        this.points = startPoints;
    }

    /**
     * Returns the list of trophies.
     * @return
     * {@link String}
     */
    public final List<String> getTrophies() {
        return trophies;
    }

    /**
     *
     * @param trophyList
     * {@link String}
     */
    public final void setTrophies(final List<String> trophyList) {
        this.trophies = trophyList;
    }

    /**
     *
     * @return
     * {@link String}
     */
    public final List<String> getCertificates() {
        return certificates;
    }

    /**
     *
     * @param certificateList
     * {@link String}
     */
    public final void setCertificates(final List<String> certificateList) {
        this.certificates = certificateList;
    }

    /**
     *
     * @return
     * {@link String}
     */
    public final String getPromotionDemotion() {
        return promotionDemotion;
    }

    /**
     *
     * @param value
     * {@link String}
     */
    public final void setPromotionDemotion(final String value) {
        this.promotionDemotion = value;
    }

    /**
     *
     * @return
     * {@link int}
     */
    public final int getPoints() {
        return points;
    }

    /**
     *
     * @param value
     * {@link int}
     */
    public final void setPoints(final int value) {
        this.points = value;
    }
}


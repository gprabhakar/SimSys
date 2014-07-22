package edu.utdallas.gamespecification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 3/3/13
 * Time: 8:58 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Color", propOrder = {
    "a",
    "r",
    "g",
    "b"
})
public class Color {
    @XmlElement(name = "A", required = true)
    private int a;
    @XmlElement(name = "R", required = true)
    private int r;
    @XmlElement(name = "G", required = true)
    private int g;
    @XmlElement(name = "B", required = true)
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}

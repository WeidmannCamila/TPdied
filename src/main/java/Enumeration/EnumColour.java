package main.java.Enumeration;

import main.java.classes.Constants;

import java.awt.*;

public enum EnumColour {

    rojo(Color.RED, "rojo"), amarillo(Color.YELLOW, "amarillo"), cyan(Color.CYAN, "cyan"), verdeAR(Color.GREEN, "verdeAR"), naranja(Color.orange, "naranja"), verdeAB(Constants.verdecla, "verdeAB"); //each is an instance of Color

    private Color vColor;
    private String vString;
    private EnumColour(){}


    EnumColour(Color c) {
        this.vColor = c;
    }

    EnumColour(Color c, String s) {
        this.vColor = c;
        this.vString= s;
    }


    public Color getvColor() {
        return vColor;
    }

    public void setvColor(Color vColor) {
        this.vColor = vColor;
    }

    public String getvString() {
        return vString;
    }

    public void setvString(String vString) {
        this.vString = vString;
    }

}

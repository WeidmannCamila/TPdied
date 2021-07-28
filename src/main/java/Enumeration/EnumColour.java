package main.java.Enumeration;

import java.awt.*;

public enum EnumColour {

    RED(Color.RED, "rojo"), YELLOW(Color.YELLOW, "amarillo"), CYAN(Color.CYAN, "cyan"), GREEN(Color.GREEN, "verde"), ORANGE(Color.orange, "naranja"); //each is an instance of Color

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


}

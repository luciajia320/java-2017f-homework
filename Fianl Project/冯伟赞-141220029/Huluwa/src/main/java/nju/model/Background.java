package nju.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Background extends Thing2D {
    public Background(int x, int y) {
        super(x, y);
        setImageByResourceFile("img/Grassland.png");
    }
}

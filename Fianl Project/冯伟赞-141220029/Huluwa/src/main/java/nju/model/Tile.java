package nju.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Tile extends Thing2D {
    public Tile(int x, int y) {
        super(x, y);
        setImageByResourceFile("img/grass.png");
    }
}

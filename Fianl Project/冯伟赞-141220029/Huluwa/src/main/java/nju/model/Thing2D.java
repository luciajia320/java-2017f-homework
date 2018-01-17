package nju.model;

import nju.util.Print;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Thing2D {

    private Location location;
    private Image image;


    public Thing2D(int x, int y) {
        this.location = new Location(x, y);
    }

    public Thing2D(Location location) {
        this.location = location;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public void setImageByResourceFile(String filename) {
        URL loc = this.getClass().getClassLoader().getResource(filename);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        setImage(image);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocation(int x, int y) {
        if (location != null) {
            this.location.update(x, y);
        } else {
            this.location = new Location(x, y);
        }
    }
}

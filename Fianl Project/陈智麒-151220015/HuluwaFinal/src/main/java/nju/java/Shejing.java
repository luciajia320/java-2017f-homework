package nju.java;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.MarshalException;

public class Shejing extends BadMan {
    public Shejing(int x, int y, Field field) {
        super(x, y, field, "shejing");
    }

    private ImagePlayer imagewatchings = new ImagePlayer("shejing_watching", 1);

    public String toString(){
        return "Shejing\t0";
    }

    @Override
    public Image getImage() throws Exception {
        return imagewatchings.getNextImage();
    }

    @Override
    public void run(){

    }
}

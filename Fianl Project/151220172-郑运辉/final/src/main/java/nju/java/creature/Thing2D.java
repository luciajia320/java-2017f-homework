package nju.java.creature;

import nju.java.common.Data2D;

import javax.swing.*;
import java.awt.Image;
import java.io.Serializable;

public class Thing2D implements Serializable {

    protected Data2D size;
    public Data2D position;
    protected transient Image image;
    protected transient Image DieImage;
    protected int pow;
    protected int ID;
    protected boolean exit=false;


    public boolean isExit() {
        return exit;
    }

    public void Exit( ) {
        this.exit = true;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    protected Role role;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Thing2D(int x, int y) {
        this.position=new Data2D(x,y);
    }

    public Thing2D(Data2D position){
        this.position=new Data2D(position.getX(),position.getY());
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
        size=new Data2D(new ImageIcon(img).getIconWidth(),new ImageIcon(img).getIconWidth());
    }

    public int getPow(){
        return pow;
    }

    public void setPow(int pow){
        this.pow=pow;
    }

    public int x() {
        return position.getX();
    }

    public int y() {
        return position.getY();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public void setY(int y) {
        position.setY(y);
    }

    public void setDieImage(Image dieImage) {
        DieImage = dieImage;
    }

    public Image getDieImage() {
        return DieImage;
    }
}

enum Role{
    GOOD,BAD
}
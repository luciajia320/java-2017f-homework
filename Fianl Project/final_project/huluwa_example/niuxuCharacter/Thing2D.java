package niuxuCharacter;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import nju.java.Point;

public class Thing2D {

    private final int SPACE = 20;
    Point p = null;
    boolean isDead;
    public String name;
    private Image image;

    public Thing2D() {     //Ĭ�Ϲ���
    	this.p = null;
    	isDead=false;
    }
    public Thing2D(Point p) {         //�趨���깹��
    	this.p = p;
    	isDead=false;
    	p.setHolder(this);
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(String ImageSource) {               //�������ͼƬ
        URL loc = this.getClass().getClassLoader().getResource(ImageSource);
        ImageIcon iia = new ImageIcon(loc);
        Image img = iia.getImage();
        image = img;
    }
    public boolean getIsDead() {
    	return isDead;
    }
    
    public void setIsDead(boolean isDead) {
    	this.isDead=isDead;
    }

    public int x() {
        return this.p.getX();
    }

    public int y() {
        return this.p.getY();
    }
    public String getSide() {
    	return "";
    }
} 
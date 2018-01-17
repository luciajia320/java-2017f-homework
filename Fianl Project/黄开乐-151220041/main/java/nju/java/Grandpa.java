package nju.java;

import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
public class Grandpa extends Player{

	private final int OFFSET = 10;
    private final int SPACE = 60;
	public Grandpa(int x, int y, Field field) {
		super(x, y, Character.grandpa, field);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			if(field.get_completed() == true)
            	break;
			try {
                Thread.sleep(1000);
                this.field.repaint();

            } catch (Exception e) {

            }
		}
	}
}

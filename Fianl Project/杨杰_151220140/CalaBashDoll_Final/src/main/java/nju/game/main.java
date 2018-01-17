package main.java.nju.game;

import javax.swing.*;

public class main extends JFrame{
    public main(){
        Battle battle = new Battle();
        Battlefield field = new Battlefield(battle);
        battle.set_Field(field);
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1620,950);
        setVisible(true);
        setTitle("葫芦娃大战蝎子精");
    }

    public static void main(String[] args){
        main start = new main();
    }
}

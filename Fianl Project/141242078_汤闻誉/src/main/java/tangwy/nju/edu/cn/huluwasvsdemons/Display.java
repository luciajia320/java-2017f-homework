package tangwy.nju.edu.cn.huluwasvsdemons;

import tangwy.nju.edu.cn.huluwasvsdemons.things.creature.Creature;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Display extends JPanel {
    private final int
            offset=40,
            rowNum=20,
            columnNum=32,
            width=columnNum*offset,
            height=rowNum*offset;
    private Battlefield battlefield;
    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
    }

    Display (){
        setBounds(240,0,width,height);
    }
    @Override
    public void paint(Graphics graphics){
    //    System.out.println("paint");
        super.paint(graphics);
        buildWorld(graphics);
    }

    private void buildWorld(Graphics graphics){
        graphics.setColor(new Color(200, 255, 200));
        graphics.fillRect(0,0,width,height);

        /*
        Battlefield battlefield=GUIWindow.getBattlefield();
        for(Creature creature:battlefield.getTeam1()){
            if(!creature.isDied())
                graphics.drawImage(creature.getImage(),creature.getX(),creature.getY(),this);
        }
        for(Creature creature:battlefield.getTeam2()){
            if(!creature.isDied())
                graphics.drawImage(creature.getImage(),creature.getX(),creature.getY(),this);
        }
        */
        ArrayList<Replay> replays=GUIWindow.getReplay();
        for(Replay replay:replays){
            URL loc = this.getClass().getClassLoader().getResource(replay.imageName);
            ImageIcon iia = new ImageIcon(loc);
            Image image = iia.getImage();
            graphics.drawImage(image,replay.x,replay.y,this);
        }
    }
}



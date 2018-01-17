package Creature;

import ENUM.COLOR;
import ENUM.NAME;
import UI.BattleImage;

import java.util.Random;

public class Brother extends Creature {

    private COLOR color;


    public COLOR getColor() {
        return color;
    }



    public Brother(COLOR color, NAME name) {

        super();
        this.color = color;
        this.name = name;
        this.basic_Hp =10;
        this.current_Hp = 10;
        this.basic_damage = 5;
        setImg_left(BattleImage.getImage("Images/" + color + "_l.png"));
        setImg_right(BattleImage.getImage("Images/" + color + "_r.png"));
        setImg_dead(BattleImage.getImage("Images/" + color + "_d.png"));
        direction = 1;
    }

    @Override
    public synchronized int damage() {
        Random random = new Random();
        return basic_damage + random.nextInt(11);
    }

}



package Creature;

import ENUM.Mname;
import ENUM.NAME;
import UI.BattleImage;

import java.util.Random;

public class Monster extends Creature {

    protected Mname mname;


    protected Monster(Mname name, int hp, int bd){
        super();
        this.mname = name;
        this.basic_Hp = hp;
        this.current_Hp = hp;
        this.basic_damage = bd;
        setImg_left(BattleImage.getImage("Images/" + mname + "_l.png"));
        setImg_right(BattleImage.getImage("Images/" + mname + "_r.png"));
        setImg_dead(BattleImage.getImage("Images/" + mname + "_d.png"));
        direction = 0;
    }

    public Monster(){
        super();
        this.mname = Mname.louluo;
        this.name = NAME.小喽啰;
        this.basic_Hp = 5;
        this.current_Hp = 5;
        this.basic_damage = 4;
        setImg_left(BattleImage.getImage("Images/" + mname + "_l.png"));
        setImg_right(BattleImage.getImage("Images/" + mname + "_r.png"));
        setImg_dead(BattleImage.getImage("Images/" + mname + "_d.png"));
        direction = 0;
    }


    @Override
    public synchronized int damage() {
        Random random = new Random();
        return basic_damage + random.nextInt(6);
    }


}


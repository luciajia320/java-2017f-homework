package Creature;

import UI.BattleImage;

public class Grandfa extends Creature {

    private static final String name="爷爷";

    private static Grandfa grandfa;

    private Grandfa(){
        super();
        basic_Hp = 5;
        current_Hp = 5;
        basic_damage = 1;
        setImg_left(BattleImage.getImage("Images/grandfa_l.png"));
        setImg_right(BattleImage.getImage("Images/grandfa_r.png"));
        setImg_dead(BattleImage.getImage("Images/grandfa_d.png"));
        direction = 1;
    }

    public static Grandfa getGrandfa() {

        if(grandfa == null){
            grandfa = new Grandfa();
        }
        return grandfa;
    }

    @Override
    public synchronized int damage() {
        return basic_damage;
    }



}

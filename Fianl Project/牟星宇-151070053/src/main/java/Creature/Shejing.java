package Creature;

import ENUM.Mname;
import ENUM.NAME;
import UI.BattleImage;

public class Shejing extends Monster {

    private static Shejing SheJ ;
    private Shejing(){
        super(Mname.shejing, 20, 10);
        this.name = NAME.蛇精;
        setImg_left(BattleImage.getImage("Images/" + mname + "_l.png"));
        setImg_right(BattleImage.getImage("Images/" + mname + "_r.png"));
        setImg_dead(BattleImage.getImage("Images/" + mname + "_d.png"));
        direction = 0;
    }

    public static Shejing getSheJ(){

        if(SheJ == null){
            SheJ = new Shejing();
        }
        return SheJ;
    }

}

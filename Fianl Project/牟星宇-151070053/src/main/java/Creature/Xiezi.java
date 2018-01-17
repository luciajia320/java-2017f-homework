package Creature;

import ENUM.Mname;
import ENUM.NAME;
import UI.BattleImage;

public class Xiezi extends Monster {

    private static Xiezi XieZ;

    private Xiezi(){
        super(Mname.xiezi, 20, 10);
        this.name = NAME.蝎子精;
        setImg_left(BattleImage.getImage("Images/" + mname + "_l.png"));
        setImg_right(BattleImage.getImage("Images/" + mname + "_r.png"));
        setImg_dead(BattleImage.getImage("Images/" + mname + "_d.png"));
        direction = 0;
    }

    public static Xiezi getXieZ(){

        if(XieZ == null){
            XieZ = new Xiezi();
        }
        return XieZ;
    }

}

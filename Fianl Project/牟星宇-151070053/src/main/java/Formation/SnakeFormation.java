package Formation;

import Creature.Creature;
import Ground.Position;

public class SnakeFormation extends Formation {

    //长蛇阵

    public SnakeFormation(Creature[] creatures){
        super(2,creatures.length);
        for(int i = 0; i < creatures.length; i++){
            creatures[i].setPosition(new Position(0,i));
            this.creatures.add(creatures[i]);
        }
    }

    @Override
    public void initial(){
        for(Creature creature:creatures){creature.initial();}
        for(int i = 0; i < creatures.toArray().length; i++) {
            creatures.get(i).setPosition(new Position(0, i));
        }
    }


}

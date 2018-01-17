package Formation;

import Creature.Creature;
import Ground.Position;

public class CraneFormation extends Formation {

    //鹤翼阵

    public CraneFormation(Creature[] creatures){
        super(creatures.length/2 + 1, creatures.length);
        creatures[0].setPosition(new Position(0, height /2));
        int i = 0;
        for(int j = creatures.length/2; i <= creatures.length/2; i++, j--){
            creatures[i + 1].setPosition(new Position(j, i));
            this.creatures.add(creatures[i]);
        }
        for(int j = 1; i < height; i++, j++){
            creatures[i].setPosition(new Position(j, i));
            this.creatures.add(creatures[i]);
        }
    }

    @Override
    public void initial() {

        for(Creature creature:creatures){
            creature.initial();
        }
        creatures.get(0).setPosition(new Position(0, height /2));
        int i = 0;
        for(int j = creatures.toArray().length/2; i <= creatures.toArray().length/2; i++, j--){
            creatures.get(i + 1).setPosition(new Position(j, i));
        }
        for(int j = 1; i < height; i++, j++){
            creatures.get(i).setPosition(new Position(j, i));
        }
    }
}

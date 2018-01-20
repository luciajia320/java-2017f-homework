package formation;

import creature.Creature;
import location.Location;

public class HeyiFormation extends Formation {
    public HeyiFormation(Creature creature[]){
        super(4, 7);

        int count = 0;
        Location[] loc = new Location[7];
        for (int i = 0; i < 4; i++){
            loc[i] = new Location(i, i);
        }
        loc[4] = new Location(2, 4);
        loc[5] = new Location(1, 5);
        loc[6] = new Location(0, 6);

        for (int i = 0; i < loc.length; i++) {
            if (i < creature.length) {
                creature[i].setLoc(loc[i]);
                contents[loc[i].getY()][loc[i].getX()] = creature[i];
            }
        }
    }
}

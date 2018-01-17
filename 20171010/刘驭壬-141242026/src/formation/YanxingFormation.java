package formation;

import creature.Creature;
import location.Location;

public class YanxingFormation extends Formation{
    public YanxingFormation(Creature creature[]){
        super(5, 5);

        int count = 0;
        Location[] loc = new Location[5];
        for (int i = 0; i < 5; i++){
            loc[i] = new Location(4- i, i);
        }

        for (int i = 0; i < loc.length; i++) {
            if (i < creature.length) {
                creature[i].setLoc(loc[i]);
                contents[loc[i].getY()][loc[i].getX()] = creature[i];
            }
        }
    }
}

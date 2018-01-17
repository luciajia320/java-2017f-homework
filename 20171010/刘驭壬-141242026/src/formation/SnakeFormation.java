package formation;

import creature.Creature;
import location.Location;

public class SnakeFormation extends Formation {
    public SnakeFormation(Creature creature[]){
        super(7, 1);

        int count = 0;
        Location[] loc = new Location[7];
        for (int i = 0; i < 7; i++){
            loc[i] = new Location(i, 0);
        }
        for (int i = 0; i < loc.length; i++) {
            if (i < creature.length) {
                creature[i].setLoc(loc[i]);
                contents[loc[i].getY()][loc[i].getX()] = creature[i];
            }
        }

    }
}

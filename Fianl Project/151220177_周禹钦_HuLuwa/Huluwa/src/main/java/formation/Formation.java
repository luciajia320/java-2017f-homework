package  formation;

/**
 * Created by qin on 18.1.2.
 */
import  creature.Creature;
public interface Formation {

    Creature getCreature(int x, int y);

    int Kind();
}

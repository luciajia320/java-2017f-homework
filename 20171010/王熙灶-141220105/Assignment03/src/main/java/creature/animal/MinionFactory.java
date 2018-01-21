package creature.animal;

import java.util.ArrayList;
import java.util.Arrays;

public class MinionFactory {
    private static ArrayList<Minion> minions = new ArrayList<>(Arrays.asList(
            new Minion(), new Minion(), new Minion(),
            new Minion(), new Minion(), new Minion()
    ));

    private static MinionFactory minionFactory = new MinionFactory();
    private MinionFactory() {}

    public static MinionFactory getInstance() {
        return minionFactory;
    }

    public Minion get(int i) {
        if(i >= 6) {
            throw new IndexOutOfBoundsException("method 'Minion get(int i)' index out of bounds in class MinionFactory.");
        }
        return minions.get(i);
    }
}

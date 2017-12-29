package creature;

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
        return minions.get(i);
    }
}

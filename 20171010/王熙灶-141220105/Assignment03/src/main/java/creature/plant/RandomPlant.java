package creature.plant;

import creature.Creature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomPlant {
    private static ArrayList<Class<? extends Plant>> plants = new ArrayList<>(Arrays.asList(
        Blossom.class, /* blossom */
        Cactus.class, /* cactus */
        FourLeafClover.class, /* four leaf clover */
        Hibiscus.class, /* hibiscus */
        MapleLeaf.class, /* maple leaf */
        Mushroom.class, /* mushroom */
        PalmTree.class, /* palm tree */
        Rose.class, /* rose */
        SunFlower.class, /* sun flower */
        Tulip.class /* tulip */
    ));

    private static Random random = new Random();

    public static Creature get() {
        Creature creature = null;
        try {
            creature = plants.get(random.nextInt(plants.size())).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return creature;
    }
}
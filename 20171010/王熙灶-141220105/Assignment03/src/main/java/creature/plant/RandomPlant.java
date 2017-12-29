package creature.plant;

import creature.Creature;

import java.util.Random;

public class RandomPlant {
    private static Class[] plants = {
            Blossom.class, /* blossom */
            Cactus.class, /* cactus */
            FourLeafClover.class, /* four leaf clover */
            Hibiscus.class, /* hibiscus */
            MapleLeaf.class, /* maple leaf */
            Mushroom.class, /* mushroom */
            PalmTree.class, /* palm tree */
            Rose.class, /* rose */
            SunFlower.class, /* sun flower */
            Tulip.class, /* tulip */
    };

    private static Random random = new Random(plants.length);

    public static Creature get() {
        Creature creature = null;
        try {
            creature = (Creature) plants[random.nextInt(plants.length)].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return creature;
    }
}
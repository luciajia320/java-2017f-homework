package creature.animal;

import creature.Creature;

/**
 * 爷爷类，继承自Creature类
 */
public final class GrandPa extends Creature {
    private static GrandPa grandPa = new GrandPa();

    private GrandPa() {}

    public static GrandPa getInstance() {
        return grandPa;
    }
    @Override
    public String toString() {
        return "\uD83D\uDC74";
    }
}

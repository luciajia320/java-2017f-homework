package creature.animal;

import creature.Creature;

/**
 * 蝎子精类，继承自Creature类
 */
public final class ScorpionEssence extends Creature {
    private static ScorpionEssence scorpionEssence = new ScorpionEssence();

    private ScorpionEssence() {}

    public static ScorpionEssence getInstance() {
        return scorpionEssence;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD82";
    }
}
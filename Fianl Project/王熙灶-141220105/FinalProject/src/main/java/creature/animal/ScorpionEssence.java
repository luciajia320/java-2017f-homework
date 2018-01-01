package creature.animal;

import util.ImageReader;

/**
 * 蝎子精类，继承自Animal类
 */
public final class ScorpionEssence extends Animal {
    private static ScorpionEssence scorpionEssence = new ScorpionEssence();

    private ScorpionEssence() {
        imageAlive = ImageReader.getImage("scorpion-alive.png");
        imageDead = ImageReader.getImage("scorpion-dead.png");
    }

    public static ScorpionEssence getInstance() {
        return scorpionEssence;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD82";
    }
}
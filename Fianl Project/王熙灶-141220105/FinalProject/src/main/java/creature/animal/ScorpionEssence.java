package creature.animal;

import util.ImageReader;

/**
 * 蝎子精类，继承自Animal类
 */
public final class ScorpionEssence extends Animal {
    private static ScorpionEssence scorpionEssence = new ScorpionEssence();

    private ScorpionEssence() {
        goodguy = false;
        imageAlivePath = "scorpion-alive.png";
        imageDeadPath = "scorpion-dead.png";
        imagePath = imageAlivePath;
        imageAlive = ImageReader.getImage(imageAlivePath);
        imageDead = ImageReader.getImage(imageDeadPath);
    }

    public static ScorpionEssence getInstance() {
        return scorpionEssence;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD82";
    }
}
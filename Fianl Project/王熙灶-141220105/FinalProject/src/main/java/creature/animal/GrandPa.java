package creature.animal;

import util.ImageReader;

/**
 * 爷爷类，继承自Animal类
 */
public final class GrandPa extends Animal {
    private static GrandPa grandPa = new GrandPa();

    private GrandPa() {
        imageAlive = ImageReader.getImage("grandpa-alive.png");
        imageDead = ImageReader.getImage("grandpa-dead.png");
    }

    public static GrandPa getInstance() {
        return grandPa;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC74";
    }
}

package creature.animal;

import util.ImageReader;

/**
 * 爷爷类，继承自Animal类
 */
public final class GrandPa extends Animal {
    private static GrandPa grandPa = new GrandPa();

    private GrandPa() {
        goodguy = true;
        imageAlivePath = "grandpa-alive.png";
        imageDeadPath = "grandpa-dead.png";
        imagePath = imageAlivePath;
        imageAlive = ImageReader.getImage(imageAlivePath);
        imageDead = ImageReader.getImage(imageDeadPath);
    }

    public static GrandPa getInstance() {
        return grandPa;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC74";
    }

    public static void main(String[] args) {
        System.out.println(grandPa.getImage().toString());
    }
}

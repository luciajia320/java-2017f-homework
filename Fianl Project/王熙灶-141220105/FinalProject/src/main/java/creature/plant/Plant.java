package creature.plant;

import creature.Creature;

import java.awt.*;

@Deprecated
public class Plant extends Creature {
    private String plantName;
    protected String plantEmoji;

    public Plant(String name, String emoji) {
        plantName = name;
        plantEmoji = emoji;
    }

    public String getPlantEmoji() {
        return plantEmoji;
    }

    public String getPlantName() {
        return plantName;
    }

    @Override
    public Image getImage() {
        return null;
    }
}

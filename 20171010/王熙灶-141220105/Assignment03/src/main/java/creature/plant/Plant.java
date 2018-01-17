package creature.plant;

import creature.Creature;

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
}

package creature;

import formation.BasicFormation;
import space.Position;

/**
 * 妖精战队，由蝎子精带领六只小喽啰组成
 */
public class EssenceCrops {
    private ScorpionEssence scorpionEssence;
    private Minion[] minions;
    private BasicFormation basicFormation;

    public EssenceCrops() {
        scorpionEssence = new ScorpionEssence();
        minions = new Minion[6];
        for(int i = 0; i < 6; i++) {
            minions[i] = new Minion();
        }
    }

    /**
     * @param formation, 设定妖精战队的阵法
     */
    public void setFormation(BasicFormation formation) {
        basicFormation = formation;
        Position pos = formation.Next();
        scorpionEssence.setPosition(pos);
        pos.setHolder(scorpionEssence);

        for(Minion minion: minions) {
            pos = formation.Next();
            if(pos == null) break;
            minion.setPosition(pos);
            pos.setHolder(minion);
        }
    }

    /**
     * 清除妖精战队的当前阵法
     */
    public void clearFormation() {
        basicFormation.reset();
        Position pos = basicFormation.Next();
        while (pos != null) {
            pos.setHolder(null);
            pos = basicFormation.Next();
        }
    }
}
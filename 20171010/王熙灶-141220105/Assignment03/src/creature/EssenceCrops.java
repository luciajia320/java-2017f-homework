package creature;

import formation.BasicFormation;
import space.Position;

import java.util.Iterator;

/**
 * 妖精战队，由蝎子精带领六只小喽啰组成
 */
public class EssenceCrops {
    private ScorpionEssence scorpionEssence  = new ScorpionEssence();
    private Minion[] minions = {
        new Minion(), new Minion(), new Minion(),
        new Minion(), new Minion(), new Minion()
    };
    private BasicFormation basicFormation;

    /**
     * @param formation, 设定妖精战队的阵法
     */
    public void setFormation(BasicFormation formation) {
        basicFormation = formation;
        Iterator<Position> itr = formation.iterator();
        Position pos = itr.next();
        scorpionEssence.setPosition(pos);
        pos.setHolder(scorpionEssence);

        for(Minion minion: minions) {
            if(itr.hasNext()) {
                pos = itr.next();
                minion.setPosition(pos);
                pos.setHolder(minion);
            }
        }
    }

    /**
     * 清除妖精战队的当前阵法
     */
    public void clearFormation() {
        if(basicFormation == null) return;
        basicFormation.reset();
        Iterator<Position> itr = basicFormation.iterator();
        while (itr.hasNext()) {
            Position pos = itr.next();
            pos.setHolder(null);
        }
    }
}
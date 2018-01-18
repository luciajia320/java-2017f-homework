import java.util.ArrayList;
import java.util.List;

public class HuluQueue {
    private List<Position> positions;
    private List<HuluBaby> huluBabies;

    /*public Position[] getPositions() {
        return positions;
    }*/

    /*public List<HuluBaby> getHuluBabies() {
        return huluBabies;
    }*/
    public HuluQueue(List<HuluBaby> babies){
        positions = new ArrayList<>(babies.size());
        huluBabies = babies;

        for(int i = 0; i < babies.size(); i++){
            positions.add(i,new Position(i, 0));
            babies.get(i).setPosition(positions.get(i));
        }

    }

    public List<Creature> getCreature() {

       List<Creature> creatures = new ArrayList<>();
       creatures.addAll(huluBabies);
       return creatures;
    }
}

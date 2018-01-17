import java.util.ArrayList;
import java.util.List;

public class EnemyFormation {
    private List<List<Position>> positions;
    private Scorpion scorpion;
    private List<Minion> minions;
    private Formation formation;
    private static final int N = 20;

    public EnemyFormation(Scorpion scorpion, List<Minion> minions) {
        this.scorpion = scorpion;
        this.minions = minions;
        positions = new ArrayList<>(N);
        for(int i = 0; i < N; i++)
            positions.add(new ArrayList<>());
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                positions.get(i).add(new Position(i, j));
        }
    }

    public void formate(Formation formation) {
        this.formation = formation;
        int indexOfMinion = 0;
        switch (formation) {
            case 鹤立:
                for (int i = 0; i < 5; i++) {
                    int j;
                    for (j = 0; j < i; j++);
                     //   System.out.print("  ");
                    minions.get(indexOfMinion++).setPosition(positions.get(i).get(j++));
                    j += 9 - 2*i;
                    minions.get(indexOfMinion++).setPosition(positions.get(i).get(j));
                }
                scorpion.setPosition(positions.get(5).get(5));
                break;
            case 雁行:
                break;
            case 冲轭:
                break;
            case 长蛇:
                break;
            case 鱼鳞:
                break;
            case 方门:
                break;
            case 偃月:
                break;
            case 锋矢:
                scorpion.setPosition(positions.get(4).get(3));
                for(int i = 0; i < 4; i++)
                    minions.get(indexOfMinion++).setPosition(positions.get(i).get(3));
                for(int i = 1; i <= 3; i++){
                    int j = 0;
                    for(; j < i - 1; j++)
                        System.out.print("  ");
                    minions.get(indexOfMinion++).setPosition(positions.get(i).get(j++));
                    j = 7-j;
                    minions.get(indexOfMinion++).setPosition(positions.get(i).get(j));
                    System.out.println("");
                }
                break;
        }
    }

    public List<Creature> getCreature() {
        List<Creature> creatures = new ArrayList<>(minions.size()+1);
        creatures.addAll(minions);
        creatures.add(scorpion);
        return creatures;
    }
}

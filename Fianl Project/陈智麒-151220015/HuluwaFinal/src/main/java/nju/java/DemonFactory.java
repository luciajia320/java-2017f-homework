package nju.java;

public class DemonFactory extends PlayersFactory {
    public DemonFactory(QueueType queueType) {
        super(queueType);
    }

    Creature[] create(Field field, Thing2D start, int space) {

        players[0] = new Xiezijing(start.x(), start.y(), field);

        for (int i = 1; i < queueType.num; i++) {
            players[i] = new Xiaolouluo1( start.x() + space * table[i][0], start.y() + space * table[i][1], field, i - 1);
        }

        return players;
    }
}

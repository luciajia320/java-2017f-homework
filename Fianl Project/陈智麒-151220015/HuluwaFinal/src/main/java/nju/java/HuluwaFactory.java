package nju.java;


public class HuluwaFactory extends PlayersFactory {
    public HuluwaFactory(QueueType queueType) {
        super(queueType);
    }

    public Creature[] create(Field field, Thing2D start, int space) {

        for (int i = 0; i < queueType.num; i++) {
            players[i] = new Huluwa(
                    start.x() + space * table[i][0],
                    start.y() + space * table[i][1],
                    field,
                    Huluwa.Color.values()[i],
                    Huluwa.Order.values()[i]);
        }

        return players;
    }
}

public class God {

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Embattler embattler = new Embattler();
        Space space = new Space2D(20, 10);

        Creature[] brothers = new Creature[7], yeye = new Creature[1];
        for (int i = 0; i < 7; i ++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        yeye[0] = new Laoyeye();
        Queue queueBro = new Queue(brothers);
        embattler.embattle(queueBro, FORMATION.长蛇,
                new PosCoord(11, 5), ORIENTATION.WEST);
        sorter.sort(queueBro, SORT_TYPE.INSERTION);
        Queue queueYeye = new Queue(yeye);
        yeye[0].getPosition().setPosCoord(new PosCoord(11, 0));

        Creature[] monsters = new Creature[7], shejing = new Creature[1];
        monsters[0] = new Xiejing();
        for (int i = 1; i < 7; i ++) {
            monsters[i] = new Louluo();
        }
        shejing[0] = new Shejing();
        Queue queueMst = new Queue(monsters);
        Queue queueSj = new Queue(shejing);
        shejing[0].getPosition().setPosCoord(new PosCoord(9, 0));

        space.add(queueBro).add(queueMst).add(queueYeye).add(queueSj);

        for (FORMATION formation : FORMATION.values()) {
            if (formation != FORMATION.长蛇) {
                embattler.embattle(queueMst, formation, new PosCoord(9, 5), ORIENTATION.EAST);
                System.out.printf("--阵型：%s--\n", formation.name());
                space.display();
            }
        }
    }
}

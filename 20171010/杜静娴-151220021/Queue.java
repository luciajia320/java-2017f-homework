import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {


    private Position[] positions;

    public Position[] getPositions() {
        return positions;
    }


    public Creature[] getCreatures() {
        return creatures;
    }



    private Creature[] creatures;

    public Queue(Huluwa[] brothers) {


        this.positions = new Position[brothers.length];

        this.creatures = brothers;

        for (int i = 0; i < brothers.length; i++) {

            this.positions[i] = new Position(i);
            this.creatures[i].setPosition(this.positions[i]);
        }
    }


    public void rollCall() {
        for (Creature creature : this.creatures) {
            creature.report();
        }
        System.out.println();
        System.out.flush();

        for (Position position : this.positions) {

            position.getHolder().report();
        }

        System.out.println("\n");
        System.out.flush();
    }

    private void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures[index].getPosition();
            creatures[index].setPosition(creatures[i].getPosition());
            creatures[i].setPosition(position);
        }
    }

    public static void main(String[] args) {

        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);

        }

        Creature[] monsters = new Creature[7];
        monsters[0] = new Scorpion("🦂");
        for(int i = 1; i < 7; i ++)
            monsters[i] = new Frog("🐸");

        BattleField field = new BattleField(10,10);

        SnakeForm huluwa = new SnakeForm(1,1,brothers);
        CraneWingForm yaoguai1 = new CraneWingForm(6,1,monsters);
        GooseForm yaoguai2 = new GooseForm(5,2,monsters);

        Creature yeye = new Grandpa(0,0,"👴");
        Creature she = new Shejing(0,0,"🐍");

        yeye.setPos(4,0);
        she.setPos(5,9);

        System.out.println("🦂：鹤翼阵！！");
        field.addFormation(huluwa);
        field.addFormation(yaoguai1);
        field.addItem(yeye);
        field.addItem(she);
        System.out.print(field.toString());
        System.out.println();

        System.out.println("👴：孩子们向前进！！");
        field.deleteForm("snakeform");
        huluwa.setPos(huluwa.getPosX()+1,huluwa.getPosY());
        field.addFormation(huluwa);
        System.out.print(field.toString());
        System.out.println();

        System.out.println("🐍：你们这群没用的东西，赶紧动起来！！");
        System.out.println("🦂：雁行阵！！");
        field.deleteForm("craneform");
        field.addFormation(yaoguai2);
        System.out.print(field.toString());

    }
}


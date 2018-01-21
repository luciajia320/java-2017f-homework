import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Ground> groundList = new ArrayList<>();

        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);

        }
        Grandpa grandpa = new Grandpa();
        Queue queue = new Queue(7, 3, 10, 20, brothers, grandpa);


        Creature[] c = new Creature[7];
        c[0] = new Scorpion();
        for(int i = 1; i < c.length; i++) {
            c[i] = new LouLuo();
        }
        Snake snake = new Snake();
        HeYi heyi = new HeYi(5, 10, 10, 10, c, snake);

        Ground g1 = new Ground(20);
        g1.layout(queue, heyi);

        groundList.add(g1);



        YanXing yanxing = new YanXing(5, 10, 10, 10, c, snake);

        Ground g2 = new Ground(20);
        g2.layout(queue, yanxing);


        groundList.add(g2);

        for(Ground g : groundList) {
            System.out.println("开始布阵：");
            g.display();
            System.out.println();
        }
    }
}

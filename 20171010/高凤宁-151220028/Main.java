public class Main {
    public static void main(String[] args) {
        Calabash[] brothers = new Calabash[7];
        String []name = {"红娃","橙娃","黄娃","绿娃","青娃","蓝娃","紫娃"};
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Calabash(Color.values()[i],Order.values()[i],name[i]);
        }

        Queue Qcalabash = new Queue();
        Qcalabash.EnQueue(brothers);

        //葫芦娃进行排序
        new BubbleSorter().sort(Qcalabash);

        Grandpa grandpa = new Grandpa();
        Snake snake = new Snake();
        Scorpion scorpion = new Scorpion();
        Creature []enemies = new Creature[7];
        enemies[0] = scorpion;
        for(int i = 1;i < enemies.length;i++)
            enemies[i] = new Follower();

        Space space = new Space();
        //葫芦兄弟的长蛇阵型
        space.SnakeFormation(5,4,Qcalabash);
        //蝎子精领衔诸多小喽啰的鹤翼阵型
        space.CranewingFormation(8,15,enemies);

        //爷爷和蛇精分别为两个阵营加油助威
        space.SetCreature(15,4,grandpa);
        space.SetCreature(15,14,snake);

        space.Display();

        //移除蝎子精小喽啰阵营
        space.DelCreature(scorpion);
        space.DelCreature(new Follower());

        //蝎子精领衔诸多小喽啰的锋矢阵型
        space.ArrowFormation(8,12,enemies);

        space.Display();
    }
}
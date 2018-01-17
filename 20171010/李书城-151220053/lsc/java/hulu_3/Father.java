package lsc.java.hulu_3;

public class Father {
    //对程序中的一些常数进行定义
    public final static int size = 50;

    //蝎子精方阵所在起点
    public final static int enemy_start_x = 15;
    public final static int enemy_start_y = 40;

    //葫芦娃方阵所在起点
    public final static int hulus_start_x = 10;
    public final static int hulus_start_y = 10;

    //爷爷所在位置
    public final static int grandpa_x = 13;
    public final static int grandpa_y = 15;

    //蛇精所在位置
    public final static int snake_x = 13;
    public final static int snake_y = 30;

    public static void main(String args[]) {
        //新建一个二维方阵
        Square s1 = new Square(size);

        //葫芦娃兄弟们乱序出现
        huluwasquare h1 = new huluwasquare(hulus_start_x, hulus_start_y, size);

        //对葫芦兄弟进行排序
        h1.sort(hulus_start_x, hulus_start_y);

        //蝎子精和小喽啰出现
        enemysquare e1 = new enemysquare(enemy_start_x, enemy_start_y, size);

        //将其排成yanxing阵
        Yanxing y1 = new Yanxing(enemy_start_x, enemy_start_y, e1.return_square());

        //将两支队伍放置在最初的二维空间之中，形成对峙局面
        System.out.print("----------------------------the first opposite to each other---------------------------\n");

        s1.move_from_square(h1.return_square());
        s1.move_from_square(y1.return_square());

        //将老爷爷和蛇精放置于空间中，为各自一方加油助威
        s1.set(grandpa_x, grandpa_y, new Grandpa());
        s1.set(snake_x, snake_y, new Snake());

        //打印输出对峙局面
        s1.display();
        //清空空间，准备下一次的操作
        s1.clear_all();

        //重复对峙操作，并变换阵法
        System.out.print("----------------------------the second opposite to each other---------------------------\n");
        s1.move_from_square(h1.return_square());

        enemysquare e2 = new enemysquare(enemy_start_x, enemy_start_y, size);
        Heyi he = new Heyi(enemy_start_x, enemy_start_y, e1.return_square());

        s1.move_from_square(he.return_square());

        s1.set(grandpa_x, grandpa_y, new Grandpa());
        s1.set(snake_x, snake_y, new Snake());

        s1.display();
        System.out.print("---------------------------------------over-----------------------------------------\n");

    }
}

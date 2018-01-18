import Creatures.*;
import Position.Ground;
import Queue.Queue;
import Sorter.*;
import Formation.*;

public class God {
    Creature [] sevenBros;
    Creature [] Enemy;
    Creature[] cheerer;
    Creature Xiezi;

    public Creature[] getSevenBros() {
        return sevenBros;
    }

    public Creature[] getEnemy() {
        return Enemy;
    }

    static int SceneSize=20;

    void initial(Ground scenery){
        //初始化葫芦娃
        sevenBros=new CucurbitBrother[7];
        for(int i=0;i<7;i++)
            sevenBros[i]=new CucurbitBrother(Rank.values()[i],Color.values()[i]);
        //初始化喽啰
        Enemy=new LouLuo[6];
        for(int i=0;i<6;i++)
            Enemy[i]=new LouLuo("喽");
        //初始化蛇精和爷爷
        cheerer=new CheeringCharacter[2];
        cheerer[0]=new CheeringCharacter("爷");
        cheerer[1]=new CheeringCharacter("蛇");
        //初始化蝎子精
        Xiezi=new Leader("蝎");


        //随机给葫芦娃安排位置
        for(int i=0;i<7;i++){
            sevenBros[i].setPosition(scenery.getScenery()[0][i]);
        }
        //给喽啰安排位置
        for(int i=0;i<6;i++)
            Enemy[i].setPosition(scenery.getScenery()[1][i]);
        //给爷爷和蛇精安排位置
        for(int i=0;i<2;i++)
            cheerer[i].setPosition(scenery.getScenery()[2][i]);
        //给蝎子精安排位置
            Xiezi.setPosition(scenery.getScenery()[3][0]);


    }



    public static void main(String[] args){
        Ground scenery=new Ground(SceneSize,SceneSize);

        God god=new God();

        god.initial(scenery);

        Queue sevenBrosQueue=new Queue(god.getSevenBros());
        Queue EnemyQueue=new Queue(god.getEnemy());
        EnemyQueue.append(god.Xiezi);

        Formation ChangShe=new SnakeFormation(1,7) ;
        Formation HeYi =new CraneFormation(4,7);
        Formation FengShi=new ArrowFormation(6,7);

        sevenBrosQueue.setFormation(ChangShe,1,6,scenery);
        EnemyQueue.setFormation(HeYi,4,6,scenery);

        //放置蛇精和爷爷
        god.cheerer[0].setPosition(scenery.getScenery()[2][0]);
        god.cheerer[1].setPosition(scenery.getScenery()[4][15]);

        sevenBrosQueue.shuffle();

        scenery.output();
        System.out.println();

        EnemyQueue.setFormation(FengShi,4,6,scenery);

        scenery.output();

    }
}

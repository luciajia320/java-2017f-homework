package BattleGround;

import Common.RandomNumArray;
import Creatures.*;
import Creatures.Bad.Lolo;
import Creatures.Bad.Scorpion;
import Creatures.Bad.Snake;
import Creatures.Good.Grandpa;
import Creatures.Good.Huluwa;
import Position.*;
import View.PlainTextView;

import java.util.ArrayList;

public class TwoDimensionBattleGround extends BattleGround {
    public static final int GROUND_SIZE=12;
    private final int NR_LOLOS=7;
    private static TwoDimensionBattleGround ourInstance = new TwoDimensionBattleGround();

    public static TwoDimensionBattleGround getInstance() {
        return ourInstance;
    }


    CreatureFactoryInterface creatureFactoryInterface=new CreatureFactory();

    private TwoDimensionBattleGround() {
        /*  init creature List                   */
        this.creatures = new ArrayList<Creature>();
        /*  init battleGround  2-D position Array*/
        this.positionInterfaces = new PositionInterface[GROUND_SIZE * GROUND_SIZE];
        for (int i = 0; i< GROUND_SIZE; i++) {
            for (int j = 0; j < GROUND_SIZE; j++) {
                positionInterfaces[GROUND_SIZE * i + j] = new CartesianPosition(i,j);
                //positionInterfaces[GROUND_SIZE * i + j] = new PolarPosition(i,j)
            }
        }
    }
    private void clearGround()
    {
        for (int i = 0; i< GROUND_SIZE; i++) {
            for (int j = 0; j < GROUND_SIZE; j++) {
                positionInterfaces[GROUND_SIZE * i + j] = null;
            }
        }
    }

    @Override
    public void battle() {
        Creature[] loloArray=new Creature[NR_LOLOS];
        int i=0;
        for(Creature each :creatures)
        {
            each.act();
            if(each instanceof Lolo)
            {
                loloArray[i]=each;
                i++;
            }

        }
        show();


        System.out.println("小怪们变换阵形_鹤翼阵");
        MonsterChangeWing(positionInterfaces[0*GROUND_SIZE+GROUND_SIZE-1],loloArray);
        show();

        System.out.println("小怪们变换阵形_衡呃阵");
        MonsterChangeHengE(positionInterfaces[3*GROUND_SIZE+6],loloArray);
        show();

        System.out.println("小怪们变换阵形_锋矢阵");
        MonsterChangeArrow(positionInterfaces[4*GROUND_SIZE+5],loloArray);
        show();

    }

    //鹤翼阵
    public void MonsterChangeWing(PositionInterface startPos,Creature ... toBeArrangeCreatures)    {
//        int i=0,j=GROUND_SIZE-1;
        int i=startPos.getPosition()[0],j=startPos.getPosition()[1];
        for (Creature each:toBeArrangeCreatures)
        {
            each.setPosition(positionInterfaces[(i+3)*GROUND_SIZE+j]);
            i++;
            if(i<=NR_LOLOS/2)
                j--;
            else
                j++;
        }
    }

    //衡呃阵
    public void MonsterChangeHengE(PositionInterface startPos,Creature ... toBeArrangeCreatures)    {
        int i=startPos.getPosition()[0];//3
        int j=startPos.getPosition()[1];//6
        for (Creature each:toBeArrangeCreatures)
        {
            each.setPosition(positionInterfaces[i*GROUND_SIZE+j+(i+1)%2]);
            i++;
        }
    }

    //锋矢阵
    public void MonsterChangeArrow(PositionInterface startPos,Creature ... toBeArrangeCreatures)    {
        int count=0;
//        int i=4,j=5;
        int i=startPos.getPosition()[0];//4
        int j=startPos.getPosition()[1];//5
        for (Creature each:toBeArrangeCreatures)
        {
            each.setPosition(positionInterfaces[i*GROUND_SIZE+j]);
            count++;
            if(count==1)
            {
                i++;
                j--;
            }
            else if(count==NR_LOLOS-1)
            {
                i++;
                j=5;
            }
            else
            {
                j++;
            }
        }
    }

    @Override
    public void initCreature()
    {
    /*  init    Huluwa randomly*/
        Huluwa[] brothers = new Huluwa[7];
        /*we randomly choose 7 coords  without repeating num*/
        int[] randCoordX= RandomNumArray.randomCommon(0,GROUND_SIZE-1,7);
        int[] randCoordY= RandomNumArray.randomCommon(0,GROUND_SIZE-1,7);
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] =creatureFactoryInterface.createCreature(
                    Huluwa.class,positionInterfaces[randCoordX[i]*GROUND_SIZE+randCoordY[i]]
            );
        }
        addCreatures(brothers);
    System.out.println("随机站");
    show();
    /*  init    Huluwa randomly*/
        for(int i=0;i<7;i++){
            brothers[i].setPosition(positionInterfaces[(2+i)*GROUND_SIZE+2]);
        }
    System.out.println("排序");
    show();
    /*  init Scorpion*/
        addCreatures(creatureFactoryInterface.createCreature(Scorpion.class,positionInterfaces[6*GROUND_SIZE+5]));

    /*  init LoLos  */
        for(int i=0;i<NR_LOLOS;i++)
            addCreatures(new Lolo(positionInterfaces[(1+i)*GROUND_SIZE+GROUND_SIZE-1-i]));/*雁行阵*/
    System.out.println("雁行阵");
    show();

    /*  init Granpa */
        addCreatures(creatureFactoryInterface.createCreature(Grandpa.class,positionInterfaces[5*GROUND_SIZE+0]));

     /*  init Snake  */
        addCreatures(creatureFactoryInterface.createCreature(Snake.class,positionInterfaces[5*GROUND_SIZE+5]));
    }

    @Override
    public void show(){
        PlainTextView.getInstance().show(positionInterfaces);
        System.out.flush();
    }


}


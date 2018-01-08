package Battle;

import Formation.*;
import UI.Field;
import creature.*;

import java.util.ArrayList;
import java.util.Random;

//用于表示战斗的类
public class Battle {

    //以下两项用于判断此战斗是否是回放，以及作为回放时的战士链表使用
    public boolean replay=false;
    public ArrayList <Creature> replayList=null;

    //友军数量，敌军数量以及开始战斗的距离
    final int friendCount=8;
    final int enemyCount=8;
    final int fightDistance=6;

    Field field;

    //葫芦娃阵营与敌军阵营
    Queue huluwa;
    Queue enemy;

    //此构造函数用于构造回放时的数据
    public Battle(ArrayList <Creature>list)
    {
        replay=true;
        replayList=list;
    }

    //真正的战斗时的构造函数
    public Battle(Field field)
    {
        this.field=field;

        //构造葫芦娃势力
        Creature [] sevenBros=new Huluwa[friendCount-1];
        for(int i=0;i<friendCount-1;i++)
            sevenBros[i]=new Huluwa(0,i,this.field, Name.values()[i],4,1);
        huluwa=new Queue(sevenBros,field);
        huluwa.append(new Yeye(0,friendCount-1,this.field,Name.爷,2,1));

        //构造敌军实力
        Creature [] wugong=new Wugong[enemyCount-2] ;
        for(int i=0;i<enemyCount-2;i++)
            wugong[i]=new Wugong(i,field.getCol()-1,field,Name.蜈,5,0);
        enemy=new Queue(wugong,field);
        enemy.append(new Snake(enemyCount-1,field.getCol()-1,field,Name.蛇,3,0));
        enemy.append(new Xiezi(enemyCount,field.getCol()-1,field,Name.蝎,3,0));
    }

    //用于战斗时返回所有的战士以供显示
    public ArrayList<Creature> returnAll(){
        ArrayList<Creature> result=new ArrayList<>();
        if(huluwa!=null)
            for(Creature c:huluwa.getCreatures())
                result.add(c);
        if(enemy!=null)
            for(Creature c:enemy.getCreatures())
                result.add(c);
        return result;
    }

    //列队
    public void allset(){
        Formation Sformation=new SnakeFormation();
        Formation Cformation=new CraneFormation();
        huluwa.setFormation(Sformation,0,0);
        enemy.setFormation(Cformation,0,field.getCol()-40);
    }

    public Queue getHuluwa() {
        return huluwa;
    }

    public Queue getEnemy() {
        return enemy;
    }

    //当敌我两个对象相距小于战斗距离时调用此函数判断战斗结果
    public void Fight(Creature good,Creature bad){
        Random random=new Random();
        double ranDouble=random.nextDouble();

//        System.out.println(ranDouble);

        Class goodClass=good.getClass();
        Class badClass=bad.getClass();
        if(goodClass.equals(Yeye.class)&&badClass.equals(Wugong.class))
        {
            if(ranDouble<0.65)
                good.setIs_dead(true);
            else
                bad.setIs_dead(true);
        }
        else if(goodClass.equals(Yeye.class)&&badClass.equals(Snake.class))
        {
            if(ranDouble<0.85)
                good.setIs_dead(true);
            else
                bad.setIs_dead(true);
        }
        else if(goodClass.equals(Yeye.class)&&badClass.equals(Xiezi.class))
        {
            if(ranDouble<0.8)
                good.setIs_dead(true);
            else
                bad.setIs_dead(true);
        }
        else if(goodClass.equals(Huluwa.class)&&badClass.equals(Wugong.class))
        {
            if(ranDouble<0.65)
                bad.setIs_dead(true);
            else
                good.setIs_dead(true);
        }
        else if(goodClass.equals(Huluwa.class)&&badClass.equals(Snake.class))
        {
            if(ranDouble<0.5)
                bad.setIs_dead(true);
            else
                good.setIs_dead(true);
        }
        else
        {
            if(ranDouble<0.55)
                bad.setIs_dead(true);
            else
                good.setIs_dead(true);
        }


    }

    //用于判断全盘的战斗情况
    public void FightCheck(){
        for(Creature creatureH:huluwa.getCreatures()) {
            if(!creatureH.getIs_dead()) {
                for (Creature creatureE : enemy.getCreatures()) {
                    if (!creatureE.getIs_dead()) {
                        int distance = Math.abs(creatureH.getX() - creatureE.getX()) + Math.abs(creatureH.getY() - creatureE.getY());
                        if (distance < fightDistance) {
                            Fight(creatureH,creatureE);
                            break;
                        }
                    }
                }
            }
        }


        int flag=0;
        for(Creature c:huluwa.getCreatures())
            if(c.getIs_dead()==false)
                flag=1;
        if(flag==0)
            field.setCompleted(1);

        flag=0;
        for(Creature c:enemy.getCreatures())
            if(c.getIs_dead()==false)
                flag=1;
        if(flag==0)
            field.setCompleted(2);

    }
}

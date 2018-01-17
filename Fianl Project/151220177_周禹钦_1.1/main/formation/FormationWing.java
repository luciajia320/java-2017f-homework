package main.formation;

/**
 * Created by qin on 18.1.2.
 */
import main.creature.*;
import main.myenum.SENIORITY;
import main.myenum.COLOR;
public class FormationWing implements Formation{
    private Creature[][] matrix=new Creature[8][8];
    private int[] usableposition={20,27,31,36,42,45,53,54};
    private int kind=7;
    @Override
    public int Kind(){return kind;}
    @Override
    public Creature getCreature(int x, int y){
        return matrix[x][y];
    }

    public FormationWing(boolean monster){
        if(monster){
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    matrix[i][j]=new GrassGround();
            matrix[2][0]=new MonsterServent();
            matrix[2][7]=new MonsterServent();
            matrix[3][1]=new MonsterScorpion();
            matrix[3][6]=new MonsterServent();
            matrix[4][2]=new MonsterServent();
            matrix[4][5]=new MonsterServent();
            matrix[5][3]=new MonsterServent();
            matrix[5][4]=new MonsterSnake();
        }
        else {
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    matrix[i][j]=new GrassGround();
            matrix[2][0]=new GrandFa();
            matrix[2][7]=new HuLuWa(COLOR.赤, SENIORITY.一);
            matrix[3][1]=new HuLuWa(COLOR.橙, SENIORITY.二);
            matrix[3][6]=new HuLuWa(COLOR.黄, SENIORITY.三);
            matrix[4][2]=new HuLuWa(COLOR.绿, SENIORITY.四);
            matrix[4][5]=new HuLuWa(COLOR.青, SENIORITY.五);
            matrix[5][3]=new HuLuWa(COLOR.蓝, SENIORITY.六);
            matrix[5][4]=new HuLuWa(COLOR.紫, SENIORITY.七);
        }
    }

}

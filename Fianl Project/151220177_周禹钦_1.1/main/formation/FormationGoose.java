package main.formation;

/**
 * Created by qin on 18.1.3.
 */
import main.creature.*;
import main.myenum.SENIORITY;
import main.myenum.COLOR;
public class FormationGoose implements Formation{
    private Creature[][] matrix=new Creature[8][8];
    private int[] usableposition={07,16,25,34,43,52,61,70};
    private int kind=3;
    @Override
    public int Kind(){return kind;}
    @Override
    public Creature getCreature(int x, int y){
        return matrix[x][y];
    }

    public  FormationGoose(boolean monster){
        if(monster){
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    if(i!=j)
                        matrix[i][j]=new GrassGround();
            matrix[0][0]=new MonsterScorpion();
            matrix[1][1]=new MonsterServent();
            matrix[2][2]=new MonsterServent();
            matrix[3][3]=new MonsterServent();
            matrix[4][4]=new MonsterServent();
            matrix[5][5]=new MonsterServent();
            matrix[6][6]=new MonsterServent();
            matrix[7][7]=new MonsterSnake();
        }
        else{
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    if(i+j!=7)
                        matrix[i][j]=new GrassGround();
            matrix[0][7]=new GrandFa();
            matrix[1][6]=new HuLuWa(COLOR.赤, SENIORITY.一);
            matrix[2][5]=new HuLuWa(COLOR.橙, SENIORITY.二);
            matrix[3][4]=new HuLuWa(COLOR.黄, SENIORITY.三);
            matrix[4][3]=new HuLuWa(COLOR.绿, SENIORITY.四);
            matrix[5][2]=new HuLuWa(COLOR.青, SENIORITY.五);
            matrix[6][1]=new HuLuWa(COLOR.蓝, SENIORITY.六);
            matrix[7][0]=new HuLuWa(COLOR.紫, SENIORITY.七);
        }
    }
}

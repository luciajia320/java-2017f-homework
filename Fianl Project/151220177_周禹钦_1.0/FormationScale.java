/**
 * Created by qin on 18.1.3.
 */
public class FormationScale implements Formation{
    private Creature[][] matrix=new Creature[8][8];
    private int[] usableposition={24,35,44,45,46,53,54,56};
    private int kind=5;
    @Override
    public int Kind(){return kind;}
    @Override
    public Creature getCreature(int x,int y){
        return matrix[x][y];
    }

    public FormationScale(boolean monster){
        if(monster){
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    matrix[i][j]=new GrassGround();
            matrix[2][3]=new MonsterServent();
            matrix[3][2]=new MonsterServent();
            matrix[4][3]=new MonsterServent();
            matrix[4][2]=new MonsterServent();
            matrix[4][1]=new MonsterSnake();
            matrix[5][4]=new MonsterServent();
            matrix[5][3]=new MonsterServent();
            matrix[5][1]=new MonsterScorpion();
        }
        else{
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                    matrix[i][j]=new GrassGround();
            matrix[2][4]=new HuLuWa(COLOR.绿,SENIORITY.四);
            matrix[3][5]=new HuLuWa(COLOR.赤,SENIORITY.一);
            matrix[4][4]=new HuLuWa(COLOR.橙,SENIORITY.二);
            matrix[4][5]=new HuLuWa(COLOR.黄,SENIORITY.三);
            matrix[4][6]=new GrandFa();
            matrix[5][3]=new HuLuWa(COLOR.青,SENIORITY.五);
            matrix[5][4]=new HuLuWa(COLOR.蓝,SENIORITY.六);
            matrix[5][6]=new HuLuWa(COLOR.紫,SENIORITY.七);
        }
    }
}

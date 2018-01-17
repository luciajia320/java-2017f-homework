import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix {

    private static final int N=13;
    private Position[][] positions;

    private List<Creature> creatures;

    public Matrix() {
        this.positions=new Position[N][N];

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                this.positions[i][j]=new Position(i,j);
            }
        }

        creatures=new ArrayList<Creature>();
    }

    public void show(){
        for (int i=0;i<N;i++)
        {
            System.out.print("==");
        }
        System.out.println();
        System.out.flush();

        for (int i=0;i<this.positions.length;i++){
            for (int j=0;j<this.positions[i].length;j++)
            {
                this.positions[i][j].report();
            }
            System.out.println();
            System.out.flush();
        }

        for (int i=0;i<N;i++)
        {
            System.out.print("==");
        }
        System.out.println();
        System.out.flush();
    }

    public void addcreature(Creature character){//随机在空位置添加生物
        Random rnd = ThreadLocalRandom.current();

        int indexx = rnd.nextInt(N-1);
        int indexy=rnd.nextInt(N-1);
        while (!positions[indexx][indexy].isempty){
            indexx = rnd.nextInt(N-1);
            indexy=rnd.nextInt(N-1);
        }
        this.addcreature(character,indexx,indexy);
    }

    public void addcreature(Creature character,int x,int y){
        creatures.add(character);
        character.setPosition(positions[x][y]);
    }

    public Position getPosition(int x,int y){
        return positions[x][y];
    }

    public static void main(String[] args) {

        System.out.println("战场展开！");

        Matrix matrix=new Matrix();

        matrix.show();

        System.out.println("出来吧，葫芦娃！");

        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
            matrix.addcreature(brothers[i]);
        }

        matrix.show();

        System.out.println("葫芦娃列阵：长蛇阵！");

        new SingleLineArray().rank(brothers,matrix);

        matrix.show();

        System.out.println("蝎子精登场，喽啰列阵：衡轭阵！");

        matrix.addcreature(new Scorpion(),6,5);
        Minion[] minions=new Minion[7];
        for (int i = 0; i < minions.length; i++) {
            minions[i] = new Minion();
            matrix.addcreature(minions[i]);
        }

        new HengeArray().rank(minions,matrix);

        matrix.show();

        System.out.println("爷爷和蛇精登场加油！");

        matrix.addcreature(new Grandpa(),0,0);
        matrix.addcreature(new Snake(),N-1,N-1);

        matrix.show();

        System.out.println("蝎子精指挥喽啰变阵：鹤翼阵！");

        new CraneArray().rank(minions,matrix);

        matrix.show();

    }


}

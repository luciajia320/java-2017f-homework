import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Field {
    final int N=15;
    static final int HLW_SUM=7;

  //  private Position[][] positions;
    private ArrayList<ArrayList<Position>>positions;
    private ArrayList<ArrayList<Creature>> creatures;

    public ArrayList<Position> getPositions(int i) {
        return positions.get(i);
    }

    public ArrayList<ArrayList<Position>> getPositions(){
        return positions;
    }
    public ArrayList<ArrayList<Creature>> getCreatures() {
        return creatures;
    }

    public Field (){
        this.positions = new ArrayList<ArrayList<Position>>();
        this.creatures = new ArrayList<ArrayList<Creature>>();
        for(int i=0;i<N;++i) {
            this.positions.add(new ArrayList<Position>());
            this.creatures.add(new ArrayList<Creature>());
        }
        for (int i = 0; i < N ;++i)
            for (int j = 0 ; j< N ;++j) {
                this.creatures.get(i).add(new Blank());
                this.positions.get(i).add(new Position(i,j));/*[i][j]*/
                this.creatures.get(i).get(j).setPosition(this.positions.get(i).get(j));
            }
    }

    public void putIn(Queue queue){
        ArrayList<Position>tmp1 = queue.getPositions();
        for (int i=0 ; i<tmp1.size() ; ++i){
            int x= tmp1.get(i).getX();
            int y= tmp1.get(i).getY();
            this.positions.get(x).get(y).setHolder(tmp1.get(i).getHolder());
            this.creatures.get(x).set(y,tmp1.get(i).getHolder());
            this.creatures.get(x).get(y).setPosition(tmp1.get(i));
        }
    }
    public void clean(){
        for (int i=0;i<N;++i)
            for (int j=0 ;j<N ;++j){
                //creatures[i][j]=new Blank();
                creatures.get(i).add(new Blank());
                positions.get(i).get(j).setHolder(new Blank());
            }
    }
    public void rollCall() {
        for (int i=0;i<N;++i) {
            for (int j = 0; j < N; ++j)
                this.positions.get(i).get(j).getHolder().report();
            System.out.println();
        }
        System.out.flush();
    }

    private void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i =  HLW_SUM-1; i > 0; i--) {
            int index = rnd.nextInt(i + 1)+4;   //4是 7个葫芦娃处于15个位置正中间左右相等 4-10

            Position position = creatures.get(0).get(index).getPosition();
            Creature creature = creatures.get(0).get(index);

            positions.get(0).get(index).setHolder(creatures.get(0).get(i+4));
            positions.get(0).get(i+4).setHolder(creature);
            positions.get(0).get(index).setX(i+4);
            positions.get(0).get(i+4).setY(index);

            creatures.get(0).set(index, creatures.get(0).get(i+4));
            creatures.get(0).set(i+4, creature);
        }
    }

    public static void main(String[] args) {
        //生成葫芦娃
        ArrayList<Huluwa>brothers= new ArrayList<>();
        for (int i = 0; i < HLW_SUM; i++) {
            brothers.add( new Huluwa(COLOR.values()[i], SENIORITY.values()[i] ,new Position(0,i)));
        }
        Queue huluwaqueue = new Queue (HLW_SUM);   //生成葫芦娃队
        for (int i=0;i<brothers.size();++i)
            huluwaqueue.JoinIn(brothers.get(i));
        new Snake().format(huluwaqueue);        //葫芦娃排成长蛇形
        Field field = new Field();
        field.putIn(huluwaqueue);   //葫芦娃放进field
        field.shuffle();        //葫芦娃乱序
        /*
        field.rollCall();
        System.out.println();
        */
        new BubbleSorter().sort(field,0);   //葫芦娃排序

        //生成喽罗
        ArrayList<LouLuo>louluos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            louluos.add ( new LouLuo());
        }
        Monster xiezijing = new Monster("🐛");
        Queue monsterqueue = new Queue(HLW_SUM);         //生成妖怪队
        monsterqueue.JoinIn(xiezijing);
        for (int i=0;i<louluos.size();++i)
            monsterqueue.JoinIn(louluos.get(i));
        new CraneWing().format(monsterqueue);    //妖怪鹤翼形
        field.putIn(monsterqueue);

        Creature grandfather = new Grandfather("🎅");  //0,0
        Monster  shejing = new Monster("🐍");      //14,14
        Queue audiencequeue = new Queue(2);  //观战助威队
        audiencequeue.JoinIn(grandfather);
        audiencequeue.JoinIn(shejing);
        new Audience().format(audiencequeue);
        field.putIn(audiencequeue);

        field.rollCall();

        System.out.println();
        field.clean();    //清空  blank填满field   重新站队形 翼形
        field.putIn(huluwaqueue);
        new Goose().format(monsterqueue);
        field.putIn(monsterqueue);
        field.putIn(audiencequeue);
        field.rollCall();


    }
}


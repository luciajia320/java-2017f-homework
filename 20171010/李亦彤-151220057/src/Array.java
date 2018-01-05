import java.util.ArrayList;

public class Array {

    final static int N=16;
    //private Position[][] positions;
    private ArrayList<ArrayList<Position<Creature>>> positions;
    //int num;
    public Array()
    {
        positions=new ArrayList<ArrayList<Position<Creature>>>();
        for(int i=0;i<N;i++)
        {
            positions.add(new ArrayList<Position<Creature>>());
            for(int j=0;j<N;j++)
            {
                positions.get(i).add(new Position<Creature>());
            }
        }
    }

    public void SetQueue(Queue q)//将阵型放在array上
    {
        for (int i = 0; i < q.getPositions().size(); i++) {

            int x= q.getPositions().get(i).getX();
            int y= q.getPositions().get(i).getY();
            positions.get(x).set(y,new Position<Creature>(x,y));
            q.getPositions().get(i).getHolder().setPosition(positions.get(x).get(y));
        }
    }

    public void switch_array(BudsAndScor_queue q)//变换阵形
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(positions.get(i).get(j).getHolder() instanceof Scorpion | positions.get(i).get(j).getHolder() instanceof Buddy| positions.get(i).get(j).getHolder() instanceof Grandpa| positions.get(i).get(j).getHolder() instanceof Snake)
                    positions.get(i).set(j,new Position<Creature>());
            }
        }
        SetQueue(q);
    }
    public void show(){
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if(positions.get(i).get(j).getX()!=-1)
                    positions.get(i).get(j).getHolder().show();
                else
                    System.out.print("🌱");
            }
            System.out.println("\n");
        }

        System.out.println("\n");
        //System.out.flush();
    }
    public void rollCall() {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if(positions.get(i).get(j).getX()!=-1)
                    positions.get(i).get(j).getHolder().report();
            }
        }

        System.out.println("\n");
        //System.out.flush();
    }
    private void set_creature(Creature c)//将爷爷，蛇精放在array上
    {
        int x=0,y=0;
        if(c instanceof Grandpa) {
            for(int i=3;i<N;i++)
            {
                for(int j=N-1;j>N/2;j--)
                {
                    if(positions.get(i).get(j).getX()==-1)
                    {
                        x=i;
                        y=j;
                        this.positions.get(x).set(y, new Position<Creature>(x, y));
                        c.setPosition(this.positions.get(x).get(y));
                        return;
                    }
                }
            }
        }
        else if(c instanceof Snake){
            for(int i=5;i<N;i++)
            {
                for(int j=0;j<N/2;j++)
                {
                    if(positions.get(i).get(j).getX()==-1)
                    {
                        x=i;
                        y=j;
                        this.positions.get(x).set(y, new Position<Creature>(x, y));
                        c.setPosition(this.positions.get(x).get(y));
                        return;
                    }
                }
            }
        }

    }
    public static void main(String[] args) {

        Scorpion scorpion = new Scorpion();
        Snake snake = new Snake();
        Grandpa grandpa = new Grandpa();
        ArrayList<Huluwa> brothers=new ArrayList<Huluwa>();
        ArrayList<Buddy> buddies=new ArrayList<Buddy>();

        for (int i = 0; i < 7; i++) {
            brothers.add(new Huluwa(COLOR.values()[i], SENIORITY.values()[i]));
        }
        for (int i = 0; i < 7; i++) {
            buddies.add(new Buddy(i));
        }

        Huluwa_queue h_queue = new Huluwa_queue(brothers);
        h_queue.shuffle();
        h_queue.rollCall();
        System.out.println("葫芦娃站队");
        new BubbleSorter().sort(h_queue);
        h_queue.rollCall();
        System.out.println("\n");
        System.out.println("蝎子精带小喽啰站队");
        BudsAndScor_queue b_queue = new BudsAndScor_queue(buddies, scorpion);
        b_queue.rollCall();
        System.out.println("\n");
        Array array = new Array();
        array.SetQueue(h_queue);
        array.SetQueue(b_queue);
        array.set_creature(grandpa);
        System.out.println("爷爷助威");
        grandpa.report();
        System.out.println("\n");
        array.set_creature(snake);
        System.out.println("蛇精助威");
        snake.report();
        System.out.println("\n");
        //array.rollCall();
        System.out.println("对峙局面-------------------------------------------------------------");
        array.show();
        System.out.println("\n");


        System.out.println("蝎子精带小喽啰变换阵法");
        b_queue.Heyi();
        b_queue.rollCall();
        System.out.println("\n");
        array.switch_array(b_queue);
        array.set_creature(grandpa);
        System.out.println("爷爷助威");
        grandpa.report();
        System.out.println("\n");
        array.set_creature(snake);
        System.out.println("蛇精助威");
        snake.report();
        System.out.println("\n");
        System.out.println("对峙局面-------------------------------------------------------------");
        array.show();
    }
}

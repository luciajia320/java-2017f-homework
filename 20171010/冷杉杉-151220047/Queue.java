import java.util.Random;

public class Queue {

	final int N=11;
    private Position[][] positions;

    public Position[][] getPositions() {
        return positions;
    }


    public Creature[] getCreatures() {
        return creatures;
    }
    


    private Creature[] creatures;

    public Queue(Creature[] creatures) {


        this.positions =new Position[N][N];

        this.creatures = creatures;

        /*int numoflouluo=0;
        for (int i = 0; i < creatures.length; i++) {
        	if(creatures[i] instanceof  Xiaolouluo )
        		numoflouluo++;
        }*/
        for (int i = 0; i < creatures.length; i++) {
        	
        	if(creatures[i] instanceof  Huluwa && i<N)
        	{
        		this.positions[i][0] =new Position(i,0);
        		this.creatures[i].setPosition(this.positions[i][0]);
        	}
        	
        	
        	
            //this.positions[i] = new Position(i,i);
            //this.creatures[i].setPosition(this.positions[i]);
        }
    }
    public void print()
    {
    	for(int i=0;i<N;i++)
    	{
    		for(int j=0;j<N;j++)
    		{
    			if(this.positions[i][j]==null )
     			{
     				System.out.print("  ");
     			}
     			else if(this.positions[i][j].getHolder()==null)
     			{
     				System.out.print("  ");
     			}
     			else
     				positions[i][j].show();
    		}
    		System.out.println();
    	}
    }
    public void standinqueue(Creature[] creature,ZHENXING zhenxing)
    {
    	int usednumoflouluo=0;
    	 for (int i = 0; i < creature.length; i++) {
    		 
    		 if(creature[i] instanceof  Xiezijing )
         	{
         		/*Random rand = new Random(); 
         		int j = rand.nextInt()%(N-1)+1;
         		int k = rand.nextInt()%(N-1)+1;*/
         		this.positions[0][N-1] =new Position(0,N-1);
         		creature[i].setPosition(this.positions[0][N-1]);
         	}
         	else if(creature[i] instanceof  Xiaolouluo )
         	{
         		if(zhenxing == ZHENXING.雁行 )
         		{
         			usednumoflouluo++;
         			this.positions[usednumoflouluo][N-1-usednumoflouluo] =new Position(usednumoflouluo,N-1-usednumoflouluo);
         			creature[i].setPosition(this.positions[usednumoflouluo][N-1-usednumoflouluo]);
             		
         		}
         		else if(zhenxing == ZHENXING.冲扼 )
         		{
         			usednumoflouluo++;
         			int a=usednumoflouluo%2;
         			this.positions[usednumoflouluo][N-1-a] =new Position(usednumoflouluo,N-1-a);
         			creature[i].setPosition(this.positions[usednumoflouluo][N-1-a]);
         		}
         		else if(zhenxing == ZHENXING.长蛇 )
         		{
         			usednumoflouluo++;
         	
         			this.positions[usednumoflouluo][N-1] =new Position(usednumoflouluo,N-1);
         			creature[i].setPosition(this.positions[usednumoflouluo][N-1]);
         		}
         		else
         		{
         			/*
         			 TODO: 其他阵型
         			 */
         		}
         	}
         	else if(creature[i] instanceof  Yeye || creature[i] instanceof Shejing)
         	{
         		int flag=0;
         		while(flag==0)
         		{
         			Random rand = new Random(); 
         			int j = rand.nextInt(N-2)+1;
         			int k = rand.nextInt(N-2)+1; 
         			if(this.positions[j][k]==null )
         			{
         				flag=1;
         				this.positions[j][k] =new Position(j,k);
         				creature[i].setPosition(this.positions[j][k]);
         			}
         			else if(this.positions[j][k].getHolder()==null)
         			{
         				flag=1;
         				this.positions[j][k] =new Position(j,k);
         				creature[i].setPosition(this.positions[j][k]);
         			}
         		}
         	}
    	 }
    }
    /*public void rollCall() {
        for (Creature creature : this.creatures) {
            creature.report();
        }
        System.out.println();
        System.out.flush();

        for (Position position : this.positions) {

            position.getHolder().report();
        }

        System.out.println("\n");
        System.out.flush();
    }

    private void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Position position = creatures[index].getPosition();
            creatures[index].setPosition(creatures[i].getPosition());
            creatures[i].setPosition(position);
        }
    }*/

    public static void main(String[] args) {

        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < brothers.length; i++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        new BubbleSorter().sort(brothers);
        Queue queue = new Queue(brothers);
        Creature[] creatures=new Creature[7];
        creatures[0]=new Xiezijing(SENIORITY.蝎子精);
        creatures[5]=new Shejing(SENIORITY.蛇精);
        creatures[6]=new Yeye(SENIORITY.爷爷);
        for(int i=1;i<=4;i++)
        {
        	 creatures[i]=new Xiaolouluo(SENIORITY.小喽啰);
        }
        queue.standinqueue(creatures, ZHENXING.雁行);
        queue.print();
        /*for(int i=0;i<brothers.length;i++)
        {
        	brothers[i].report();
        	System.out.println();
        }
        for(int i=0;i<creatures.length;i++)
        {
        	creatures[i].report();
        	System.out.println();
        }*/
        System.out.println("第一遍完了");
        System.out.println();
        System.out.println();
        queue = new Queue(brothers);
        queue.standinqueue(creatures, ZHENXING.冲扼);
        queue.print();
        /*for(int i=0;i<brothers.length;i++)
        {
        	brothers[i].report();
        	System.out.println();
        }
        for(int i=0;i<creatures.length;i++)
        {
        	creatures[i].report();
        	System.out.println();
        }*/
        /*queue.rollCall();

        queue.shuffle();


        queue.rollCall();

        new InsertionSorter().sort(queue);

        queue.rollCall();

        queue.shuffle();


        queue.rollCall();

        new BubbleSorter().sort(queue);
        queue.rollCall();*/

    }
}
enum ZHENXING {
    鹤翼,雁行,冲扼,长蛇,鱼鳞,方圆,偃月,锋矢
}

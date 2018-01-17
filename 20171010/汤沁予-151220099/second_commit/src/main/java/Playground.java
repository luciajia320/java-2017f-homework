import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;

public class Playground
{
    public final static String PLACE_HOLDER = "☘";
    private int length;
    private Huluwa[] huluwas;
    private Minion[] minions;
    private Grandpa grandpa;
    private SnakeWoman snakewoman;
    private ArrayList<Format> formations;
    public Playground(int len)
    {
        assert len>=7;
        this.length = len;
        formations = new ArrayList<Format>();
        InitCreatures();
    }
    private void InitCreatures()
    {
        huluwas = new Huluwa[7];
        minions = new Minion[7];
        for(int i = 0;i<7;i++) {
            minions[i] =  new Minion();
        }
        huluwas[0] = new Huluwa(Color.values()[2],2);
        huluwas[1] = new Huluwa(Color.values()[3],3);
        huluwas[2] = new Huluwa(Color.values()[1],1);
        huluwas[3] = new Huluwa(Color.values()[5],5);
        huluwas[4] = new Huluwa(Color.values()[6],6);
        huluwas[5] = new Huluwa(Color.values()[0],0);
        huluwas[6] = new Huluwa(Color.values()[4],4);
        grandpa = new Grandpa();
        snakewoman = new SnakeWoman();
    }

    public void HuluwaSnakeFormat(int x0,int y0)
    {
        SnakeFormat snake = new SnakeFormat(huluwas,x0,y0);
        formations.add(snake);
        snake.PutCreatures();
    }
    public void HuluwaSort()
    {
        Sorter sorter = new QuickSort();
        sorter.Sort(huluwas);
        if(formations.get(0) instanceof SnakeFormat)
            ((SnakeFormat)formations.get(0)).PutCreatures();
    }
    public void MinionsCranewingFormat(int x0,int y0)
    {
        CranewingFormat crane = new CranewingFormat(minions,x0,y0);
        formations.add(crane);
       // crane.PutCreatures();
    }
    public void MinionsArrowFormat(int x0,int y0)
    {
        ArrowFormat arrow = new ArrowFormat(minions,x0,y0);
        formations.add(arrow);
        // crane.PutCreatures();
    }
    public void PutGrandpa(int x,int y)
    {
        SingleCreatureFormat single = new SingleCreatureFormat(grandpa,x,y);
        formations.add(single);
    }
    public void PutSnakeWoman(int x,int y)
    {
        SingleCreatureFormat single = new SingleCreatureFormat(snakewoman,x,y);
        formations.add(single);
    }
    public void Remove()
    {
        /*
        for(Iterator<Format> it=formations.iterator();it.hasNext();)
        {
            Format i = it.next();
            if(i instanceof CranewingFormat || i instanceof SingleCreatureFormat)
                it.remove();
        }
        */
        formations.clear();
    }
    public void PrintPlayground()
    {
        System.out.print(this.ToString());
    }
    private String ToString()
    {
        String content[][] = new String[length][length];
        for(int i = 0;i<length;i++)
        {
            for (int j = 0; j < length; j++)
            {
                content[i][j] = PLACE_HOLDER;
            }
        }
        for(Object o :formations)
        {
            Format fp = (Format) o;
            for(int i = 0;i<fp.getHeight();i++)
            {
                for(int j = 0;j<fp.getWidth();j++)
                {
                    if(fp.getGround()[i][j].getHolder()!=null)
                        content[i+fp.getPos().getX()][j+fp.getPos().getY()] = fp.getGround()[i][j].getHolder().report();
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<length;i++)
        {
            for (int j = 0; j < length; j++)
            {
                result.append(content[i][j]+"\t\t");
            }
            result.append("\n\n");
        }
        result.append("\n");
        return result.toString();
    }

}

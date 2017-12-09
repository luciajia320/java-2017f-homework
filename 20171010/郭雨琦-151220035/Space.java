//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.ArrayList;


public class Space {//This is a 2D space
    static final int N = 20;

    private Position[][] positions;

    ArrayList<Creature> positiveCreatures;
    ArrayList<Creature> negativeCreatures;
    ArrayList<Creature> onlookerCreatures;

    public Space()
    {
        positions = new Position[N][];
        for(int i = 0; i < N; i++)
        {
            positions[i]=new Position[N];
            for(int j = 0; j < N; j++)
            {
                positions[i][j] = new Position(i,j);
            }
        }

    }

    public void addPositiveCreatures(ArrayList<Creature> brothers) {
        //positiveCreatures = new Creature[brothers.length];
        //for(int i = 0; i <brothers.length; ++i ){
        //    positiveCreatures[i] = brothers[i];
        //}

        positiveCreatures = new ArrayList<Creature>(brothers);
    }

    public void addNegativeCreatures(Scorpion scorpion, ArrayList<Creature> lackeys) {
        /*negativeCreatures = new Creature[lackeys.length+1];
        negativeCreatures[0] = scorpion;
        for(int i = 0; i <lackeys.length; ++i ){
            negativeCreatures[i+1] = lackeys[i];
        }
        */
        negativeCreatures = new ArrayList<Creature>();
        negativeCreatures.add(scorpion);
        negativeCreatures.addAll(lackeys);
    }

    public void addOnlookerCreatures(Grandpa grandpa, Snake snake) {
        /*onlookerCreatures = new Creature[2];
        onlookerCreatures[0]=grandpa;
        onlookerCreatures[1]=snake;*/
        onlookerCreatures = new ArrayList<Creature>();
        onlookerCreatures.add(grandpa);
        onlookerCreatures.add(snake);
    }

    public void setPositive(){
        Formation.buildCHANGSHE(positiveCreatures, positions,10, 10,true);
    }

    public void setNegative(){

        Formation.bulidHEYI(negativeCreatures, positions,9, 10,false);
    }

    public void setOnlooker(){
        onlookerCreatures.get(0).setPosition(positions[10][0]);
        onlookerCreatures.get(1).setPosition(positions[9][0]);
    }

    public void shout(){
        for(int i = 0; i < onlookerCreatures.size(); ++i){
            onlookerCreatures.get(i).cheer();
        }
    }

    public void changeNegative(FORMATIONS f)
    {
        switch(f)
        {
            case HEYI:Formation.bulidHEYI(negativeCreatures, positions,9, 10,false);break;
            case CHANGSHE:Formation.buildCHANGSHE(negativeCreatures, positions,9, 10,false);break;
            case YANXING:Formation.buildYANXING(negativeCreatures, positions,9, 10,false);break;
            default:
                break;
        }
    }

    public void print()
    {
        for(int i=0; i < N; i++)
        {
            for(int j=0; j<N; j++)
            {
                Creature temp = positions[i][j].getHolder();
                if(temp!=null)
                {
                    System.out.print(temp.getIcon());
                }
                else
                {
                    System.out.print("\uD83C\uDF40");
                }
            }
            System.out.println();
        }
    }



}

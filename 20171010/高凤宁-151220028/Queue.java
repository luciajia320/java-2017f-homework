import java.util.ArrayList;
import java.util.Collections;

public class Queue {
    private Position[] positions;
    private int size;

    public int GetSize(){return size;}
    public Position []GetPositions(){return positions;}

    public Queue(){
        positions = null;
        size = 0;
    }

    public void EnQueue(Creature []creatures){
        positions = new Position[creatures.length];
        for(int i = 0;i < creatures.length;i++)
            positions[i] = new Position();//很关键
        size = creatures.length;

        if(creatures[0] instanceof Calabash){

            //排队时先进行打乱
            ArrayList list = new ArrayList();
            for(int i = 0;i < creatures.length;i++)
                list.add(i);
            Collections.shuffle(list);


            for(int i = 0;i < creatures.length;i++){
                positions[i].SetHolder(creatures[(int)list.get(i)]);
            }
        }
        else {
            for (int i = 0; i < creatures.length; i++) {
                positions[i].SetHolder(creatures[i]);
            }
        }
    }

    /*public void Display(){
        for(int i = 0;i < positions.length;i++)
            if(positions[i].GetHolder() != null)
                System.out.println(positions[i].GetHolder().GetName());
    }*/
}

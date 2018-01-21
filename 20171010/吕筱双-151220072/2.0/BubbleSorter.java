import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;

import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Matrix matrix) {
        ArrayList<ArrayList<Position<Creature>>> positions = matrix.getPositions();

        //将葫芦娃放在最右列
        int brotherQueueNum = 0;

        Creature creature = null;

       for(int i = 0; i < positions.size() ;i++) {
            for (int j = 1; j < positions.get(i).size(); j++) {
                if (positions.get(i).get(j).getHolder() instanceof Calabash ) {
                       // System.out.println("brotherQueueNum" + brotherQueueNum + ((Calabash) positions[i][j].getHolder()).getName());
                        if (positions.get(brotherQueueNum).get(0).getHolder() == null) {
                           // positions[i][j].getHolder().setPosition(positions[brotherQueueNum][0]);
                            positions.get(brotherQueueNum).get(0).setHolder(positions.get(i).get(j).getHolder());
                            positions.get(i).get(j).cleanHolder();
                        }
                        else{
                            while(positions.get(brotherQueueNum).get(0).getHolder() instanceof Calabash)
                                brotherQueueNum++;
                            Creature temp = positions.get(brotherQueueNum).get(0).getHolder();
                            positions.get(brotherQueueNum).get(0).setHolder(positions.get(i).get(j).getHolder());
                            positions.get(i).get(j).setHolder(temp);

                        }
                    brotherQueueNum++;
                }

            }
        }

        for(int i = 0,count = 0; i < positions.size()&&count < 7;i++){
            if(positions.get(i).get(0).getHolder() instanceof Calabash)
                count++;
            else {
                for(int t= i+1; t < positions.size();t++){
                    if(positions.get(t).get(0).getHolder() instanceof Calabash){
                        Creature temp = positions.get(i).get(0).getHolder();
                        positions.get(i).get(0).setHolder(positions.get(t).get(0).getHolder());
                        positions.get(t).get(0).setHolder(temp);
                    }
                }
                count++;
            }
        }

        for(int i = 0; i < 6;i++){
            for(int j = 0 ; j < 6-i;j++){
                if(((Comparable)(positions.get(j).get(0).getHolder())).biggerThan((Comparable) positions.get(j+1).get(0).getHolder()))
                {
                    Creature temp = positions.get(j).get(0).getHolder();
                    positions.get(j).get(0).setHolder(positions.get(j+1).get(0).getHolder());
                    positions.get(j+1).get(0).setHolder(temp);

                }
            }
        }

        for(int i= 0; i < 7;i++){
            matrix.getCreatures().get(i).setPosition(positions.get(i).get(0));
            //System.out.println(matrix.getCreatures()[i].getName()+matrix.getCreatures()[i].getPosition().getX()+"  "+matrix.getCreatures()[i].getPosition().getY());
        }

    }

}

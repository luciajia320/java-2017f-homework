import java.util.ArrayList;
public class SelectSorter implements Sorter {
    public void sort(ArrayList<Position>positions){//Position positions[]) {
        int max=0;
        for(int i=0;i<positions.size()-1;i++)
        {
            max=0;
            for(int j=0;j<positions.size()-i;j++)
            {
                if(((Comparable)(positions.get(j).getSitter())).isBiggerThan((Comparable)positions.get(max).getSitter()))
                    max=j;
            }
            //交换
            if(max!=positions.size()-1-i) {
                Creature atemp = positions.get(positions.size() - 1 - i).getSitter();
                positions.get(max).getSitter().setPos(positions.get(positions.size() - 1 - i));
                atemp.setPos(positions.get(max));
//                System.out.println(atemp.getName()+'-'+positions.get(positions.size() - 1 - i).getSitter().getName());
            }

       }
    }

}

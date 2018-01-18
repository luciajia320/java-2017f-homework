public class SelectSorter implements Sorter {
    public void sort(Position positions[]) {
        int max=0;
        for(int i=0;i<positions.length-1;i++)
        {
            max=0;
            for(int j=0;j<positions.length-i;j++)
            {
                if(((Comparable)(positions[j].getSitter())).isBiggerThan((Comparable)positions[max].getSitter()))
                    max=j;
            }
            //交换
            Creature atemp=positions[positions.length-1-i].getSitter();
            positions[max].getSitter().setPos(positions[positions.length-1-i]);
            atemp.setPos(positions[max]);
            //告诉葫芦娃是怎么交换的
            ((Huluwa) atemp).changeHereThere(positions[max],positions[positions.length-1-i]);
            ((Huluwa)(positions[max].getSitter())).changeHereThere(positions[positions.length-1-i],positions[max]);
        }
    }

}

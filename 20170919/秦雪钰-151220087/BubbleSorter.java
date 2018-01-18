public class BubbleSorter implements Sorter{
    public void sort(Position positions[])
    {
        Creature atemp; //一个交换的变量
        for(int i=0;i<positions.length-1;i++)
        {
            for(int j=0;j<positions.length-1-i;j++) //持续交换把大的往后移动
            {
                //j比j+1大
                if(((Comparable)(positions[j].getSitter())).isBiggerThan((Comparable)positions[j+1].getSitter()))
                {
                    //交换
                    atemp = positions[j].getSitter();
                    positions[j + 1].getSitter().setPos(positions[j]);
                    atemp.setPos(positions[j + 1]);
                    //告诉葫芦娃是怎么交换的
                    Huluwa reporthuluwa=(Huluwa)(atemp);
                    reporthuluwa.changeHereThere(positions[j],positions[j+1]);
                    reporthuluwa=(Huluwa)positions[j + 1].getSitter();
                    reporthuluwa.changeHereThere(positions[j+1],positions[j]);
                }
            }
        }
    }
}

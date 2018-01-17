package lsc.java.hulu_3;

public abstract class Creature {
    //0 for huluwa
    //1 for snake
    //2 for scorpion
    //3 for lackey
    //4 for grandpa
    protected int spieces;

    //输出
    public abstract void appear();
    //返回生物种类
    public int return_spieces()
    {
        return spieces;
    }
    //返回在本种族中的排行
    public abstract int return_index();
}

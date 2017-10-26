public class Creature {
    protected int row;
    protected int col;
    protected CHARACTER character;
    public Creature(int row, int col){
        if(Battlefield.battlefiled[row][col]!=CHARACTER.NULL)
            Battlefield.battlefiled[row][col]=character;
    }
    public void setPosition(int row, int col){
        if(Battlefield.battlefiled[row][col]!=CHARACTER.NULL)
            ;
    }
}

enum CHARACTER {
    Dawa, Erwa, Sanwa, Siwa, Wuwa, Liuwa, Qiwa, Grandfather, SnakeDemon, ScorpionDemon, EvilTroop, NULL
};

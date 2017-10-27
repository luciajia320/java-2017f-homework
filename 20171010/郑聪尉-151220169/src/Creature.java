public class Creature {
    protected int row;
    protected int col;
    protected CHARACTER character;

    Creature(int row, int col, CHARACTER character){
        if(Battlefield.battlefiled[row][col]!=null) {
            Battlefield.battlefiled[row][col] = character;
        }
        this.character = character;
    }

    public void setPosition(int row, int col){
        /*
        if(Battlefield.battlefiled[row][col]==null)
            System.out.println("Error! Out of Battlefield!");
        else if(Battlefield.battlefiled[row][col]!=CHARACTER.NULL)
            System.out.println("Error! Someone's sitting there!");
        else
        */
        Battlefield.battlefiled[row][col]=character;
    }

}

enum CHARACTER {
    Dawa, Erwa, Sanwa, Siwa, Wuwa, Liuwa, Qiwa, Yeye, DemonSnake, DemonScorpion, DemonTroop, NULL,
};

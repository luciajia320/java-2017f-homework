public class Battlefield {
    static int BattlefieldSize=0;
    static CHARACTER[][] battlefiled;

    public static void init(int n){
        BattlefieldSize = n;
        battlefiled = new CHARACTER[BattlefieldSize][BattlefieldSize];
        int i, j;
        for(i=0;i<BattlefieldSize;i++){
            for(j=0;j<BattlefieldSize;j++){
                battlefiled[i][j]=CHARACTER.NULL;
            }
        }
    }

    public static void restart(){
        int i, j;
        for(i=0;i<BattlefieldSize;i++){
            for(j=0;j<BattlefieldSize;j++){
                battlefiled[i][j]=CHARACTER.NULL;
            }
        }
    }

    static void showTime(){
        for(int cnt=0;cnt<BattlefieldSize+2;cnt++)
            System.out.print("#\t");
        System.out.println();
        int i, j;
        for(i=0;i<BattlefieldSize;i++){
            System.out.print("#\t");
            for(j=0;j<BattlefieldSize;j++){
                if(battlefiled[i][j]==CHARACTER.Yeye)
                    System.out.print("V\t");
                else if(battlefiled[i][j]==CHARACTER.Dawa )
                    System.out.print("①\t");
                else if(battlefiled[i][j]==CHARACTER.Erwa )
                    System.out.print("②\t");
                else if(battlefiled[i][j]==CHARACTER.Sanwa )
                    System.out.print("③\t");
                else if(battlefiled[i][j]==CHARACTER.Siwa )
                    System.out.print("④\t");
                else if(battlefiled[i][j]==CHARACTER.Wuwa )
                    System.out.print("⑤\t");
                else if(battlefiled[i][j]==CHARACTER.Liuwa )
                    System.out.print("⑥\t");
                else if(battlefiled[i][j]==CHARACTER.Qiwa )
                    System.out.print("⑦\t");
                else if(battlefiled[i][j]==CHARACTER.DemonScorpion)
                    System.out.print("Y\t");
                else if(battlefiled[i][j]==CHARACTER.DemonTroop)
                    System.out.print("H\t");
                else if(battlefiled[i][j]==CHARACTER.DemonSnake)
                    System.out.print("S\t");
                else
                    System.out.print("\t");
            }
            System.out.print("#\n");
        }
        for(int cnt=0;cnt<BattlefieldSize+2;cnt++)
            System.out.print("#\t");
        System.out.println();
    }

    public static boolean setPosition(int row, int col, CHARACTER character){
        /*if(Battlefield.battlefiled[row][col]==null)
            System.out.println("Error! Out of Battlefield!");
        else*/
        battlefiled[row][col]=character;
        return true;
    }

}

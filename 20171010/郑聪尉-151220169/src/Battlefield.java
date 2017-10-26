public class Battlefield {
    static int BattlefieldSize=0;
    static CHARACTER[][] battlefiled;

    Battlefield(int N){
        BattlefieldSize = N;
        battlefiled = new CHARACTER[N][N];
    }
    void initial(){
        int i, j;
        for(i=0;i<BattlefieldSize;i++){
            for(j=0;j<BattlefieldSize;j++){
                battlefiled[i][j]=CHARACTER.NULL;
            }
        }
    }
    void showTime(){
        for(int cnt=0;cnt<BattlefieldSize+2;cnt++)
            System.out.print("#\t");
        System.out.println();
        int i, j;
        for(i=0;i<BattlefieldSize;i++){
            System.out.print("#\t");
            for(j=0;j<BattlefieldSize;j++){
                if(battlefiled[i][j]!=CHARACTER.NULL)
                    System.out.print("*\t");
                else
                    System.out.print("\t");
            }
            System.out.print("#\n");
        }
        for(int cnt=0;cnt<BattlefieldSize+2;cnt++)
            System.out.print("#\t");
        System.out.println();
    }
}

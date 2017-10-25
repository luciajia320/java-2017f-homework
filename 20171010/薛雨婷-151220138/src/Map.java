import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    private Position[][] positions;
    private creature[] creatures1;
    private creature creature2;
    private creature[] creatures3;


    public Map(brothers[] bro,xiezijing xie,monsters[] mon){
        this.positions=new Position[15][10];
        this.creatures1=bro;
        this.creature2=xie;
        this.creatures3=mon;
     //   this.creatures4=blank;
        for(int i=0;i<15;i++)
            for(int j=0;j<10;j++)
                this.positions[i][j]=new Position(i,j);

        for(int i=8;i<15;i++){
            this.positions[i][3].setHolder(bro[i-8]);
        }
        for(int i=0;i<6;i++){
            this.positions[0][i].setHolder(mon[i]);
        }
        this.positions[1][0].setHolder(xie);

    }

    public Position[][] getPositions() {
        return positions;
    }

    public void output(){
        for(int i=0;i<15;i++) {
            for (int j = 0; j < 10; j++) {
                if(positions[i][j].getValid())
                    System.out.print(positions[i][j].getHolder().getName() + "  ");
                else
                    System.out.print("       ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}

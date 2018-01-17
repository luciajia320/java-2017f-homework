import java.util.ArrayList;


public class Map <T extends creature>{
    //private Position[][] positions;
    private ArrayList<ArrayList<Position>> positions;
 //   private creature[] creatures1;
  //  private creature creature2;
  //  private creature[] creatures3;
    int length,width;

    public Map(int n,int m){
        length=n;
        width=m;
        this.positions=new ArrayList<>();
        for(int i=0;i<n;i++){
            positions.add(new ArrayList<>());
            for(int j=0;j<m;j++)
                positions.get(i).add(new Position(i,j));
        }
    }

    public void PutOnMap(queue huluQueue,T xie,ArrayList<T> mon){
      //  this.positions=new Position[15][10];
     //   this.creatures1=bro;
      //  this.creature2=xie;
     //   this.creatures3=mon;
     //   this.creatures4=blank;
      /*  for(int i=0;i<15;i++)
            for(int j=0;j<10;j++)
                this.positions[i][j]=new Position(i,j);
       */

        for(int i=6;i<13;i++){
            this.positions.get(i).get(3).setHolder(huluQueue.getCreat(i-6));
        }
        for(int i=0;i<6;i++){
            this.positions.get(0).get(i).setHolder(mon.get(i));
        }
        this.positions.get(1).get(0).setHolder(xie);

    }

    public ArrayList<ArrayList<Position>> getPositions() {
        return positions;
    }

    //打印阵型
    public void output(){
        for(int i=0;i<length;i++) {
            for (int j = 0; j < width; j++) {
                if(positions.get(i).get(j).getValid())
                    System.out.print(positions.get(i).get(j).getHolder().getName());
                else
                    System.out.print("🌿");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


}

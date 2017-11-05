/**
 *
 * This is the place where the battle between gourds and monsters happens.
 * Seven gourds and many monsters stand in the battle field forming the stage
 * of confrontation.
 */

public class Grid {
    public String pos[][];
    public int x,y;
    private static String K;
    Grid(int X, int Y, String key){
        x=X;
        y=Y;
        K=key;
        pos=new String[x][];
        for(int i=0;i<x;++i){
            pos[i]=new String[y];
        }
        reset();
    }
    public void set(Cordinate cord, String name){
        pos[cord.x][cord.y]=name;
    }
    public void reset() {
        for(int i=0;i<x;++i){
            for(int j=0;j<y;++j){
                pos[i][j]=K;
            }
        }
    }
    public void show(){
        for(int i=0;i<x;++i){
            for(int j=0;j<y;++j){
                System.out.print(pos[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
class Cordinate{
    public int x, y;

    Cordinate(int X, int Y){
        x=X;
        y=Y;
    }
}

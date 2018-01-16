import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    public BattleField(int length, int height, int middle) {
        this.ground = new ArrayList<List<Position>>();
        for(int i=0;i<height;i++){
            List<Position> t = new ArrayList<Position>();
            for(int j=0;j<length;j++){
                t.add(new Position(j,i));
            }
            this.ground.add(t);
        }
        this.length = length;
        this.height = height;
        this.middle = middle;
    }

    public List<List<Position>> getGround() {

        return ground;
    }

    public void setGround(List<List<Position>> ground) {
        this.ground = ground;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMiddle() {
        return middle;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    private List<List<Position>> ground;

    private int length,height,middle;

    public void clearLeftGround(){
        for(int i=0;i<height;i++){
            for (int j=0;j<middle;j++){
                Creature t= ground.get(i).get(j).getHolder();
                if(t != null){
                    ground.get(i).get(j).clearHolder();
                }
            }
        }
    }
    public void clearRightGround(){
        for(int i=0;i<height;i++){
            for (int j=middle+1;j<length;j++){
                Creature t= ground.get(i).get(j).getHolder();
                if(t != null){
                    ground.get(i).get(j).clearHolder();
                }
            }
        }
    }

    public Position getPosition(Position position){
        return ground.get(position.getY()).get(position.getX());
    }

    public void show(){
        for(List<Position> i : ground){
            for(Position j : i){
                if(j.getHolder()!=null)
                    j.getHolder().report();
                else
                    System.out.print("　");
            }
            System.out.println();
        }
    }
}

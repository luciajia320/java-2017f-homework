package hlw;

public class Coord {
    int x;
    int y;
    Coord(int x1, int y1){
        x = x1;
        y = y1;
    }
    Coord(){
        x = -1;
        y = -1;
    }
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}

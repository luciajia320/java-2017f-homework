package lsc.java.hulu_3;

public class Yanxing {
    Square square;
    public Yanxing(int start_x, int start_y, Square s){
        s.clear_all();
        s.set(start_x, start_y, new Scorpion());
        for(int index = 0; index < 7; ++index)
        {
            s.set(start_x- index,start_y - index, new lackey());
        }
        square = new Square(s.size);
        square.move_from_square(s);
    }
    public Square return_square()
    {
        return square;
    }
}

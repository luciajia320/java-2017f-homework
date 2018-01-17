package lsc.java.hulu_3;

public class Heyi {
    Square square;
    public Heyi(int start_x, int start_y, Square s) {
        s.clear_all();
        s.set(start_x, start_y,new Scorpion());
        for(int index = 0; index < 4;++index)
            s.set(start_x-index,start_y - index,new lackey());
        for(int index = 0; index < 4;++index)
            s.set(start_x-index,start_y + index,new lackey());

        square  = new Square(s.size);
        square.move_from_square(s);
    }
    public Square return_square()
    {
        return square;
    }
}
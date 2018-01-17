package lsc.java.hulu_3;

public class enemysquare {
    private Square enemies;
    public enemysquare(int start_x,int start_y, int size)
    {
        enemies = new Square(size);
    }
    public Square return_square() {
        return enemies;
    }
}

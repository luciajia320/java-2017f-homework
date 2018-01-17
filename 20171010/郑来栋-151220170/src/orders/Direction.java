package orders;

public enum Direction{
    EAST(0,1),SOUTH(1,0),WEST(0,-1),NORTH(-1,0);
    int dx,dy;

    Direction(int dx,int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }
}

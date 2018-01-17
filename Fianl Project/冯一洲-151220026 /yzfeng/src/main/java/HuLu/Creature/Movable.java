package HuLu.Creature;

public interface Movable {
    public int x();
    public int y();
    public void move(int x, int y);
    public void moveTo(int x, int y);
    public boolean validMove(int x, int y);
}

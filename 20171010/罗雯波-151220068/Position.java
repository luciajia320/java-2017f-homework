public class Position {
    private Creature holder;
    private PosCoord posCoord;

    public void setHolder(Creature holder) {
        this.holder = holder;
    }

    public Creature getHolder() {
        return holder;
    }

    public void setPosCoord(PosCoord posCoord) {
        this.posCoord = posCoord;
    }

    public PosCoord getPosCoord() {
        return posCoord;
    }
}

class PosCoord {
    private int x, y;
    public PosCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PosCoord nextPosCoord(ORIENTATION orientation) {
        PosCoord next = new PosCoord(x, y);
        switch (orientation) {
            case WEST:
                next.setX(next.getX() - 1);
                break;
            case EAST:
                next.setX(next.getX() + 1);
                break;
            case NORTH:
                next.setY(next.getY() + 1);
                break;
            case SOUTH:
                next.setY(next.getY() - 1);
                break;
        }
        return next;
    }
}

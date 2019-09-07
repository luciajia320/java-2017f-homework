
public abstract class Formation {
    protected Creature[] creatures;
    protected Creature leader;
    protected Position[] positions;
    protected Position lp;
    private int x;
    private int y;
    protected int width;
    protected int height;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Formation(int a, int b, int w, int h, Creature[] c, Creature l) {
        x = a;
        y = b;
        width = w;
        height = h;
        this.creatures = c;
        this.leader = l;
        this.positions = new Position[creatures.length];
    }



    public abstract void arrange();

    public void fill(Position[][] g) {
        arrange();
        for(Position p : positions) {
            g[x + p.getX()][y + p.getY()] = p;
        }
        g[x + lp.getX()][y + lp.getY()] = lp;
    }

}

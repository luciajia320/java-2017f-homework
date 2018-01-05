public class Ground {
    private Position[][] p;
    private int gsize;

    Ground(int size) {
        p = new Position[size][size];
        gsize = size;
    }

    public Position[][] getPos() {
        return p;
    }



    public void layout(Formation f1, Formation f2) {
        f1.fill(p);
        f2.fill(p);
    }

    public void display() {
        for(int i = 0; i < gsize; i++) {
            for (int j = 0; j < gsize; j++) {
                if(p[i][j] == null)
                    System.out.print("- - ");
                else
                    p[i][j].getHolder().report();
            }
            System.out.println();
        }
    }
}

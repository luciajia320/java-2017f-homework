public class Formations {

    protected Grid[][] grids;
    private Coordinate coordinate;
    int width;
    int height;

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setGrids(Grid[][] grids) {
        this.grids = grids;
    }
    public Grid getGridof(int i,int j) { return grids[i][j];}

    public Grid[][] getGrids() {
        return grids;
    }

    public Formations(int x,int y){
        this.width = x;
        this.height = y;
        Qi qi = new Qi();
        this.grids = new Grid[x][y];
        for (int i = 0;i<width;i++) {
            for (int j = 0;j<height;j++) {
                grids[i][j]=new Grid(x,y);
                grids[i][j].setHolder(qi);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (grids[i][j].isOccupied())
                {sb.append(grids[i][j].getHolder().getName() + " ");}
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}

class Qi implements Creature{
    private Grid grid;
    public static final String  PLACE_HOLDER = "🈳";

    public Qi() {

    }
    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
        grid.setHolder(this);
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() {
        return PLACE_HOLDER + " ";
    }

    @Override
    public String getName() {
        return PLACE_HOLDER;
    }

    public void move(int x, int y) {
        return;
    }
}

public class Grid {
    private Coordinate coordinate;
    private  boolean occupied;
    private Creature holder;

    public Grid() {
        super();
        this.coordinate = new Coordinate();
        this.occupied=false;
        this.holder= null;

    }
    public Grid(int x, int y){
        super();
        this.coordinate= new Coordinate(x,y);
        this.holder= null;
        this.occupied=false;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public void cleanOccupied() {
        this.occupied = false;
    }

    public int getX() {return this.coordinate.getX();}


    public int getY() { return this.coordinate.getY(); }

    public void setHolder(Creature holder) {
        if (!(holder==null)) {
        this.holder = holder;
        this.setOccupied();}
    }

    public void setNull(){
        this.holder=null;
        this.cleanOccupied();
    }

    public Creature getHolder() {
        return holder;
    }


}

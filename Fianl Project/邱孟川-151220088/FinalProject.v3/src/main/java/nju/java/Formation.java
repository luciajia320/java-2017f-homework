package main.java.nju.java;

public class Formation {
    private int width;
    private int height;
    Position location;
    Position[] positions;

    public Formation(int x, int y) {
        this.width = x;
        this.height = y;
        positions = new Position[x*y];
        for(int i = 0; i < x*y; ++i)
            positions[i] = new Position(0,0);
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setLocation(Position location){ this.location = location; }

    public Position getLocation() { return location; }

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

    public boolean isCrossBound(int bound){
        if ((location.getX() + this.getWidth() > bound)
                || (location.getY() + this.getHeight() > bound))
            return true;
        return false;
    }

    public boolean conflict(Formation f){
        if(contains(f) || overlap(f)) return true;
        else return false;
    }
    
    public boolean contains(Formation f){
        if ((this.getLocation().getX() <= f.getLocation().getX())
                && (this.getLocation().getY() <= f.getLocation().getY())
                && (this.getLocation().getX() + this.getWidth() >= f.getLocation().getX() + f.getWidth())
                && (this.getLocation().getY() + this.getHeight() >= f.getLocation().getY() + f.getHeight())) {
            return true;
        }
        return false;
    }
    
    public boolean overlap(Formation f){
        if (((this.getLocation().getX() + this.getWidth() >= f.getLocation().getX()))
                && (this.getLocation().getY() + this.getHeight() >= f.getLocation().getY())
                && (this.getLocation().getX() <= f.getLocation().getX())
                && (this.getLocation().getY() <= f.getLocation().getY()))
            return true;
        return false;
    }
}

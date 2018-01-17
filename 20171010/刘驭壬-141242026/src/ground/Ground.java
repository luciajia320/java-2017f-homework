package ground;

import java.util.ArrayList;
import java.util.List;

import creature.Grass;
import location.Location;
import creature.Creature;
import formation.Formation;

public class Ground {
    private int maxY;
    private int maxX;

    private Creature [][] ground;

    private ArrayList<Formation> exists;
    private ArrayList<Location> loc;

    public Ground() {
        this.maxY = 15;
        this.maxX = 15;
        this.ground = new Creature[this.maxY][this.maxX];
        this.exists = new ArrayList<Formation>();
        this.loc = new ArrayList<Location>();

        for (int i = 0; i < this.maxY; i++){
            for (int j = 0; j < this.maxX; j++){
                ground[i][j] = new Grass();
            }
        }
    }

    public void addFormation(Formation f, Location l){
        this.exists.add(f);
        Creature[][] tmp = f.getContents();
        for (Creature[] t : tmp) {
            for (Creature tt : t) {
                int y = tt.getLoc().getY() + l.getY();
                int x = tt.getLoc().getX() + l.getX();
                tt.getLoc().setY(y);
                tt.getLoc().setX(x);
                ground[y][x] = tt;
            }
        }
    }

    public void addCreature(Location l, Creature c){
        c.setLoc(l);
        int y = l.getY();
        int x = l.getX();
        ground[y][x] = c;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxY; i++){
            for (int j = 0; j < maxX; j++){
                sb.append(ground[i][j].toString());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return  sb.toString();
    }
}

package creature;
import location.Location;

public class Creature {
    protected String name;
    protected Location loc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

package archive;

import util.State;

public class CreatureArchived {
    public String image;
    public State state;
    public int x;
    public int y;

    @Override
    public String toString() {
        return image + " " + state.toString() + " " +  x + " " + y;
    }
}

package archive;

import util.State;

public class CreatureArchived {
    public State state;
    public int x;
    public int y;

    @Override
    public String toString() {
        return state.toString() + " " +  x + " " + y;
    }
}

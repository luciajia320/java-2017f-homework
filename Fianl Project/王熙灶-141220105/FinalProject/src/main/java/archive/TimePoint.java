package archive;

import java.util.ArrayList;
import java.util.List;

public class TimePoint {
    public List<CreatureArchived> creatures = new ArrayList<>();

    @Override
    public String toString() {
        return creatures.toString();
    }
}

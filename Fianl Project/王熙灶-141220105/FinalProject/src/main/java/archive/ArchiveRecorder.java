package archive;

import creature.animal.Animal;
import util.ArchiveIO;

import java.util.ArrayList;
import java.util.List;

import static util.Constant.animals;
import static util.Constant.recordNo;

public class ArchiveRecorder {
    private final List<TimePoint> timePoints = new ArrayList<>();

    public synchronized void add() {
        TimePoint timePoint = new TimePoint();
        for(Animal animal: animals) {
            CreatureArchived c = new CreatureArchived();
            c.state = animal.getCurrentState();
            if(animal.getPosition() == null) {
                System.out.println(animal + " position is null");
            }
            c.x = animal.getPosition().getX();
            c.y = animal.getPosition().getY();
            timePoint.creatures.add(c);
        }
        timePoints.add(timePoint);
    }

    public synchronized void save() {
        String filename = "record" + recordNo;
        ArchiveIO.write(timePoints, filename);
        recordNo++;
    }

    public synchronized void save(String filename) {
        ArchiveIO.write(timePoints, filename);
    }

    public synchronized void clear() {
        timePoints.clear();
    }

    public static void main(String[] args) {
        new ArchiveRecorder().add();
    }
}

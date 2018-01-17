import java.util.ArrayList;
import java.util.Collections;

public class RandomSorter implements Sorter {
	@Override
	public void sort(Queue queue) {
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		Collections.shuffle(queuePositions);
	}
}

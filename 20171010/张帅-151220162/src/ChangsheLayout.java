import java.util.ArrayList;

public class ChangsheLayout implements Layout {
	@Override
	public void place(Queue queue) {
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(int i=0;i<queuePositions.size();i++)
			queuePositions.get(i).setPosition(i, 0);
	}
}

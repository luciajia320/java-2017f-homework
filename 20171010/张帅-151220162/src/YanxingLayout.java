import java.util.ArrayList;

public class YanxingLayout implements Layout {
	@Override
	public void place(Queue queue) {
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(int i=0;i<queuePositions.size();i++)
			queuePositions.get(i).setPosition(i,queuePositions.size()-1-i);
	}
}

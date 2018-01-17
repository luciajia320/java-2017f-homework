import java.util.ArrayList;

public class HeyiLayout implements Layout {
	@Override
	public void place(Queue queue) {
		ArrayList<Position> queuePositions = queue.getQueuePositions();
		for(int i=0;i<queuePositions.size();i++) {
			if(i%2==0)
				queuePositions.get(i).setPosition(i/2, i/2);
			else
				queuePositions.get(i).setPosition(i/2, queuePositions.size()-1-i/2);
		}
	}
}

import java.util.ArrayList;

public class Queue {
	private ArrayList<Position> queuePositions; //阵型中的相对位置

	public Queue(Creature[] creatures){
		queuePositions = new ArrayList<Position>();
		for(int i=0;i<creatures.length;i++) {
			queuePositions.add(new Position(0, i));
			creatures[i].setQueuePosition(queuePositions.get(i));
		}
	}

	public ArrayList<Position> getQueuePositions(){
		return queuePositions;
	}
}

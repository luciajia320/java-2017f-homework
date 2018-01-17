import java.util.ArrayList;

public class BubbleSorter implements Sorter {
	public void sort(Queue queue){
		ArrayList<Position> positions = queue.getQueuePositions();
		for(int i=0;i<positions.size()-1;i++)
			for(int j=0;j<positions.size()-i-1;j++)
				if(((Comparable)(positions.get(j).getHolder())).biggerThan((Comparable) (positions.get(j+1).getHolder()))){
					Creature creature = positions.get(j).getHolder();
					positions.get(j+1).getHolder().setQueuePosition(positions.get(j));
					creature.setQueuePosition(positions.get(j+1));
				}
	}
}

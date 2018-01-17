package example;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Queue queue,int row,int col,int length) {
        Creature creature;
        Position[][] positions = queue.getPositions();
        for(int i = 0; i < length - 1; i++){
        	    for(int j = 0; j < length - i - 1; j++){
        	      if(((Comparable) (positions[row+j + 1][col].getHolder())).biggerThan((Comparable)(positions[row+j][col].getHolder()))){
        	        creature = positions[row+1+j][col].getHolder();
        	        positions[row+j+1][col].setHolder(positions[row+j][col].getHolder());
        	        positions[row+j][col].setHolder(creature);
        	      }
        	   }
        }
     }
}

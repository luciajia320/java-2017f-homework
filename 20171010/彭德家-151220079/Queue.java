package example;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Queue {


    private Position[][] positions;
    final int NUM = 30;
    
    
    public Position[][] getPositions() {
        return positions;
    }
    public void entry(Creature c,Position p) {
    	c.setPosition(p);
    	p.setHolder(c);
    }
    public void entry(Creature c,int i,int j) {
    	positions[i][j].setHolder(c);
    }
    public Queue() {
    	positions = new Position[NUM][NUM];
    	for(int i = 0;i < NUM;i++) {
    		for(int j = 0;j < NUM;j++)
    			this.positions[i][j] = new Position(i,j);
    	}
    }
    public void shuffle(Creature[]creatures,int row,int col) {
    	int num = creatures.length - 1;
    	Random random = ThreadLocalRandom.current();
    	for(int i = 0;i < num;i++) {
    		int index = random.nextInt(num);
    		//System.out.println(index);
    		Creature creature = positions[row+i][col].getHolder();
    		positions[row+i][col].setHolder(positions[row+index][col].getHolder());
    		positions[row+index][col].setHolder(creature);
    	}
    	
    }
   public void rollback() {
	   System.out.println(".................................................................................................................................");
	   for(int i = 0;i < positions.length;i++) {
		   String str = "[";
		   for(int j = 0;j < positions[i].length;j++) {
			   if(positions[i][j].getHolder() == null)
				   str += "口";
			   else
				   {
				   str += positions[i][j].getHolder().toString();
				   str += " ";
				   }
		   }
		   str += "]";
		   System.out.println(str);
	   }
	   System.out.println("...............................................................................................................................");
   }  
    public void cleanPositions() {
    	for(int i = 0;i < positions.length;i++) {
    		for(int j = 0;j < positions[i].length;j++) {
    			//positions[i][j].getHolder().resetPosition(positions[i][j]);
    			positions[i][j].setHolder(null);
    		}
    	}
    }
   
}
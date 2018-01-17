package FinalProject;

import java.util.Random;

public class Step {
	private boolean all_moved;
	private boolean [] self_moved;
	private int count;
	//public  int allcount;
 	public Step() {
 		self_moved = new boolean [Creature.All];
 		for(int i=0;i<Creature.All;i++)
 			self_moved[i] = false;
 		all_moved = true;
 		count = 0;
 		//allcount = 0;
 	}
 	public synchronized void showing(int i) {
 		count++;
 		self_moved[i-1] = true; 
 		if(count == Creature.getamount()) {
 			all_moved = true;
 			notifyAll();
 		}
 	}

 	public synchronized void showed() {
 		//allcount++;
 		count = 0;
 		all_moved = false; 
 		for(int i=0;i<Creature.All;i++)
 			self_moved[i] = false;
 		notifyAll();
	}

 	public synchronized void waitForshowed(int i)
        throws InterruptedException {
 		while (all_moved == true || self_moved[i-1] == true)
	        wait();
			
 	}

 	public synchronized void waitFormoved()
        throws InterruptedException {
 		while (all_moved == false)
	        wait();
	}
}
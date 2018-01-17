
public class Formation {
	
	public static void heyi(Queue queue, Creature[] brothers, Creature[] enemies) {
        queue.putCreature(5, 8, enemies[0]);
        queue.putCreature(2, 2, enemies[1]);
        queue.putCreature(2, 14, enemies[2]);
        queue.putCreature(3, 4, enemies[3]);
        queue.putCreature(3, 12, enemies[4]);
        queue.putCreature(4, 6, enemies[5]);
        queue.putCreature(4, 10, enemies[6]);
        
        queue.putCreature(4, 2, enemies[7]);
        queue.putCreature(12, 12, enemies[8]);
	}
	
	public static void fengshi(Queue queue, Creature[] brothers, Creature[] enemies) {
        queue.putCreature(6, 8, enemies[0]);
        queue.putCreature(1, 8, enemies[1]);
        queue.putCreature(2, 8, enemies[1]);
        queue.putCreature(3, 8, enemies[1]);
        queue.putCreature(4, 6, enemies[2]);
        queue.putCreature(4, 8, enemies[3]);
        queue.putCreature(4, 10, enemies[4]);
        queue.putCreature(5, 7, enemies[5]);
        queue.putCreature(5, 8, enemies[6]);
        queue.putCreature(5, 9, enemies[6]);
        
        queue.putCreature(4, 2, enemies[7]);
        queue.putCreature(12, 12, enemies[8]);
	}
}

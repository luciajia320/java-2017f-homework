
package huluwa3;
import java.util.Random;
public class Queue<T extends Creature> {//������������ͬ���������У����򣬷��ض�Ӧλ��Holder,����λ�ã�������	
	private Position<Creature>[] positions;
	private T[] creatures;
	
	
	@SuppressWarnings("unchecked")
	public Queue(T[] creaturesToSet)//��ʼ�����������
	{
		this.creatures=creaturesToSet;
		this.positions=new Position[creatures.length];//�趨���鳤��
		
		for (int i=0;i<creatures.length;i++)
		{
			positions[i]=new Position<Creature>(i,0);
			creatures[i].setPosition(positions[i]);
			
		}
		
	}
	public Position<Creature>[] getPositions()
	{
		return positions;
	}
	
	public void queueReport()
	{
		for (int i=0;i<positions.length;i++)
		{
			positions[i].getHolder().report();
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public void shuffle()
	{
		 Random random = new Random();
	     for (int i = creatures.length - 1; i >= 0; i--) {
	            int index = random.nextInt(i + 1);
	            Position<Creature> position = creatures[index].getPosition();
	            creatures[index].setPosition(creatures[i].getPosition());
	            creatures[i].setPosition(position);
	        }
	     
	}
		
	public void setFirst(Position<Creature> pos)
	{
		creatures[0].setPosition(pos);
	}
	
	public T[] getCreatures()
	{
		return creatures;
	}
	
	/*
	public static void main(String[] args)
	{
		
		Huluwa[] brothers=new Huluwa[7];
		 for (int i = 0; i < brothers.length; i++) {
			 
	            brothers[i]=new Huluwa(COLOR.values()[i], NUMBER.values()[i]);
	        }
		 
		 Queue huluQueue=new Queue(brothers);
		 huluQueue.queueReport();
		 
		 huluQueue.shuffle();
		 huluQueue.queueReport();
		 
		 Sorter bubble=new BubbleSorter();
		 bubble.Sort(huluQueue);
		 huluQueue.queueReport();
		 
		 huluQueue.shuffle();
		 huluQueue.queueReport();
		 
		 Sorter quick=new QuickSorter();
		 quick.Sort(huluQueue);
		 huluQueue.queueReport();
		
	}
*/
}

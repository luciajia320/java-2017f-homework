package example;

public class ResetQueue implements  Formation{
	
	@Override
	public void Changlong(Queue queue, Creature[] creatures, int row, int col) {
		for(int i = 0;i < creatures.length;i++)
		queue.entry(creatures[i], row - i - 1,col);
	}
	@Override
	public  void Arrow(Queue queue, Creature[] creatures, int row, int col) {
		queue.entry(creatures[0], row,col);
		for(int i = 1; i <= 3;i++) {
			queue.entry(creatures[2 * i - 1], row + i,col - i );
			queue.entry(creatures[2*i], row+i,col + i);
		}
		for(int  i = 5; i < creatures.length;i++) {
			queue.entry(creatures[i], i - 4 + row,col);
		}
	}
	@Override
	public  void HeYi(Queue queue, Creature[] creatures, int row, int col) {
		queue.entry(creatures[0], row,col);
		for(int i = 1;i < creatures.length;i+= 2) {
			int val = i /2 +1;
			queue.entry(creatures[i],row +val,col - val);
			queue.entry(creatures[i + 1],row + val,col + val);
		}
	}
	@Override
	public  void YanXing(Queue queue, Creature[] creatures, int row, int col) {
		for(int i = 0;i < creatures.length;i++) {
			queue.entry(creatures[i], row + i,col  - i);
		}
	}
	@Override
	public  void ChongYuan(Queue queue, Creature[] creatures, int row, int col) {
		for(int i = 0;i< creatures.length;i++) {
			int val = i %2;
			queue.entry(creatures[i], row + i,col - val);
		}
	}
	@Override
	public  void YuLin(Queue queue, Creature[] creatures, int row, int col) {
		int i;
		for( i = 0;i * i < creatures.length;i++) {
			for(int j = 0;j < 2 * i + 1;j++)
				queue.entry(creatures[j + i * i], row +i ,col - i + j);
		}
		System.out.println("i:" + i);
		int num = creatures.length - i * i;
		for(int m = 0;m < num;m++) {
			queue.entry(creatures[i *i +m], row + i + m,col);
		}
	}
	@Override
	public  void FangYuan(Queue queue, Creature[] creatures, int row, int col) {
		int num = creatures.length / 4;
		int i = 0;
		for(int  j = 0;j < num;j++) {
			queue.entry(creatures[i + j], row + j,col - j);
		}
		i += num;
		for(int j = 0;j < num;j++) {
			queue.entry(creatures[i + j], row + j + 1,col + 1+ j);
		}
		i += num;
		for(int j = 0;j < num;j++) {
			queue.entry(creatures[i + j], row + j + num,col -num + j);
		}
		i+= num;
		for(int j = 0;j < num;j++) {
			queue.entry(creatures[i + j], row + 2 *num,col);
		}
		i += num;
		for(int j = i;j < creatures.length;j++) {
			queue.entry(creatures[j], row + 2 *num + j - i + 1 ,col);
		}
	}
	@Override
	public  void YanYue(Queue queue, Creature[] creatures, int row, int col) {
		
	}

}

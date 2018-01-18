package huluwa2;

import huluwa.Admin;

public interface Sort  {//设置排序接口
	public void sort(Queue queue);
}
class BubbleSort implements Sort{

	@Override
	public void sort(Queue queue) {
		Position []positions =queue.getPosition();
		for (int i = 0; i < Position.no; i++) {
			for (int j = 0; j < Position.no; j++) {
				queue.swap(positions[j], positions[j+1]);//排序并交換
				
				}
		}	
	}
}
class FastSort implements Sort{//快速排序

	@Override
	public void sort(Queue queue) {
		Position []positions = queue.getPosition();	
		
	}
	
}

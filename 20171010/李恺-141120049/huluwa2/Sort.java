package huluwa2;

import huluwa.Admin;

public interface Sort  {//��������ӿ�
	public void sort(Queue queue);
}
class BubbleSort implements Sort{

	@Override
	public void sort(Queue queue) {
		Position []positions =queue.getPosition();
		for (int i = 0; i < Position.no; i++) {
			for (int j = 0; j < Position.no; j++) {
				queue.swap(positions[j], positions[j+1]);//���򲢽��Q
				
				}
		}	
	}
}
class FastSort implements Sort{//��������

	@Override
	public void sort(Queue queue) {
		Position []positions = queue.getPosition();	
		
	}
	
}

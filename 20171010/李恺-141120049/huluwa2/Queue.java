package huluwa2;

abstract public class Queue {//���������
	public int no;
	public Creature []creatures;//������
	Position []positions ;//λ����
	Formation form=new Formation();
	
	


public Position[] getPosition(){//���λ����Ϣ
	return positions;
	
}
public void swap(Position a,Position b) {//����λ��
	Creature t1= a.getHolder();
	Creature t2 =b.getHolder();
	t1.leavePosition();
	t2.leavePosition();
	t1.setPosition();
	t2.setPosition();
}
public  void FormTransform(Formation Form) {//���ͱ任
	this.form = form;
	positions = form.position;
	Creature creatures;
	creatures.leavePosition();//ȫ�����
}
public Position[] getQueuePosition() {//��������λ����Ϣ
	return positions;
	
}


}
class Huluwaqueue extends Queue{
	
}
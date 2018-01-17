package huluwa2;

abstract public class Queue {//抽象类队列
	public int no;
	public Creature []creatures;//生物组
	Position []positions ;//位置组
	Formation form=new Formation();
	
	


public Position[] getPosition(){//获得位置信息
	return positions;
	
}
public void swap(Position a,Position b) {//交换位置
	Creature t1= a.getHolder();
	Creature t2 =b.getHolder();
	t1.leavePosition();
	t2.leavePosition();
	t1.setPosition();
	t2.setPosition();
}
public  void FormTransform(Formation Form) {//阵型变换
	this.form = form;
	positions = form.position;
	Creature creatures;
	creatures.leavePosition();//全体离队
}
public Position[] getQueuePosition() {//返回排列位置信息
	return positions;
	
}


}
class Huluwaqueue extends Queue{
	
}
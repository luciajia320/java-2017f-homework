package example;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int start = 15,end = 15;
    	final Position startPoint = new Position(start,end);
    	final int numofhulu = 7;
    	final int numoflou = 7;
    	Creature[]justice = new Creature[numofhulu + 1];
    	for(int i = 0;i < numofhulu;i++) {
    		justice[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
    	}
    	justice[numofhulu] = new Grandpa();
    	Creature[]evil = new Creature[numoflou + 2];
    	evil[0] = new Xiezi();
    	for(int i = 1;i <= numoflou;i ++) {
    		evil[i] = new Loulou();
    	}
    	evil [numoflou + 1] = new Shejing();
    	Queue queue = new Queue();
    	ResetQueue resetqueue = new ResetQueue();
    	resetqueue.Changlong(queue, justice, start, end);
    	queue.shuffle(justice,start,end);
    	BubbleSorter sort = new BubbleSorter();
    	//sort.sort(queue,start,end,7);
    	/*箭矢阵*/
    	System.out.println("正邪第一次大战:");
    	resetqueue.Arrow(queue, evil, start, end);
    	queue.rollback();
    	queue.cleanPositions();
    	resetqueue.Changlong(queue, justice, start, end);
    	System.out.println("爷爷:葫芦娃加油");
    	System.out.println("蛇精:喽喽们加油");
    	//resetqueue.Arrow(queue, evil, start, end);
    	//resetqueue.Changlong(queue,evil, start + evil.length, end);
    	/*����*/
    	System.out.println("正邪第二次大战:");
    	System.out.println("鹤翼阵:");
    	resetqueue.HeYi(queue, evil, start, end);
    	//resetqueue.ChongYuan(queue, evil, start, end);
    	//resetqueue.FangYuan(queue, evil, start, end);
    	//resetqueue.YanYue(queue, evil, start, end);
    	queue.rollback();
    	System.out.println("爷爷:葫芦娃加油");
    	System.out.println("蛇精:喽喽们加油");
	}
	
	
}

public class Queue
{
	public static void main(String args[])
	{
		HuluRanks huluQueue = new HuluRanks();
		huluQueue.randomRanks();
		huluQueue.numberOff();
		huluQueue.bubbleSort();
		huluQueue.numberOff();
		huluQueue.randomRanks();
		huluQueue.numberOff();
		System.out.println("快速排序：");
		huluQueue.quickSort(1,7);
		huluQueue.numberOff();
	}
}


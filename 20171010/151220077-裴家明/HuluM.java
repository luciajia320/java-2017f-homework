package javaH2;
 
 import java.util.Random;
 
 public class HuluM{
 	int population;
 	
 	
 	Huluwa[] huluwa;
 
 	public HuluM() {
 		this.population = 0;
 		huluwa = new Huluwa[8];
 	}
 
 	public int getPopulation() {
 		return population;
 	}
 
 	public void setPopulation(int population) {
 		this.population = population;
 	}
 	
 	public void add(Huluwa H)
 	{
 		if (population>=8)
 		{
 			System.out.println("建议百度搜索“葫芦娃有几兄弟”");
 		return;
 		}
 		huluwa[population]=H;
 		population=population+1;
 	}
	
	public void assemble() {
		int []flag=new int[7];
		for (int i = 0; i < population; i++) {
			Random r=new Random();
			int R=r.nextInt(7);
 			while(flag[R]!=0)
 			flag[R]=1;
 			for (int j = 0; j < population; j++) {
 				if(huluwa[j].getName().ordinal()==R)
 				{
 					if (i==j) continue;
 					huluwa[i].swap(huluwa[j], huluwa);
 				}
 			}
 		}
 	}
 	
 	public void BubbleSort()
 	{
 		for (int i = 0; i < population; i++) {
 			for (int j = 0; j < population-1; j++) {
 				if(huluwa[j].solo(huluwa[j+1])>0)
 				{
 					huluwa[j].swap(huluwa[j+1], huluwa);
 				}
 			}
 		}
 	}
 	
 	
 	public void Quick(int left,int right,Huluwa []A)
 	{
 		if (left>=right) 
 		{
 			return;
 		}
 		Huluwa Index=A[left];
 		int low=left;
 		int high=right;
 		while(low<high)
 		{
 			while(A[high].solo(Index)<0&&low<high)
 			{
 				high--;
 			}
 			A[low].swap(A[high], A);
 			while(A[low].solo(Index)>0&&left<right)
 			{
 				low++;
 			}
 			A[high].swap(A[low], A);
 			
 		}
 		Quick(left,low-1,A);
 		Quick(low+1,right,A);
 	}
 	
 	public void Quicksort()
 	{
 		Quick(0,6,huluwa);
 	}
 	
 	
 	public Huluwa[] getHuluM()
 	{
 		return huluwa;
 	}
 	
 	
 	
 	public void Show()
 	{
 		for (int i = 0; i < population; i++) {
 			System.out.println("第"+(i+1)+"位:"+"我是"+huluwa[i].getName().toString()+"  "+huluwa[i].getColor().toString());
 		}
 	}
 }
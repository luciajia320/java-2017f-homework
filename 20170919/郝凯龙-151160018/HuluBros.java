public class HuluBros{
	static Hulu[] array=new Hulu[7];
	public static void main(String args[]){
		array[0]=new Hulu(1,"老大","红色");
		array[1]=new Hulu(2,"老二","橙色");
		array[2]=new Hulu(3,"老三","黄色");
		array[3]=new Hulu(4,"老四","绿色");
		array[4]=new Hulu(5,"老五","青色");
		array[5]=new Hulu(6,"老六","蓝色");
		array[6]=new Hulu(7,"老七","紫色");
		System.out.println("冒泡排序");
		bubbleSort();
		System.out.println("\n快速排序");
		quickSort();	
	}	
	public static void bubbleSort(){
		disorder();
		showArray(1);
		for(int i=0;i<7;++i)
			for(int j=6;j>i;--j)
				if(array[j].key<array[i].key){
					printStep(i,j,1);
					printStep(j,i,1);
					swap(i,j);
				}
		showArray(1);
	}
	public static void quickSort(){
		disorder();
		showArray(2);
		qSort(0,6);
		showArray(2);
	}
	private static void qSort(int l,int r){
		if(l>=r)
			return;
		Hulu tmp=array[l];
		int i=l; int j=r;
		while(i<j){
			while(array[j].key>=tmp.key&&i<j)
				--j;
			while(array[i].key<=tmp.key&&i<j)
				++i;
			if(i<j){
				printStep(i,j,2);
				printStep(j,i,2);
				swap(i,j);
			}
		}
		if(l!=j){
			printStep(l,j,2);
			printStep(j,l,2);
			swap(l,j);
		}
		qSort(l,j-1);
		qSort(j+1,r);
	}
	private static void disorder(){
		for(int i=0;i<10;++i){
			int	t1=(int)(Math.random()*7);
			int t2=(int)(Math.random()*7);
			swap(t1,t2);
		}
	}	
	private static void swap(int t1,int t2){
		Hulu tmp=array[t1];
		array[t1]=array[t2];
		array[t2]=tmp;
	}
	private static void printStep(int i,int j,int choice){//1.rank 2.color
		if(choice==1)
			System.out.println(array[i].rank+": "+i+"->"+j);	
		else
			System.out.println(array[i].color+": "+i+"->"+j);	
	}
	private static void showArray(int choice){
		if(choice==1)
			for(int i=0;i<7;++i)
				System.out.print(array[i].rank+" ");
		else
			for(int i=0;i<7;++i)
				System.out.print(array[i].color+" ");
		System.out.println();
	}
}

class Hulu{
	public int key;
	public String rank;
	public String color;
	public Hulu(int key,String rank,String color){
		this.key=key;
		this.rank=rank;
		this.color=color;
	}
}

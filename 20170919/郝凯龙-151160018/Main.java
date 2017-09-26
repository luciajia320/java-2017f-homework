public class Main{
	public static void main(String[] args){
		HuluBros family=new HuluBros();
		System.out.println("BubbleSort:");
		family.bubbleSort();
		System.out.println("\nQuickSort:");
		family.quickSort();	
	}	
}

class HuluBros{
	private Hulu[] array;
	private Hulu empty;

	public HuluBros(){
		array=new Hulu[7];
		array[0]=new Hulu(1,"老大","红色");
		array[1]=new Hulu(2,"老二","橙色");
		array[2]=new Hulu(3,"老三","黄色");
		array[3]=new Hulu(4,"老四","绿色");
		array[4]=new Hulu(5,"老五","青色");
		array[5]=new Hulu(6,"老六","蓝色");
		array[6]=new Hulu(7,"老七","紫色");
	}	

	public void bubbleSort(){
		disorder();
		showArray(1);
		for(int i=0;i<7;++i)
			for(int j=6;j>i;--j)
				if(array[j].getKey()<array[i].getKey()){
					printStep(i,j,1);
					printStep(j,i,1);
					swap(i,j);
				}
		showArray(1);
	}

	public void quickSort(){
		disorder();
		showArray(2);
		qSort(0,6);
		showArray(2);
	}

	private void qSort(int l,int r){
		if(l>=r) return;
		Hulu tmp=array[l];
		int i=l; int j=r;
		while(i<j){
			while(array[j].getKey()>=tmp.getKey()&&i<j) --j;
			while(array[i].getKey()<=tmp.getKey()&&i<j) ++i;
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

	private void disorder(){
		for(int i=0;i<7;++i){
			int	t=(int)(Math.random()*7);
			swap(i,t);
		}
	}	

	private void swap(int t1,int t2){
		empty=array[t1];
		array[t1]=array[t2];
		array[t2]=empty;
	}

	private void printStep(int i,int j,int choice){				//1.rank 2.color
		if(choice==1) 
			System.out.println(array[i].getRank()+": "+i+"->"+j);	
		else System.out.println(array[i].getColor()+": "+i+"->"+j);	
	}

	private void showArray(int choice){
		if(choice==1)
			for(int i=0;i<7;++i)
				System.out.print(array[i].getRank()+" ");
		else for(int i=0;i<7;++i)
				System.out.print(array[i].getColor()+" ");
		System.out.println();
	}
}

class Hulu{
	private int key;
	private String rank;
	private String color;
	public Hulu(int key,String rank,String color){
		this.key=key;
		this.rank=rank;
		this.color=color;
	}
	public int getKey(){
		return this.key;
	}
	public String getRank(){
		return this.rank;
	}
	public String getColor(){
		return this.color;
	}
}

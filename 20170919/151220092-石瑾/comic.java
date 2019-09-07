
public class comic {
	huluwa []h;
	comic(){
		h = new huluwa[7];
		for (int i = 0; i < 7; i++)
			h[i] = new huluwa();
	}
	void bubblesort() {
		for(int i=0;i<7;i++)
			for(int j=i+1;j<7;j++)
			{
				if(h[i].age>h[j].age)
				{
					int temp=h[i].age;
					h[i].age=h[j].age;
					h[i].printname();
					System.out.println(": "+i+"->"+j);
					h[j].age=temp;
					h[j].printname();
					System.out.println(": "+j+"->"+i);
				}
			}
	};
	void quicksort(int left,int right) {
		if(left>=right)
			return;
		int i=left,j=right;
		int key=h[left].age;
		while(i<j) {
			while(i<j && h[j].age>=key) {
				j--;
			}
			if(i!=j) {
				h[i].age=h[j].age;
				h[i].printname();
				System.out.println(": "+j+"->"+i);
			}		
			while(i<j && h[i].age<=key) {
				i++;
			}
			if(i!=j) {
				h[j].age=h[i].age;
				h[j].printname();
				System.out.println(": "+i+"->"+j);
			}		
		}
		if(h[i].age!=key) {
			h[i].age=key;
			h[i].printname();
			System.out.println(": "+left+"->"+i);
		}
		quicksort(left,i-1);
		quicksort(i+1,right);
	}
	void printorder() {
		for(int i=0;i<7;i++)
		{
			h[i].printname();
			System.out.print(" ");
		}
		System.out.println(" ");
	}
	void setposition() {
		h[0].setage(3);
		h[1].setage(2);
		h[2].setage(5);
		h[3].setage(7);
		h[4].setage(1);
		h[5].setage(4);
		h[6].setage(6);
	}
}

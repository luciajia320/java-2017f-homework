package huluwa;

public class sort {
	 static void bubble_sort(int[] kids,int n) {
			for(int i=0;i<n-1;i++)
				for(int j=0;j<n-i-1;j++) 
					if(kids[j]>kids[j+1]) {
					//output.output_change(kids[j], j+1, j+2);
					//output.output_change(kids[j+1], j+2, j+1);
						int temp;
						temp = kids[j];
						kids[j]=kids[j+1];
						kids[j+1]= temp;
					}
		}
}
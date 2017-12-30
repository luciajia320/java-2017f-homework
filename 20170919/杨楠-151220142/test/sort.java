package test;

public class sort {
	 static void bubble_sort(int[] kids,int n) {
			for(int i=0;i<n-1;i++)
				for(int j=0;j<n-i-1;j++) 
					if(kids[j]>kids[j+1]) {
					output.output_change(kids[j], j+1, j+2);
					output.output_change(kids[j+1], j+2, j+1);
						int temp;
						temp = kids[j];
						kids[j]=kids[j+1];
						kids[j+1]= temp;
					}
		}
	static int[] bynary_sort(int[] kids,int n) {
			 int[] temp = new int[n];
			 temp[0] = kids[0];
			 for (int i = 0; i < n; i++) {
				 for (int j = 0, k = i - 1; j < i && k >= 0;) {
					 	if (temp[(j + k) / 2] >= kids[i]) {
					 		if ((j + k) / 2 == 0) {
					 			for (int iter = i; iter > 0; iter--) {
					 				temp[iter] = temp[iter - 1];
					 				output.output_change(temp[iter-1], iter,iter+1);
					 			}
					 		temp[0] = kids[i];
					 		output.output_change(kids[i], i+1,1);
					 		break;
					 		}
					 		else if (temp[(j + k) / 2 - 1] <= kids[i]) {
					 			for (int iter = i; iter > (j + k) / 2; iter--) {
					 				temp[iter] = temp[iter - 1];
					 				output.output_change(temp[iter-1], iter,iter+1);
					 			}
					 			temp[(j + k) / 2] = kids[i];
					 			output.output_change(kids[i], i+1,(j+k)/2+1);
					 			break;
					 		} 
					 		else 
					 			k = (k + j) / 2-1;
					 	} 
					 	else if (temp[(j + k) / 2] < kids[i]) {
					 		if ((j + k) / 2 == i - 1) {
					 			temp[i] = kids[i];
					 			break;
					 		}
					 	else 
					 		j=(k + j) / 2+1;
					 	}
				 }
			 }
			 return temp;
		}
}

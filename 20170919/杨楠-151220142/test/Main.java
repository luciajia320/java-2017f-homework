package test;

public class Main {
	public static void main(String[] args) {
		int[] kids = {1,2,3,4,5,6,7};
		kids = sevenkids.random_kids(1,7,7);
		sort.bubble_sort(kids,7);
		for(int i=0;i<7;i++)
			output.output_num(kids[i]);
		kids = sevenkids.random_kids(1,7,7);
		kids = sort.bynary_sort(kids,7);
		for(int i=0;i<7;i++)
			output.output_color(sevenkids.color[kids[i]-1]);
	}
}

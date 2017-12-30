package huluwa;


public class huluwa extends group{
	public static int[] kids = {1,2,3,4,5,6,7};
	public static void setkids() {
		kids = sevenkids.random_kids(1,7,7);
		sort.bubble_sort(kids,7);
	}
	public huluwa(int n) {
		super(n);
		for(int i=0;i<n;i++)
			g[i].ty.settype(5+kids[i]);
	}
}

package test;
import java.util.Random;
public class sevenkids {
	static char[] color = {'r','o','y','g','b','d','p'};
	public static int[] random_kids(int min, int max, int n){
			int index;
	        int len = max-min+1;
	        int[] source = new int[len];
	        for (int i = min; i < max+1; i++)
	            source[i-min] = i;
	        int[] randomkids = new int[n];
	        Random x = new Random();
	        for (int i = 0; i < n; i++) {
	            index = Math.abs(x.nextInt() % len--);
	            randomkids[i] = source[index];
	            source[index] = source[len];
	        }
	        return randomkids;
	}

}

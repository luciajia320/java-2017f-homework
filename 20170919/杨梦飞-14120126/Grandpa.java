/** grandpa is a very smart person, he knows how to train seven gourd wa and trains
 *  them everyday.
 */


import java.util.*;
public class Grandpa
{
    public static void swap(Gourd huluwa[], int i, int j){
        if(i==j) return ;
        huluwa[i].baoshu();
        System.out.printf(": %d -> %d\n", i, j);
        Gourd tmp=huluwa[i];
        huluwa[i]=huluwa[j];
        huluwa[j]=tmp;
    }
    public static void disorder(Gourd huluwa[], int n){
        Random random=new Random();
        int min=0, max=6;
        for(int i=0;i<n;++i){
            int j=random.nextInt(max)%(max-min+1)+min;
            swap(huluwa, i, j);
        }
    }
    public static int partition(Gourd huluwa[],int lo,int hi){
        Gourd key=huluwa[lo];
        while(lo<hi){
            while(huluwa[hi].compare_by_color(key)==0&&hi>lo){
                hi--;
            }
            huluwa[lo]=huluwa[hi];
            while(huluwa[lo].compare_by_color(key)==1&&hi>lo){
                lo++;
            }
            huluwa[hi]=huluwa[lo];
        }
        huluwa[hi]=key;
        return hi;
    }
    public static void sort_by_color(Gourd huluwa[],int lo ,int hi){
        if(lo>=hi){
            return ;
        }

        int index=partition(huluwa,lo,hi);
        sort_by_color(huluwa,lo,index-1);
        sort_by_color(huluwa,index+1,hi);
    }
    public static void sort_by_position(Gourd huluwa[], int n){
        for (int x = 0; x < n-1; x++) {
            for (int y = x + 1; y < n; y++) {
                if (huluwa[x].compare_by_position(huluwa[y])==0) {
                    swap(huluwa, x, y);
                }
            }
        }
    }
    public static void train(Gourd huluwa[], int gourd_number)
    {
        disorder(huluwa, gourd_number);
        System.out.print("sort by bubble sort\n");
        sort_by_position(huluwa,gourd_number);
        System.out.print("begin: \n");
        for(int i=0;i<gourd_number;++i){
            huluwa[i].baoshu();
            System.out.print("\n");
        }
        disorder(huluwa,gourd_number);
        System.out.print("sort by quick sort\n");
        sort_by_color(huluwa, 0,gourd_number-1);
        System.out.print("begin: \n");
        for(int i=0;i<gourd_number;++i){
            huluwa[i].baoshu();
            System.out.print("\n");
        }
    }
}

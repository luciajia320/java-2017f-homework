/** grandpa is a very smart person, he knows how to train seven calabash wa and trains
 *  them everyday.
 */


import java.util.*;
public class Grandpa extends Creature
{

    /* Grandpa let two calabash wa to swap their position */
    public static void swap(Calabash calabash[], int i, int j){
        if(i==j) return ;
        calabash[i].baoshu();
        System.out.printf(": %d -> %d\n", i, j);
        Calabash tmp=calabash[i];
        calabash[i]=calabash[j];
        calabash[j]=tmp;
    }
    /* Grandpa let all n calabash wa random sort*/
    public static void disorder(Calabash calabash[], int n){
        System.out.print("打乱葫芦娃顺序：\n");
        Random random=new Random();
        int min=0, max=6;
        for(int i=0;i<n;++i){
            int j=random.nextInt(max)%(max-min+1)+min;
            swap(calabash, i, j);
        }
        System.out.print("\n");
    }
    public static int partition(Calabash calabash[],int lo,int hi){
        Calabash key=calabash[lo];
        while(lo<hi){
            while(calabash[hi].compare_by_color(key)==0&&hi>lo){
                hi--;
            }
            swap(calabash, hi, lo);
            while(calabash[lo].compare_by_color(key)==1&&hi>lo){
                lo++;
            }
            swap(calabash, lo, hi);
        }
        calabash[hi]=key;
        return hi;
    }
    /* Grandpa sort all n calabash wa by their color */
    public static void sort_by_color(Calabash calabash[],int lo ,int hi){
        if(lo>=hi){
            return ;
        }

        int index=partition(calabash,lo,hi);
        sort_by_color(calabash,lo,index-1);
        sort_by_color(calabash,index+1,hi);
    }
    /* Grandpa sort all n calabash wa by their position in the family. */
    public static void sort_by_position(Calabash calabash[], int n){
        for (int x = 0; x < n-1; x++) {
            for (int y = x + 1; y < n; y++) {
                if (calabash[x].compare_by_position(calabash[y])==0) {
                    swap(calabash, x, y);
                }
            }
        }
    }
    /* All calabash wa count off */
    public static void baoshu(Calabash calabash[], int calabash_number){
        System.out.print("报数：\n");
        for(int i=0;i<calabash_number;++i){
            calabash[i].baoshu();
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    /* Grandpa train n calabash wa to be more strong */
    public static void train(Calabash calabash[], int calabash_number)
    {
        disorder(calabash, calabash_number);
        System.out.print("对位置进行排序: \n");
        sort_by_position(calabash,calabash_number);

        baoshu(calabash, calabash_number);
        disorder(calabash,calabash_number);
        baoshu(calabash, calabash_number);
        System.out.print("对颜色进行排序: \n\n");
        sort_by_color(calabash, 0,calabash_number-1);
        baoshu(calabash, calabash_number);
    }
    Grandpa(Cordinate cord, String name){
        super(cord, name);
    }
}

import java.io.*;

import java.lang.reflect.Array;
import java.math.*;
import java.util.Arrays;
import java.util.*;

import java.text.*;
public class Huluwa_sort
{
    public static void swap(Huluwa huluwa[], int i, int j){
        if(i==j) return ;
        huluwa[i].baoshu();
        System.out.printf(": %d -> %d\n", i, j);
        Huluwa tmp=huluwa[i];
        huluwa[i]=huluwa[j];
        huluwa[j]=tmp;
    }
    public static void disorder(Huluwa huluwa[], int n){
        Random random=new Random();
        int min=0, max=6;
        for(int i=0;i<n;++i){
            int j=random.nextInt(max)%(max-min+1)+min;
            swap(huluwa, i, j);
        }
    }
    public static int partition(Huluwa huluwa[],int lo,int hi){
        Huluwa key=huluwa[lo];
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
    public static void sort_by_color(Huluwa huluwa[],int lo ,int hi){
        if(lo>=hi){
            return ;
        }

        int index=partition(huluwa,lo,hi);
        sort_by_color(huluwa,lo,index-1);
        sort_by_color(huluwa,index+1,hi);
    }
    public static void sort_by_position(Huluwa huluwa[], int n){
        for (int x = 0; x < n-1; x++) {
            for (int y = x + 1; y < n; y++) {
                if (huluwa[x].compare_by_position(huluwa[y])==0) {
                    swap(huluwa, x, y);
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Huluwa huluwa[]=new Huluwa[7];
        huluwa[0]=new Huluwa("one", "red");
        huluwa[1]=new Huluwa("two", "oringe");
        huluwa[2]=new Huluwa("three", "yellow");
        huluwa[3]=new Huluwa("four", "green");
        huluwa[4]=new Huluwa("five", "blue");
        huluwa[5]=new Huluwa("six", "indigo");
        huluwa[6]=new Huluwa("seven", "violet");
        disorder(huluwa, 7);
        System.out.print("sort by bubble sort\n");
        sort_by_position(huluwa,7);
        System.out.print("begin: \n");
        for(int i=0;i<7;++i){
            huluwa[i].baoshu();
            System.out.print("\n");
        }
        disorder(huluwa,7);
        System.out.print("sort by quick sort\n");
        sort_by_color(huluwa, 0,6);
        System.out.print("begin: \n");
        for(int i=0;i<7;++i){
            huluwa[i].baoshu();
            System.out.print("\n");
        }
    }
}

class Huluwa{
    private String number;
    private String color;

    static HashMap<String, Integer> mm, mm2;
    static {
        mm=new HashMap<>();
        mm.put("red",   1);
        mm.put("oringe",2);
        mm.put("yellow",3);
        mm.put("green",4);
        mm.put("blue",5);
        mm.put("indigo",6);
        mm.put("violet",7);
        mm2=new HashMap<>();
        mm2.put("one", 1);
        mm2.put("two", 2);
        mm2.put("three", 3);
        mm2.put("four", 4);
        mm2.put("five", 5);
        mm2.put("six",6);
        mm2.put("seven", 7);
    }
    Huluwa(String num, String co){
        number=num;
        color=co;
    }
    int compare_by_color(Huluwa x){
        int c1=mm.get(color), c2=mm.get(x.color);
        if(c1<=c2) return 1;
        else return 0;
    }
    int compare_by_position(Huluwa x){
        int c1=mm2.get(number), c2=mm2.get(x.number);
        if(c1<=c2) return 1;
        else return 0;
    }
    void baoshu(){
        System.out.print(number);
    }
}
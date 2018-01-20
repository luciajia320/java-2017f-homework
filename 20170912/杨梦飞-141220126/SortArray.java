import java.io.*;

import java.lang.reflect.Array;
import java.math.*;
import java.util.Arrays;
import java.util.*;

import java.text.*;
public class SortArray
{
    public static void main(String[] args)
    {
        int []arr={ 2,3,1,5,8,6,7,4};
        for (int x = 0; x < arr.length - 1; x++) {
            for (int y = x + 1; y < arr.length; y++) {
                if (arr[x] > arr[y]) {
                    int temp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = temp;
                }
            }
        }
        for(int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }
}
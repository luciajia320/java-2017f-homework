package nju.java.common;

import java.io.File;
import java.util.Random;

public class GLOBAL {
    public static void readConfigFile(){
        try{
            IOFile.setReadFile(new File("config"));
            String s= IOFile.getNextString();
            String[] readFileLine = s.split(" ");

            for(int i=0;i<7;i++){
                HULUWA_POWER[i]=Integer.parseInt(readFileLine[i]);
            }
            for(int i=0;i<5;i++) {
                ANIMAL_POWER[i]=Integer.parseInt(readFileLine[i+7]);
            }
            TIMES=Integer.parseInt(readFileLine[12]);
            FREQUENT=Integer.parseInt(readFileLine[13]);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static int[] HULUWA_POWER=new int[7];
    public static int[] ANIMAL_POWER=new int[5];
    public static int TIMES=10;
    public static int FREQUENT=50;
    public int Xcount=11*GLOBAL.TIMES;
    public int Ycount=7*GLOBAL.TIMES;
    public static int randomDire(){
        Random rand = new Random();
        int x = rand.nextInt(100);
        if(x%3==0)
            return 1;
        else if(x%3==1)
            return 0;
        else
            return -1;
    }
    public static boolean randomNum(){
        Random rand = new Random();
        int x = rand.nextInt(100);
        if(x>30)
            return true;
        else
            return false;
    }

    public static int randomNum(int begin,int end){
        Random rand = new Random();
        if(begin==end)
            return -1;
        else{
            int x = rand.nextInt(end-begin);
            return x+begin;
        }
    }

    //第一个胜利返回true
    public static boolean checkWin(int firstPower,int secondPower){
        Random rand = new Random();
        int x=rand.nextInt(firstPower+secondPower);
        if(x < firstPower)
            return true;
        else
            return false;
    }
}

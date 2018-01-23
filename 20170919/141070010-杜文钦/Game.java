package java

import java.util.*;

/**
 * Created by Administrator on 2017/10/17.
 */
public class Game {
    public static void main(String[] args) {
        Game game=new Game();
       /* swap(game.huLuWaList,0,1);
        for (HuLuWa huLuWa : game.huLuWaList) {
            System.out.println(huLuWa.getName()+":"+huLuWa.getPos());
        }*/
        //Collections.shuffle(game.huLuWaList);
        //shuffle(game.huLuWaList);
        /*for (HuLuWa huLuWa : game.huLuWaList) {
            System.out.println(huLuWa.getName()+":"+huLuWa.getPos());
        }*/
        bubbleSort(game.huLuWaList);
        quickSort(game.huLuWaList);

    }
    final String[] nameList={"大娃","二娃","三娃","四娃","五娃","六娃","七娃"};
    final String[] colorList = {"赤", "橙", "黄", "绿", "青", "蓝", "紫"};

    private List<HuLuWa> huLuWaList;
    private static Map<String,Integer> colorMap=new HashMap<>();


    public Game() {
        huLuWaList = new ArrayList<>();
        for(int i = 0; i < nameList.length; i++) {
            huLuWaList.add(new HuLuWa(i + 1, nameList[i], colorList[i],
                    i + 1));
        }
        for(int i = 0; i < colorList.length; i++) {
            colorMap.put(colorList[i],i);
        }

    }

    private static void shuffle(List<HuLuWa> list) {
        System.out.println("shuffling....");
        for(int i = 0; i < list.size()-1; i++) {
            //generate a random number between i to list.size()-1
            int j=(int)(Math.random()*(list.size()-i)+i);
            if(i!=j) {
                swap(list,i,j);
            }
        }
       /*
        for (HuLuWa huLuWa : list) {
            System.out.println(huLuWa.getName()+":"+huLuWa.getPos());
        }*/
        System.out.println("shuffle complete..");
    }

    private static void swap(List<HuLuWa> list,int i,int j){
       // list.get(i).reportPositionChange(list.get(j).getPos());
        //list.get(j).reportPositionChange(list.get(i).getPos());
        //System.out.println();
        int tempPos=list.get(i).getPos();
        list.get(i).setPos(list.get(j).getPos());
        list.get(j).setPos(tempPos);
        Collections.swap(list,i,j);
    }

    private static void bubbleSort(List<HuLuWa> list) {
        shuffle(list);
        for(int i = 1; i < list.size(); i++) {
            for(int j=0;j<list.size()-i;j++) {
                if (list.get(j).getRank() > list.get(j + 1).getRank()) {
                    swap(list,j, j + 1);
                    /*list.get(j).reportPositionChange(j+1+1);
                    list.get(j + 1).reportPositionChange(j+1);
                    list.get(j).setPos(j+1+1);
                    list.get(j + 1).setPos(j + 1);
                    System.out.println();
                    Collections.swap(list, j, j + 1);*/
                }
            }
        }
        for (HuLuWa huLuWa : list) {
            huLuWa.countOffByName();
        }
        System.out.println();
    }

    private static int getColorIndex(HuLuWa s){
        return colorMap.get(s.getColor());
    }
    public static int quickSortPartion(List<HuLuWa> list,int first,int last) {
       // int pivot=colorMap.get(list.get(first).getColor());
        int pivot = getColorIndex(list.get(first));
        int partionIndex=first;
        for(int i=first+1;i <= last;i++) {
            //if(colorMap.get(list.get(i).getColor())<pivot)
            if(getColorIndex(list.get(i))<pivot)
                swap(list,++partionIndex,i);
        }
        swap(list,first,partionIndex);
        return partionIndex;
    }

    public static void quickSortAux(List<HuLuWa> list, int first, int last) {
        if (first < last) {
            int partionIndex=quickSortPartion(list,first,last);
            quickSortAux(list,first,partionIndex-1);
            quickSortAux(list,partionIndex+1,last);
        }
    }
    public static void quickSort(List<HuLuWa> list) {
        shuffle(list);
        quickSortAux(list,0,list.size()-1);
        for (HuLuWa huLuWa : list) {
            huLuWa.countOffByColor();
        }
        System.out.println();
    }
}

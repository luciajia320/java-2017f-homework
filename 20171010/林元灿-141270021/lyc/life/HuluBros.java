package lyc.life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class HuluBros {
    private Sorter sorter;
    private List list;

    public static final Huluwa[] bros = {
            new Huluwa(Color.红色, Name.老大),
            new Huluwa(Color.橙色, Name.老二),
            new Huluwa(Color.黄色, Name.老三),
            new Huluwa(Color.绿色, Name.老四),
            new Huluwa(Color.青色, Name.老五),
            new Huluwa(Color.蓝色, Name.老六),
            new Huluwa(Color.紫色, Name.老七)
    };

    public HuluBros() {
        list = new ArrayList<Huluwa>();
    }

    public  Huluwa[] getHulubros() {
        return (Huluwa[])list.toArray(new Huluwa[list.size()]);
    }

    public void prepare(){
        this.lineup();  //葫芦娃随机站成一排
        this.setSorter(new MySorter());
        this.sort();    //按照老大，老二顺序排序
        //this.report();  //葫芦娃报告位置
    }
    public void report(){
        for(int i=0;i<list.size();i++){
            Huluwa hulu = (Huluwa) list.get(i);
            System.out.print(hulu.sayName() + " ");
        }
        System.out.println();
    }

    public void lineup() {
        list.clear();
        list.addAll(Arrays.asList(bros));
        shuffle();
    }

    public boolean shuffle() {
        if (list.isEmpty()) {
            System.out.println("please lineup huluwa first!");
            return false;
        }
        Collections.shuffle(list);
        return true;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public boolean sort() {
        if (this.sorter == null) {
            System.out.println("please assign a sorter!");
            return false;
        }
        sorter.sort(list);
        return true;
    }

    @Override
    public String toString(){
        return "🍐";
    }
}

/**
 *  Red wa has a lot of strength.
 *  Orange wa is very smart.
 *  Yellow wa is very strong.
 *  Green wa can use fire.
 *  Blue wa can use water.
 *  indigo wa can hide himself in the environment.
 *  violet wa has a treasure gourd that is very powerful.
 **/



import java.util.HashMap;

class Calabash extends Creature {
    private String number;
    private String color;

    static HashMap<String, Integer> mm, mm2;
    static {
        mm=new HashMap<>();
        mm.put("红",   1);
        mm.put("橙",2);
        mm.put("黄",3);
        mm.put("绿",4);
        mm.put("青",5);
        mm.put("蓝",6);
        mm.put("紫",7);
        mm2=new HashMap<>();
        mm2.put("大娃", 1);
        mm2.put("二娃", 2);
        mm2.put("三娃", 3);
        mm2.put("四娃", 4);
        mm2.put("五娃", 5);
        mm2.put("六娃",6);
        mm2.put("七娃", 7);
    }
    Calabash(String num, String co, Cordinate cord){
        super(cord, co);
        number=num;
        color=co;
    }

    int compare_by_color(Calabash x){
        int c1=mm.get(color), c2=mm.get(x.color);
        if(c1<=c2) return 1;
        else return 0;
    }
    int compare_by_position(Calabash x){
        int c1=mm2.get(number), c2=mm2.get(x.number);
        if(c1<=c2) return 1;
        else return 0;
    }
    void baoshu(){
        System.out.print(number);
    }
}

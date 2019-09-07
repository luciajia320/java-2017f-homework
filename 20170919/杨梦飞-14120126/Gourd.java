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

class Gourd{
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
    Gourd(String num, String co){
        number=num;
        color=co;
    }
    int compare_by_color(Gourd x){
        int c1=mm.get(color), c2=mm.get(x.color);
        if(c1<=c2) return 1;
        else return 0;
    }
    int compare_by_position(Gourd x){
        int c1=mm2.get(number), c2=mm2.get(x.number);
        if(c1<=c2) return 1;
        else return 0;
    }
    void baoshu(){
        System.out.print(number);
    }
}
/**
 * Created by yangmengfei on 2017/11/5.
 */
import java.util.*;

public class Queue {
    public static LinkedList<Cordinate> gen_lie(String name, Cordinate begin_pos, int number){
        if(name=="CHANG"){
            return gen_chang(begin_pos, number);
        }
        else if(name=="HE"){
            return gen_he(begin_pos, number);
        }
        else if(name=="YAN"){
            return gen_yan(begin_pos, number);
        }
        return null;
    }
    public static LinkedList<Cordinate> gen_chang(Cordinate begin_pos, int number){
        LinkedList<Cordinate> cord_list=new LinkedList<Cordinate>();
        for(int i=0;i<number;++i){
            Cordinate cur=new Cordinate(begin_pos.x+i, begin_pos.y);
            cord_list.add(cur);
        }
        return cord_list;
    }
    public static LinkedList<Cordinate> gen_yan(Cordinate begin_pos, int number){
        begin_pos.x+=number;
        LinkedList<Cordinate> cord_list=new LinkedList<Cordinate>();
        for(int i=0;i<number;++i){
            Cordinate cur=new Cordinate(begin_pos.x-i, begin_pos.y+i);
            cord_list.add(cur);
        }
        return cord_list;
    }
    public static LinkedList<Cordinate> gen_he(Cordinate begin_pos, int number){
        LinkedList<Cordinate> cord_list=new LinkedList<Cordinate>();
        int i;
        for(i=0;i<(number+1)/2;++i){
            Cordinate cur=new Cordinate(begin_pos.x+i, begin_pos.y+i);
            cord_list.add(cur);
        }
        begin_pos.x+=i;
        begin_pos.y+=i;
        if((number%1)==0){
            begin_pos.x-=2;
        }
        for(i=0;i<number/2;++i){
            Cordinate cur=new Cordinate(begin_pos.x-i, begin_pos.y+i);
            cord_list.add(cur);
        }
        return cord_list;
    }
}

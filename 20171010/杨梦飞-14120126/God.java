import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by yangmengfei on 2017/11/5.
 */
public class God {
    static private Grandpa gpa;
    static private Calabash calabash[];
    static private Monster monster[];
    static private int monster_number;
    static private Monster_queue mon_queue;
    static private Monster_king mon_king;

    static private Grid grid;
    static private Cordinate tmp_cord=new Cordinate(0,0);
    public static void init() {
        /* Grandpa comes to the stage */
        gpa=new Grandpa(tmp_cord, "爷爷");

        /* Seven Calabash wa emerge*/
        /* Seven Calabash wa was born in Calabash mountain */
        calabash=new Calabash[7];
        /* Blow is seven groud wa with their own ability */
        calabash[0]=new Calabash("大娃", "红", tmp_cord);
        calabash[1]=new Calabash("二娃", "橙", tmp_cord);
        calabash[2]=new Calabash("三娃", "黄", tmp_cord);
        calabash[3]=new Calabash("四娃", "绿", tmp_cord);
        calabash[4]=new Calabash("五娃", "青", tmp_cord);
        calabash[5]=new Calabash("六娃", "蓝", tmp_cord);
        calabash[6]=new Calabash("七娃", "紫", tmp_cord);

        /* create monster*/
        monster_number=11;
        monster=new Monster[monster_number];
        for(int i=0;i<monster_number;++i){
            monster[i]=new Monster(tmp_cord, "怪");
        }

        /* create monster queue */
        mon_queue=new Monster_queue(tmp_cord, "蛇");

        /* create monster king */
        mon_king=new Monster_king(tmp_cord, "王");

        /* battle field create */
        grid=new Grid(15, 20, "+");

    }
    public static void story_1(){
        gpa.train(calabash, 7);
    }
    public static void story_2(){
        grid.reset();
        lie_dui("CHANG", "HE");
        show();
        grid.reset();
        lie_dui("CHANG", "YAN");
        show();
    }
    public static void lie_dui(String cala, String mon){
        lie_Calabashs(cala);
        lie_Grandpa();
        lie_Monster(mon);
        lie_queue_king();
    }
    public static void lie_Calabashs(String lie){
        Cordinate begin_pos=new Cordinate(3,1);
        LinkedList<Cordinate> cord_list=Queue.gen_lie(lie, begin_pos,7);
        Iterator<Cordinate>it=cord_list.listIterator();
        int cnt=0;
        while(it.hasNext()){
            calabash[cnt].cord=it.next();
            cnt++;
        }
    }
    public static void lie_Grandpa(){
        Cordinate begin_pos=new Cordinate(1,1);
        gpa.cord=begin_pos;
    }
    public static void lie_queue_king(){
        Cordinate begin_pos=new Cordinate(1,8);
        mon_queue.cord=begin_pos;
        Cordinate pos2=new Cordinate(1, 12);
        mon_king.cord=pos2;
    }
    public static void lie_Monster(String lie) {
        Cordinate begin_pos=new Cordinate(3, 7);
        LinkedList<Cordinate> cord_list=Queue.gen_lie(lie, begin_pos,monster_number);
        Iterator<Cordinate>it=cord_list.listIterator();
        int cnt=0;
        while(it.hasNext()){
            monster[cnt].cord=it.next();
            cnt++;
        }
    }
    public static void show(){
        show_Calabashs_grandpa();
        show_monster_queue_king();
        show_grid();
    }
    public static void show_Calabashs_grandpa(){
        for(int i=0;i<7;++i){
            Cordinate cord=calabash[i].cord;
            grid.set(cord, calabash[i].name);
        }
        grid.set(gpa.cord, gpa.name);
    }
    public static void show_monster_queue_king(){
        for(int i=0;i<monster_number;++i){
            Cordinate cord=monster[i].cord;
            grid.set(cord, monster[i].name);
        }
        grid.set(mon_king.cord, mon_king.name);
        grid.set(mon_queue.cord, mon_queue.name);
    }
    public static void show_grid(){
        grid.show();
    }
}

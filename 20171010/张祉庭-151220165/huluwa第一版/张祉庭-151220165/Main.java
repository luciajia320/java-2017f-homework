import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main{
    static void init_Huluwa(Huluwa[] huluwas)
    {
        huluwas[0]=new Huluwa(COLOR.CHI);
        huluwas[1]=new Huluwa(COLOR.CHENG);
        huluwas[2]=new Huluwa(COLOR.HUANG);
        huluwas[3]=new Huluwa(COLOR.LV);
        huluwas[4]=new Huluwa(COLOR.QING);
        huluwas[5]=new Huluwa(COLOR.LAN);
        huluwas[6]=new Huluwa(COLOR.ZI);
    }
    public static void main(String[] args){
    Huluwa[] huluwas=new Huluwa[7];
    init_Huluwa(huluwas);
    map battle_map=new map();
    SnakeFormation snake=new SnakeFormation(battle_map,huluwas);
    battle_map.report_map();
    CraneWing crane=new CraneWing(battle_map);
    battle_map.report_map();
    FormationClear fc=new FormationClear(battle_map);
    Flying fly=new Flying(battle_map);
    battle_map.report_map();
    fc.set_map();
 //   fc.report_map();
    SquareFormation sf=new SquareFormation(battle_map);
    battle_map.report_map();
    return;
    }
}
/**
 * This class defines Huluwas.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SevenBrothers {
    private Battlefield battlefieldExample;
    private Huluwa[] HuluwaTroop;
    private DemonTroop evilTroop;
    private Yeye yeye;
    private DemonSnake snakeDemon;

    public static void main(String[] args) {
        SevenBrothers example = new SevenBrothers();
        example.process();
    }

    public void process(){
        initial();
        battlefieldExample.showTime();
        randomStandInLine();
    }

    public void initial(){
        battlefieldExample = new Battlefield(13);
        battlefieldExample.initial();

        HuluwaTroop = new Huluwa[7];
        HuluwaTroop[0] = new Huluwa(CHARACTER.Dawa,Color.Chi,4, 2);
        HuluwaTroop[1] = new Huluwa(CHARACTER.Erwa,Color.Cheng,4, 3);
        HuluwaTroop[2] = new Huluwa(CHARACTER.Sanwa,Color.Huang,4, 4);
        HuluwaTroop[3] = new Huluwa(CHARACTER.Siwa,Color.Lv,4, 5);
        HuluwaTroop[4] = new Huluwa(CHARACTER.Wuwa,Color.Qing,4, 6);
        HuluwaTroop[5] = new Huluwa(CHARACTER.Liuwa,Color.Lan,4, 7);
        HuluwaTroop[6] = new Huluwa(CHARACTER.Qiwa,Color.Zi,4, 8);
    }

    public void randomStandInLine(){
        List<Huluwa> myList = Arrays.asList(HuluwaTroop);
        Collections.shuffle(myList);
    }

}

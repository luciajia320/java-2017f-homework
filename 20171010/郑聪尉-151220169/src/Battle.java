import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Battle {
    private Huluwa[] huluwa;
    private FighterDemon[] demonTroop;
    private FighterDemon demonScorpion;
    private DemonSnake demonSnake;
    private Yeye yeye;

    public void main() {
        Battlefield.init(13);
        init(1, 0, 6, CHARACTER.Yeye);
        init(7, 2, 3, CHARACTER.Dawa);
        init(1, 5, 6, CHARACTER.DemonScorpion);
        init(20, 5, 6, CHARACTER.DemonTroop);
        init(1, 12, 6, CHARACTER.DemonSnake);

        System.out.println("\t\t\t\t\t\tHuluwa 3.0");
        Battlefield.showTime();

        System.out.println("\n\n\t\t\t\t\t\tHuluwa 3.1");
        new BubbleSorter().sort(huluwa);
        Battlefield.showTime();

        System.out.println("\n\n\t\t\t\t\t\tHuluwa 3.2");
        doubleFengVectrix(5, 3);
        Battlefield.showTime();
    }

    public void init(int n, int row, int col, CHARACTER character){
        if(character==CHARACTER.Yeye)
            yeye = new Yeye(row, col, character);
        else if(character==CHARACTER.DemonSnake)
            demonSnake = new DemonSnake(row, col, character);
        else if (character==CHARACTER.DemonScorpion)
            demonScorpion = new FighterDemon(row, col, character);
        else if (character==CHARACTER.Dawa) {
            huluwa = new Huluwa[7];
            huluwa[0] = new Huluwa(CHARACTER.Dawa, Color.Chi, row, col, Seniority.First);
            huluwa[1] = new Huluwa(CHARACTER.Erwa, Color.Cheng, row, col+1, Seniority.Second);
            huluwa[2] = new Huluwa(CHARACTER.Sanwa, Color.Huang, row, col+2, Seniority.Third);
            huluwa[3] = new Huluwa(CHARACTER.Siwa, Color.Lv, row, col+3, Seniority.Fourth);
            huluwa[4] = new Huluwa(CHARACTER.Wuwa, Color.Qing, row, col+4, Seniority.Fifth);
            huluwa[5] = new Huluwa(CHARACTER.Liuwa, Color.Lan, row, col+5, Seniority.Sixth);
            huluwa[6] = new Huluwa(CHARACTER.Qiwa, Color.Zi, row, col+6, Seniority.Seventh);

            randomStandInLine();
            for(int i=0;i<7;i++)
                Battlefield.setPosition(row, i+col, huluwa[i].character);
        }
        else if (character==CHARACTER.DemonTroop) {
            demonTroop = new FighterDemon[20];
            /*Initial Fish Formation*/
            demonTroop[1] = new FighterDemon(row+1, col-1, CHARACTER.DemonTroop);
            demonTroop[2] = new FighterDemon(row+1, col, CHARACTER.DemonTroop);
            demonTroop[3] = new FighterDemon(row+1, col+1, CHARACTER.DemonTroop);
            demonTroop[4] = new FighterDemon(row+2, col-2, CHARACTER.DemonTroop);
            demonTroop[5] = new FighterDemon(row+2, col-1, CHARACTER.DemonTroop);
            demonTroop[6] = new FighterDemon(row+2, col, CHARACTER.DemonTroop);
            demonTroop[7] = new FighterDemon(row+2, col+1, CHARACTER.DemonTroop);
            demonTroop[8] = new FighterDemon(row+2, col+2, CHARACTER.DemonTroop);
            demonTroop[9] = new FighterDemon(row+3, col-3, CHARACTER.DemonTroop);
            demonTroop[10] = new FighterDemon(row+3, col-2, CHARACTER.DemonTroop);
            demonTroop[11] = new FighterDemon(row+3, col-1, CHARACTER.DemonTroop);
            demonTroop[12] = new FighterDemon(row+3, col, CHARACTER.DemonTroop);
            demonTroop[13] = new FighterDemon(row+3, col+1, CHARACTER.DemonTroop);
            demonTroop[14] = new FighterDemon(row+3, col+2, CHARACTER.DemonTroop);
            demonTroop[15] = new FighterDemon(row+3, col+3, CHARACTER.DemonTroop);
            demonTroop[16] = new FighterDemon(row+4, col, CHARACTER.DemonTroop);
            demonTroop[17] = new FighterDemon(row+5, col-1, CHARACTER.DemonTroop);
            demonTroop[18] = new FighterDemon(row+5, col, CHARACTER.DemonTroop);
            demonTroop[19] = new FighterDemon(row+5, col+1, CHARACTER.DemonTroop);
        }
    }

    public void randomStandInLine(){
        List<Huluwa> myList = Arrays.asList(huluwa);
        Collections.shuffle(myList);
    }

    public void doubleFengVectrix(int row, int col){
        Battlefield.restart();
        /*Double Feng Vectrix*/
        for(int i=0;i<7;i++)
            Battlefield.setPosition(2, i+3, huluwa[i].character);
        yeye.setPosition(0, 6);
        demonSnake.setPosition(12, 6);
        demonScorpion.setPosition(row, col);
        demonTroop[1].setPosition(row+1, col-1);
        demonTroop[2].setPosition(row+1, col);
        demonTroop[3].setPosition(row+1, col+1);
        demonTroop[4].setPosition(row+2, col-2);
        demonTroop[5].setPosition(row+2, col);
        demonTroop[6].setPosition(row+2, col+2);
        demonTroop[7].setPosition(row+3, col);
        demonTroop[8].setPosition(row+4, col);
        demonTroop[9].setPosition(row+5, col);
        demonTroop[10].setPosition(row, col+6);
        demonTroop[1].setPosition(row+1, col+5);
        demonTroop[2].setPosition(row+1, col+6);
        demonTroop[3].setPosition(row+1, col+7);
        demonTroop[4].setPosition(row+2, col+4);
        demonTroop[5].setPosition(row+2, col+6);
        demonTroop[6].setPosition(row+2, col+8);
        demonTroop[7].setPosition(row+3, col+6);
        demonTroop[8].setPosition(row+4, col+6);
        demonTroop[9].setPosition(row+5, col+6);
    }

}

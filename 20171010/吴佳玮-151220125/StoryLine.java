import java.util.ArrayList;
import java.util.List;

public class StoryLine {
    List<Creature> justiceBattleCreatures;
    List<Creature> justiceBuffCreatures;
    List<Creature> evilBattleCreatures;
    List<Creature> evilBuffCreatures;
    BattleField battleField;
    List<Embattle> embattles;

    static int height = 7;
    static int length = 15;
    static int middle = 7;

    public StoryLine(List<Creature> justiceBattleCreatures, List<Creature> justiceBuffCreatures, List<Creature> evilBattleCreatures, List<Creature> evilBuffCreatures, BattleField battleField) {
        this.justiceBattleCreatures = justiceBattleCreatures;
        this.justiceBuffCreatures = justiceBuffCreatures;
        this.evilBattleCreatures = evilBattleCreatures;
        this.evilBuffCreatures = evilBuffCreatures;
        this.battleField = battleField;
        embattles=new ArrayList<Embattle>();
        embattles.add(new Rand());
        embattles.add(new Queue());
        embattles.add(new Echelon());
        embattles.add(new Crane());
    }

    public void randAndShow(){
        embattles.get(0).action(new Position(0,0),battleField,justiceBattleCreatures);
        battleField.show();
    }

    public void showDividingLine(){
        System.out.println("<------------------------------>");
    }

    public void queueAndShow(){
        battleField.clearLeftGround();
        embattles.get(1).action(new Position(0,0),battleField,justiceBattleCreatures);
        battleField.show();
    }

    public void echelonAndShow(){
        embattles.get(2).action(new Position(middle+1,0),battleField,evilBattleCreatures);
        battleField.show();
    }

    public void craneAndShow(){
        battleField.clearRightGround();
        embattles.get(3).action(new Position(middle+1,0),battleField,evilBattleCreatures);
        battleField.show();
    }

    public void buffAndShow(){
        embattles.get(0).action(new Position(0,0),battleField,justiceBuffCreatures);
        embattles.get(0).action(new Position(middle+1,0),battleField,evilBuffCreatures);
        battleField.show();
    }

    public static void main(String[] args) {

        List<Creature>  justiceBattleCreatures=new ArrayList<Creature>();
        List<Creature> evilBattleCreatures=new ArrayList<Creature>();
        List<Creature>  justiceBuffCreatures=new ArrayList<Creature>();
        List<Creature> evilBuffCreatures=new ArrayList<Creature>();
        justiceBuffCreatures.add(new Grandpa());
        for (int i = 0; i < 7; i++) {
            Huluwa t = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
            justiceBattleCreatures.add(t);
        }
        evilBuffCreatures.add(new Snake());
        evilBattleCreatures.add(new Scorpion());
        for (int i = 0;i < 6; i++){
            evilBattleCreatures.add(new Minion());
        }
        BattleField battleField=new BattleField(length,height,middle);
        StoryLine storyLine=new StoryLine(justiceBattleCreatures,justiceBuffCreatures,evilBattleCreatures,evilBuffCreatures,battleField);

        storyLine.randAndShow();
        storyLine.showDividingLine();
        storyLine.queueAndShow();
        storyLine.showDividingLine();
        storyLine.echelonAndShow();
        storyLine.showDividingLine();
        storyLine.buffAndShow();
        storyLine.showDividingLine();
        storyLine.queueAndShow();
        storyLine.showDividingLine();
        storyLine.craneAndShow();
        storyLine.showDividingLine();
        storyLine.buffAndShow();
        storyLine.showDividingLine();
    }
}

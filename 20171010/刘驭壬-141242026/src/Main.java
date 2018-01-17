import formation.HeyiFormation;
import formation.SnakeFormation;
import group.Hulubros;
import creature.Huluwa;
import creature.Goblin;
import ground.Ground;
import location.Location;
import formation.Formation;
import creature.Grandpa;
import creature.Shejing;
import formation.YanxingFormation;

public class Main {

    public static void main(String[] args) {
        conflict1();
        conflict2();
    }

    public static void conflict1(){
        Hulubros h = new Hulubros();
        Huluwa[] hw = h.getBros().toArray(new Huluwa[7]);
        Formation f1 = new SnakeFormation(hw);

        Goblin[] gob = new Goblin[7];
        for (int i = 0; i < 7; i++){
            gob[i] = new Goblin();
        }
        Formation f2 = new HeyiFormation(gob);

        Ground g = new Ground();
        g.addFormation(f1, new Location(2, 0));
        g.addFormation(f2, new Location(2, 4));


        g.addCreature(new Location(5, 1), new Grandpa());
        g.addCreature(new Location(5, 3), new Shejing());

        System.out.println(g);
    }

    public static void conflict2(){
        Hulubros h = new Hulubros();
        Huluwa[] hw = h.getBros().toArray(new Huluwa[7]);
        Formation f1 = new SnakeFormation(hw);

        Goblin[] gob = new Goblin[5];
        for (int i = 0; i < 5; i++){
            gob[i] = new Goblin();
        }
        Formation f2 = new YanxingFormation(gob);

        Ground g = new Ground();
        g.addFormation(f1, new Location(3, 0));
        g.addFormation(f2, new Location(3, 4));


        g.addCreature(new Location(5, 1), new Grandpa());
        g.addCreature(new Location(5, 3), new Shejing());

        System.out.println(g);
    }
}

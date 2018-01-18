package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

import java.util.*;

public class CalabrosFamily extends Creature implements Show, Sorter {
    private int number;
    public LinkedList<CalabashBro> CBfamily;

    public CalabrosFamily() {
        number = 7;
        CBfamily = new LinkedList<CalabashBro>();
        //In the family, the Calabash Brothers get together in a [List] without order.
        //Using a [Set] first and then converting it to the [List] in order to simplify the random creating process.
        Set<CalabashBro> Bros = new HashSet<CalabashBro>();
        Random r = new Random();
        while (Bros.size() < number) {
            int t = r.nextInt(number);
            Bros.add(new CalabashBro(Rank.values()[t], Color.values()[t]));
        }
        CBfamily.addAll(Bros);
    }

    public void sorting() {
        //Make the Calabash Brothers get in order
        Collections.sort(CBfamily, new CBComparator());
    }

    public void showyourself() {
        for(Iterator<CalabashBro> it = CBfamily.iterator();it.hasNext();) {
            it.next().showyourself();
        }
        System.out.print("\n");
    }
}

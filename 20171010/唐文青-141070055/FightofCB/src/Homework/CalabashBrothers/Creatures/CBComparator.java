package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.Creatures.CalabashBro;

import java.util.Comparator;

public class CBComparator implements Comparator {
    public int compare(Object obj1, Object obj2) {
        CalabashBro Bro1 = (CalabashBro) obj1;
        CalabashBro Bro2 = (CalabashBro) obj2;
        return Bro1.getordinal() - Bro2.getordinal();
    }
}

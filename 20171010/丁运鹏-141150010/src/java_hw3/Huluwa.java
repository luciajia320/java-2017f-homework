package java_hw3;

import java.util.Vector;

public class Huluwa {
    Vector<Huluwa_Entity> huluwas;

    Huluwa() {
        huluwas = new Vector<>(7);
        for(int i = 0; i < 7; i++)
            huluwas.add(new Huluwa_Entity());
    }
}

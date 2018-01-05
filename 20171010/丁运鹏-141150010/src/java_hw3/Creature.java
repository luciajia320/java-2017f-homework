package java_hw3;

import sun.java2d.CRenderer;

import java.lang.*;

abstract class Creature implements Cloneable{
    protected String name;

    void display() {

        System.out.print(name);
    }

    @Override
    public Creature clone() {
        Creature cre = null;
        try{
            cre = (Creature) super.clone();
        }catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return cre;
    }
}

class Grass extends Creature {
    Grass() {
        name = "🍀";
    }
}

class Huluwa_Entity extends Creature {
    static int count = 1;
    int rank;

    Huluwa_Entity() {
        rank = count++;
        name = "\uD83D\uDC76";
    }
}

class Scorpion_Entity extends Creature {
    Scorpion_Entity() {
        name = "\uD83E\uDD82";
    }
}

class Grandpa_Entity extends Creature {
    Grandpa_Entity() {
        name = "\uD83C\uDF85";
    }
}

class Snake_Entity extends Creature {
    Snake_Entity() {
        name = "\uD83D\uDC0D";
    }
}

package nju.java;

import java.util.ArrayList;
import java.util.Random;

public enum Format {
    heyileft("@...\n.@..\n..@.\n...@\n..@.\n.@..\n@..."),
    heyiright("...@\n..@.\n.@..\n@...\n.@..\n..@.\n...@"),
    yanxing("@......\n.@.....\n..@....\n...@...\n....@..\n.....@.\n......@"),
    henge(".@\n@.\n.@\n@.\n.@\n@.\n.@"),
    changshe("@\n@\n@\n@\n@\n@\n@");

    private final String text;
    private static final ArrayList<Format> depot= new ArrayList<Format>();
    static {
        depot.add(heyileft);
        depot.add(heyiright);
        depot.add(yanxing);
        depot.add(henge);
        depot.add(changshe);
    }

    Format(final String text){
        this.text=text;
    }
    @Override
    public String toString(){
        return text;
    }

    static public Format randFormat(){
        Random rd = new Random();
        int i = rd.nextInt(depot.size());
        return depot.get(i);
    }

}

package nju.java;

import java.util.ArrayList;

public enum Hulu {
    yi("大娃"),
    er("二娃"),
    san("三娃"),
    si("四娃"),
    wu("五娃"),
    liu("六娃"),
    qi("七娃");

    private final String text;
    private static final ArrayList<Hulu> depot= new ArrayList<Hulu>();
    static{
        depot.add(yi);
        depot.add(er);
        depot.add(san);
        depot.add(si);
        depot.add(wu);
        depot.add(liu);
        depot.add(qi);
    }

    Hulu(final String text){
        this.text=text;
    }
    @Override
    public String toString(){
        return text;
    }

    static public Hulu newHulu(int id){
        return depot.get(id-1);
    }
}

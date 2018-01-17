package nju.java;

public class Xiaolouluo1 extends Xiaolouluo {

    private int index;

    public Xiaolouluo1(int x, int y, Field field, int index) {
        super(x, y, field, 1);
        this.index = index;
    }

    public String toString(){
        return "Xiaolouluo1\t" + index;
    }

}

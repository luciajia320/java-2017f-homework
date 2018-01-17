package nju.wz.creature;

import nju.wz.position.Field;

public class Shejing extends Monster {

    public Shejing(Field field, int id) {
        super(field);
        setFightPower(20.0);
        setName("蛇精");
        setID(id);
        setImageName("shejing.jpg");
    }

    @Override
    public void report() {
        System.out.print("蛇 ");
    }
}

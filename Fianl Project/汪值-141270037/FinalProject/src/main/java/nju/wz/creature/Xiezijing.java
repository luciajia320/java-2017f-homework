package nju.wz.creature;

import nju.wz.position.Field;

public class Xiezijing extends Monster {

    public Xiezijing(Field field, int id) {
        super(field);
        setFightPower(15.0);
        setName("蝎子精");
        setID(id);
        setImageName("xiezijing.jpg");
    }

    @Override
    public void report() {
        System.out.print("蝎 ");
    }

}

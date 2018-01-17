package nju.wz.creature;


import nju.wz.position.Field;

public class Xiaoloulou extends Monster {

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;

    public int getID() {
        return ID;
    }

    public Xiaoloulou(Field field, int id) {
        super(field);
        setName("喽喽"+id);
        setFightPower(2.0);
        ID = id;
        setImageName("lou.jpg");
    }

    @Override
    public void report() {
        System.out.print("喽 ");
    }

}

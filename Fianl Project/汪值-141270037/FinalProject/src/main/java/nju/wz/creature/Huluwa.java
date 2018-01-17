package nju.wz.creature;

import nju.wz.position.Field;
import nju.wz.position.Position;

public class Huluwa extends GoodPerson implements Creature {

    public static final String[] names = {"大", "二", "三", "四", "五", "六", "七"};
    public static final String[] colors = {"红", "橙", "黄", "绿", "青", "蓝", "紫"};
    public static final double[] powers = {9.0, 5.0, 7.0, 10.0, 7.0, 9.0, 12};

    private int ID;
    private String color;
    private Position position;

    public Huluwa(Field field, int id) {
        super(field);
        setName(names[id]);
        this.ID = id;
        this.color = colors[id];
        setFightPower(powers[id]);
        setImageName("huluwa.jpg");
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void report() {
        System.out.print(this.getName() + " ");
    }

}



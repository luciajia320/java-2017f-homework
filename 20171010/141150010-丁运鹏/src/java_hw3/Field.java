package java_hw3;

import java.util.*;

class Field {
    private int height;
    private int width;
    private Vector<Vector<Field_Unit>> field;

    Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Vector<Vector<Field_Unit>>(height);

        for(int i = 0; i < height; i++) {
            Vector<Field_Unit> unit = new Vector<>(width);
            for(int j = 0; j < width; j++)
                unit.add(new Field_Unit());
            field.add(unit);
        }
    }

    void print_field() {
        System.out.println(field.isEmpty());
        for(Vector<Field_Unit> units: field) {
            for(Field_Unit unit: units) {
                unit.print_field_unit();
            }
            System.out.print("\n");
        }
    }

    void insert_formation(Formation formation) {
        Vector<Field_Unit> units = formation.getField_units();
        for(Field_Unit entity: units) {
            int x = entity.getX(), y = entity.getY();
            field.get(x).get(y).set_creature(entity.getCreature());
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

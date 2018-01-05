package java_hw3;

import java.util.*;
import java.lang.*;

abstract class Formation {
    Location border;

    Vector<Field_Unit> field_units;

    Formation(int x, int y) {
        this.border = new Location(x, y);
    }

    public Location getBorder() {
        return border;
    }

    public Vector<Field_Unit> getField_units() {
        return field_units;
    }
}

class Snake_formation extends Formation {

    Snake_formation(int border_x, int border_y, Vector<? extends Creature> entities) {
        super(border_x, border_y);

        int size = entities.size();

        field_units = new Vector<>(size);

        int x = border_x;
        int y = border_y;
        for(int i = 0; i < size; i++) {
            field_units.add(new Field_Unit());
            field_units.get(i).set_location(x++, y);
            field_units.get(i).set_creature(entities.get(i));
        }
    }

    public void set_creature(Vector<? extends Creature> entities) {

        for(int i = 0; i < field_units.size(); i++) {
            field_units.get(i).set_creature(entities.get(i));
        }
    }
}

class Goose_formation extends Formation {

    Goose_formation(int border_x, int border_y, Creature entity) {
        super(border_x, border_y);

        int size = 6;
        int x = border_x, y = border_y;
        field_units = new Vector<>(size);

        try {
            for(int i = 0; i < size; i++) {
                field_units.add(new Field_Unit());
                field_units.get(i).set_location(x++, y++);
                field_units.get(i).set_creature(entity.clone());
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class Crane_formation extends Formation {
    Crane_formation(int border_x, int border_y, Creature entity) {
        super(border_x, border_y);

        int size = 9;
        int x = border_x, y = border_y;
        field_units = new Vector<>(size);

        try {
            int i = 0;
            for(; i <= size / 2; i++) {
                field_units.add(new Field_Unit());
                field_units.get(i).set_location(x--, y--);
                field_units.get(i).set_creature(entity.clone());
            }
            x = border_x - 1;
            y = border_y + 1;
            for(; i < size; i++) {
                field_units.add(new Field_Unit());
                field_units.get(i).set_location(x--, y++);
                field_units.get(i).set_creature(entity.clone());
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

class Single_formation extends Formation {
    Single_formation(int border_x, int border_y, Creature entity) {
        super(border_x, border_y);
        field_units = new Vector<>(1);
        field_units.add(new Field_Unit());
        field_units.get(0).set_location(border_x, border_y);
        field_units.get(0).set_creature(entity);
    }
}
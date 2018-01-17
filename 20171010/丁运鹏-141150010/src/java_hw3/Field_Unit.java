package java_hw3;

class Field_Unit {
    Location location;
    Creature creature;

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    Field_Unit() {
        creature = new Grass();
        location = new Location();
    }

    Field_Unit(Creature cre) {
        creature = cre;
    }

    void set_location(Location loc) {
        location = loc;
    }

    public Creature getCreature() {
        return creature;
    }

    void set_location(int x, int y) {
        location.setX(x);
        location.setY(y);

    }

    void set_creature(Creature cre) {
        creature = cre;
    }

    void print_field_unit() {
        creature.display();
    }
}

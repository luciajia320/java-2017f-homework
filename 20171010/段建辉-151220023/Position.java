class Position {
    private int row, col;
    private creature Creature;
    Position(creature Creature, int row, int col) {
        this.row = row;
        this.col = col;
        this.Creature = Creature;
    }

    creature getCreature() {
        return Creature;
    }

    void setCreature(creature another) {
        this.Creature = another;
    }

    int getROW() {
        return this.row;
    }

    int getCOL() {
        return this.col;
    }
}

import javafx.geometry.Pos;

class Position {
    private int row, col;
    private creature Creature;
    Position(int row, int col) {
        this.row = row;
        this.col = col;
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

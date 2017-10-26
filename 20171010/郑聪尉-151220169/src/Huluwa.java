/**
 * This class defines Huluwa.
 */

public class Huluwa extends FightMan {
    private Color color;

    public Huluwa(CHARACTER character, Color color, int row, int col) {
        super(row, col);
        this.color = color;
        this.row = row;
        this.col = col;
        this.character = character;
    }
}

enum Color{
    Chi, Cheng, Huang, Lv, Qing, Lan, Zi
};

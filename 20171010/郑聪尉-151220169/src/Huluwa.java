public class Huluwa extends FightMan implements Comparable {
    private Color color;
    private Seniority seniority;

    Huluwa(CHARACTER character, Color color, int row, int col, Seniority seniority) {
        super(row, col, character);
        this.color = color;
        this.row = row;
        this.col = col;
        this.character = character;
        this.seniority = seniority;
    }

    public Seniority getSeniority(){
        return seniority;
    }

    @Override
    public boolean biggerThan(Huluwa object){
        return this.getSeniority().ordinal() > ((Huluwa) object).getSeniority().ordinal();
    }

}

enum Color{
    Chi, Cheng, Huang, Lv, Qing, Lan, Zi
};

enum Seniority{
    First, Second, Third, Fourth, Fifth, Sixth, Seventh
};

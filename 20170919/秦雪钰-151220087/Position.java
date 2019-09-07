

public class Position {
    private int number; //其序号
    private Creature sitter; //上面的葫芦娃
    public void setSitter(Creature sitter)
    {
        this.sitter=sitter;
    }
    public Creature getSitter()
    {
        return sitter;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public Position(int number)
    {
        super();
        this.number=number;
    }
}

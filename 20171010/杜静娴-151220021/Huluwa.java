public class Huluwa implements Creature, Comparable{

    static final String []FACE = {"🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻"};
    private String face;
    private COLOR color;
    private SENIORITY seniority;
    private Position position;

    private int posX;
    private int posY;

    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getFace() { return face; }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public void setPos(int x, int y) {
        ;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    Huluwa(COLOR color, SENIORITY seiority) {
        this.color = color;
        this.seniority = seiority;
        for(int i = 0; i < 7; i ++){
            if(color == COLOR.values()[i]) {
                this.face = FACE[i];
                break;
            }
        }
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() {
        return face;
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }



}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}

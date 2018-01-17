public class Huluwa implements Creature, Comparable{

    private COLOR color;
    private SENIORITY seniority;
    private Position<Creature> position;
    private String name;

    public COLOR getColor() {
        return color;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }


    @Override
    public Position<Creature> getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position<Creature> position) {
        this.position = position;
        switch(this.color) {
            case 赤:
                name = "🍎";break;
            case 橙:
                name = "🍊";break;
            case 黄:
                name = "🍋";break;
            case 绿:
                name = "🍏";break;
            case 青:
                name = "🌎";break;
            case 蓝:
                name = "💧";break;
            case 紫:
                name = "🍆";break;
        }
        position.setHolder(this);
    }

    Huluwa(COLOR color, SENIORITY seiority) {
        this.color = color;
        this.seniority = seiority;

    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        return this.seniority.toString() + "(" + this.color.toString() + ")@" + this.position.getX() + ","+this.position.getY()+";";
    }

    @Override
    public void show() {
        System.out.print(name);
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

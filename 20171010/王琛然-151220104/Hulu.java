enum Color {
    红, 橙, 黄, 绿, 青, 蓝, 紫
}

enum Rank {
    老大, 老二, 老三, 老四, 老五, 老六, 老七
}

public class Hulu implements Creature, Comparable {
    private Rank name;
    private Color color;
    private Position position;


    public Hulu(Color color, Rank name) {
        this.name = name;
        this.color = color;
    }

    public Rank getName(){
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void print_name() {
        System.out.println(name);
    }

    public void print_color() {
        System.out.println(color);
    }



    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        position.setHolder(this);
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Hulu)
            return this.getName().ordinal()> ((Hulu) brother).getName().ordinal();
        else
            return false;
    }

    @Override
    public void report() {
        System.out.println(this.toString());
    }


    @Override
    public void reportName(){
        System.out.print(name);
    }


    @Override
    public String toString(){
        return this.name.toString() + "(" + this.color.toString() + ")at[" + this.position.getX() + "][" + this.position.getY() + "]";
    }

}

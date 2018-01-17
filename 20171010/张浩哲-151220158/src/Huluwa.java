enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}


public class Huluwa implements Creature, Comparable{

    private COLOR color;
    private SENIORITY seniority;
    private Position position;

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
    public void setPosition(Position position) {
        this.position = position;
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
        return this.seniority.toString() + "(" + this.color.toString() + ")@" + this.position.coordinate.getX()+","+this.position.coordinate.getY() + ";";
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }
    @Override
    public char getCoin(){
        String temp= seniority.toString();
        return temp.charAt(0);
    }
}


class Xiezijing implements Creature{
    private Position position;
    private String name;
    Xiezijing(){
        this.name = "蝎";
        //setPosition(new Position(new Coordinate(-1,-1)));
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
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        if(position == null)
            return "";
        return this.name  + "@" + this.position.coordinate.getX()+","+this.position.coordinate.getY() + ";";
    }

    @Override
    public char getCoin(){
        return '蝎';
    }
}

class Yeye implements Creature{
    private Position position;
    private String name;
    Yeye(){
        this.name = "爷";
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
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        if(position == null)
            return "";
        return this.name  + "@" + this.position.coordinate.getX()+","+this.position.coordinate.getY() + ";";
    }
    @Override
    public char getCoin(){
        return '爷';
    }

}

class Louluo implements Creature{
    private Position position;
    private String name;
    Louluo(){
        this.name = "喽";
    }
    Louluo(Coordinate temp){
        this.name = "喽";
        position.coordinate = temp;
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
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        if(position == null)
            return "";
        return this.name  + "@" + this.position.coordinate.getX()+","+this.position.coordinate.getY() + ";";
    }
    @Override
    public char getCoin(){
        return '喽';
    }

}
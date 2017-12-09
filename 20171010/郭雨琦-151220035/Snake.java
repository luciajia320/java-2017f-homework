public class Snake implements Creature {
    private Position position;
    String icon;

    public Snake(String icon){
        this.icon = icon;
    }
    @Override
    public String getIcon(){
        return icon;
    }
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        if(position != null) {
            position.setHolder(this);
        }
    }

    @Override
    public String toString() { return "蛇"; }

    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public void cheer(){ System.out.print("蛇精：蝎子精小喽啰加油！\n");}

}
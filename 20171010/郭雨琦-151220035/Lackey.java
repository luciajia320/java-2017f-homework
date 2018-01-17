public class Lackey implements Creature {
    private Position position;
    String icon;
    public Lackey(String icon){
        this.icon = icon;
    }
    @Override
    public String getIcon(){
        return icon;
    }
    @Override
    public void cheer(){}
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
    public String toString() { return "啰"; }

    public void report() {
        System.out.print(this.toString());
    }

}
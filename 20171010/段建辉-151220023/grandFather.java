public class grandFather implements creature {
    private int rank;
    String name;
    private Position position;
    grandFather() {
        rank = 8;
        name = "爷";
    }

    @Override
    public void talkSomething() {
        System.out.println("葫芦娃加油V(^_^)V！");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getRank() {
        return this.rank;
    }
    @Override
    public void setPosition(Position position){
        this.position = position;
        this.position.setCreature(this);
    }
    @Override
    public Position getPosition(){
        return this.position;
    }
}

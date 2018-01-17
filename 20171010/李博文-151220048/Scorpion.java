public class Scorpion implements Creature{
    private Position position;
    private String name = "蝎";
    @Override
    public void report() {
        System.out.print("🦂 ");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        if (this.position != null)
            this.position.setHolder(this);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

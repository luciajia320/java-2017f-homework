public class Grandpa implements Creature{
    private String name;
    public Grid grid;
    private FormatCommander formatCommander;

    public Grandpa(){
        name="爷爷";
        formatCommander=new FormatCommander();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void report() {
        System.out.println("I'm Grandpa!");
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void arrowFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：锋矢阵！");
        formatCommander.arrowFormat(queue, start, planar);
    }

    public void chongeFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：衝轭阵！");
        formatCommander.chongeFormat(queue, start, planar);
    }

    public void lineFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：长蛇阵！");
        formatCommander.lineFormat(queue, start, planar);
    }

    public void yanxingFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：雁行阵！");
        formatCommander.yanxingFormat(queue, start, planar);
    }

    public void heyiFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：鹤翼阵！");
        formatCommander.heyiFormat(queue, start, planar);
    }

}

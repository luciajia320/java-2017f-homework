public class Grandpa implements Creature{
    private String name;
    public Grid grid;

    public Grandpa(){
        name="爷";
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

    public void arrowFormation(CreatureQueue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：锋矢阵！");

    }

    public void chongeFormation(CreatureQueue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：衝轭阵！");
    }

    public void lineFormation(CreatureQueue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：长蛇阵！");
    }

    public void yanxingFormation(CreatureQueue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：雁行阵！");
    }

    public void heyiFormation(CreatureQueue queue, Coordinate start, Planar planar){
        System.out.println("爷爷：鹤翼阵！");
    }

}

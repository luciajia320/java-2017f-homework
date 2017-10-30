public class Snake extends Villain {
    private NAME yourName;
    //private LEVEL yourlevel;
    private Grid grid;
    private FormatCommander formatCommander;

    public Snake(NAME name,LEVEL level) {
        this.yourName=name;
        this.yourLevel=level;
        formatCommander=new FormatCommander();
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
        grid.setHolder(this);
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() {
        return this.yourName.toString() +this.yourLevel.toString()+ "@grid(" + grid.getX() + "," + grid.getY() + ")";
    }

    @Override
    public String getName() {
        return "蛇精";
    }

    public void arrowFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("蛇精：锋矢阵！");
        formatCommander.arrowFormat(queue, start, planar);
    }

    public void chongeFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("蛇精：衝轭阵！");
        formatCommander.chongeFormat(queue, start, planar);
    }

    public void lineFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("蛇精：长蛇阵！");
        formatCommander.lineFormat(queue, start, planar);
    }

    public void yanxingFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("蛇精：雁行阵！");
        formatCommander.yanxingFormat(queue, start, planar);
    }

    public void heyiFormation(Queue queue, Coordinate start, Planar planar){
        System.out.println("蛇精：鹤翼阵！");
        formatCommander.heyiFormat(queue, start, planar);
    }
    enum NAME {
        金蛇精(1),青蛇精(2);
        private int sNum =0;

        private NAME(int _sNum) { this.sNum = _sNum;}

        public int toNumber() {
            return this.sNum;
        }

    }
}

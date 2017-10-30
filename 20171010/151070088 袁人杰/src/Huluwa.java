public class Huluwa implements Creature,Comparation {
    private SENIORITY seniority;
    private Grid grid;//葫芦娃的位置
    private COLOUR colour;//葫芦娃的颜色


    public Huluwa(COLOUR colour,SENIORITY seniority){
        this.colour = colour;
        this.seniority = seniority;
    }

    public void setColor(COLOUR colour) {
        this.colour = colour;
    }

    public COLOUR getColour() {
        return colour;
    }

    public SENIORITY getSeniority() {
        return seniority;
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public void setGrid(Grid grid) {
        this.grid = grid;
        grid.setHolder(this);
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString() {
        return "老" + this.seniority.toString() +" ";
    }

    @Override
    public boolean biggerThan(Comparation bro) {

        if (bro instanceof Huluwa)
            return this.getSeniority().ordinal() > ((Huluwa) bro).getSeniority().ordinal();
        else
            return false;
    }

    @Override
    public String getName() {
        return "葫芦";
    }

    enum COLOUR {
        RED(1),ORANGE(2),YELLOW(3),GREEN(4),BLUE(5),INDIGO(6),VIOLET(7);

        private int cNum=0;

        private COLOUR(int _cNum) {
            this.cNum=_cNum;
        }

        public int toNumber() {
            return this.cNum;
        }

    }

    enum SENIORITY {
        一(1),二(2),三(3),四(4),五(5),六(6),七(7);

        private int sNum=0;

        private SENIORITY(int _sNum) {
            this.sNum=_sNum;
        }

        public int toNumber() {
            return this.sNum;
        }
    }

    public static void main(String[] args) {
        Huluwa[] bros = new Huluwa[7];
        for (int i=0;i<bros.length;i++){
            bros[i] = new Huluwa(Huluwa.COLOUR.values()[i], Huluwa.SENIORITY.values()[i]);
        }

        Queue huluQueue =new Queue(bros.length);
        for(int i=0;i<bros.length;i++){
            huluQueue.enQueue(bros[i]);
        }

        try {
            new HuluShuffle().arrRandom(bros);
        }
        catch (NullPointerException e){
            System.out.println("Exception"+e);
        }

        for (int i=0;i<bros.length;i++){
            huluQueue.getGrid(i).getHolder().report();
        }
    }
}

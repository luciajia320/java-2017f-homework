public class Formation {
    protected String formName = "";

    public int getX() {
        return X;
    }

    protected int X;

    public int getY() {
        return Y;
    }

    protected int Y;

    public int getPosX() {
        return posX;
    }

    protected int posX;

    public int getPosY() {
        return posY;
    }

    public void setPos(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    protected int posY;

    public String[][] tostring() {
        String [][]printContent = new String[X][Y];
        for(int i = 0; i < X; i ++){
            for(int j = 0; j < Y; j ++){
                printContent[i][j] = content[i][j].toString();
            }
        }
        return printContent;
    }

    protected Creature[][] content = new Creature[1][1];

    public final Creature empty = new Nobody();

    public String getName() {
        return formName;
    }

    public Formation(int x, int y, String name){
        formName = name;
        X = x;
        Y = y;
        posX = 0;
        posY = 0;
        content = new Creature[x][y];
        for(int i = 0; i < X; i++){
            for(int j = 0; j < Y; j++) {
                content[i][j] = empty;
            }
        }
    }
}

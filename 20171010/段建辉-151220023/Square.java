import java.util.Random;

public class Square {
    private static final int N = 15;
    Position[][] positions = new Position[N][N];
    creature[][] creatures = new creature[N][N];

    Position[][] getPositions() {
        return positions;
    }

    private void print() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(positions[i][j] == null)
                    System.out.printf("\t");
                else
                    System.out.printf("%s\t",positions[i][j].getCreature().getName());
            }
            System.out.println();
        }
    }
    private void initHuluwas(Square square, int row, int col) {
        if(col + 7 >= N)
            System.out.println("超过了");
        square.creatures[row][col] = new Dawa();
        square.creatures[row][col+3] = new Erwa();
        square.creatures[row][col+2] = new Sanwa();
        square.creatures[row][col+5] = new Siwa();
        square.creatures[row][col+4] = new Wuwa();
        square.creatures[row][col+1] = new Liuwa();
        square.creatures[row][col+6] = new Qiwa();

        for(int i = 0; i < 7; i++)
        {
            square.positions[row][col+i] = new Position(row, col+i);
            square.creatures[row][col + i].setPosition(square.positions[row][col+i]);
        }

    }

    private void sortHuluBros(int row, int start){
        ranksort S = new ranksort();
        S.Sort(creatures[row], positions[row], start, start + 7);
    }
    private void initMonsters(Square square, int left, int right, int up, int down, formationID ID) {
        Formation formation1 = new Formation(square, left,right, up, down);
        formation1.setFormation(ID);
    }

    private void reshapeMonsters() {
        for (int i = 4; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (positions[i][j] != null) {
                    positions[i][j] = null;
                    creatures[i][j] = null;
                }
            }
        }
    }

    private void setCheers() {
        creatures[3][7] = new grandFather();
        positions[3][7] = new Position(3, 7);
        creatures[3][7].setPosition(positions[3][7]);

        creatures[5][7] = new Snake();
        positions[5][7] = new Position(5, 7);
        creatures[5][7].setPosition(positions[5][7]);
    }
    public static void main(String arg[]) {
        Square square = new Square();
        int huluBroStartPosCol = 4, huluBroStartPosRow = 1;

        square.initHuluwas(square, huluBroStartPosRow, huluBroStartPosCol);

        System.out.print("Initialize HuluBros:");
        for(int i = huluBroStartPosCol; i < huluBroStartPosCol+7; i++)
            System.out.printf("%s\t", square.positions[huluBroStartPosRow][i].getCreature().getName());
        System.out.println();

        square.sortHuluBros(huluBroStartPosRow, huluBroStartPosCol);

        square.setCheers();
        square.initMonsters(square, 5,9,8,12, formationID.YULIN);
        System.out.println("The first form:------------");
        square.print();

        square.reshapeMonsters();
        square.setCheers();
        square.initMonsters(square, 3,11,7,12, formationID.YANYUE);
        System.out.println("The second form:------------");
        square.print();
    }
}

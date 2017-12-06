/*
TODO:
* 1、用容器重新实现葫芦娃
* 2、加入
* */

import java.util.*;

public class Square {
    private static final int N = 15;
    public ArrayList<Position>[][] positions = new ArrayList[N][N];
    //Position[][] positions = new Position[N][N];
    //creature[][] positions = new creature[N][N];
    Square() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                positions[i][j] = new ArrayList();
    }
    Position getThisPositions(int row, int col) {
        return positions[row][col].get(0);
    }

    private void print(int left, int right, int up, int down) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i >= up - 1 && i <= down + 1 && j >= left - 1 && j <= right + 1 && positions[i][j].isEmpty())
                    System.out.print("🐾\t");
                else if(positions[i][j].isEmpty())
                    System.out.printf("🌴\t");
                else
                    System.out.printf("%s\t",positions[i][j].get(0).getCreature().getName());
            }
            System.out.println();
        }
    }

    private void initHuluwas(Square square, int row, int col) {
        if (col + 7 >= N)
            System.out.println("超过了");
        square.positions[row][col].add(new Position(new Dawa(), row, col));
        square.positions[row][col + 3].add(new Position(new Erwa(), row, col));
        square.positions[row][col + 2].add(new Position(new Sanwa(), row, col));
        square.positions[row][col + 5].add(new Position(new Siwa(), row, col));
        square.positions[row][col + 4].add(new Position(new Wuwa(), row, col));
        square.positions[row][col + 1].add(new Position(new Liuwa(), row, col));
        square.positions[row][col + 6].add(new Position(new Qiwa(), row, col));
    }

    private void sortHuluBros(int row, int start){
        ranksort S = new ranksort();
        S.Sort(positions[row], start, start + 7);
    }

    private void initMonsters(Square square, int left, int right, int up, int down, formationID ID) {
        Formation formation1 = new Formation(square, left,right, up, down);
        formation1.setFormation(ID);
    }

    private void reshapeMonsters() {
        for (int i = 6; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!positions[i][j].isEmpty()) {
                    positions[i][j] = new ArrayList<>();
                }
            }
        }
    }

    private void setCheers() {
        positions[3][7] = new ArrayList<>();
        positions[5][7] = new ArrayList<>();
        positions[3][7].add(new Position(new grandFather(), 3, 7));
        positions[5][7].add(new Position(new Snake(),5, 7));
    }

    public static void main(String arg[]) {
        Square square = new Square();
        int huluBroStartPosCol = 4, huluBroStartPosRow = 1;

        square.initHuluwas(square, huluBroStartPosRow, huluBroStartPosCol);

        System.out.print("Initialize HuluBros:");
        for(int i = huluBroStartPosCol; i < huluBroStartPosCol+7; i++) {
            System.out.printf("%s\t", square.positions[huluBroStartPosRow][i].get(0).getCreature().getName());
        }
        System.out.println();

        square.sortHuluBros(huluBroStartPosRow, huluBroStartPosCol);

        square.setCheers();
        square.initMonsters(square, 5,9,8,12, formationID.YULIN);
        System.out.println("The first form:------------");
        square.print(5,9,8,12);

        square.reshapeMonsters();
        square.setCheers();
        square.initMonsters(square, 3,11,7,12, formationID.YANYUE);
        System.out.println("The second form:------------");
        square.print(3,11,7,12);
    }
}

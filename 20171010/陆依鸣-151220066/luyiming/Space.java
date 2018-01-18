package luyiming;

import luyiming.zhenfa.*;
import luyiming.creature.*;

public class Space {
  private int row, col;

  private Position[][] positions;
  private Huluwa[] huluwas;
  private Minion[] minions;
  private Scorpion scorpion;
  private Snake snake;
  private OldMan yeye;

  public Space(int row, int col) {
    this.row = row;
    this.col = col;

    positions = new Position[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        positions[i][j] = new Position(i, j);
      }
    }

    huluwas = new Huluwa[7];
    for (int i = 0; i < huluwas.length; i++) {
      huluwas[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
    }

    minions = new Minion[20];
    for (int i = 0; i < minions.length; i++) {
      minions[i] = new Minion(i);
    }
    scorpion = new Scorpion();
    snake = new Snake();
    yeye = new OldMan();
  }

  @Override
  public String toString() {
    String res = "葫芦娃: number, 老爷爷: L, 喽啰: *, 蝎子精: X, 蛇精: S\n";
    for (int i = 0; i < row; i++) {
      res += "|";
      for (int j = 0; j < col; j++) {
        if (!positions[i][j].isEmpty()) {
          res += positions[i][j].getCreature().symbol() + "|";
        } else {
          res += " |";
        }
      }
      res += "\n";
    }
    res += "\n";
    return res;
  }

  public int getRow() { return row; }

  public int getColumn() { return col; }

  public Position[][] getPositions() { return positions; }

  public Huluwa[] getHuluwas() { return huluwas; }

  public Minion[] getMinions() { return minions; }

  public Scorpion getScorpion() { return scorpion; }

  public Snake getSnake() { return snake; }

  public OldMan getOldMan() { return yeye; }

  public boolean inSpace(int x, int y) {
    if (x >= 0 && x < row && y >= 0 && y < col)
      return true;
    else
      return false;
  }

  public static void main(String[] args) {
    Space space = new Space(14, 20);

    // 对葫芦娃施加长蛇阵法，葫芦娃呈长蛇阵一字排开
    new ChangSheZhen(4, 5).apply(space, space.getHuluwas());

    // 蝎子精领队
    space.getScorpion().lead(space.getPositions()[7][12]);

    // 小喽啰呈方円阵排开
    new FangYuanZhen(7, 15).apply(space, space.getMinions());

    // 老爷爷加油助威
    space.getOldMan().cheer(space.getPositions()[2][5]);

    // 蛇精加油助威
    space.getSnake().cheer(space.getPositions()[2][15]);

    // 打印局面
    System.out.print(space.toString());

    // 小喽啰变换阵法
    new YanYueZhen(7, 13).apply(space, space.getMinions());

    System.out.print(space.toString());
  }
}
package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 偃月阵

public class YanYueZhen implements ZhenFa {

  private int leftX, leftY;

  public YanYueZhen(int leftX, int leftY) {
    this.leftX = leftX;
    this.leftY = leftY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    if (creatures.length < 19)
      return;
    Position[][] positions = space.getPositions();
    int cnt = 0;
    for (int i = 0; i < 3; i++) {
      if (space.inSpace(leftX, leftY + i))
        creatures[cnt++].setPosition(positions[leftX][leftY + i]);
      if (space.inSpace(leftX - 1, leftY + i))
        creatures[cnt++].setPosition(positions[leftX - 1][leftY + i]);
      if (space.inSpace(leftX + 1, leftY + i))
        creatures[cnt++].setPosition(positions[leftX + 1][leftY + i]);
    }
    for (int i = 0; i < 2; i++) {
      if (space.inSpace(leftX - 2 - i, leftY + 2 + i))
        creatures[cnt++].setPosition(positions[leftX - 2 - i][leftY + 2 + i]);
      if (space.inSpace(leftX + 2 + i, leftY + 2 + i))
        creatures[cnt++].setPosition(positions[leftX + 2 + i][leftY + 2 + i]);
    }
    for (int i = 0; i < 3; i++) {
      if (space.inSpace(leftX - 2 - i, leftY + 3 + i))
        creatures[cnt++].setPosition(positions[leftX - 2 - i][leftY + 3 + i]);
      if (space.inSpace(leftX + 2 + i, leftY + 3 + i))
        creatures[cnt++].setPosition(positions[leftX + 2 + i][leftY + 3 + i]);
    }
  }
}
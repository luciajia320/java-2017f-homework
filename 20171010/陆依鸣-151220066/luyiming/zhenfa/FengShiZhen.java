package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 锋矢阵

public class FengShiZhen implements ZhenFa {

  private int upperX, upperY;

  public FengShiZhen(int upperX, int upperY) {
    this.upperX = upperX;
    this.upperY = upperY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    if (creatures.length < 12)
      return;

    Position[][] positions = space.getPositions();

    int cnt = 0;
    for (int i = 0; i < 6; i++) {
      if (space.inSpace(upperX + i, upperY))
        creatures[cnt++].setPosition(positions[upperX + i][upperY]);
    }

    for (int i = 1; i <= 3; i++) {
      if (space.inSpace(upperX + i, upperY - i))
        creatures[cnt++].setPosition(positions[upperX + i][upperY - i]);
      if (space.inSpace(upperX + i, upperY + i))
        creatures[cnt++].setPosition(positions[upperX + i][upperY + i]);
    }

  }
}
package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 雁行阵

public class YanXingZhen implements ZhenFa {

  private int upperX, upperY;

  public YanXingZhen(int upperX, int upperY) {
    this.upperX = upperX;
    this.upperY = upperY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    Position[][] positions = space.getPositions();
    for (int i = 0; i < creatures.length; i++) {
      if (space.inSpace(upperX + i, upperY - i))
        creatures[i].setPosition(positions[upperX + i][upperY - i]);
    }
  }
}
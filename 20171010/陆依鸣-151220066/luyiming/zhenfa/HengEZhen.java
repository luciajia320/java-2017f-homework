package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 衡轭阵

public class HengEZhen implements ZhenFa {

  private int upperX, upperY;

  public HengEZhen(int upperX, int upperY) {
    this.upperX = upperX;
    this.upperY = upperY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    Position[][] positions = space.getPositions();
    for (int i = 0; i < creatures.length; i++) {
      if (space.inSpace(upperX + i, upperY - (i % 2)))
        creatures[i].setPosition(positions[upperX + i][upperY - (i % 2)]);
    }
  }
}
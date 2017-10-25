package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 长蛇阵

public class ChangSheZhen implements ZhenFa {

  private int startX, startY;

  public ChangSheZhen(int startX, int startY) {
    this.startX = startX;
    this.startY = startY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    Position[][] positions = space.getPositions();
    for (int i = 0; i < creatures.length; i++) {
      if (space.inSpace(startX + i, startY))
        creatures[i].setPosition(positions[startX + i][startY]);
    }
  }
}
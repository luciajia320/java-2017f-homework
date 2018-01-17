package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 鹤翼阵

public class HeYiZhen implements ZhenFa {

  private int startX, startY;

  public HeYiZhen(int startX, int startY) {
    this.startX = startX;
    this.startY = startY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    int len1 = (creatures.length - 1) / 2;
    Position[][] positions = space.getPositions();

    if (space.inSpace(startX, startY))
      creatures[0].setPosition(positions[startX][startY]);

    int i = 1;
    for (; i <= len1; i++) {
      if (space.inSpace(startX - i, startY - i))
        creatures[i].setPosition(positions[startX - i][startY - i]);
    }

    for (; i < creatures.length; i++) {
      int newX = startX - (i - len1);
      int newY = startY + (i - len1);
      if (space.inSpace(newX, newY))
        creatures[i].setPosition(positions[newX][newY]);
    }
  }
}
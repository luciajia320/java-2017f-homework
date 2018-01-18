package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 方円阵

public class FangYuanZhen implements ZhenFa {

  private int midX, midY;

  public FangYuanZhen(int midX, int midY) {
    this.midX = midX;
    this.midY = midY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    if (creatures.length < 8)
      return;

    Position[][] positions = space.getPositions();

    int cnt = 0;
    if (space.inSpace(midX - 2, midY))
      creatures[cnt++].setPosition(positions[midX - 2][midY]);
    if (space.inSpace(midX + 2, midY))
      creatures[cnt++].setPosition(positions[midX + 2][midY]);
    if (space.inSpace(midX, midY - 2))
      creatures[cnt++].setPosition(positions[midX][midY - 2]);
    if (space.inSpace(midX, midY + 2))
      creatures[cnt++].setPosition(positions[midX][midY + 2]);
    if (space.inSpace(midX - 1, midY + 1))
      creatures[cnt++].setPosition(positions[midX - 1][midY + 1]);
    if (space.inSpace(midX + 1, midY + 1))
      creatures[cnt++].setPosition(positions[midX + 1][midY + 1]);
    if (space.inSpace(midX + 1, midY - 1))
      creatures[cnt++].setPosition(positions[midX + 1][midY - 1]);
    if (space.inSpace(midX - 1, midY - 1))
      creatures[cnt++].setPosition(positions[midX - 1][midY - 1]);
  }
}
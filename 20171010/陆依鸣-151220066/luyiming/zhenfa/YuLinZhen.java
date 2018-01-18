package luyiming.zhenfa;

import luyiming.Space;
import luyiming.Position;
import luyiming.creature.Creature;

// 鱼鳞阵

public class YuLinZhen implements ZhenFa {

  private int upperX, upperY;

  public YuLinZhen(int upperX, int upperY) {
    this.upperX = upperX;
    this.upperY = upperY;
  }

  @Override
  public void apply(Space space, Creature[] creatures) {
    Position[][] positions = space.getPositions();
    if (creatures.length > 0 && space.inSpace(upperX, upperY))
      creatures[0].setPosition(positions[upperX][upperY]);
    if (creatures.length > 1 && space.inSpace(upperX + 1, upperY + 1))
      creatures[1].setPosition(positions[upperX + 1][upperY + 1]);
    if (creatures.length > 2 && space.inSpace(upperX + 2, upperY - 2))
      creatures[2].setPosition(positions[upperX + 2][upperY - 2]);
    if (creatures.length > 3 && space.inSpace(upperX + 2, upperY))
      creatures[3].setPosition(positions[upperX + 2][upperY]);
    if (creatures.length > 4 && space.inSpace(upperX + 2, upperY + 2))
      creatures[4].setPosition(positions[upperX + 2][upperY + 2]);
    if (creatures.length > 5 && space.inSpace(upperX + 3, upperY - 3))
      creatures[5].setPosition(positions[upperX + 3][upperY - 3]);
    if (creatures.length > 6 && space.inSpace(upperX + 3, upperY - 1))
      creatures[6].setPosition(positions[upperX + 3][upperY - 1]);
    if (creatures.length > 7 && space.inSpace(upperX + 3, upperY + 1))
      creatures[7].setPosition(positions[upperX + 3][upperY + 1]);
    if (creatures.length > 8 && space.inSpace(upperX + 3, upperY + 3))
      creatures[8].setPosition(positions[upperX + 3][upperY + 3]);
    if (creatures.length > 9 && space.inSpace(upperX + 4, upperY))
      creatures[9].setPosition(positions[upperX + 4][upperY]);
  }
}
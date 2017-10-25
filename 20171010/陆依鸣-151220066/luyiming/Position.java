package luyiming;

import luyiming.creature.Creature;

public class Position {
  private int x, y;

  private Creature creature;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
    this.creature = null;
  }

  public void setCreature(Creature creature) {
    if (this.creature != null) {
      this.creature.releasePosition();
    }
    this.creature = creature;
  }

  public Creature getCreature() { return creature; }

  public void clearCreature() {
    if (creature != null)
      creature.releasePosition();
    creature = null;
  }

  public boolean isEmpty() { return creature == null; }

  public void setX(int x) { this.x = x; }

  public void setY(int y) { this.y = y; }

  public int getX() { return x; }

  public int getY() { return y; }
}
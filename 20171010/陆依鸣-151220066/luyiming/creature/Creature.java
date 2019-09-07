package luyiming.creature;

import luyiming.Position;

public abstract class Creature {
  protected Position position;

  public Creature() { position = null; }

  public void setPosition(Position position) {
    if(this.position !=null){
      this.position.clearCreature();
    }
    this.position = position;
    position.setCreature(this);
  }

  public Position getPosition() { return position; }

  public void releasePosition() { position = null; }

  public abstract void report();

  public abstract String name();

  public abstract String symbol();
}
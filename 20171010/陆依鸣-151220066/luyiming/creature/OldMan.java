package luyiming.creature;

public class OldMan extends Creature implements Cheerable {
  public OldMan() { super(); }

  @Override
  public void cheer(luyiming.Position position) {
    setPosition(position);
  }

  @Override
  public void report() {
    System.out.print(this.toString());
  }

  @Override
  public String toString() {
    if (position == null)
      return name() + "@-1,-1 ";
    else
      return name() + "@" + position.getX() + "," + position.getY() + " ";
  }

  @Override
  public String name() {
    return "老爷爷";
  }

  @Override
  public String symbol() {
    return "L";
  }
}
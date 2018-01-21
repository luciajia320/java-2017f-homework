package luyiming.creature;

public class Snake extends Monster implements Cheerable {
  public Snake() { super(); }

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
      return name() + "@-1, -1 ";
    else
      return name() + "@" + position.getX() + "," + position.getY() + " ";
  }

  @Override
  public String name() {
    return "蛇精";
  }

  @Override
  public String symbol() {
    return "S";
  }
}
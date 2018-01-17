package luyiming.creature;

public class Minion extends Monster {
  private int index;

  public Minion(int index) {
    super();
    this.index = index;
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
    return "喽啰#" + index;
  }

  @Override
  public String symbol() {
    return "*";
  }
}
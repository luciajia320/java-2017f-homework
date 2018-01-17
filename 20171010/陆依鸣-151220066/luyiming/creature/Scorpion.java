package luyiming.creature;

public class Scorpion extends Monster {
  public Scorpion() { super(); }

  public void lead(luyiming.Position position) { setPosition(position); }

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
    return "蝎子精";
  }

  @Override
  public String symbol() {
    return "X";
  }
}
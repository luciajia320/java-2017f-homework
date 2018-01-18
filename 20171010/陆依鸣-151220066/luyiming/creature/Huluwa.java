package luyiming.creature;

public class Huluwa extends Creature implements Comparable<Huluwa> {
  private COLOR color;
  private SENIORITY seniority;

  public Huluwa(COLOR color, SENIORITY seniority) {
    super();
    this.color = color;
    this.seniority = seniority;
  }

  @Override
  public void report() {
    System.out.print(this.toString());
  }

  @Override
  public String toString() {
    if (position == null)
      return name() + "@null ";
    else
      return name() + "@" + position.getX() + "," + position.getY() + " ";
  }

  @Override
  public String name() {
    return seniority.toString() + "(" + color.toString() + ")";
  }

  @Override
  public String symbol() {
    return String.format("%d", this.seniority.ordinal() + 1);
  }

  public SENIORITY getSeniority() { return seniority; }

  public COLOR getColor() { return color; }

  public int compareTo(Huluwa rhs) {
    return seniority.compareTo(rhs.seniority);
  }
}
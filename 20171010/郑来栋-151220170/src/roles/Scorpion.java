package roles;

public class Scorpion implements Creature {
    public final String name = "蝎子精";

    private Scorpion() { }

    private static Scorpion scorpion = new Scorpion();

    public static Scorpion getScorpion()
    {
        return scorpion;
    }

}

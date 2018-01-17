package roles;


public class OldMan implements Creature {

    public final String name = "老爷爷";

    private OldMan() { }

    private static OldMan oldMan = new OldMan();

    public static OldMan getOldMan()
    {
        return oldMan;
    }
}

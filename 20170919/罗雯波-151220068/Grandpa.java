
public class Grandpa {
    public static void main(String[] args) {
        SevenBrothers sevenBro = new SevenBrothers();
        sevenBro.lineUpRandomly();
        sevenBro.sort(SevenBrothers.SortType.BUBBLE,
                CalabashBoy.Attribute.NAME);
        sevenBro.report(CalabashBoy.Attribute.NAME);

        sevenBro.lineUpRandomly();
        sevenBro.sort(SevenBrothers.SortType.BIN_INSERT,
                CalabashBoy.Attribute.COLOR);
        sevenBro.report(CalabashBoy.Attribute.COLOR);
    }
}

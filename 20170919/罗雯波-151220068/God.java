
public class God {
    public static void main(String[] args) {
        SevenBrothers sevenBro = new SevenBrothers();

        sevenBro.lineUpRandomly();
        sevenBro.sort(SevenBrothers.SortType.BUBBLE,
                CalabashBoy.Attribute.RANK);
        sevenBro.report(CalabashBoy.Attribute.RANK);

        sevenBro.lineUpRandomly();
        sevenBro.sort(SevenBrothers.SortType.BIN_INSERT,
                CalabashBoy.Attribute.COLOR);
        sevenBro.report(CalabashBoy.Attribute.COLOR);
    }
}

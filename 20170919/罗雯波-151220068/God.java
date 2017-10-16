public class God {
    public static void main(String[] args) {
        Huluwa[] brothers = new Huluwa[7];
        for (int i = 0; i < 7; i ++) {
            brothers[i] = new Huluwa(COLOR.values()[i], SENIORITY.values()[i]);
        }
        Queue queue = new Queue(brothers, FORMATION.长蛇,
                new PosCoord(0, 0), ORIENTATION.WEST);

        queue.rollCall();

        queue.shuffle();
        queue.rollCall();
        queue.sort(SortType.BUBBLE);
        queue.rollCall();

        queue.shuffle();
        queue.rollCall();
        queue.sort(SortType.INSERTION);
        queue.rollCall();
    }
}

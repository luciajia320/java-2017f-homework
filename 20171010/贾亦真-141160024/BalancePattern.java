package hlw;

public class BalancePattern extends Pattern {
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        for (int i = 0; i<num; i++){
            result_array[i] = new Coord(i%2, i);
            System.out.println((result_array[i]).toString());
        }
        return result_array;
    }
}

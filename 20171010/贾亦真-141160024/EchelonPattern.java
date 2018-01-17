package hlw;

public class EchelonPattern extends Pattern{
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        for (int i = 0; i<num; i++){
            result_array[i] = new Coord(i, num-i);
        }
        return result_array;
    }
}

package hlw;

public class WingPattern extends Pattern {
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        for (int i =0; i < num; i++){
            if (i<= num/2){
                result_array[i] = new Coord(num/2-i, num/2-i);
            }
            else{
                result_array[i] = new Coord(num-i, i);
            }
        }
        return result_array;
    }
}

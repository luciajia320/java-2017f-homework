package hlw;

public class ArrowPattern extends Pattern{
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        int wing_num = (num - 2) / 3;
        int axis_num = num - wing_num * 2 ;
        for (int i = 0; i<num; i++){
            if ( i <axis_num){
                int k = i + 1;
                result_array[i] = new Coord(axis_num-k,wing_num);
            }
            else if (i<axis_num + wing_num){
                int k = i-axis_num;
                result_array[i] = new Coord(axis_num-wing_num + k -1, k);
            }
            else {
                int k = i-axis_num-wing_num;
                result_array[i] = new Coord(axis_num-wing_num + k -1, 2 * wing_num - k);
            }
        }
        return result_array;
    }
}

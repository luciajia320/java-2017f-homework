package hlw;

public class ScalePattern extends Pattern {
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        int i = 1;
        int layer_num = (int)Math.sqrt(num-1);
        int mod_num = num - 1 - layer_num * layer_num;
        for (int x = 0; x <layer_num && i<num; x++){
            for (int y = 0; y < (layer_num-x)*2-1; y++){
                result_array[i] = new Coord(x+1, y+x+1);
                i++;
            }
        }
        for (int x = 0; x <layer_num && i<num; x++){
            result_array[i] = new Coord(x+1,x );
            i++;
            if (i < num){
                result_array[i] = new Coord(x +1, (layer_num-x)*2 + x);
                i++;
            }
        }
        result_array[0] = new Coord(0,layer_num);
        return result_array;
    }
}

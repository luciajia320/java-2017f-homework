package hlw;

public class SquarePattern extends Pattern {
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        int seg_num = num / 4;
        int mod_num = num % 4;
        int key = 0;
        int i = 0;
        int current_y = 0;
        for (; current_y < 2*seg_num+1; current_y++){
            if (current_y==0){
                result_array[i++] = new Coord(seg_num, current_y);
            }
            else if (current_y<=seg_num){
                result_array[i++] = new Coord(seg_num-current_y, current_y);
                result_array[i++] = new Coord(seg_num+current_y, current_y);
                if (current_y == seg_num) key = i-1;
            }
            else if (current_y==2*seg_num){
                result_array[i++] = new Coord(seg_num, current_y);
            }
            else{
                result_array[i++] = new Coord(current_y-seg_num, current_y);
                result_array[i++] = new Coord(3*seg_num-current_y, current_y);
            }
        }
        System.out.println();
        current_y--;
        while (i <num){
            if(i==num-1){
                result_array[i] = new Coord(seg_num-1, current_y);
                i++;
            }
            else if(i==num-2){
                result_array[i] = new Coord(seg_num+1, current_y);
                i++;
            }
            else {
                result_array[i] = new Coord(seg_num - 1, 1);
                i++;
            }
        }
        Coord temp = result_array[0];
        result_array[0] = result_array[key];
        result_array[key] = temp;
        return result_array;
    }
}

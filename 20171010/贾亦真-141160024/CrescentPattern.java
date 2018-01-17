package hlw;

public class CrescentPattern extends Pattern{
    @Override
    public Coord[] get_basic_pattern(int num) {
        Coord[] result_array = new Coord[num];
        int key = 0;
        if (num <=16){
            int mooon_num = 2;
            int seg_num = num / 5;
            int straight_num = seg_num + (num-seg_num*5)/2;
            int i = 0;
            int current_y = seg_num-seg_num/2;
            while (i < straight_num + seg_num){
                if (i < seg_num/2){
                    result_array[i] = new Coord(seg_num/2-i, current_y);
                }
                else if (i < seg_num / 2 + straight_num) {
                    result_array[i] = new Coord(0, current_y);
                }
                else{
                    result_array[i] = new Coord(i-seg_num/2-straight_num+1, current_y);
                }
                i++;
                current_y++;
            }
            current_y = 0;
            while (i < num){
                if (current_y < seg_num){
                    result_array[i] = new Coord(seg_num-current_y+1, current_y);
                }
                else if (current_y < seg_num+ straight_num) {
                    if (current_y == seg_num + straight_num / 2){
                        key = i;
                    }
                    result_array[i] = new Coord(1, current_y);
                }
                else{
                    result_array[i] = new Coord(current_y-seg_num-straight_num + 2, current_y);
                }
                i++;
                current_y++;
            }
        }
        Coord temp = result_array[0];
        result_array[0] = result_array[key];
        result_array[key] = temp;
        return result_array;
    }
}

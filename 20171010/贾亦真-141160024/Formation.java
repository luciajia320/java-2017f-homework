package hlw;

public class Formation {
    public Coord[] Wing(int num){
        Coord[] result_array = new Coord[num];
        for (int i =0; i < num; i++){
            if (i<= num/2){
                result_array[i] = new Coord(i, i);
            }
            else{
                result_array[i] = new Coord(i, num-i-1);
            }
        }
        return result_array;
    }
    public Coord[] LongSnake(int num){
        Coord[] result_array = new Coord[num];
        for (int i = 0; i<num; i++){
            result_array[i] = new Coord(0, i);
        }
        return result_array;
    }
    public Coord[] Echelon(int num){
        Coord[] result_array = new Coord[num];
        for (int i = 0; i<num; i++){
            result_array[i] = new Coord(i, num-i);
        }
        return result_array;
    }
    public Coord[] Balance(int num){
        Coord[] result_array = new Coord[num];
        for (int i = 0; i<num; i++){
            result_array[i] = new Coord(i%2, i);
        }
        return result_array;
    }
    public Coord[] Arrow(int num){
        Coord[] result_array = new Coord[num];
        int wing_num = (num-2)%3;
        int axis_num = num - wing_num*2;
        for (int i = 0; i<num; i++){
            if ( i <axis_num){
                result_array[i] = new Coord(wing_num,i);
            }
            else if (i<axis_num + wing_num){
                int k = i-axis_num;
                result_array[i] = new Coord(k, wing_num-k);
            }
            else {
                int k = i-axis_num-wing_num;
                result_array[i] = new Coord(k + wing_num + 1, k+1);
            }
        }
        return result_array;
    }
    public Coord[] Crescent(int num){
        Coord[] result_array = new Coord[num];
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
                    result_array[i] = new Coord(1, current_y);
                }
                else{
                    result_array[i] = new Coord(current_y-seg_num-straight_num + 2, current_y);
                }
                i++;
                current_y++;
            }
        }
        return result_array;
    }
    public Coord[] Square(int num){
        Coord[] result_array = new Coord[num];
        int seg_num = num / 4;
        int mod_num = num % 4;
        int i = 0;
        int current_y = 0;
        for (; current_y < 2*seg_num+1; current_y++){
            if (current_y==0){
                result_array[i] = new Coord(seg_num, current_y);
                i++;
            }
            else if (current_y<=seg_num){
                result_array[i] = new Coord(seg_num-current_y, current_y);
                i++;
                result_array[i] = new Coord(seg_num+current_y, current_y);
                i++;
            }
            else if (current_y==2*seg_num){
                result_array[i] = new Coord(seg_num, current_y);
                i++;
            }
            else{
                result_array[i] = new Coord(current_y-seg_num, current_y);
                i++;
                result_array[i] = new Coord(current_y-seg_num, current_y);
                i++;
            }
        }
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
            else{
                result_array[i] = new Coord(seg_num-1, 1);
                i++;
            }
        }
        return result_array;
    }
    public Coord[] Scale(int num){
        Coord[] result_array = new Coord[num];
        int i = 1;
        int layer_num = (int)Math.sqrt(num-1);
        int mod_num = num - 1 - layer_num * layer_num;
        for (int y = layer_num-1; y >= 0 && i<num; y--){
            for (int x = 0; x < (y+1)*2-1; x++){
                result_array[i] = new Coord(layer_num-y+x, y);
                i++;
            }
        }
        System.out.println(i);
        for (int y = layer_num-1; y >= 0 && i<num; y--){
            result_array[i] = new Coord(layer_num-y-1, y);
            System.out.println(layer_num-y-1 + " " + y);
            i++;
            if (i < num){
                result_array[i] = new Coord(2*(y+1), y);
                i++;
            }
        }
        result_array[0] = new Coord(layer_num,layer_num);
        return result_array;
    }
}
package hlw;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Pattern {
    private Coord get_pattern_range(Coord[] coord_pattern){
        Coord range_coord = new Coord(0, 0);
        for ( int i = 0; i < coord_pattern.length; i++){
            if ( coord_pattern[i].x > range_coord.x) range_coord.x = coord_pattern[i].x;
            if ( coord_pattern[i].y > range_coord.y) range_coord.y = coord_pattern[i].y;
        }
        return range_coord;
    }
    public void Reform(CreatureQueue queue) {
        Creature[] creatures = queue.get_creatures();
        Position[][] space = queue.getSpace();
        Coord[] b_pattern = this.get_basic_pattern(creatures.length);
        Coord range_coord = get_pattern_range(b_pattern);
        int dev_y = queue.getBound_y1() + (queue.getBound_y2() - queue.getBound_y1())/2 - range_coord.y / 2;
        int dev_x = Math.abs((queue.getBound_x2() - queue.getBound_x1())/2) - range_coord.x / 2;
        java.util.Queue <Integer> control_q = new LinkedList<Integer>();
        for (int i = 0; i < creatures.length; i++) {
            control_q.offer(i);
        }
        int count = 0;
        while (!control_q.isEmpty() && count<=5 * creatures.length){
            count ++;
            int i = control_q.poll();
            int x, y;
            y = b_pattern[i].y + dev_y;
            if (queue.getBound_x1() < queue.getBound_x2()){
                x = queue.getBound_x1() + b_pattern[i].x + dev_x;
            }
            else{
                x = queue.getBound_x2() + (queue.getBound_x1() - queue.getBound_x2())/2 - b_pattern[i].x + dev_x;
            }
            if (space[y][x].get_avail()){
                creatures[i].set_pos(space[y][x]);
            }
            else{
                control_q.offer(i);
            }
        }
        while (!control_q.isEmpty()){
            int i = control_q.poll();
        }
    }
    public abstract Coord[] get_basic_pattern(int num);
}

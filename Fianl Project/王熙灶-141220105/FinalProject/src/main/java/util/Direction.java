package util;

public enum Direction {
        UP, DOWN, LEFT, RIGHT,
        UPLEFT, UPRIGHT,
        DOWNLEFT, DOWNRIGHT;

        public static Direction And(Direction d1, Direction d2) {
                if(d1 == UP) {
                        if(d2 == LEFT) return UPLEFT;
                        if(d2 == RIGHT) return UPRIGHT;
                }
                if(d1 == DOWN) {
                        if(d2 == LEFT) return DOWNLEFT;
                        if(d2 == RIGHT) return DOWNRIGHT;
                }
                return UP; // 实际上不会到达这里
        }
}
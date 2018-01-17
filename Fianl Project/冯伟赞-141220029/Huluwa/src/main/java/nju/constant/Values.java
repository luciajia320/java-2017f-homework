package nju.constant;

public class Values {
    public static class str {
        public static final String GOURD_BOY = "葫芦娃";
        public static final String GRANDPA = "爷爷";
        public static final String SNAKE = "蛇精";
        public static final String SCORPION = "蝎子精";
        public static final String CREEP = "小怪";
    }

    public static class id {
        public static final int DEAD = -1;
        public static final int NONE = 0;
        public static final int GOURD_BOY = 1;
        public static final int GRANDPA = 2;
        public static final int SNAKE = 3;
        public static final int SCORPION = 4;
        public static final int CREEP = 5;
    }

    public static enum AnsiColor {

        ANSI_RESET("\u001B[0m"),
        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m");

        private String color;

        AnsiColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}

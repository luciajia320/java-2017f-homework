package util;

public enum State {
    CHEER, WAIT, ATTACK, FIGHT, DEAD;

    @Override
    public String toString() {
        switch (this.ordinal()) {
            case 0: return "加油";
            case 1: return "对阵";
            case 2: return "冲锋";
            case 3: return "战斗";
            case 4: return "阵亡";
            default: return "";
        }
    }

    public static void main(String[] args) {
        State state = State.ATTACK;
        System.out.println(state.toString());
        System.out.println(state.name());
        state = State.valueOf("WAIT");
        System.out.println(state.toString());
        System.out.println(state.name());
    }
}
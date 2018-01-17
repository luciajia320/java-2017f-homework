package Scene;

public class Position<K> {
    private K holder = null;

    public K getHolder() {
        return holder;
    }

    public void setHolder(K holder) {
        this.holder = holder;
    }

    @Override public String toString() {
        if (holder == null)
            return "□";
        String className = holder.getClass().getSimpleName();
        if (className.equals("OldMan"))
            return "♠";
        else if (className.equals("Huluwa"))
            return "8";
        else if (className.equals("Snake"))
            return "◆";
        else if (className.equals("Scorpion"))
            return "◈";
        else
            return "◇";
    }
}

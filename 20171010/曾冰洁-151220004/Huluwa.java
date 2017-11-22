class Huluwa extends Creature implements Comparable{
    private int rank;
    private COLOR color;

    Huluwa(int rank, COLOR color) {
        this.rank = rank;
        this.color = color;
        this.name = "🍐";
    }

    public COLOR getColor() {
        return color;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean isOlderThan(Comparable huluwa){
        if (huluwa instanceof  Huluwa)
            return this.rank < ((Huluwa) huluwa).rank;
        else
            return false;
    }

    @Override
    public boolean isYoungerThan(Comparable huluwa) {
        if (huluwa instanceof Huluwa)
            return this.rank > ((Huluwa) huluwa).rank;
        else
            return false;
    }
}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum NAME{
    大娃, 二娃, 三娃, 四娃, 五娃, 六娃, 七娃
}

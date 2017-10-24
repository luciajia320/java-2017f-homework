public class Huluwa extends Creature implements Comparable{
    private COLOR color;
    private SENIORITY seniority;

    public Huluwa(COLOR color, SENIORITY seniority) {
        this.color = color;
        this.seniority = seniority;
    }

    @Override
    public void report() {
        System.out.printf("%s@%d;", this.toString(), position.getPosCoord().getX());
    }

    @Override
    public String toString() {
        return String.format("%s%s", seniority.name(), color.name());
    }

    @Override
    public boolean biggerThan(Comparable brother) {
        if (!(brother instanceof Huluwa)) {
            return false;
        }
        return seniority.ordinal() > ((Huluwa)brother).seniority.ordinal();
    }
}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}
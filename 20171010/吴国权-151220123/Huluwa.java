public class Huluwa extends Creature implements Comparable{

    private COLOR color;
    private SENIORITY seniority;

    public SENIORITY getSeniority() {
        return seniority;
    }

    Huluwa(COLOR color, SENIORITY seiority) {
        this.color = color;
        this.seniority = seiority;
    }

    @Override
    public String toString(){
        return this.seniority.toString();
    }

    @Override
    public boolean biggerThan(Comparable brother){

        if (brother instanceof  Huluwa)
            return this.getSeniority().ordinal()> ((Huluwa) brother).getSeniority().ordinal();
        else
            return false;
    }

}

enum COLOR {
    赤, 橙, 黄, 绿, 青, 蓝, 紫
}

enum SENIORITY {
    一, 二, 三, 四, 五, 六, 七
}
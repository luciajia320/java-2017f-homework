package creature;

/**
 * 葫芦娃类，继承自Creature类，实现Comparable<Calabash>接口以进行排序
 */
public class Calabash extends Creature implements Comparable<Calabash> {
    private Color color;
    private Order order;

    /**
     * @param color, 设定葫芦娃的颜色
     * @param order, 设定葫芦娃的长幼次序
     */
    public Calabash(Color color, Order order) {
        this.color = color;
        this.order = order;
    }

    /**
     * @return 获取葫芦娃的颜色
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return 获取葫芦娃的长幼次序
     */
    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return color.name();
    }

    @Override
    public int compareTo(Calabash o) {
        return Integer.compare(this.color.ordinal(), o.color.ordinal());
    }
}

/**
 * 枚举类型，颜色
 */
enum Color {
    红, 橙, 黄, 绿, 青, 蓝, 紫
}

/**
 * 枚举类型，长幼次序
 */
enum Order {
    一, 二, 三, 四, 五, 六, 七
}
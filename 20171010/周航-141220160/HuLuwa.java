public class HuLuwa extends Creature {

   public enum Order{ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN}
 // public enum Order{老大, 老二, 老三, 老四, 老五, 老六, 老七}
    public enum Color{RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE}

    private Order order;
    private Color color;

    public HuLuwa(Order order, Color color){
        super();
        this.order = order;
        this.color = color;
    }

    @Override
    public String speak() {
        return "HuLu:"+this.order.name();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof HuLuwa){
            return this.order.ordinal() - ((HuLuwa)o).order.ordinal();
        }else{
            return -1;
        }
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Order getOrder() {
        return order;
    }
    public Color getColor() {
        return color;
    }

}
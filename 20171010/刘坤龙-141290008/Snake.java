package lonhh.huluwa;

public class Snake extends Creature implements Active {

    Snake(){
        super("🐍");
    }

    @Override
    public void act(){
        System.out.println("蝎哥哥,666!");
    }
}

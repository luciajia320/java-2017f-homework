package lonhh.huluwa;

public class Grandpa extends Creature implements Active {

    Grandpa(){
        super("\uD83C\uDF85");
    }

    @Override
    public void act(){
        System.out.println("葫芦娃,666!");
    }
}

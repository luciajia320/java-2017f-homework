package lonhh.huluwa;

public class Creature {

    String name;

    Creature(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

}

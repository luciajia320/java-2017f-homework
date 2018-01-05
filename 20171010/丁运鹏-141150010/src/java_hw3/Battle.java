package java_hw3;

import java.net.Socket;

public class Battle {
    public static void main(String[] args) {
        Field battle_field = new Field(30, 10);

        Formation Huluwa_format = new Snake_formation(1, 5, new Huluwa().huluwas);
        Formation Scorpion_format = new Goose_formation(1, 18, new Scorpion_Entity());
        Formation grandpa = new Single_formation(4,3, new Grandpa_Entity());
        Formation snake = new Single_formation(4, 25, new Snake_Entity());


        battle_field.insert_formation(Huluwa_format);
        battle_field.insert_formation(Scorpion_format);
        battle_field.insert_formation(grandpa);
        battle_field.insert_formation(snake);
        battle_field.print_field();


        battle_field.remove_formation(Scorpion_format);
        Scorpion_format = new Crane_formation(6, 18, new Scorpion_Entity());
        battle_field.insert_formation(Scorpion_format);
        battle_field.print_field();

    }
}

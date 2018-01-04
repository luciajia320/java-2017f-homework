package java_hw3;

public class Battle {
    public static void main(String[] args) {
        Field battle_field = new Field(30, 10);

        Snake_formation Huluwa_format = new Snake_formation(1, 5, new Huluwa().huluwas);
        Goose_formation Scorpion_format = new Goose_formation(1, 18, new Scorpion_Entity());
        Single_formation grandpa = new Single_formation(4,3, new Grandpa_Entity());
        Single_formation snake = new Single_formation(4, 25, new Snake_Entity());


        battle_field.insert_formation(Huluwa_format);
        battle_field.insert_formation(Scorpion_format);
        battle_field.insert_formation(grandpa);
        battle_field.insert_formation(snake);

        battle_field.print_field();
    }
}

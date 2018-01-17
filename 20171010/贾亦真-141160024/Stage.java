package hlw;


public class Stage {
    private Position[][] space;

    public static void main(String args[]){
        int N = 20;
        Stage stage = new Stage(N);

        //Create Huluwa Brothers
        HuLuWa[] brothers = new HuLuWa[7];
        for (int i = 0; i<brothers.length; i++){
            brothers[i] = new HuLuWa(RANK.values()[i], COLOR.values()[i]);
        }
        //Create Monsters
        Creature[] monsters = new Creature[12];
        for ( int i = 0; i < monsters.length; i++){
            if ( i == 0 ) monsters[i] = new Scorpion();
            else{
                monsters[i] = new LouLuo();
            }
        }
        //Create Grandpa
        Grandpa grandpa = new Grandpa();
        //Create Snake
        Snake snake= new Snake();

        CreatureQueue hulu_q = new CreatureQueue(brothers, stage.get_space(), 1, N/2-1, 0, N);
        CreatureQueue mst_q = new CreatureQueue(monsters, stage.get_space(), N-2,N/2, 0, N);
        Pattern ls_p= new LongSnakePattern();
        ls_p.Reform(hulu_q);
        Pattern arrow_p = new ArrowPattern();
        arrow_p.Reform(mst_q);
        grandpa.set_pos(stage.get_space()[N/2][0]);
        snake.set_pos(stage.get_space()[N/2][N-1]);
        stage.printStage();
        Pattern crescent_p = new CrescentPattern();
        crescent_p.Reform(mst_q);
        stage.printStage();
    }

    Stage(int N){
        space = new Position[N][N];
        for (int i = 0; i < space.length; i++){
            for (int j = 0; j < space[i].length; j++){
                space[i][j] = new Position(new Coord(j, i));
            }
        }
    }
    public void printStage(){
        System.out.println("-------------------------------------");
        for (int i = 0; i < space.length; i++){
            for (int j = 0; j < space[i].length; j++){
                if (space[i][j].get_avail())
                {
                    System.out.print("  ");
                }
                else
                {
                    String symbol = space[i][j].get_holder().get_symbol();
                    System.out.print(symbol);
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }
    public Position[][] get_space(){
        return space;
    }
}

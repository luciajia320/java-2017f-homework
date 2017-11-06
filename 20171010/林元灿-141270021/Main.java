import lyc.hw.*;
import lyc.life.*;


public class Main {
    public static void main(String[] args) {
        //造物主创造万物
        Grandpa papa = new Grandpa("Grandpa");
        HuluBros huluwa = new HuluBros();
        Snake snake = new Snake("Snake");
        Scorpion scorpion = new Scorpion("Scorpion");

        // 葫芦娃准备战斗：打乱后有序排成一排
        huluwa.prepare();

        //蛇精召集蝎子精和小喽啰准备战斗
        snake.prepare();

        // 蛇精的阵型初始化
        Formation[] formations = {
                new SnakeFormation(Snake.getArmies()),  //不使用此阵法
                new CraneFormation(Snake.getArmies()),
                new ArrowFormation(Snake.getArmies()),
                new BalanceFormation(Snake.getArmies()),
                new FishFormation(Snake.getArmies()),
                new GooseFormation(Snake.getArmies()),
                new SquareFormation(Snake.getArmies()),
                new CrescentFormation(Snake.getArmies())
        };
        //战斗双方入阵
        for (int i = 1; i < formations.length; i++) {
            Ground battlefield = new Ground(15, 15);
            try {
                battlefield.layout(new SnakeFormation(huluwa.getHulubros()), new Location(2, 1));
                battlefield.layout(formations[i], new Location(9 - formations[i].getWidth(), 5));
                battlefield.setPlace(papa, new Location(2 + formations[0].getWidth(), 1));   //放置爷爷
                battlefield.setPlace(snake, new Location(2 + formations[0].getWidth(), 5));  //放置蛇精
            } catch (Exception e) {
                e.printStackTrace();
            }
            //打印战场状态
            System.out.println(formations[0].getName() + " VS " + formations[i].getName());
            System.out.println(battlefield);
        }
    }
}

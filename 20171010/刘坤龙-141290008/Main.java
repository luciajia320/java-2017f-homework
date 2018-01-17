package lonhh.huluwa;

public class Main {

    public static void main(String[] args){
        Space space = new Space(12);

        try{
            /*Huluwa*/
            Huluwa[] brothers = new Huluwa[7];
            for(int i=0;i<brothers.length;i++)
                brothers[i] = new Huluwa(COLOR.values()[i],SENIORITY.values()[i]);

            /*Grandpa*/
            Grandpa grandpa = new Grandpa();
            SingleFormation singleGp = new SingleFormation(grandpa);

            /*Snake*/
            Snake snake = new Snake();
            SingleFormation singleSk = new SingleFormation(snake);

            /*Minions*/
            Minions[] minions = new Minions[7];
            minions[0] = new Minions("\uD83D\uDC7E");
            for(int i=1;i<minions.length;i++)
                minions[i] = new Minions("\uD83C\uDF83" );


            /* Minions form the Heyi Formation */
            HeyiFormation heyiFormation = new HeyiFormation(minions);

            /* Huluwa form the Long Snake Formation and shuffle */
            System.out.println("\n葫芦无序长蛇阵");
            LongSnakeFormation snakeFormation = new LongSnakeFormation(brothers);
            snakeFormation.shuffle();
            System.out.println(snakeFormation.toString());

            /* Huluwa sort */
            System.out.println("\n葫芦有序长蛇阵!");
            snakeFormation.sort();
            System.out.println(snakeFormation.toString());

            /* Step 4, 对峙*/
            System.out.println("\n葫芦有序长蛇阵 VS 蝎子喽啰鹤翼阵");
            space.layout(snakeFormation,new Position(2,0));
            space.layout(heyiFormation,new Position(2,6));

            /* Step 5, grandpa and snake */
            space.layout(singleGp, new Position(0,0));
            space.layout(singleSk, new Position(11,11));

            /* Step 6, output */
            System.out.println(space.toString());

            for(Huluwa huluwa : brothers)
                huluwa.act();
            System.out.println();

            grandpa.act();
            System.out.println();

            for(Minions minion: minions)
                minion.act();
            System.out.println();

            snake.act();
            System.out.println();


            /* Step 7, change the formation */
            space.remove(heyiFormation);
            ArrowFormation arrowFormation = new ArrowFormation(minions);

            /* Step 4, 对峙*/
            System.out.println("\n葫芦有序长蛇阵 VS 蝎子喽啰锋矢阵");
            space.layout(arrowFormation,new Position(3,6));

            /* Step 6, output */
            System.out.println(space.toString());

            for(Huluwa huluwa : brothers)
                huluwa.act();
            System.out.println();

            grandpa.act();
            System.out.println();

            for(Minions minion: minions)
                minion.act();
            System.out.println();

            snake.act();
            System.out.println();



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

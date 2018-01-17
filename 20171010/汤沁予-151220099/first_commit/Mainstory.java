public class Mainstory
{
    public static void main(String[] args)
    {
        //System.out.print("\uD83D\uDC74 \n\n");
        Playground playground = new Playground(11);//生成活动场所
        //playground.PutHuluwas();
        playground.HuluwaSnakeFormat(1,1);//初始兄弟乱序进场
        playground.HuluwaSort();//依次站队
        playground.MinionsCranewingFormat(1,3);
        playground.PutGrandpa(3,0);
        playground.PutSnakeWoman(6,8);
        playground.PrintPlayground();

        playground.Remove();
        playground.HuluwaSnakeFormat(1,1);
        playground.MinionsArrowFormat(3,3);
        playground.PutGrandpa(6,0);
        playground.PutSnakeWoman(7,9);
        playground.PrintPlayground();
    }
}

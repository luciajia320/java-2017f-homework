import Scene.Arena;
import orders.*;
import roles.*;

import java.util.List;

public class Story {
    private Huluwa.HuluBrothers huluBrothers = null;
    private Huluwa[] huluwas = new Huluwa[7];
    private OldMan oldMan = null;
    private Snake snake = null;
    private Scorpion scorpion = null;

    /*擂台大小*/
    private final int N = 15;

    private Arena arena = null;

    private List<Point> soldierPositions = null;

    public Story()
    {
        /*创建人物角色*/
        createRoles();

        /*创建擂台*/
        createArena();

    }

    public void start()
    {
        System.out.println("故事开始...");

        System.out.println("葫芦娃按长蛇阵站队...");

        huluwaStandInLongSnake();

        System.out.println("蝎子精领若干小喽啰按鹤翼阵法列阵...");

        scorpionAndSoldiresStandInHeyi();

        System.out.println("爷爷：孩子们不要怕，消灭妖精！");

        System.out.println("蛇精：该死的葫芦娃！把他们都抓起来！");

        setOldmanAndSnakePosition(new Point(11,2),new Point(11,5));

        showSituation();

        System.out.println("蝎子精：小的们，变换阵型...");

        recoverLastSoldierFormation();

        scorpionAndSoldiresStandInFengShi();

        showSituation();

    }

    private void huluwaStandInLongSnake()
    {
        LongSnake snakeOrder = new LongSnake(Direction.SOUTH,new Point(3,2),N,7);
        List<Point> points = snakeOrder.order();
        if (points == null)
            System.out.println("没有空间给你列此阵了");
        else {
            int count = 0;
            for (Point point : points)
                arena.setPosition(point.getX(), point.getY(), huluwas[count++]);
        }
    }

    private void scorpionAndSoldiresStandInHeyi()
    {
        Heyi heyi = new Heyi(Direction.WEST, new Point(6,5),N,11);
        soldierPositions = heyi.order();
        if (soldierPositions == null)
            System.out.println("没有空间给你列此阵了");
        else {
            for (int i = 0; i < soldierPositions.size(); i++)
            {
                Point point = soldierPositions.get(i);
                if (i == 0)
                    arena.setPosition(point.getX(),point.getY(),scorpion);
                else
                    arena.setPosition(point.getX(),point.getY(),new Soldier());
            }
        }
    }

    private void setOldmanAndSnakePosition(Point poldman, Point psnake)
    {
        arena.setPosition(poldman.getX(),poldman.getY(),oldMan);
        arena.setPosition(psnake.getX(),psnake.getY(),snake);
    }

    private void scorpionAndSoldiresStandInFengShi()
    {
        FengShi fengShi = new FengShi(Direction.WEST, new Point(6,5),N,11);
        soldierPositions = fengShi.order();
        if (soldierPositions == null)
            System.out.println("没有空间给你列此阵了");
        else {
            for (int i = 0; i < soldierPositions.size(); i++)
            {
                Point point = soldierPositions.get(i);
                if (i == soldierPositions.size()-1)
                    arena.setPosition(point.getX(),point.getY(),scorpion);
                else
                    arena.setPosition(point.getX(),point.getY(),new Soldier());
            }
        }
    }

    private void recoverLastSoldierFormation()
    {
        for (int i = 0; i < soldierPositions.size(); i++) {
            Point point = soldierPositions.get(i);
            arena.clearPosition(point.getX(), point.getY());
        }
    }

    private void createRoles()
    {
        huluBrothers = Huluwa.getHuluBrothers();
        huluwas[0] = huluBrothers.first;
        huluwas[1] = huluBrothers.second;
        huluwas[2] = huluBrothers.third;
        huluwas[3] = huluBrothers.fourth;
        huluwas[4] = huluBrothers.fifth;
        huluwas[5] = huluBrothers.sixth;
        huluwas[6] = huluBrothers.seventh;
        oldMan = OldMan.getOldMan();
        snake = Snake.getSnake();
        scorpion = Scorpion.getScorpion();
    }

    private void createArena()
    {
        arena = new Arena(N);
    }

    public void showSituation()
    {
        System.out.println(arena);
    }
}

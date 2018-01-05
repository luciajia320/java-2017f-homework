import java.util.ArrayList;
import javax.swing.JFrame;

public class Main extends JFrame{
    final int N=15;
    static final int HLW_SUM=7;
    static final int LL_SUM=6;
    static final int MON_SUM=8;
    private Field field;
    public Main(Field field) {
        this.field=field;
        InitUI(this.field);
    }
    public void InitUI(Field field) {

        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000 , 800);
        setLocationRelativeTo(null);
        setTitle("HuLuWa VS Monster");
    }

    public static void main(String[] args) {
        //生成葫芦娃
        ArrayList<String>hlwname=new ArrayList<String>();
        hlwname.add("赤");
        hlwname.add("橙");
        hlwname.add("黄");
        hlwname.add("绿");
        hlwname.add("青");
        hlwname.add("蓝");
        hlwname.add("紫");
        ArrayList<HuLuWa> brothers = new ArrayList<HuLuWa>();
        for (int i = 0; i < HLW_SUM; i++) {
            brothers.add(new HuLuWa(hlwname.get(i), new Position(0, i)));
        }
        Queue huluwaqueue = new Queue (HLW_SUM);   //生成葫芦娃队
        for (int i=0;i<brothers.size();++i)
            huluwaqueue.JoinIn(brothers.get(i));
        new Snake().format(huluwaqueue);        //葫芦娃排成长蛇形
        Field field = new Field();
        field.putIn(huluwaqueue);

        ArrayList<Monster>louluos = new ArrayList<Monster>();
        for (int i = 0; i < LL_SUM; i++) {
            louluos.add ( new Monster("💀"));
        }
        Monster xiezijing = new Monster("🐛");
        Monster shejing = new Monster("🐍");
        Queue monsterqueue = new Queue(MON_SUM);         //生成妖怪队

        monsterqueue.JoinIn(xiezijing);
        for (int i=0;i<louluos.size();++i)
            monsterqueue.JoinIn(louluos.get(i));
        monsterqueue.JoinIn(shejing);
        new CraneWing().format(monsterqueue);    //妖怪鹤翼形
        field.putIn(monsterqueue);
        Creature grandfather = new Grandfather("🎅");
        Queue audiencequeue = new Queue(1);  //观战助威队
        audiencequeue.JoinIn(grandfather);
        new Audience().format(audiencequeue);
        field.putIn(audiencequeue);

        field.initfield();
      // field.repaint();

    //    field.rollCall();
        Main world = new Main(field);
     //   System.out.println(field.getBoardWidth()+" "+field.getBoardHeight());
        world.setVisible(true);
    }

}

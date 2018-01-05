import org.junit.Test;

import java.util.ArrayList;

public class test {
    Field field;

    @Test
    public void testField(){
        ArrayList<String> hlwname=new ArrayList<String>();
        hlwname.add("赤");
        hlwname.add("橙");
        hlwname.add("黄");
        hlwname.add("绿");
        hlwname.add("青");
        hlwname.add("蓝");
        hlwname.add("紫");
        ArrayList<HuLuWa> brothers = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            brothers.add(new HuLuWa(hlwname.get(i), new Position(0, i)));
        }
        Queue huluwaqueue = new Queue (7);   //生成葫芦娃队
        for (int i=0;i<brothers.size();++i)
            huluwaqueue.JoinIn(brothers.get(i));
        new Snake().format(huluwaqueue);
        field = new Field();
        field.putIn(huluwaqueue);
        for(int i=0;i<7*2;i=2*(i+1)){
            assert (field.creatures.get(i).get(1) instanceof HuLuWa);
        }
    }
    @Test
    public void testCreature(){
        field = new Field();
        field.creatures.get(0).set(0,new HuLuWa("橙",125, 0, field, 2));
        field.initfield();
        Main world=new Main(field);
        world.setVisible(true);
        assert (!field.positions.get(0).get(0).getHolder().exist);
    }
}

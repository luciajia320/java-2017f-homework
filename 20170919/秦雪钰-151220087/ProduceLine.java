import javafx.geometry.Pos;

public class ProduceLine {
    private Position []positions;//流水线上有位置，位置上自动标记存放的元素
    private Sorter mysorter; //流水线上固有的排队方法
    private Creature[] creatures; //流水线的位置上应该排队的生物
    public ProduceLine(Huluwa []brothers)
    {
        creatures=brothers;
        positions=new Position[brothers.length]; //给他们提供位置
        for(int i=0;i<positions.length;i++) {
            positions[i] = new Position(i); //i号位
            creatures[i].setPos(positions[i]); //登记每个葫芦娃的位置，并在登记时把位置的上葫芦娃兄弟的信息补上
            Huluwa a=(Huluwa) creatures[i];
            a.setWords(SPEAKKINDS.seniorty); //bubble的排序模式
        }
        mysorter=new BubbleSorter(); //先用bubble，并且设置叫法为
    }

    public Position[] getPositions() {
        return positions;
    }

    public void setPosSitterRandom()
    {
        //随机变换位置
        for(int i=0;i<positions.length;i++)
        {
            int arandnum=(int)(Math.random()*((double)positions.length-0.01));//生成0-6之间的随机数
            if(i!=arandnum) {
                Position temp = creatures[arandnum].getPos(); //随便换个位置
                creatures[arandnum].setPos(creatures[i].getPos());
                creatures[i].setPos(temp);
            }
        }
    }
    public void setBrotherSpeakKind(SPEAKKINDS akind)
    {
        for (int i=0;i<creatures.length;i++) {
            Huluwa awa = (Huluwa) creatures[i];
            awa.setWords(akind);
        }
    }

    public void reportAllBrothers()
    {
        System.out.println("葫芦娃兄弟报数");
        for (Creature creature : this.creatures) {
            creature.speak();
        }
        System.out.println("\n位置0-6上依次站的葫芦娃");
        for (Position pos : this.positions) {
            pos.getSitter().speak();
        }

        System.out.println();
        System.out.flush();
    }

    public static void main(String args[]){
        Huluwa []brothers=new Huluwa[7];
        for(int i=0;i<brothers.length;i++)
            brothers[i]=new Huluwa(COLOR.values()[i],SENIORTY.values()[i]);
        ProduceLine apline=new ProduceLine(brothers); //最终的流水线表示形式

        System.out.println("打乱次序后：");
        apline.setPosSitterRandom();
        apline.setBrotherSpeakKind(SPEAKKINDS.pos); //报告位置
        apline.reportAllBrothers();

        apline.setBrotherSpeakKind(SPEAKKINDS.seniorty); //报告位置
        System.out.println("冒泡排序后：");
        apline.mysorter.sort(apline.getPositions()); //排序
        apline.setBrotherSpeakKind(SPEAKKINDS.seniorty); //报告次序
        apline.reportAllBrothers();

        System.out.println("打乱次序后：");
        apline.setPosSitterRandom();
        apline.setBrotherSpeakKind(SPEAKKINDS.pos); //报告位置
        apline.reportAllBrothers();

        System.out.println("选择排序后：");
        apline.mysorter=new SelectSorter();
        apline.mysorter.sort(apline.getPositions()); //排序
        apline.setBrotherSpeakKind(SPEAKKINDS.color); //报告次序
        apline.reportAllBrothers();

    }
}

import java.util.ArrayList;

public class God { //造物主
    private JusticeCamp justiceCamp;//正义联盟
    private EvilCamp evilCamp;//一堆垃圾
    //造物主拥有土地Ground
    private Ground ground;
    public God(){
        ground=new Ground(12);
    }
    void createCamp(){ //造物主创建了两个联盟
        justiceCamp=new JusticeCamp();
        evilCamp=new EvilCamp();
    }
    void createJusticeCamp(){
        Grandpa grandpa=new Grandpa();
        justiceCamp.setLeader(grandpa);
        for(int i=0;i<7;i++){ //爷爷控制7个葫芦娃的诞生
            Huluwa huluwa=grandpa.giveBirth(i);
            justiceCamp.addMember(huluwa); //添加成员
        }
        justiceCamp.shuffleMembers(); //初始让葫芦娃们乱序
        justiceCamp.createFormation(); //这时候创建阵型
        try {
            ground.layoutLeader(grandpa,ground.getPosition(1,1)); //放爷爷
            ground.layoutFormation(justiceCamp.formation, ground.getPosition(3, 1)); //放置阵型
        }catch (LayOutException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        justiceCamp.setMembersPosition(ground); //将每个成员的位置随着放置好的阵型落实下来
    }

    void createEvilCamp(){
        Snake snake=new Snake();
        evilCamp.setLeader(snake);
        Scorpion scorpion=snake.giveBirth(); //蛇精生蝎子精
        evilCamp.addMember(scorpion);
        ArrayList<Minion> minions=scorpion.giveBirth(); //蝎子精集齐小怪
        for(Minion m:minions){
            evilCamp.addMember(m);
//            System.out.println(m.getName());
        }
        evilCamp.createFormation();//垃圾们的第一个阵型
        try { //放置阵营可能超出边界或与已存在阵型相互影响
            ground.layoutLeader(snake,ground.getPosition(1,9));
            ground.layoutFormation(evilCamp.formation, ground.getPosition(3, 3)); //放置阵型
        }catch (LayOutException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        evilCamp.setMembersPosition(ground);//确定阵型后需要把阵型上应占用的位置落实给每个成员
    }

    void sortJusticeCamp(){
        justiceCamp.sortMember();
    }

    void changeEvilCamp(){ //邪恶势力换阵型了
        ground.deleteFormation(evilCamp.formation);
        evilCamp.changeFormation(ground);
    }

    void printBattle(){
        System.out.println(ground.toString());
    }
    void tellStory(String story){
        System.out.println(story+"\n");
    }

    public static void main(String args[]){
        God god=new God();
        god.tellStory("上帝创建了两个阵营，一是正义的阵营，二是邪恶的势力\n正义联盟由爷爷和他种下成精的葫芦娃带领\n邪恶势力由蛇精以及她的小弟蝎子精带领的下喽啰队构成");
        god.createCamp();
        god.createJusticeCamp();
        god.createEvilCamp();

        god.tellStory("有一天，邪恶势力在领导蛇精的带领下挑衅了措手不及的爷爷一家人，当前局面如下");
        god.printBattle();

        god.tellStory("正义联盟的爷爷和葫芦兄弟马上调整了队形应对挑衅，当前局面如下");
        god.sortJusticeCamp(); //排序后重新输出
        god.printBattle();

        god.tellStory("邪恶势力见状更为挑衅了，又变换了新的队形，当前局面如下");
        god.changeEvilCamp();
        god.printBattle();

        god.tellStory("这场战役即将打响，终究鹿死谁手，敬请期待下期节目......");
    }
}

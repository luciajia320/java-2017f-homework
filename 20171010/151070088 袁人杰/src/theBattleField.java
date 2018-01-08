public class theBattleField {
    private Planar planar;

    private Huluwa[] bros;
    private Minions[] minions;
    private Scorpion scorpion;
    private Snake shejing;
    private Grandpa grandpa;

    public theBattleField(){
        planar= new Planar(20);

        bros=new Huluwa[7];
        for (int i=0;i<bros.length;i++){
            bros[i] = new Huluwa(Huluwa.COLOUR.values()[i], Huluwa.SENIORITY.values()[i],this.planar);
        }

        grandpa=new Grandpa();

        shejing=new Snake(Snake.NAME.金蛇精, Villain.LEVEL.Boss);
        scorpion=new Scorpion(Villain.LEVEL.Captain,1);

        minions=new Minions[9];
        for (int i=0;i<minions.length;i++){
            minions[i]=new Minions(i, Villain.LEVEL.Minions);
        }
    }

    public static void main(String[] args) {
        theBattleField field = new theBattleField();

        //葫芦娃入队
        CreatureQueue huluQueue =new CreatureQueue(field.bros.length);
        for(int i=0;i<field.bros.length;i++){
            huluQueue.enQueue(field.bros[i]);
        }
        new HuluShuffle().arrRandom(field.bros);
        new Sorter().bubbleSort(huluQueue);


        //蝎子精带队
        CreatureQueue villainQueue = new CreatureQueue(field.minions.length+1);
        villainQueue.enQueue(field.scorpion);
        for (Creature i:field.minions) {
            huluQueue.enQueue(i);
        }

        System.out.println("First Battle！");
        //双方势力入场
        try {

            field.planar.enterCreature(field.grandpa, new Coordinate(0, 10));
            field.planar.enterCreature(field.shejing, new Coordinate(19, 10));

            LineFormation l1 = new LineFormation(huluQueue);
            field.planar.layout(l1, new Coordinate(1, 6));
            YanxingFormation y1 = new YanxingFormation(villainQueue);
            field.planar.layout(y1, new Coordinate(5, 5));

            field.bros[0].move(0,1);

            field.planar.printPlanar();

            field.planar.cleanPlanar();
        }catch (Exception e){
            e.printStackTrace();
        }

/*
        System.out.println("First Battle！");
        //双方势力入场

        field.grandpa.lineFormation(huluQueue,new Coordinate(8,5),field.planar);
        field.shejing.chongeFormation(villainQueue,new Coordinate(15,5),field.planar);
        field.planar.printPlanar();
        field.planar.cleanPlanar();


        System.out.println("Second Battle!");
        //双方再次入场
        field.planar.enterCreature(field.grandpa,new Coordinate(0,10));
        field.planar.enterCreature(field.shejing,new Coordinate(19,10));
        field.grandpa.arrowFormation(huluQueue,new Coordinate(4,10),field.planar);
        field.shejing.yanxingFormation(villainQueue,new Coordinate(18,5),field.planar);
        field.planar.printPlanar();
        field.planar.cleanPlanar();
      */


    }


}

import com.sun.media.sound.ModelStandardIndexedDirector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix{
    private ArrayList<ArrayList<Position<Creature>>> positions;

    public ArrayList<ArrayList<Position<Creature>>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<ArrayList<Position<Creature>>> positions) {
        this.positions = positions;
    }

    private ArrayList<Creature> creatures;

    public Matrix(int x){
        int length = x > 10 ? x:10;
        this.positions = new ArrayList<ArrayList<Position<Creature>>>(length);

        for(int i = 0 ; i < length ; i++){
            ArrayList<Position<Creature>> temp = new ArrayList<>();
            for(int j = 0 ; j < length; j++) {
                temp.add(j,new Position<Creature>(i,j));
            }
            this.positions.add(i,temp);
        }

    }


    private void shuffle(ArrayList<Creature> member){
        this.creatures= member;
        Random rnd = ThreadLocalRandom.current();
        for(int i = creatures.size()-1; i >= 0; i--){
            int x = rnd.nextInt(positions.size());
            int y = rnd.nextInt(positions.size());

            if(positions.get(x).get(y).getHolder() == null){
                creatures.get(i).setPosition(positions.get(x).get(y));
            }
            else
                i++;

        }
    }

    public void printMatix(){
        for(ArrayList<Position<Creature>>position0 :this.positions) {
            for (Position position : position0) {
                if (position.getHolder() == null) {
                    System.out.print("    ");
                } else {
                    position.getHolder().printName();
                }
            }
            System.out.print("\n");
        }

        System.out.println("\n");
        System.out.flush();
    }

    public void StandFormation(Formation formation,ArrayList<Creature>creature){

        ArrayList<Position> position = formation.getPositions();

        for(int i = 0,j=0 ; i < creature.size();i++){
            positions.get(creature.get(i).getPosition().getX()).get(creature.get(i).getPosition().getY()).setHolder(null);
            if(creature.get(i) instanceof Minion) {

                creature.get(i).setPosition(position.get(j));
                j++;
            }
            else if(creature.get(i) instanceof Grandpa){
                creature.get(i).setPosition(position.get(0));
            }
            else if(creature.get(i) instanceof  Magnate && creature.get(i).getName() == "蛇精"){
                creature.get(i).setPosition(position.get(1));
            }
            else if(creature.get(i) instanceof  Magnate && creature.get(i).getName() == "蝎子") {
                creature.get(i).setPosition(position.get(0));
                j++;
            }


        }
    }

    public  void StandInMatrix(int n,Formation formation,ArrayList<Creature> creature){
        for(ArrayList<Position<Creature>>position0 :this.positions) {
            for (Position position : position0) {
                if(position.getHolder() instanceof Calabash)
                    continue;
                else
                    position.setHolder(null);
            }
        }
        Formation go = new Formation(2);
        go.GoGoGo();

        ArrayList<Creature> temp1 = new ArrayList<>();
        ArrayList<Creature> temp2 = new ArrayList<>();


        for(int i = 2; i < creature.size();i++)
            temp1.add(creature.get(i));
        StandFormation(formation,temp1);

        for(int i = 0; i < 2;i++)
            temp2.add(creature.get(i));
        StandFormation(go, temp2);

        for(int i = 0 ;i < creature.size();i++){
            this.positions.get(creature.get(i).getPosition().getX()+n/5).get(creature.get(i).getPosition().getY()+4).cleanHolder();
            this.positions.get(creature.get(i).getPosition().getX()+n/5).get(creature.get(i).getPosition().getY()+4).setHolder(creature.get(i));
            creature.get(i).setPosition(this.positions.get(creature.get(i).getPosition().getX()+n/5).get(creature.get(i).getPosition().getY()+4));
        }
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public static void main(String[] args){
        ArrayList<Creature> all = new ArrayList<Creature>(20);
        for(int i = 0 ; i < 7;i++){
            all.add(i ,new Calabash(i));
        }
        all.add(7 ,new Grandpa());
        all.add(8,new Magnate(1));
        all.add(9, new Magnate(0));

        for(int i = 10;i < 20;i++)
            all.add(i , new Minion(i-10));
        System.out.println(all.size());
        Matrix matrix = new Matrix(15);

        matrix.shuffle(all);
        matrix.printMatix();

        new BubbleSorter().sort(matrix);

        matrix.printMatix();

        Formation formation;
        ArrayList<Creature> temp = new ArrayList<>();
        formation = new Formation(all.size()-9);
        Random random = new Random();
        int a=random.nextInt(4);
        switch(a)
        {
            case 0:formation.YanXing();break;
            case 1:formation.ChongE();break;
            case 2:formation.YuLin();break;
            case 3:formation.HeYi();break;
        }

        for(int i = 7; i < 20;i++)
            temp.add(all.get(i));
        matrix.StandInMatrix(15,formation,temp);

        matrix.printMatix();
    }
}

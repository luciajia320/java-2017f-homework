

public class Main {
    public static void main(String[] args){
        Map map=new Map(10);
        System.out.println("变阵前");
        map.addBooster(new Grandpa(new Position(1,1)));
        map.addBooster(new Snake(new Position(1,5)));
        map.addFormation(new SnakeFormation(new Position(2,0)));
        map.addFormation(new CraneFormation(new Position(2,6)));
        System.out.println(map.toString());
        map.clearFormations();
        System.out.println("变阵后");
        map.addFormation(new SnakeFormation(new Position(2,0)));
        map.addFormation(new YokeFormation(new Position(2,6)));
        System.out.println(map.toString());
        map.clear();
    }
}

public class Blank extends Creature {
    Blank(String name,int x,int y, Field field){
        this.field=field;
        this.name=name;
        setPosition(new Position(y/50,(x-125)/50));
        this.x=x;
        this.y=y;
        this.exist=false;
    }
    Blank(int x,int y){
        this.name="空";
        setPosition(new Position(x,y));

        this.exist=false;
    }
}

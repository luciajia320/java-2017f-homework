
public class Creature{
    protected String name;

    public void SetName(String name){this.name = name;}
    public String GetName(){return name;}

    Creature(){
        name = new String();
    }
}

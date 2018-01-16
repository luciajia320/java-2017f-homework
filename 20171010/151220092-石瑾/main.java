
public class main {
	public static void main(String[] args) {
        Ground g = new Ground(10);
        Formation snake= new SnakeFormation("🍐");
        Formation grus= new GrusFormation("🐹");
        Formation circle= new CircleFormation("🐹");
        Formation monster=new CallFormation("🐍");
        Formation oldman=new CallFormation("🎅");
        
        g.setloc(snake,0,0);
        g.setloc(grus,6,0);
        g.setloc(monster,5,9);
        g.setloc(oldman,4,9);
        g.printout();
        
        System.out.println(" ");
        System.out.println("阵型变换：");
        g.clear_ground();
        
        g.setloc(snake,0,0);
        g.setloc(circle,5,0);
        //g.setloc(grus,6,0);
        g.setloc(monster,5,9);
        g.setloc(oldman,4,9);
        
        g.printout();
    }
}


public class organism{
    private String name;
    private space sp;//生物体生存空间
    private int x;//横坐标
    private int y;//纵坐标
    private String ImagePath;//图像保存路径
    private double life;//生物体的血量

    public void SetPath(String IP){
         ImagePath=IP;
    }
    public String GetPath(){
         return ImagePath;
    }


    public void SetLife(double i){
         this.life=i;
    }
    public double GetLife(){
         return life;
    }

    public void SetSpace(space sp){
         this.sp=sp;
    }
    public space GetSpace(){
         return this.sp;
    }
    public int GetX(){
         return x;
    }
    public int GetY(){
         return y;
    }
    public void SetXY(int x,int y){
         this.x=x;this.y=y;
    }        

    public String GetName(){
         if (this.name==null)
             return "**";
         else 
             return this.name;

    }
    public void SetName(String na){
         this.name=na;
    }
}
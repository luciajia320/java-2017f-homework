
enum COLORS{RED,ORANGE,YELLOW,GREEN,CYAN,BLUE,VIOLET};
enum SENIORITY{老大,老二,老三,老四,老五,老六,老七};

public class brothers implements creature{
    //private String name;
    private COLORS color;
    private SENIORITY seniority;
    private Position position;

    public brothers(COLORS col,SENIORITY sen){
        seniority=sen;
        color=col;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
       // position.setHolder(this);
    }

    public boolean biggerThan(brothers brother){

        if (brother instanceof  brothers)
            return this.getSeniority().ordinal()> ((brothers) brother).getSeniority().ordinal();
        else
            return false;
    }


    public SENIORITY getSeniority() {
        return seniority;
    }

    @Override
    public String getName() {
        return seniority.toString();
    }

    /*

    public String get_name(){
        return name;
    }

    public String get_color(){
        switch(name) {
            case ("老大"):
                return "红色";
            case ("老二"):
                return "橙色";
            case ("老三"):
                return "黄色";
            case ("老四"):
                return "绿色";
            case ("老五"):
                return "青色";
            case ("老六"):
                return "蓝色";
            case ("老七"):
                return "紫色";
            default:
                return " ";
        }

    }*/

}

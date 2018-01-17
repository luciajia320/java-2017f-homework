
enum COLORS{RED,ORANGE,YELLOW,GREEN,CYAN,BLUE,VIOLET};
enum SENIORITY{老大,老二,老三,老四,老五,老六,老七};

public class brothers implements creature{
    private String name;
    private COLORS color;
    private SENIORITY seniority;

    public brothers(COLORS col,SENIORITY sen){
        seniority=sen;
        color=col;
        switch (seniority) {
            case 老大:name="🤔";break;
            case 老二:name="😬";break;
            case 老三:name="😆";break;
            case 老四:name="😉";break;
            case 老五:name="🙃";break;
            case 老六:name="😎";break;
            case 老七:name="🤗";break;
        }
    }

    //返回比较大小的结果
    public boolean biggerThan(brothers brother){

        if (brother instanceof  brothers)
            return this.getSeniority().ordinal()> brother.getSeniority().ordinal();
        else
            return false;
    }


    public SENIORITY getSeniority() {
        return seniority;
    }

    @Override
    public String getName() {
        return name;
    }


}

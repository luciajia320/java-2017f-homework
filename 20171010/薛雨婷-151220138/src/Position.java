public class Position {
    private int x;
    private int y;
    private creature holder;
    private boolean valid;



    public Position(int x,int y){
        this.x=x;
        this.y=y;
        valid=false;
    }

    public creature getHolder() {
        return holder;
    }

    public void setHolder(creature holder) {
        this.holder = holder;
        valid=true;
    }

    public void invalid(){valid=false;}

    public boolean getValid(){
        return valid;
    }


}

public class Position <T extends creature>{
    private int x;
    private int y;
    private T holder;
    private boolean valid;



    public Position(int x,int y){
        this.x=x;
        this.y=y;
        valid=false;
    }

    public creature getHolder() {
        return holder;
    }

    public void setHolder(T holder) {
        this.holder = holder;
        valid=true;
    }

    public void invalid(){valid=false;}

    public boolean getValid(){
        return valid;
    }


}

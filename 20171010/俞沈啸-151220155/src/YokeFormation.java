public class YokeFormation implements Formation {
    private final int width=7;
    private final int height=2;
    private Position position;
    private Position[] positions=new Position[7];
    private Minion[] minions=new Minion[6];
    private Scorpion scorpion;

    public YokeFormation(Position position){
        this.position=position;
        for(int i=0;i<6;i++){
            minions[i]=new Minion();
        }
        scorpion=new Scorpion();
        positions[0]=new Position(this.position.getX(),this.position.getY());
        positions[0].setHolder(scorpion);
        for(int i=1;i<7;i++){
            positions[i]=new Position(this.position.getX()+i,this.position.getY()+i%2);
            positions[i].setHolder(minions[i-1]);
        }
    }

    @Override
    public int getWidth(){
        return this.width;
    }
    @Override
    public int getHeight(){
        return this.height;
    }
    @Override
    public Position getPosition(){
        return this.position;
    }
    @Override
    public void setPosition(Position position){
        this.position=position;
    }
    @Override
    public String[][] getContent(){
        String[][] content=new String[this.width][this.height];
        for(int i=0;i<this.width;i++){
            for(int j=0;j<this.height;j++){
                content[i][j]="\uD83C\uDF3F";
            }
        }
        for(int i=0;i<7;i++){
            content[positions[i].getX()-this.position.getX()][positions[i].getY()-this.position.getY()]=positions[i].getHolder().toString();
        }
        return content;
    }
}

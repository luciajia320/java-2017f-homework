import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SnakeFormation implements Formation{
    private final int width = 7;
    private final int height = 1;
    private Position position;
    private Position[] positions=new Position[7];
    private Huluwa[] huluwas = new Huluwa[7];
    public SnakeFormation(Position position){
        this.position=position;
        for(int i=0;i<7;i++){
            huluwas[i]=new Huluwa(COLOR.values()[i],SENIORITY.values()[i]);
        }

        for(int i=0;i<7;i++){
            positions[i]=new Position(this.position.getX()+i,this.position.getY());
            positions[i].setHolder(huluwas[i]);
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
            content[i][0]=positions[i].getHolder().toString();
        }
        return content;
    }
}

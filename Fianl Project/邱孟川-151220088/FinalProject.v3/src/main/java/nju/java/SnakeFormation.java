package main.java.nju.java;

public class SnakeFormation extends Formation{
    public SnakeFormation(Position location){
        super(2,8);
        setLocation(location);
        for(int i = 0; i < 8; ++i){
            getPositions()[i].setXY(location.getX(), location.getY()+i);
        }
        for(int i = 0; i < 8; ++i){
            getPositions()[i+8].setXY(location.getX()+1, location.getY()+i);
        }
    }
}

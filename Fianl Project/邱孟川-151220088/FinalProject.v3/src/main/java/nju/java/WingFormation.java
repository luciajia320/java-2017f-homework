package main.java.nju.java;

public class WingFormation extends Formation {
    public WingFormation(Position location){
        super(4,8);
        setLocation(location);
        for(int i = 0; i < 4; ++i){
            getPositions()[i].setXY(location.getX()+i, location.getY()+3-i);
            getPositions()[i+4].setXY(location.getX()+i, location.getY()+4+i);
        }
        for(int i = 0; i < 3; ++i){
            getPositions()[8+i].setXY(location.getX()+1+i,location.getY()+3-i);
            getPositions()[8+i+3].setXY(location.getX()+1+i, location.getY()+4+i);
        }
    }
}

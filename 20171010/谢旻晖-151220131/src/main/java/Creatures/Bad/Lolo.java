package Creatures.Bad;/*
* @author Minhui Xie
* @date 24,10,2017
* @time 19:20
*/

import Position.PositionInterface;

public class Lolo extends Creatures.Bad.Monster {
    public Lolo(PositionInterface positionInterface)   {
        super("喽",positionInterface);
    }

    @Override
    public void act(){
        ;//小人物，哪那么多屁话
    }
}

package Creatures.Bad;/*
* @author Minhui Xie
* @date 24,10,2017
* @time 18:25
*/

import Creatures.Creature;
import Position.PositionInterface;

import java.awt.*;

public abstract class Monster extends Creature {
    Monster(String name, PositionInterface position)
    {
        super(name,position);
    }
}

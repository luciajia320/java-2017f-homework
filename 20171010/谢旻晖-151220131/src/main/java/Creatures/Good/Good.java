package Creatures.Good;

import Creatures.Creature;
import Position.PositionInterface;

import java.awt.*;

public abstract class Good extends Creature {
    Good(String name, PositionInterface position) {
        super(name, position);
    }
}

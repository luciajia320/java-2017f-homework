package Creatures;

import Position.PositionInterface;

public interface CreatureFactoryInterface {
     <T extends Creature> T createCreature(Class<T> tClass, PositionInterface positionInterface);
}

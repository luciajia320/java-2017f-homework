package Characters;

import Field.Position;
import Types.Vector2;

import java.util.Collections;
import java.util.List;

/**
 * 实现了一些与寻路有关的方法。
 * 以组合的方式添加到一个creature上。
 * 在creature初始化时，将其设置为这个寻路组件的creatureClient。
 */
public class NavigationDelegate {
    private Creature creatureClient;

    public NavigationDelegate(Creature creature) {
        creatureClient = creature;
    }

    public Position getPositionOfNearestHostileCreature() {
        try {
            List<Creature> hostileCreatures = creatureClient.troop.getHostileCreatures();
            Creature nearestHostileCreature = Collections.min(hostileCreatures, (c1, c2) -> {
                int distanceToC1 = Vector2.ManhattanDistance(creatureClient.position.getCoordinate(), c1.position.getCoordinate());
                int distanceToC2 = Vector2.ManhattanDistance(creatureClient.position.getCoordinate(), c2.position.getCoordinate());
                return distanceToC1 - distanceToC2;
            });
            Position destination = null;
            if (nearestHostileCreature != null) {
                destination = nearestHostileCreature.position;
            } else {
                //System.out.println("葫芦娃：" + this.seniority + "寻路时找不到最近的敌人。");
            }
            return destination;
        } catch (NullPointerException e) {
            System.out.println("NavigationDelegate.getPositionOfNearestHostileCreature(): creatureClient可能未初始化。");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vector2[] getPossibleNextPositionVectors(Position destination) {
        try {

            // 判断是否可以去
            if (destination == null) { // 一个无效的position，可能是坐标越界，或者找不到最近的敌人
                return null;
            }

            // 有了目的地后，根据目的地的方向确定下一个position的坐标
            Vector2 direction = destination.getCoordinate().minus(creatureClient.position.getCoordinate()).getDirection();
            Vector2[] possibleNextPositionVectors = {
                    // 有三个候选position坐标，如果目的地与在同一列或者同一行上，则实际上只会有两个
                    // 并且由于destination的坐标和自身的坐标肯定不会越界，所以这些候选的坐标也不会越界
                    new Vector2(creatureClient.position.getX() + direction.getX(), creatureClient.position.getY() + direction.getY()),
                    new Vector2(creatureClient.position.getX() + 0, creatureClient.position.getY() + direction.getY()),
                    new Vector2(creatureClient.position.getX() + direction.getX(), creatureClient.position.getY() + 0),
            };
            return possibleNextPositionVectors;
        } catch (NullPointerException e) {
            System.out.println("NavigationDelegate.getPositionOfNearestHostileCreature(): creatureClient可能未初始化。");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

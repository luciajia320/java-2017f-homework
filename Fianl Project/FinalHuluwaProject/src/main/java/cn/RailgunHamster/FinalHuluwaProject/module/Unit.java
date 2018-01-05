package cn.RailgunHamster.FinalHuluwaProject.module;

import cn.RailgunHamster.FinalHuluwaProject.gui.MapControllerProtocol;
import cn.RailgunHamster.FinalHuluwaProject.gui.Materials;
import cn.RailgunHamster.FinalHuluwaProject.gui.Position;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 可运动单位，可运行
 */
public abstract class Unit extends Mono2D implements Runnable, UnitComparable {
    private static Random random = new Random(new Date().getTime());
    /**
     * 移动速度
     */
    private int movementSpeed = 1;
    /**
     * 对地图的记忆
     */
    protected MapControllerProtocol map;
    private String name;
    public Thread thread;

    Unit(String name, Position Position) {
        super(Position);
        this.name = name;
        if (!isNull()) setThread();
    }

    Unit(String name, Position Position, Image image) {
        super(Position, image);
        this.name = name;
        if (!isNull()) setThread();
    }

    private void setThread() {
        this.thread = new Thread(this);
    }

    public void learn(MapControllerProtocol map) {
        this.map = map;
    }

    boolean moveTo(Position Position) {
        return this.map.move(this, Position);
    }

    public Unit attack(Unit other) {
        if (this.isNull()) {
            return other;
        } else if (other.isNull()) {
            return this;
        } else {
            // 首先攻击的人获得胜利
            return this;
            //return random.nextBoolean() ? this : other;
        }
    }

    @Override
    public void run() {
        if (isNull()) return;
        try {
            while (true) {
                if (!action()) break;
                Thread.sleep(1000);
            }
        } catch (InterruptedException ie) {
            //killed
        } finally {
            this.stop();
        }
    }

    private boolean action() {
        List<Unit> enemies = map.conform(this::antagonize);
        boolean act = enemies.size() > 0;
        if (act) {
            enemies.sort(Comparator.comparing(this::distanceTo));
            List<Position> dsts = availableDsts(enemies.get(0).getPosition());
            for (int i = 0;i < dsts.size();i++) {
                if (moveTo(bestMoveDst(dsts, i))) break;
            }
        }
        return act;
    }

    public void kill() {
        if (thread != null && thread.isAlive()) thread.interrupt();
        this.setImage(new Materials().getBlackImage(this.getImage()));
    }

    private void stop() {
        this.map.unitStop();
    }

    private Position bestMoveDst(List<Position> aims, int count) {
        for (Position position : aims) {
            if (!map.observe(position).friendlyTo(this)) {
                count--;
                if (count <= 0) return position;
            }
        }
        return getPosition();
    }

    private List<Position> availableDsts(Position dst) {
        List<Position> aims = new ArrayList<>();
        aims.add(this.getPosition().stepTo(Position.Direction.up, movementSpeed));
        aims.add(this.getPosition().stepTo(Position.Direction.down, movementSpeed));
        aims.add(this.getPosition().stepTo(Position.Direction.left, movementSpeed));
        aims.add(this.getPosition().stepTo(Position.Direction.right, movementSpeed));
        aims.sort(Comparator.comparing(dst::distanceTo));
        return aims;
    }

    @Override
    public int compareTo(Unit o) {
        boolean tnull = this.isNull();
        boolean onull = o.isNull();
        if (tnull && onull) {
            return 0;
        } else if (tnull) {
            return -1;
        } else if (onull) {
            return 1;
        } else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + name + " : " + getPosition();
    }

    public static Unit getNull(Position Position) {
        return new NullUnit(Position);
    }

    public static Unit getNull(int row, int col) {
        return Unit.getNull(new Position(row, col));
    }

    public boolean isNull() {
        return false;
    }
    public Camp camp() {
        return Camp.Neutral;
    }
    public boolean antagonize(Unit other) {
        return this.camp().antagonize(other.camp());
    }
    public boolean friendlyTo(Unit other) {
        return this.camp().friendlyTo(other.camp());
    }
    public boolean neutralTo(Unit other) {
        return this.camp().neutralTo(other.camp());
    }
    public Integer distanceTo(Unit other) {
        return this.getPosition().distanceTo(other.getPosition());
    }
}

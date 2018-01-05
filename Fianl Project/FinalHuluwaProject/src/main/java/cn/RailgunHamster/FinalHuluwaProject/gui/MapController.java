package cn.RailgunHamster.FinalHuluwaProject.gui;

import cn.RailgunHamster.FinalHuluwaProject.module.Camp;
import cn.RailgunHamster.FinalHuluwaProject.module.Mono2D;
import cn.RailgunHamster.FinalHuluwaProject.module.Unit;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MapController implements MapControllerProtocol {
    /**
     * 场景中可见物体
     */
    private Map<Position, List<Mono2D>> visible = new TreeMap<>();
    /**
     * 场景中可运动物体
     */
    private Map<Position, Unit> map = new TreeMap<>();

    @Override
    public void addUnits(List<? extends Unit> units) {
        units.forEach(this::addUnit);
    }

    @Override
    public void addUnit(Unit unit) {
        unit.learn(App.getMap());
        map.put(unit.getPosition(), unit);
    }

    @Override
    public Position getEmptyPosition() {
        List<Position> list = getEmptyPositions(1);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Position> getEmptyPositions(int count) {
        List<Position> list = new ArrayList<>();
        for (Map.Entry<Position, Unit> entry : map.entrySet()) {
            if (entry.getValue().isNull()) {
                list.add(entry.getKey());
                if (list.size() >= count)
                    break;
            }
        }
        return list;
    }

    @Override
    public void addVisibles(List<? extends Mono2D> monos) {
        monos.forEach(this::addVisible);
    }

    @Override
    public void addVisible(Mono2D mono) {
        visible.putIfAbsent(mono.getPosition(), new ArrayList<>());
        visible.get(mono.getPosition()).add(mono);
    }

    @Override
    public void clear() {
        this.map.clear();
        this.visible.clear();
    }

    @Override
    public void removeUnit(Unit unit) {
        map.remove(unit.getPosition());
    }

    @Override
    public void removeVisible(Mono2D mono) {
        visible.get(mono.getPosition()).remove(mono);
    }

    @Override
    public synchronized boolean move(Unit unit, Position dst) {

        if (unit.getPosition().equals(dst)) return false;

        if (unit.friendlyTo(observe(dst))) return false;

        Unit winner = unit.attack(observe(dst));

        if (winner == null) return false;

        Unit loser = winner == unit ? observe(dst) : unit;
        // 杀死失败者
        loser.kill();
        // 留下尸体
        this.addVisible(loser);
        if (winner != unit) {
            this.addUnit(Unit.getNull(loser.getPosition()));
        } else {
            Position src = winner.getPosition();
            winner.setPosition(dst);
            this.addUnit(winner);
            this.addUnit(Unit.getNull(src));
        }

        return true;
    }

    @Override
    public synchronized void draw(Graphics g) {
        final Dimension
                unitSize = Materials.getUnitSize(),
                unitSpace = Materials.getUnitSpace(),
                space = Materials.getSpace();
        visible.forEach((position, mono2DS) -> mono2DS.forEach(mono2D -> g.drawImage(
                mono2D.getImage(),
                mono2D.getPosition().getCol() * (unitSize.width + unitSpace.width) + space.width,
                mono2D.getPosition().getRow() * (unitSize.height + unitSpace.height) + space.height,
                null
        )));
        map.forEach((position, unit) -> g.drawImage(
                unit.getImage(),
                unit.getPosition().getCol() * (unitSize.width + unitSpace.width) + space.width,
                unit.getPosition().getRow() * (unitSize.height + unitSpace.height) + space.height,
                null
        ));
    }

    private boolean isAllUnitsStop = false;

    @Override
    public void unitStop() {
        Camp c1 = null, c2 = null;
        for (Map.Entry<Position, Unit> entry : map.entrySet()) {
            Unit unit = entry.getValue();
            if (unit.isNull()) continue;
            if (c2 == null) {
                c2 = unit.camp();
            }
            else {
                c1 = c2;
                c2 = unit.camp();
                if (!c1.friendlyTo(c2))
                    return;
            }
        }
        this.isAllUnitsStop = true;
    }

    @Override
    public boolean isEnd() {
        if (this.isAllUnitsStop) {
            this.isAllUnitsStop = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Unit observe(Position dst) {
        return map.get(dst);
    }

    @Override
    public synchronized void forEach(BiConsumer<? super Position, ? super Unit> biConsumer) {
        map.forEach(biConsumer);
    }

    @Override
    public List<Unit> conform(Predicate<? super Unit> predicate) {
        List<Unit> list = new ArrayList<>();
        forEach((position, unit) -> {
            if (predicate.test(unit)) list.add(unit);
        });
        return list;
    }

    @Override
    public void apply(Consumer<? super Unit> consumer, Predicate<? super Unit> predicate) {
        forEach((position, unit) -> {
            if (predicate.test(unit)) consumer.accept(unit);
        });
    }
}
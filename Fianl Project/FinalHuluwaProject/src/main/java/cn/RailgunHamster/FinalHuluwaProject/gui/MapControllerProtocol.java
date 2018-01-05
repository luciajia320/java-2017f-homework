package cn.RailgunHamster.FinalHuluwaProject.gui;

import cn.RailgunHamster.FinalHuluwaProject.module.Mono2D;
import cn.RailgunHamster.FinalHuluwaProject.module.Unit;

import java.awt.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface MapControllerProtocol {
    void addUnits(List<? extends Unit> units);
    void addUnit(Unit unit);
    void addVisible(Mono2D mono);
    void addVisibles(List<? extends Mono2D> monos);
    void removeUnit(Unit unit);
    void removeVisible(Mono2D mono);
    void clear();
    void forEach(BiConsumer<? super Position, ? super Unit> biConsumer);
    /**
     * 一个Unit停止运行后会向地图报告
     */
    void unitStop();
    /**
     * @return 返回地图是否所有单位都停止运行（被杀死或者结束）
     */
    boolean isEnd();
    /**
     * @param g 在g上绘制场景
     */
    void draw(Graphics g);
    /**
     * @param unit 移动的单位
     * @param dst 单位前往的位置
     */
    boolean move(Unit unit, Position dst);
    /**
     * @param dst 为需要观察的位置，允许单位观察地图某一个点，并获取信息
     * @return 返回某位置上的单位信息
     */
    Unit observe(Position dst);
    /**
     * 注意：需要将空位使用完毕再交由其他人，否则可能会插入同一位置
     * 线程不安全
     * @param count 获取场景中count数量的空位
     * @return 返回空位的List
     */
    List<Position> getEmptyPositions(int count);
    Position getEmptyPosition();
    List<Unit> conform(Predicate<? super Unit> predicate);
    void apply(Consumer<? super Unit> consumer, Predicate<? super Unit> predicate);
}

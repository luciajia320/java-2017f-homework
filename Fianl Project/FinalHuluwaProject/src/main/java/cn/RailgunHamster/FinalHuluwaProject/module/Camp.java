package cn.RailgunHamster.FinalHuluwaProject.module;

/**
 * 阵营表示
 */
public enum Camp {
    Huluwa, Monster, Neutral;

    /**
     * @param other 另一个阵营
     * @return 返回阵营是否敌对
     */
    public boolean antagonize(Camp other) {
        return (this == Huluwa && other == Monster) || (this == Monster && other == Huluwa);
    }

    /**
     * @param other 另一个阵营
     * @return 返回阵营是否友好
     */
    public boolean friendlyTo(Camp other) {
        return !neutralTo(other) && other == this;
    }

    /**
     * @param other 另一个阵营
     * @return 返回阵营是否中立
     */
    public boolean neutralTo(Camp other) {
        return this == Neutral || other == Neutral;
    }
}

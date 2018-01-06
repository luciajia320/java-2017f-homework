package creature.animal;

import formation.BasicFormation;

public abstract class Crops implements Iterable<Animal> {
    BasicFormation basicFormation;

    /**
     * @param formation, 一种阵法
     * 为战队/军团设定一个阵法
     */
    public abstract void setFormation(BasicFormation formation);

    /**
     * 清除战队当前使用的阵法
     */
    public void clearFormation() {
        if(basicFormation == null) return;
        basicFormation.clear();
        basicFormation = null;
    }
}
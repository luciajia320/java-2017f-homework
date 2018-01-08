package creature.animal;

import formation.BasicFormation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Crops implements Iterable<Animal> {
    protected List<Animal> animals = new ArrayList<>();
    protected List<Animal> enemies = new LinkedList<>();
    BasicFormation basicFormation;

    public synchronized void setEnemyList(List<Animal> enemies) {
        this.enemies.addAll(enemies);

        for(Animal animal: animals) {
            animal.setEnemyList(this.enemies);
        }
    }

    public synchronized boolean Win() {
        for(Animal animal: enemies) {
            if(!animal.isDead()) return false;
        }
        return true;
    }

    public synchronized List<Animal> getEnemies() {
        return enemies;
    }

    public synchronized void delEnemy(Animal a) {
        enemies.remove(a);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * @param formation, 一种阵法
     * 为战队/军团设定一个阵法
     */
    public abstract void setFormation(BasicFormation formation);

    /**
     * 清除战队当前使用的阵法
     */
//    public void clearFormation() {
//        if(basicFormation == null) return;
//        basicFormation.clear();
//        basicFormation = null;
//    }
}
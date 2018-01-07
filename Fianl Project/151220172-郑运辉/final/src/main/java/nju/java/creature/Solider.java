package nju.java.creature;

import nju.java.common.cell;

public interface Solider extends Runnable {
    void moveTo(cell position);
}

package HuLu.Formation;

import HuLu.Creature.Creature;

public class Holder {
    private Creature item;
    private boolean empty = true;

    public synchronized void setItem(Creature item) {
        this.item = item;
        empty = false;
    }

    public synchronized Creature getItem() {
        return item;
    }

    public synchronized void leaveHolder(){
        this.item = null;
        empty = true;
    }

    public synchronized boolean isEmpty() {
        return empty;
    }

}

package creature.animal;

import creature.Creature;

import java.awt.*;

public class Animal extends Creature {
    private State state = State.对阵;

    Image imageAlive;
    Image imageDead;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive() {
        return (state != State.阵亡);
    }

    @Override
    public Image getImage() {
        if(isAlive()) {
            return imageAlive;
        }
        else {
            return imageDead;
        }
    }
}

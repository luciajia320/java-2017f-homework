package nju.java;

public class State {
    Position pos;
    int life;

    public State(Position pos, int life) {
        this.pos = pos;
        this.life = life;
    }

    public Position getPos(){
        return pos;
    }

    @Override
    public String toString() {
        return pos+" "+life;
    }
}

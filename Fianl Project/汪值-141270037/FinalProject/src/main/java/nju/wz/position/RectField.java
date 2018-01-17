package nju.wz.position;

import nju.wz.creature.Creature;

import java.util.Random;

public class RectField {
    Position[][] positions;
    private final int M, N;

    public RectField(int m, int n) {
        this.M = m;
        this.N = n;
        positions = new Position[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                positions[i][j] = new Position(i, j);
            }
        }
    }

    public Position[][] getPositions() {
        return positions;
    }

    public int getM() {
        return M;
    }

    public int getN() {
        return N;
    }

    public void setPositions(int i, int j, Creature creature) {
        if(!positions[i][j].isHasCreature()) {
            positions[i][j].setHolder(creature);
        }
    }

    public void addCreature(Creature creature, int i, int j) {
        positions[i][j].setHolder(creature);
        creature.setPosition(positions[i][j]);
    }

    public void reset() {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                positions[i][j].reset();
            }
        }
    }

    public void shuffle(Creature[] creatures, int row, int col) {
        Random random = new Random();
        for(int i = 0; i < creatures.length; i++) {
            int rnd = random.nextInt(creatures.length);
            Position pos = positions[row + i][col];
            positions[row + i][col] = positions[row + rnd][col];
            positions[row + rnd][col] = pos;
        }
    }

    public void displayMap() {
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(positions[i][j].isHasCreature()) {
                    positions[i][j].getHolder().report();
                }
                else {
                    System.out.print("口 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

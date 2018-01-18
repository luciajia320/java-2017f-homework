package Homework.CalabashBrothers.Creatures;
import Homework.CalabashBrothers.*;

public final class CalabashBro extends Creature implements Show {
    private Rank rank;
    private Color color;

    public CalabashBro(Rank rank, Color color) {
        isalive = true;
        isgood = true;
        gender = false;
        species = Species.CALABASHBRO;
        this.rank = rank;
        this.color = color;
    }

    public int getordinal() {
        return rank.ordinal();
    }

    @Override
    public void showyourself() {
        switch (rank) {
            case FIRST:
                System.out.print("①");
                break;
            case SECOND:
                System.out.print("②");
                break;
            case THIRD:
                System.out.print("③");
                break;
            case FOURTH:
                System.out.print("④");
                break;
            case FIFTH:
                System.out.print("⑤");
                break;
            case SIXTH:
                System.out.print("⑥");
                break;
            case SEVENTH:
                System.out.print("⑦");
                break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof CalabashBro) {
            CalabashBro cb = (CalabashBro) obj;
            if (cb.rank == this.rank || cb.color == this.color) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return rank.hashCode();
    }
}

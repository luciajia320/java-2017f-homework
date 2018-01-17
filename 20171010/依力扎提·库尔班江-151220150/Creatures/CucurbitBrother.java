package Creatures;

import Sorter.Comparable;

public class CucurbitBrother extends Creature implements Comparable {
    private Rank rank;
    private Color color;

    public CucurbitBrother(Rank r,Color c)
    {
        rank=r;
        color=c;
    }

    public Rank getRank() {
        return rank;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void speak(){
        System.out.print(rank.toString());
    }

    public void speakColor(){
        System.out.print(color.toString());
    }

    @Override
    public boolean biggerThan(Comparable another) {
        if (another instanceof  CucurbitBrother)
            return this.getRank().ordinal()> ((CucurbitBrother) another).getRank().ordinal();
        else
            return false;
    }
}


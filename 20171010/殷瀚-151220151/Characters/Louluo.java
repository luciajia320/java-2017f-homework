/**
 * Louluo:小喽啰，是一种生物,没有名字，只有代号
 */
package Characters;

import Types.TianGan;

public class Louluo extends Creature{

    private TianGan codeName;

    public Louluo(TianGan codeName){
        this.codeName = codeName;
    }

    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    public String toString(){
        return "喽啰 "
                + this.codeName.toString()
                + "@"
                + this.position.getX() + this.position.getY()
                + ";";
    }
}



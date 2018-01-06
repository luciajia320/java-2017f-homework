/**
 * Louluo:小喽啰，是一种生物,没有名字，只有代号
 */
package Characters;

import Types.TianGan;

import javax.swing.*;
import java.net.URL;
import java.util.Random;

public class Louluo extends Creature{

    private TianGan codeName;

    public Louluo(TianGan codeName){
        super();

        this.codeName = codeName;

        this.combatComponent.setAttackValue(10);
    }
    @Override
    protected void prepareRenderComponent() {
        try {
            Random random = new Random();
            int louluoKindNum = 2;
            int choice = random.nextInt(louluoKindNum);

            renderComponent.prepare(RenderComponent.ImageType.MOVING, "Image/louluo" + choice + "_moving.png", 6);
            renderComponent.prepare(RenderComponent.ImageType.HEALTH_BAR, "Image/healthBar.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.HEALTH_BAR_FILL, "Image/healthBarFill.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.TOMB, "Image/tomb.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.IDLE, "Image/louluo" + choice + "_idle.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.ATTACKING, "Image/louluo" + choice + "_attack.png", 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void report() {
        System.out.print(this.toString());
    }

    @Override
    protected void doThreadOperations() {
        try{
            doBattleOperations();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }


    @Override
    public String toString(){
        return this.codeName.toString()
                + " @"
                + this.position.getX() + "," + this.position.getY()
                + ";_ ";
    }
}



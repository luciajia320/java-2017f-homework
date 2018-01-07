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
    int louluoKind = 0;

    public Louluo(TianGan codeName){
        super();
        this.codeName = codeName;
        this.combatComponent.setAttackValue(10);
        Random random = new Random();
        int louluoKindNum = 2;
        int louluoKind = random.nextInt(louluoKindNum);
    }

    public Louluo(TianGan codeName, int luoluokind) {
        super();
        this.codeName = codeName;
        this.combatComponent.setAttackValue(10);
        this.louluoKind = louluoKind;
    }
    @Override
    protected void prepareRenderComponent() {
        try {
            renderComponent.prepare(RenderComponent.ImageType.MOVING, "Image/louluo" + louluoKind + "_moving.png", 6);
            renderComponent.prepare(RenderComponent.ImageType.HEALTH_BAR, "Image/healthBar.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.HEALTH_BAR_FILL, "Image/healthBarFill.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.TOMB, "Image/tomb.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.IDLE, "Image/louluo" + louluoKind + "_idle.png", 1);
            renderComponent.prepare(RenderComponent.ImageType.ATTACKING, "Image/louluo" + louluoKind + "_attack.png", 3);
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
        if (alive) {
            switch (state) {
                case MOVING:
                    return "louluo" + louluoKind + "_moving";
                case ATTACKING:
                    return "louluo" + louluoKind + "_attack";
                case IDLE:
                default:
                    return "louluo" + louluoKind + "_idle";
            }
        }else {
            return "tomb";
        }
        //return "Louluo" + louluoKind;
//        return this.codeName.toString()
//                + " @"
//                + this.position.getX() + "," + this.position.getY()
//                + ";_ ";
    }

    @Override
    public String initInfo() {
        return "Louluo;" + codeName + ";" + louluoKind + ";";
    }
}



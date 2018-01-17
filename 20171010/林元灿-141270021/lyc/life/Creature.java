package lyc.life;

public abstract class Creature {

    private int power;
    private int blood;
    private String identity;

    public Creature(int power,int blood,String identity){
        this.power = power;
        this.blood = blood;
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getPower() {
        return power;
    }

    public int getBlood() {
        return blood;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }


}

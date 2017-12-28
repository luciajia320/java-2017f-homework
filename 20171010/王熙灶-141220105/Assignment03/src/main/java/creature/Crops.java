package creature;

import formation.BasicFormation;

public abstract class Crops {
    BasicFormation basicFormation;

    public abstract void setFormation(BasicFormation formation);

    public void clearFormation() {
        if(basicFormation == null) return;
        basicFormation.clear();
    }
}
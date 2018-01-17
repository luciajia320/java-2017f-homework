package lonhh.huluwa;

public class HeyiFormation extends Formation {
    public HeyiFormation(Creature[] elements){
        super(elements.length,(elements.length - 1)/2 + 2);
        Creature[][] creatures = super.getContent();
        int midx = super.getWidth()/2;
        int midy = 1;
        creatures[midx][midy] = elements[0];
        int i = 1;
        for(;2*i<elements.length;i++){
            creatures[midx-i][i+midy] = elements[2*i-1];
            creatures[midx+i][i+midy] = elements[2*i];
        }
        if(2*i - 1 < elements.length){
            creatures[midx][midy + 1] = elements[2*i-1];
        }
    }
}

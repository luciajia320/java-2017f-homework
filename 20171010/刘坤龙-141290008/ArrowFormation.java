package lonhh.huluwa;

public class ArrowFormation extends Formation {
    public ArrowFormation(Creature[] elements){
        super(2*(elements.length-1)/3 + 1,(elements.length - 1)/3 + 2);
        Creature[][] creatures = super.getContent();
        int midx = super.getWidth()/2;
        int midy = 1;
        creatures[super.getWidth()/2][1] = elements[0];
        int i = 1;
        for(;3*i<elements.length;i++){
            creatures[midx-i][i+midy] = elements[3*i-2];
            creatures[midx][i+midy] = elements[3*i-1];
            creatures[midx+i][i+midy] = elements[3*i];
        }
        if(3*i-1 < elements.length){
            creatures[midx+i][i+midy] = elements[3*i];
            creatures[midx-i][i+midy] = elements[3*i-2];
        }
        else if(3*i - 2 < elements.length){
            creatures[midx][i+midy] = elements[3*i-2];
        }
    }
}

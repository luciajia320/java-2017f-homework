package lonhh.huluwa;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* Random order */
public class LongSnakeFormation extends Formation {
    public LongSnakeFormation(Creature[] elements){
        super(elements.length,2);
        Creature[][] creatures = super.getContent();
        for(int i=0;i<elements.length;i++){
            creatures[i][0] = elements[i];
            creatures[i][1] = null;
        }
    }

    public void shuffle(){
        Creature[][] creatures = super.getContent();
        Random rnd = ThreadLocalRandom.current();
        for (int i = creatures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Creature tmp = creatures[index][0];
            creatures[index][0] = creatures[i][0];
            creatures[i][0] = tmp;
        }
    }

    public void sort(){
        Huluwa[] elements = new Huluwa[super.getWidth()];
        Creature[][] creatures = super.getContent();
        for(int i = 0; i < super.getWidth();i++)
            elements[i] = (Huluwa) creatures[i][0];
        new BubbleSorter().sort(elements);
        for(int i = 0; i < super.getWidth();i++)
            creatures[i][0] = elements[i];
    }

}

package lonhh.huluwa;

import sun.plugin.com.event.COMEventHandler;

public class BubbleSorter implements Sorter {
    @Override
    public void sort(Comparable[] elements){
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = 0; j < elements.length- 1 - i; j++) {
                if(elements[j].biggerThan(elements[j+1])){
                    Comparable tmp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = tmp;
                }
            }
        }
    }
}

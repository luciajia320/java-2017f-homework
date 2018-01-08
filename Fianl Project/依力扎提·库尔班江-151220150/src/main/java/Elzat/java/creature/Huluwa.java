package creature;

import UI.Field;


/*从Huluwa开始到Yeye这些类都没有出现的必要，写之前认为可以作为要加载的图像判断的依据
* 写完才发现连这点用都没有，但既然都写了，懒得改了。*/
public class Huluwa extends Creature {

    public Huluwa(int x, int y, Field field, Name id,int moveBounds,int direction){
        super(x,y,field,id,moveBounds,direction);
    }


}

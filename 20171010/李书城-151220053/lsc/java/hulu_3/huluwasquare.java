package lsc.java.hulu_3;
import  java.util.Random;

public class huluwasquare {
    private Square hulus;

    public huluwasquare(int start_x, int start_y, int size){
        hulus = new Square(size);

        hulus.set(start_x,start_y,new Huluwa(1));
        hulus.set(start_x + 1,start_y,new Huluwa(3));
        hulus.set(start_x + 2,start_y,new Huluwa(6));
        hulus.set(start_x + 3,start_y,new Huluwa(7));
        hulus.set(start_x + 4,start_y,new Huluwa(5));
        hulus.set(start_x + 5,start_y,new Huluwa(4));
        hulus.set(start_x + 6,start_y,new Huluwa(2));


    }

    public void display()
    {
        hulus.display();
    }
    public void sort(int index_i, int index_j)
    {
        for(int i = 1 ; i < 7;++i)
            for(int j = 0; j< 7-i;++j)
            {
                if(hulus.creature_re(index_i+j,index_j).return_index() > hulus.creature_re(index_i + j+1,index_j).return_index())
                    hulus.exchange(index_i+j, index_j, index_i + j + 1, index_j);
            }
    }

    public Square return_square()
    {
        return hulus;
    }
}
